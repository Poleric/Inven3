package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.OrderDirection;
import com.lavacorp.inven3.dao.StockDao;
import com.lavacorp.inven3.model.Stock;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/stock")
public class StockController {
    StockDao stockDao;

    @Autowired
    public StockController(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    @PostMapping("/search")
    public String show(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page-size", defaultValue = "20") int pageSize,
            @RequestParam(name = "ordering", defaultValue = "name") String ordering,
            @RequestParam(name = "ordering-direction", defaultValue = "ASC") OrderDirection orderingDirection,
            Model model) {
        int totalResults = stockDao.selectAll(true);

        List<Stock> results = stockDao.selectAll(ordering, orderingDirection, page, pageSize);

        model.addAttribute("results", results);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalResults / pageSize + 1);
        model.addAttribute("currentRow", (page - 1) * pageSize + 1);
        model.addAttribute("totalRows", totalResults);

        return "stock/search";
    }

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
