package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.*;
import com.lavacorp.inven3.model.Item;
import jakarta.servlet.http.HttpServletResponse;
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
    SupplierDao supplierDao;

    @Autowired
    public ItemController(ItemDao itemDao, StockDao stockDao, CategoryDao categoryDao, SupplierDao supplierDao) {
        this.itemDao = itemDao;
        this.stockDao = stockDao;
        this.categoryDao = categoryDao;
        this.supplierDao = supplierDao;
    }

    @PostMapping("/search")
    public String search(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "25") int pageSize,
            @RequestParam(name = "ordering", defaultValue = "Item.id") String ordering,
            @RequestParam(name = "ordering-direction", defaultValue = "ASC") OrderDirection orderingDirection,
            @RequestParam(name = "low", defaultValue = "false") boolean low,
            Model model) {
        int totalResults;
        List<Item> items;

        if (!low) {
            totalResults = itemDao.selectAllByNameLike(query, true);
            items = itemDao.selectAllByNameLike(query, ordering, orderingDirection, page, pageSize);
        } else {
            totalResults = itemDao.selectAllByStockLevel(StockLevel.LOW, true);
            items = itemDao.selectAllByStockLevel(StockLevel.LOW, page, pageSize);
        }

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
    public String create(@RequestBody NewItemContext context, Model model, HttpServletResponse response) {
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
            model.addAttribute("status","bad");
            model.addAttribute("message", "Failed to create Item.");
            response.setStatus(HttpStatus.OK.value());
            return "fragments/status";
        }

        model.addAttribute("status", "ok");
        model.addAttribute("message", "Successfully created new Item.");
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("HX-Trigger", "update");
        return "fragments/status";
    }

    public record NewItemContext(String name, String description, Double basePrice, String unit, int categoryId, int minStock) {}

    @DeleteMapping("/delete")
    public String delete(@RequestParam(name = "selected") int[] ids, Model model, HttpServletResponse response) {
        for (int id : ids)
            try {
                itemDao.deleteById(id);
            } catch (UnableToExecuteStatementException e) {
                model.addAttribute("status","bad");
                model.addAttribute("message", "The Item(s) is referenced by other Stocks.");
                response.setStatus(HttpStatus.OK.value());
                return "fragments/status";
            }

        model.addAttribute("status", "ok");
        model.addAttribute("message", "Successfully deleted Item(s).");
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("HX-Trigger", "update");
        return "fragments/status";
    }

    @GetMapping("/options")
    public String getOptions(Model model) {
        model.addAttribute("items", itemDao.selectAll());
        return "item/options";
    }

    @PostMapping("/supplier-options")
    public String getSupplierOptions(@RequestParam("itemId") int itemId, Model model) {
        model.addAttribute("suppliers", supplierDao.selectAllByItemId(itemId));
        return "supplier/options";
    }

    @PostMapping("/supplier-items")
    public String getSupplierItems(@RequestParam("supplierId") int supplierId, Model model) {
        model.addAttribute("items", itemDao.selectAllBySupplierId(supplierId));
        return "item/options";
    }
}
