package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.Dao;
import com.lavacorp.inven3.model.Stock;
import org.jdbi.v3.spring5.JdbiRepository;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JdbiRepository
@RegisterBeanMapper(Stock.class)
public interface StockDao extends Dao<Stock> {
    @SqlQuery("select")
    List<Stock> selectAllByStatus(@Bind @Define Stock.StockStatus status);

    @SqlQuery("select")
    int selectAllByStatus(@Bind @Define Stock.StockStatus status, @Define boolean count);

    @SqlQuery("select")
    List<Stock> selectAllByStatus(@Bind @Define Stock.StockStatus status, @Define String orderColumn, @Define OrderDirection orderDirection);

    @SqlQuery("select")
    List<Stock> selectAllByStatus(@Bind @Define Stock.StockStatus status, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<Stock> selectAllByStatus(@Bind @Define Stock.StockStatus status, @Define String orderColumn, @Define OrderDirection orderDirection, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<Stock> selectAllByItemId(@Bind @Define int itemId);

    @SqlQuery("select")
    int selectAllByItemId(@Bind @Define int itemId, @Define boolean count);

    @SqlQuery("select")
    List<Stock> selectAllByItemId(@Bind @Define int itemId, @Define String orderColumn, @Define OrderDirection orderDirection);

    @SqlQuery("select")
    List<Stock> selectAllByItemId(@Bind @Define int itemId, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<Stock> selectAllByItemId(@Bind @Define int itemId, @Define String orderColumn, @Define OrderDirection orderDirection, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<Stock> selectAllBySupplierId(@Bind @Define int supplierId);

    @SqlQuery("select")
    int selectAllBySupplierId(@Bind @Define int supplierId, @Define boolean count);

    @SqlQuery("select")
    List<Stock> selectAllBySupplierId(@Bind @Define int supplierId, @Define String orderColumn, @Define OrderDirection orderDirection);

     @SqlQuery("select")
    List<Stock> selectAllBySupplierId(@Bind @Define int supplierId, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<Stock> selectAllBySupplierId(@Bind @Define int supplierId, @Define String orderColumn, @Define OrderDirection orderDirection, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<Stock> selectAllByStockLevel(@Bind @Define StockLevel stockLevel);

    @SqlQuery("select")
    int selectAllByStockLevel(@Bind @Define StockLevel stockLevel, @Define boolean count);

    @SqlQuery("select")
    List<Stock> selectAllByStockLevel(@Bind @Define StockLevel stockLevel, @Define String orderColumn, @Define OrderDirection orderDirection);

     @SqlQuery("select")
    List<Stock> selectAllByStockLevel(@Bind @Define StockLevel stockLevel, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<Stock> selectAllByStockLevel(@Bind @Define StockLevel stockLevel, @Define String orderColumn, @Define OrderDirection orderDirection, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<Stock> selectAllByStockLevel(@Bind @Define StockLevel stockLevel, int stockValue);

    @SqlQuery("select")
    int selectAllByStockLevel(@Bind @Define StockLevel stockLevel, int stockValue, @Define boolean count);

    @SqlQuery("select")
    List<Stock> selectAllByStockLevel(@Bind @Define StockLevel stockLevel, int stockValue, @Define String orderColumn, @Define OrderDirection orderDirection);

     @SqlQuery("select")
    List<Stock> selectAllByStockLevel(@Bind @Define StockLevel stockLevel, int stockValue, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<Stock> selectAllByStockLevel(@Bind @Define StockLevel stockLevel, int stockValue, @Define String orderColumn, @Define OrderDirection orderDirection, @Define int page, @Define int pageSize);

    @SqlUpdate
    void increaseStock(int id, int quantity);

    @SqlUpdate
    void decreaseStock(int id, int quantity);

    @SqlQuery()
    int countItemStock(int itemId);
}
