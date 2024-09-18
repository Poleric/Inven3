package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.DaoTests;
import com.lavacorp.inven3.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;

public class StockDaoTests extends DaoTests<Stock, StockDao> {

    @Autowired
    public StockDaoTests(StockDao dao) {
        super(dao);
    }

}
