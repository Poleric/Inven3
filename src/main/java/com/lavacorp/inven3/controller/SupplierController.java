package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.SupplierDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    @GetMapping("/search")
    public String searchSupplier(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize,
            Model model) {


        return "supplier/suppliers_table";
    }
}
