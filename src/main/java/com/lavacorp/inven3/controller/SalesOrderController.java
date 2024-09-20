package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.OrderDirection;
import com.lavacorp.inven3.dao.SalesOrderDao;
import com.lavacorp.inven3.model.SalesOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/sales")
public class SalesOrderController {
    SalesOrderDao salesOrderDao;

    @Autowired
    public SalesOrderController(SalesOrderDao salesOrderDao) {
        this.salesOrderDao = salesOrderDao;
    }

    @PostMapping("/search")
    public String show(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page-size", defaultValue = "20") int pageSize,
            @RequestParam(name = "ordering", defaultValue = "Stock.id") String ordering,
            @RequestParam(name = "ordering-direction", defaultValue = "ASC") OrderDirection orderingDirection,
            Model model) {
        int totalResults = salesOrderDao.selectAll(true);

        List<SalesOrder> sos = salesOrderDao.selectAll(ordering, orderingDirection, page, pageSize);

        model.addAttribute("contexts", sos);
        model.addAttribute("pageContext", new PageContext(pageSize, totalResults, page));

        return "sales/search";
    }
}
