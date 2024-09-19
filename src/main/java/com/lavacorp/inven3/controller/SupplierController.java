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

    @GetMapping
    public String home() {
        return "supplier/suppliers";
    }

    @GetMapping("/search")
    public String show(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "20") int pageSize,
            @RequestParam(name = "order", defaultValue = "name") String order,
            @RequestParam(name = "orderDirection", defaultValue = "ASC") OrderDirection orderDirection,
            Model model) {
        int totalResults = supplierDao.selectAllByNameLike(query, true);

        List<Supplier> suppliers = supplierDao.selectAllByNameLike(query, order, orderDirection, page, pageSize);
        Map<Supplier, Integer> supplierItems = new HashMap<>();
        suppliers.forEach(supplier -> {
            assert supplier.getId() != null;
            supplierItems.put(supplier, itemDao.selectAllBySupplierId(supplier.getId(), true));
        });

        model.addAttribute("results", supplierItems);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalResults);
        model.addAttribute("currentRow", (page - 1) * pageSize + 1);
        model.addAttribute("totalRows", totalResults);

        return "supplier/search";
    }

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
