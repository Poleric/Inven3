package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.Dao;
import com.lavacorp.models.Stock;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterBeanMapper(Stock.class)
public interface StockDao extends Dao<Stock> {
    @SqlQuery
    List<Stock> selectByItemId(int id);

    @SqlQuery
    List<Stock> selectBySupplierId(int id);

    @SqlUpdate
    void increaseStock(int id, int quantity);

    @SqlUpdate
    void decreaseStock(int id, int quantity);
}
