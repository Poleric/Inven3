package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.SalesOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/sales-order")
public class SalesOrderController {
    SalesOrderDao salesOrderDao;

    @Autowired
    public SalesOrderController(SalesOrderDao salesOrderDao) {
        this.salesOrderDao = salesOrderDao;
    }

}
