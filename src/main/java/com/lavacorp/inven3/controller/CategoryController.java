package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.CategoryDao;
import com.lavacorp.inven3.dao.OrderDirection;
import com.lavacorp.inven3.model.Category;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Configuration
@RequestMapping("/category")
public class CategoryController {
    CategoryDao categoryDao;

    @Autowired
    public CategoryController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @PostMapping("/search")
    public String show(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page-size", defaultValue = "20") int pageSize,
            @RequestParam(name = "ordering", defaultValue = "name") String ordering,
            @RequestParam(name = "ordering-direction", defaultValue = "ASC") OrderDirection orderingDirection,
            Model model) {
        int totalResults = categoryDao.selectAllByNameLike(query, true);

        List<Category> results = categoryDao.selectAllByNameLike(query, ordering, orderingDirection, page, pageSize);

        model.addAttribute("results", results);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalResults);
        model.addAttribute("currentRow", (page - 1) * pageSize + 1);
        model.addAttribute("totalRows", totalResults);

        return "category/search";
    }


    @PostMapping("/create")
    @ResponseBody
    public HttpStatus create(@RequestBody Category category) {
        try {
            categoryDao.insert(category);
        } catch (UnableToExecuteStatementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return HttpStatus.OK;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public HttpStatus delete(@RequestParam(name = "id") int id) {
        try {
            categoryDao.deleteById(id);
        } catch (UnableToExecuteStatementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return HttpStatus.OK;
    }
}
