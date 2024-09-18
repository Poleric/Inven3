package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.PurchaseOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/purchase-order")
public class PurchaseOrderController {
    PurchaseOrderDao purchaseOrderDao;

    @Autowired
    public PurchaseOrderController(PurchaseOrderDao purchaseOrderDao) {
        this.purchaseOrderDao = purchaseOrderDao;
    }


}
