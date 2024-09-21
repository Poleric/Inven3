package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.OrderDirection;
import com.lavacorp.inven3.dao.PurchaseOrderDao;
import com.lavacorp.inven3.model.PurchaseOrder;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/purchase")
public class PurchaseOrderController {
    PurchaseOrderDao purchaseOrderDao;

    @Autowired
    public PurchaseOrderController(PurchaseOrderDao purchaseOrderDao) {
        this.purchaseOrderDao = purchaseOrderDao;
    }

    @PostMapping("/search")
    public String show(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page-size", defaultValue = "25") int pageSize,
            @RequestParam(name = "ordering", defaultValue = "Stock.id") String ordering,
            @RequestParam(name = "ordering-direction", defaultValue = "ASC") OrderDirection orderingDirection,
            Model model) {
        int totalResults = purchaseOrderDao.selectAll(true);

        List<PurchaseOrder> pos = purchaseOrderDao.selectAll(ordering, orderingDirection, page, pageSize);

        model.addAttribute("contexts", pos);
        model.addAttribute("pageContext", new PageContext(pageSize, totalResults, page));

        return "purchase/search";
    }


    @DeleteMapping("/delete")
    @ResponseBody
    public HttpStatus delete(@RequestParam(name = "selected") int[] ids) {
        for (int id : ids)
            try {
                purchaseOrderDao.deleteById(id);
            } catch (UnableToExecuteStatementException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }

        return HttpStatus.OK;
    }
}
