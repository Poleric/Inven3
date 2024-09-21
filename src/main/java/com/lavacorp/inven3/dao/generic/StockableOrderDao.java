package com.lavacorp.inven3.dao.generic;

import com.lavacorp.inven3.dao.StockDao;
import com.lavacorp.inven3.model.PurchaseOrder;
import com.lavacorp.inven3.model.SalesOrder;
import com.lavacorp.inven3.model.Stock;
import com.lavacorp.inven3.model.generic.StockableOrder;
import org.jdbi.v3.sqlobject.CreateSqlObject;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.jetbrains.annotations.Nullable;

public interface StockableOrderDao<T extends StockableOrder> extends OrderDao<T> {
    @CreateSqlObject
    StockDao getStockDao();

    @SqlUpdate
    @GetGeneratedKeys("id")
    int insertOrderDetails(@BindBean T order);

    @SqlUpdate
    void insertOrderLine(int orderId, int stockId, int quantity);

    @Transaction
    default T insert(T order) {
        int orderId = insertOrderDetails(order);
        order.setId(orderId);

        order.getStocks().forEach(
                (key, value) -> {
                    Stock stock = getStockDao().insert(key);
                    assert stock.getId() != null;
                    insertOrderLine(orderId, stock.getId(), value);
                }
        );

        return order;
    }

    @SqlQuery("select")
    @Nullable T selectByStockId(@Bind @Define int stockId);
}
