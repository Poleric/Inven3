package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.SupplierDao;
import com.lavacorp.inven3.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    SupplierDao supplierDao;

    @Autowired
    public SupplierController(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    @GetMapping("/")
    public String showSupplierList(Model model) {
        return "supplier/suppliers";
    }

    @PostMapping("/search")
    public String searchSupplier(
            @RequestParam(name="query", defaultValue = "") String query,
            @RequestParam(name="page", defaultValue = "1") int page,
            @RequestParam(name="pageSize", defaultValue = "20") int pageSize,
            Model model) {
        int totalResults = supplierDao.selectAllByNameLike(query, true);

        List<Supplier> results = supplierDao.selectAllByNameLike(query, "name", "DESC", page, pageSize);

        model.addAttribute("suppliers", results);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalResults);
        model.addAttribute("currentRow", (page - 1) * pageSize + 1);
        model.addAttribute("totalRows", totalResults);

        return "supplier/search";
    }
}
