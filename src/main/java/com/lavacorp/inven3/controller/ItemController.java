package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.ItemDao;
import com.lavacorp.inven3.dao.OrderDirection;
import com.lavacorp.inven3.dao.StockDao;
import com.lavacorp.inven3.model.Item;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/item")
public class ItemController {
    ItemDao itemDao;
    StockDao stockDao;

    @Autowired
    public ItemController(ItemDao itemDao, StockDao stockDao) {
        this.itemDao = itemDao;
        this.stockDao = stockDao;
    }

    @PostMapping("/search")
    public String search(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "20") int pageSize,
            @RequestParam(name = "ordering", defaultValue = "Item.id") String ordering,
            @RequestParam(name = "ordering-direction", defaultValue = "ASC") OrderDirection orderingDirection,
            Model model) {
        int totalResults = itemDao.selectAllByNameLike(query, true);

        List<Item> items = itemDao.selectAllByNameLike(query, ordering, orderingDirection, page, pageSize);
        AtomicInteger totalStock = new AtomicInteger();
        Map<Item, Integer> results = new HashMap<>();
        items.forEach(item -> {
            assert item.getId() != null;
            int stockCount = stockDao.countItemStock(item.getId());
            results.put(item, stockCount);
            totalStock.addAndGet(stockCount);
        });

        model.addAttribute("results", results);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalResults / pageSize + 1);
        model.addAttribute("currentRow", (page - 1) * pageSize + 1);
        model.addAttribute("totalRows", totalResults);
        model.addAttribute("totalStock", totalStock);

        return "item/search";
    }

    @PostMapping("/create")
    @ResponseBody
    public HttpStatus create(@RequestBody Item item) {
        try {
            itemDao.insert(item);
        } catch (UnableToExecuteStatementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return HttpStatus.OK;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public HttpStatus delete(@RequestParam(name = "id") int id) {
        try {
            itemDao.deleteById(id);
        } catch (UnableToExecuteStatementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return HttpStatus.OK;
    }
}
