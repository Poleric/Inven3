package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.ItemDao;
import com.lavacorp.inven3.dao.OrderDirection;
import com.lavacorp.inven3.dao.SupplierDao;
import com.lavacorp.inven3.model.Supplier;
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

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    SupplierDao supplierDao;
    ItemDao itemDao;

    @Autowired
    public SupplierController(SupplierDao supplierDao, ItemDao itemDao) {
        this.supplierDao = supplierDao;
        this.itemDao = itemDao;
    }

    @PostMapping("/search")
    public String show(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page-size", defaultValue = "25") int pageSize,
            @RequestParam(name = "ordering", defaultValue = "id") String ordering,
            @RequestParam(name = "ordering-direction", defaultValue = "ASC") OrderDirection orderingDirection,
            Model model) {
        int totalResults = supplierDao.selectAllByNameLike(query, true);

        List<Supplier> suppliers = supplierDao.selectAllByNameLike(query, ordering, orderingDirection, page, pageSize);

        List<SupplierContext> contexts = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            assert supplier.getId() != null;
            contexts.add(new SupplierContext(supplier, itemDao.selectAllBySupplierId(supplier.getId(), true)));
        }

        model.addAttribute("contexts", contexts);
        model.addAttribute("pageContext", new PageContext(pageSize, totalResults, page));

        return "supplier/search";
    }

    public record SupplierContext(Supplier supplier, int itemCount) {}

    @PostMapping("/create")
    @ResponseBody
    public HttpStatus create(@RequestBody Supplier supplier) {
        try {
            supplierDao.insert(supplier);
        } catch (UnableToExecuteStatementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return HttpStatus.OK;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public HttpStatus delete(@RequestParam(name = "id") int id) {
        try {
            supplierDao.deleteById(id);
        } catch (UnableToExecuteStatementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return HttpStatus.OK;
    }
}
