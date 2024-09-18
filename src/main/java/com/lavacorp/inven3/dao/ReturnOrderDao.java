package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.model.ReturnOrder;
import com.lavacorp.inven3.model.Stock;
import org.jdbi.v3.spring5.JdbiRepository;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.springframework.stereotype.Repository;

@Repository
@JdbiRepository
@RegisterBeanMapper(ReturnOrder.class)
@RegisterBeanMapper(value = Stock.class, prefix = "stock")
public interface ReturnOrderDao {
    // TODO: logic
}
