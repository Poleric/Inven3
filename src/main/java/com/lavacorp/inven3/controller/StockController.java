package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.*;
import com.lavacorp.inven3.model.PurchaseOrder;
import com.lavacorp.inven3.model.Stock;
import jakarta.servlet.http.HttpServletResponse;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        model.addAttribute("ordering", ordering);
        model.addAttribute("contexts", contexts);
        model.addAttribute("pageContext", new PageContext(pageSize, totalResults, page));
        model.addAttribute("totalStocks", totalStocks);

        return "stock/search";
    }

    public record StockContext(Stock stock, @Nullable PurchaseOrder order) {}

    @PostMapping("/create")
    public String create(@RequestBody NewStockContext context, Model model, HttpServletResponse response) {
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
            model.addAttribute("status","bad");
            model.addAttribute("message", "Failed to create stock.");
            response.setStatus(HttpStatus.OK.value());
            return "fragments/status";
        }

        model.addAttribute("status", "ok");
        model.addAttribute("message", "Successfully created new Stock.");
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("HX-Trigger", "update");
        return "fragments/status";
    }

    public record NewStockContext(int itemId, int supplierId, int locationId, int quantity, Stock.StockStatus status, String notes) {}

    @DeleteMapping("/delete")
    public String delete(@RequestParam(name = "selected") int[] ids, Model model, HttpServletResponse response) {
        for (int id : ids)
            try {
                stockDao.deleteById(id);
            } catch (UnableToExecuteStatementException e) {
                model.addAttribute("status","bad");
                model.addAttribute("message", "The Stock(s) is still referenced by other entities");
                response.setStatus(HttpStatus.OK.value());
                return "fragments/status";
            }

        model.addAttribute("status", "ok");
        model.addAttribute("message", "Successfully deleted.");
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("HX-Trigger", "update");
        return "fragments/status";
    }

    @PostMapping("/options")
    public String getOptions(Model model) {
        model.addAttribute("stocks", stockDao.selectAll("Stock.quantity", OrderDirection.DESC));
        return "stock/options";
    }
}
