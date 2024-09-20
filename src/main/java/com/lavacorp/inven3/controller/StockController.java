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

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
        AtomicInteger totalStocks = new AtomicInteger();
        AtomicInteger totalStockPurchases = new AtomicInteger();

        List<Stock> stocks = stockDao.selectAllByItemNameLike(query, ordering, orderingDirection, page, pageSize);
        List<StockContext> results = new ArrayList<>();
        stocks.forEach(stock -> {
            assert stock.getId() != null;
            PurchaseOrder po = purchaseOrderDao.selectByStockId(stock.getId());
            int purchaseCount = 0;
            if (po != null)  // this is horrible
                purchaseCount = po.getStocks().entrySet().stream().dropWhile(
                        entry -> stock.getId().equals(entry.getKey().getId())
                ).findFirst().orElse(new AbstractMap.SimpleEntry<>(null, 0)).getValue();
            results.add(new StockContext(stock, po, purchaseCount));
            if (stock.getStatus() == Stock.StockStatus.OK
                || stock.getStatus() == Stock.StockStatus.IN_TRANSIT)
                totalStocks.addAndGet(stock.getQuantity());
            totalStockPurchases.addAndGet(purchaseCount);
        });


        model.addAttribute("results", results);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalResults / pageSize + 1);
        model.addAttribute("currentRow", (page - 1) * pageSize + 1);
        model.addAttribute("totalRows", totalResults);
        model.addAttribute("totalStocks", totalStocks);
        model.addAttribute("totalStockPurchases", totalStockPurchases);

        return "stock/search";
    }

    public record StockContext(Stock stock, @Nullable PurchaseOrder order, int purchaseCount) {}

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
