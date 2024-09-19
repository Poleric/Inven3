package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.StockableOrderDaoTests;
import com.lavacorp.inven3.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;

public class PurchaseOrderDaoTests extends StockableOrderDaoTests<PurchaseOrder, PurchaseOrderDao> {

    @Autowired
    public PurchaseOrderDaoTests(PurchaseOrderDao dao) {
        super(dao);
    }
}
