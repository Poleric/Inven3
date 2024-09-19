package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.StockableOrderDaoTests;
import com.lavacorp.inven3.model.SalesOrder;
import org.springframework.beans.factory.annotation.Autowired;

public class SalesOrderDaoTests extends StockableOrderDaoTests<SalesOrder, SalesOrderDao> {

    @Autowired
    public SalesOrderDaoTests(SalesOrderDao dao) {
        super(dao);
    }
}
