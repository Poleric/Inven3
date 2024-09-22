package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.ItemDao;
import com.lavacorp.inven3.dao.OrderDirection;
import com.lavacorp.inven3.dao.SupplierDao;
import com.lavacorp.inven3.model.Supplier;
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
        model.addAttribute("ordering", ordering);
        boolean sort_item = false;
        if (ordering.equals("number_of_items")) {
            ordering = "id";
            sort_item = true;
        }

        int totalResults = supplierDao.selectAllByNameLike(query, true);

        List<Supplier> suppliers = supplierDao.selectAllByNameLike(query, ordering, orderingDirection, page, pageSize);

        List<SupplierContext> contexts = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            assert supplier.getId() != null;
            contexts.add(new SupplierContext(supplier, itemDao.selectAllBySupplierId(supplier.getId(), true)));
        }

        if (sort_item)
            contexts.sort((ctx1, ctx2) -> ctx2.itemCount - ctx1.itemCount);

        model.addAttribute("contexts", contexts);
        model.addAttribute("pageContext", new PageContext(pageSize, totalResults, page));

        return "supplier/search";
    }

    public record SupplierContext(Supplier supplier, int itemCount) {}

    @PostMapping("/create")
    public String create(@RequestBody Supplier supplier, Model model, HttpServletResponse response) {
        try {
            supplierDao.insert(supplier);
        } catch (UnableToExecuteStatementException e) {
            model.addAttribute("status","bad");
            model.addAttribute("message", "Failed to create supplier.");
            response.setStatus(HttpStatus.OK.value());
            return "fragments/status";
        }

        model.addAttribute("status", "ok");
        model.addAttribute("message", "Successfully created new supplier.");
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("HX-Trigger", "update");
        return "fragments/status";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam(name = "selected") int[] ids, Model model, HttpServletResponse response) {
        for (int id : ids)
            try {
                supplierDao.deleteById(id);
            } catch (UnableToExecuteStatementException e) {
                model.addAttribute("status","bad");
                model.addAttribute("message", "The Item(s) is still referenced by other items");
                response.setStatus(HttpStatus.OK.value());
                return "fragments/status";
            }

        model.addAttribute("status", "ok");
        model.addAttribute("message", "Successfully deleted.");
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("HX-Trigger", "update");
        return "fragments/status";
    }

    @GetMapping("/options")
    public String options(Model model) {
        model.addAttribute("suppliers", supplierDao.selectAll());
        return "supplier/options";
    }
}
