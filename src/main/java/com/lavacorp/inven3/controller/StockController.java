package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.*;
import com.lavacorp.inven3.model.PurchaseOrder;
import com.lavacorp.inven3.model.Stock;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/stock")
public class StockController {
    StockDao stockDao;
    PurchaseOrderDao purchaseOrderDao;
    SupplierDao supplierDao;
    LocationDao locationDao;
    ItemDao itemDao;

    @Autowired
    public StockController(StockDao stockDao, PurchaseOrderDao purchaseOrderDao, SupplierDao supplierDao, LocationDao locationDao, ItemDao itemDao) {
        this.stockDao = stockDao;
        this.purchaseOrderDao = purchaseOrderDao;
        this.supplierDao = supplierDao;
        this.locationDao = locationDao;
        this.itemDao = itemDao;
    }

    @PostMapping("/search")
    public String show(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page-size", defaultValue = "25") int pageSize,
            @RequestParam(name = "ordering", defaultValue = "Stock.id") String ordering,
            @RequestParam(name = "ordering-direction", defaultValue = "ASC") OrderDirection orderingDirection,
            Model model) {
        int totalResults = stockDao.selectAll(true);

        List<Stock> stocks = stockDao.selectAllByItemNameLike(query, ordering, orderingDirection, page, pageSize);

        int totalStocks = 0;
        List<StockContext> contexts = new ArrayList<>();
        for (Stock stock : stocks) {
            assert stock.getId() != null;
            PurchaseOrder po = purchaseOrderDao.selectByStockId(stock.getId());
            contexts.add(new StockContext(stock, po));

            if (stock.getStatus() == Stock.StockStatus.OK
                    || stock.getStatus() == Stock.StockStatus.IN_TRANSIT)
                totalStocks += stock.getQuantity();
        }

        model.addAttribute("contexts", contexts);
        model.addAttribute("pageContext", new PageContext(pageSize, totalResults, page));
        model.addAttribute("totalStocks", totalStocks);

        return "stock/search";
    }

    public record StockContext(Stock stock, @Nullable PurchaseOrder order) {}

    @PostMapping("/create")
    @ResponseBody
    public HttpStatus create(@RequestBody NewStockContext context) {
        Stock stock = new Stock();
        stock.setItem(Objects.requireNonNull(itemDao.selectById(context.itemId)));
        stock.setSupplier(supplierDao.selectById(context.supplierId));
        if (context.locationId != 0)
            stock.setLocation(locationDao.selectById(context.locationId));
        stock.setQuantity(context.quantity);
        stock.setStatus(context.status);
        stock.setNotes(context.notes);

        try {
            stockDao.insert(stock);
        } catch (UnableToExecuteStatementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return HttpStatus.OK;
    }

    public record NewStockContext(int itemId, int supplierId, int locationId, int quantity, Stock.StockStatus status, String notes) {}

    @DeleteMapping("/delete")
    @ResponseBody
    public HttpStatus delete(@RequestParam(name = "selected") int[] ids) {
        for (int id : ids)
            try {
                stockDao.deleteById(id);
            } catch (UnableToExecuteStatementException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }

        return HttpStatus.OK;
    }
}
