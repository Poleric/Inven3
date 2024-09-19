package com.lavacorp.inven3.dao.generic;

import com.lavacorp.inven3.model.PurchaseOrder;
import com.lavacorp.inven3.model.SalesOrder;
import com.lavacorp.inven3.model.Stock;
import com.lavacorp.inven3.model.generic.StockableOrder;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.jetbrains.annotations.Nullable;

public interface StockableOrderDao<T extends StockableOrder> extends OrderDao<T> {
    @SqlUpdate
    @GetGeneratedKeys("id")
    int insertOrderDetails(@BindBean T order);

    @SqlUpdate
    void insertOrderStocks(int id, @BindBean Stock stock, int orderQuantity);

    @Transaction
    default T insert(T order) {
        int orderId = insertOrderDetails(order);
        order.setId(orderId);

        if (order instanceof PurchaseOrder purchaseOrder)
            purchaseOrder.getStocks().forEach(
                    (key, value) -> insertOrderStocks(orderId, key, value)
            );
        else if (order instanceof SalesOrder salesOrder)
            salesOrder.getStocks().forEach(
                    (key, value) -> insertOrderStocks(orderId, key, value)
            );

        return order;
    }

    @SqlQuery("select")
    @Nullable T selectByStockId(@Bind @Define int stockId);
}
