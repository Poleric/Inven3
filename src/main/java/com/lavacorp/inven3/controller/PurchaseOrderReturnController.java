package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.OrderDirection;
import com.lavacorp.inven3.dao.PurchaseOrderDao;
import com.lavacorp.inven3.dao.PurchaseOrderReturnDao;
import com.lavacorp.inven3.model.PurchaseOrderReturn;
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
@RequestMapping("/purchase/return")
public class PurchaseOrderReturnController {
    PurchaseOrderDao purchaseOrderDao;
    PurchaseOrderReturnDao purchaseOrderReturnDao;

    @Autowired
    public PurchaseOrderReturnController(PurchaseOrderReturnDao purchaseOrderReturnDao, PurchaseOrderDao purchaseOrderDao) {
        this.purchaseOrderReturnDao = purchaseOrderReturnDao;
        this.purchaseOrderDao = purchaseOrderDao;
    }

    @PostMapping("/search")
    public String show(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page-size", defaultValue = "25") int pageSize,
            @RequestParam(name = "ordering", defaultValue = "Stock.id") String ordering,
            @RequestParam(name = "ordering-direction", defaultValue = "ASC") OrderDirection orderingDirection,
            Model model) {
        int totalResults = purchaseOrderReturnDao.selectAll(true);

        List<PurchaseOrderReturn> pos = purchaseOrderReturnDao.selectAll(ordering, orderingDirection, page, pageSize);

        model.addAttribute("contexts", pos);
        model.addAttribute("pageContext", new PageContext(pageSize, totalResults, page));

        return "purchase/return/search";
    }

    @PostMapping("/create")
    public String create(@RequestBody NewPurchaseOrderReturnContext context, Model model, HttpServletResponse response) {
        PurchaseOrderReturn por = new PurchaseOrderReturn();
        por.setStatus(Order.OrderStatus.FULFILLED);
        por.setOrderReturned(purchaseOrderDao.selectById(context.purchaseOrderId));
        por.setReturnDate(LocalDateTime.now());
        por.setReference(context.reference);

        try {
            purchaseOrderReturnDao.insert(por);
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

    public record NewPurchaseOrderReturnContext(int purchaseOrderId, String reference, LocalDateTime returnDate) {}

    @DeleteMapping("/delete")
    public String delete(@RequestParam(name = "selected") int[] ids, Model model, HttpServletResponse response) {
        for (int id : ids)
            try {
                purchaseOrderReturnDao.deleteById(id);
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
