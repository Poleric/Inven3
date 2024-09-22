package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.*;
import com.lavacorp.inven3.model.PurchaseOrderReturn;
import com.lavacorp.inven3.model.SalesOrderReturn;
import com.lavacorp.inven3.model.generic.Order;
import jakarta.servlet.http.HttpServletResponse;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/sales/return")
public class SalesOrderReturnController {
    SalesOrderDao salesOrderDao;
    SalesOrderReturnDao salesOrderReturnDao;

    @Autowired
    public SalesOrderReturnController(SalesOrderReturnDao purchaseOrderReturnDao, SalesOrderDao purchaseOrderDao) {
        this.salesOrderReturnDao = purchaseOrderReturnDao;
        this.salesOrderDao = purchaseOrderDao;
    }

    @PostMapping("/search")
    public String show(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page-size", defaultValue = "25") int pageSize,
            @RequestParam(name = "ordering", defaultValue = "Stock.id") String ordering,
            @RequestParam(name = "ordering-direction", defaultValue = "ASC") OrderDirection orderingDirection,
            Model model) {
        int totalResults = salesOrderReturnDao.selectAll(true);

        List<SalesOrderReturn> pos = salesOrderReturnDao.selectAll(ordering, orderingDirection, page, pageSize);

        model.addAttribute("contexts", pos);
        model.addAttribute("pageContext", new PageContext(pageSize, totalResults, page));

        return "sales/return/search";
    }

    @PostMapping("/create")
    public String create(@RequestBody NewSalesOrderReturnContext context, Model model, HttpServletResponse response) {
        SalesOrderReturn sor = new SalesOrderReturn();
        sor.setStatus(Order.OrderStatus.FULFILLED);
        sor.setOrderReturned(salesOrderDao.selectById(context.salesOrderId));
        sor.setReturnDate(LocalDateTime.now());
        sor.setReference(context.reference);

        try {
            salesOrderReturnDao.insert(sor);
        } catch (UnableToExecuteStatementException e) {
            model.addAttribute("status","bad");
            model.addAttribute("message", "Failed to create Return Order.");
            response.setStatus(HttpStatus.OK.value());
            return "fragments/status";
        }

        model.addAttribute("status", "ok");
        model.addAttribute("message", "Successfully created new Return Order.");
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("HX-Trigger", "update");
        return "fragments/status";
    }

    public record NewSalesOrderReturnContext(int salesOrderId, String reference, LocalDateTime returnDate) {}

    @DeleteMapping("/delete")
    public String delete(@RequestParam(name = "selected") int[] ids, Model model, HttpServletResponse response) {
        for (int id : ids)
            try {
                salesOrderReturnDao.deleteById(id);
            } catch (UnableToExecuteStatementException e) {
                model.addAttribute("status","bad");
                model.addAttribute("message", "Failed to delete Return Order(s).");
                response.setStatus(HttpStatus.OK.value());
                return "fragments/status";
            }

        model.addAttribute("status", "ok");
        model.addAttribute("message", "Successfully deleted Return Order(s).");
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("HX-Trigger", "update");
        return "fragments/status";
    }

}
