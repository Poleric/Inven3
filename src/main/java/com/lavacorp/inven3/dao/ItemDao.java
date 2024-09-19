package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.NamedDao;
import com.lavacorp.inven3.model.Item;
import com.lavacorp.inven3.model.Stock;
import org.jdbi.v3.spring5.JdbiRepository;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JdbiRepository
@RegisterBeanMapper(Item.class)
public interface ItemDao extends NamedDao<Item> {
    @SqlQuery("select")
    List<Item> selectAllBySupplierId(@Bind @Define int supplierId);

    @SqlQuery("select")
    int selectAllBySupplierId(@Bind @Define int supplierId, @Define boolean count);

    @SqlQuery("select")
    List<Item> selectAllBySupplierId(@Bind @Define int supplierId, @Define String orderColumn, @Define OrderDirection orderDirection);

    @SqlQuery("select")
    List<Item> selectAllBySupplierId(@Bind @Define int supplierId, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<Item> selectAllBySupplierId(@Bind @Define int supplierId, @Define String orderColumn, @Define OrderDirection orderDirection, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<Item> selectAllByCategoryId(@Bind @Define int categoryId);

    @SqlQuery("select")
    int selectAllByCategoryId(@Bind @Define int categoryId, @Define boolean count);

    @SqlQuery("select")
    List<Item> selectAllByCategoryId(@Bind @Define int categoryId, @Define String orderColumn, @Define OrderDirection orderDirection);

    @SqlQuery("select")
    List<Item> selectAllByCategoryId(@Bind @Define int categoryId, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<Item> selectAllByCategoryId(@Bind @Define int categoryId, @Define String orderColumn, @Define OrderDirection orderDirection, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<Item> selectAllByStockLevel(@Bind @Define StockLevel stockLevel);

    @SqlQuery("select")
    int selectAllByStockLevel(@Bind @Define StockLevel stockLevel, @Define boolean count);

    @SqlQuery("select")
    List<Item> selectAllByStockLevel(@Bind @Define StockLevel stockLevel, @Define String orderColumn, @Define OrderDirection orderDirection);

    @SqlQuery("select")
    List<Item> selectAllByStockLevel(@Bind @Define StockLevel stockLevel, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<Item> selectAllByStockLevel(@Bind @Define StockLevel stockLevel, @Define String orderColumn, @Define OrderDirection orderDirection, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<Item> selectAllByStockLevel(@Bind @Define StockLevel stockLevel, int stockValue);

    @SqlQuery("select")
    int selectAllByStockLevel(@Bind @Define StockLevel stockLevel, int stockValue, @Define boolean count);

    @SqlQuery("select")
    List<Item> selectAllByStockLevel(@Bind @Define StockLevel stockLevel, int stockValue, @Define String orderColumn, @Define OrderDirection orderDirection);

    @SqlQuery("select")
    List<Item> selectAllByStockLevel(@Bind @Define StockLevel stockLevel, int stockValue, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<Item> selectAllByStockLevel(@Bind @Define StockLevel stockLevel, int stockValue, @Define String orderColumn, @Define OrderDirection orderDirection, @Define int page, @Define int pageSize);
}