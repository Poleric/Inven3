package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.CategoryDao;
import com.lavacorp.inven3.dao.ItemDao;
import com.lavacorp.inven3.dao.OrderDirection;
import com.lavacorp.inven3.model.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/item/category")
public class CategoryController {
    CategoryDao categoryDao;
    ItemDao itemDao;

    @Autowired
    public CategoryController(CategoryDao categoryDao, ItemDao itemDao) {
        this.categoryDao = categoryDao;
        this.itemDao = itemDao;
    }

    @PostMapping("/search")
    public String show(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page-size", defaultValue = "25") int pageSize,
            @RequestParam(name = "ordering", defaultValue = "name") String ordering,
            @RequestParam(name = "ordering-direction", defaultValue = "ASC") OrderDirection orderingDirection,
            Model model) {
        int totalResults = categoryDao.selectAllByNameLike(query, true);

        List<Category> categories = categoryDao.selectAllByNameLike(query, ordering, orderingDirection, page, pageSize);

        List<CategoryContext> contexts = new ArrayList<>();
        for (Category category : categories) {
            assert category.getId() != null;
            contexts.add(new CategoryContext(category, itemDao.selectAllByCategoryId(category.getId(), true)));
        }

        model.addAttribute("contexts", contexts);
        model.addAttribute("pageContext", new PageContext(pageSize, totalResults, page));

        return "item/category/search";
    }

    public record CategoryContext(Category category, int itemCount) {}

    @PostMapping("/create")
    public String create(@RequestBody Category category, Model model, HttpServletResponse response) {
        try {
            categoryDao.insert(category);
        } catch (UnableToExecuteStatementException e) {
            model.addAttribute("status","bad");
            model.addAttribute("message", "Failed to create Item category.");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return "fragments/status";
        }

        model.addAttribute("status", "ok");
        model.addAttribute("message", "Successfully created new Item category.");
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("HX-Trigger", "update");
        return "fragments/status";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam(name = "selected") int[] ids, Model model, HttpServletResponse response) {
        for (int id : ids)
            try {
                categoryDao.deleteById(id);
            } catch (UnableToExecuteStatementException e) {
                model.addAttribute("status","bad");
                model.addAttribute("message", "The Item category(s) is referenced by other Items.");
                response.setStatus(HttpStatus.OK.value());
                return "fragments/status";
            }

        model.addAttribute("status", "ok");
        model.addAttribute("message", "Successfully deleted Item category(s).");
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("HX-Trigger", "update");
        return "fragments/status";
    }

    @GetMapping("/options")
    public String getOptions(Model model) {
        model.addAttribute("categories", categoryDao.selectAll());

        return "item/category/options";
    }
}
