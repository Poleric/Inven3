package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.model.PurchaseOrder;
import com.lavacorp.inven3.model.Stock;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;
import org.jdbi.v3.spring5.JdbiRepository;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.stream.Stream;

@Repository
@JdbiRepository
@RegisterBeanMapper(PurchaseOrder.class)
@RegisterBeanMapper(value = Stock.class, prefix = "stock")
public interface PurchaseOrderDao {
    @SqlUpdate
    @GetGeneratedKeys("id")
    int insertOrderDetails(@BindBean PurchaseOrder purchaseOrder);

    @SqlUpdate
    void insertOrderStocks(int purchaseOrderId, Stock stock, int orderQuantity);

    @Transaction
    default void insert(PurchaseOrder purchaseOrder) {
        int orderId = insertOrderDetails(purchaseOrder);
        purchaseOrder.setId(orderId);

        purchaseOrder.getStocks().forEach(
                (key, value) -> insertOrderStocks(orderId, key, value)
        );
    }

    @SqlQuery
    @UseRowReducer(PurchaseOrderRowReducer.class)
    @Nullable PurchaseOrder selectById(int id);

    @SqlQuery("select")
    @UseRowReducer(PurchaseOrderRowReducer.class)
    Stream<PurchaseOrder> selectAll();

    class PurchaseOrderRowReducer implements LinkedHashMapRowReducer<Integer, PurchaseOrder> {
        @Override
        public void accumulate(Map<Integer, PurchaseOrder> map, RowView rowView) {
            PurchaseOrder po = map.computeIfAbsent(
                    rowView.getColumn("purchase_order_id", Integer.class),
                    row -> rowView.getRow(PurchaseOrder.class)
            );

            if (rowView.getColumn("stock_id", Integer.class) != null)
                po.getStocks().put(
                        rowView.getRow(Stock.class),
                        rowView.getColumn("order_quantity", Integer.class)
                );
        }
    }
}


