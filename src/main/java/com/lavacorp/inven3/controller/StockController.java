package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.OrderDirection;
import com.lavacorp.inven3.dao.PurchaseOrderDao;
import com.lavacorp.inven3.dao.StockDao;
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

    @Autowired
    public StockController(StockDao stockDao, PurchaseOrderDao purchaseOrderDao) {
        this.stockDao = stockDao;
        this.purchaseOrderDao = purchaseOrderDao;
    }

    @PostMapping("/search")
    public String show(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page-size", defaultValue = "20") int pageSize,
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
    public HttpStatus create(@RequestBody Stock stock) {
        try {
            stockDao.insert(stock);
        } catch (UnableToExecuteStatementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return HttpStatus.OK;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public HttpStatus delete(@RequestParam(name = "id") int id) {
        try {
            stockDao.deleteById(id);
        } catch (UnableToExecuteStatementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return HttpStatus.OK;
    }
}
