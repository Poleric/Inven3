package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.ReturnOrderDaoTests;
import com.lavacorp.inven3.model.SalesOrderReturn;
import org.springframework.beans.factory.annotation.Autowired;

public class SalesOrderReturnDaoTests extends ReturnOrderDaoTests<SalesOrderReturn, SalesOrderReturnDao> {

    @Autowired
    public SalesOrderReturnDaoTests(SalesOrderReturnDao dao) {
        super(dao);
    }
}
