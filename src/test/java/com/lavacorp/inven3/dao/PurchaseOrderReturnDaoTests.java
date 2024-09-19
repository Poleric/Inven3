package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.ReturnOrderDaoTests;
import com.lavacorp.inven3.model.PurchaseOrderReturn;
import org.springframework.beans.factory.annotation.Autowired;

public class PurchaseOrderReturnDaoTests extends ReturnOrderDaoTests<PurchaseOrderReturn, PurchaseOrderReturnDao> {

    @Autowired
    public PurchaseOrderReturnDaoTests(PurchaseOrderReturnDao dao) {
        super(dao);
    }
}
