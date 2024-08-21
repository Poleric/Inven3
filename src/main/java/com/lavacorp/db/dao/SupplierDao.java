package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoNamed;
import com.lavacorp.entities.Supplier;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(Supplier.class)
public interface SupplierDao extends DaoNamed<Supplier> {
    @SqlQuery
    List<Supplier> retrieveByItemId(int itemId);
}
