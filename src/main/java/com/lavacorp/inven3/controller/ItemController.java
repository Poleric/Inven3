package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.CategoryDao;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/item")
public class ItemController {
    CategoryDao categoryDao;
    ItemDao itemDao;
    StockDao stockDao;

    @Autowired
    public ItemController(ItemDao itemDao, StockDao stockDao, CategoryDao categoryDao) {
        this.itemDao = itemDao;
        this.stockDao = stockDao;
        this.categoryDao = categoryDao;
    }

    @PostMapping("/search")
    public String search(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "25") int pageSize,
            @RequestParam(name = "ordering", defaultValue = "Item.id") String ordering,
            @RequestParam(name = "ordering-direction", defaultValue = "ASC") OrderDirection orderingDirection,
            Model model) {
        int totalResults = itemDao.selectAllByNameLike(query, true);

        List<Item> items = itemDao.selectAllByNameLike(query, ordering, orderingDirection, page, pageSize);

        int totalStocks = 0;
        List<ItemContext> contexts = new ArrayList<>();
        for (Item item : items) {
            assert item.getId() != null;
            int stockCount = stockDao.countItemStock(item.getId());
            contexts.add(new ItemContext(item, stockCount));
            totalStocks += stockCount;
        }

        model.addAttribute("contexts", contexts);
        model.addAttribute("pageContext", new PageContext(pageSize, totalResults, page));
        model.addAttribute("totalStocks", totalStocks);

        return "item/search";
    }

    public record ItemContext(Item item, int stockCount) {}

    @PostMapping("/create")
    @ResponseBody
    public HttpStatus create(@RequestBody NewItemContext context) {
        Item item = new Item();
        item.setName(context.name);
        item.setDescription(context.description);
        item.setBasePrice(context.basePrice);
        item.setUnit(context.unit);
        if (context.categoryId != 0)
            item.setCategory(categoryDao.selectById(context.categoryId));
        item.setMinStock(context.minStock);

        try {
            itemDao.insert(item);
        } catch (UnableToExecuteStatementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return HttpStatus.OK;
    }

    public record NewItemContext(String name, String description, Double basePrice, String unit, int categoryId, int minStock) {}

    @DeleteMapping("/delete")
    @ResponseBody
    public HttpStatus delete(@RequestParam(name = "selected") int[] ids) {
        for (int id : ids)
            try {
                itemDao.deleteById(id);
            } catch (UnableToExecuteStatementException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }

        return HttpStatus.OK;
    }
}
