package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.ReturnOrderDao;
import com.lavacorp.inven3.model.PurchaseOrderReturn;
import com.lavacorp.inven3.model.Stock;
import com.lavacorp.inven3.model.generic.Order;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;
import org.jdbi.v3.spring5.JdbiRepository;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@JdbiRepository
@RegisterBeanMapper(PurchaseOrderReturn.class)
@RegisterBeanMapper(value = Stock.class, prefix = "order_stock")
public interface PurchaseOrderReturnDao extends ReturnOrderDao<PurchaseOrderReturn> {
    @Transaction
    default PurchaseOrderReturn insert(PurchaseOrderReturn order) {
        int orderId = insertOrderDetails(order);
        order.setId(orderId);
        return order;
    }

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    @Nullable PurchaseOrderReturn selectById(int id);

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    List<PurchaseOrderReturn> selectAll();

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    List<PurchaseOrderReturn> selectAll(String orderColumn, OrderDirection orderDirection);

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    List<PurchaseOrderReturn> selectAll(int page, int pageSize);

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    List<PurchaseOrderReturn> selectAll(String orderColumn, OrderDirection orderDirection, int page, int pageSize);

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    List<PurchaseOrderReturn> selectAllByStatus(@Bind @Define Order.OrderStatus status);

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    List<PurchaseOrderReturn> selectAllByStatus(@Bind @Define Order.OrderStatus status, String orderColumn, OrderDirection orderDirection);

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    List<PurchaseOrderReturn> selectAllByStatus(@Bind @Define Order.OrderStatus status, int page, int pageSize);

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    List<PurchaseOrderReturn> selectAllByStatus(@Bind @Define Order.OrderStatus status, String orderColumn, OrderDirection orderDirection, int page, int pageSize);

    @Override
    @SqlQuery("select")
    @Nullable PurchaseOrderReturn selectByOrderReferenceId(@Bind @Define int referenceOrderId);
    
    class ReturnOrderRowReducer implements LinkedHashMapRowReducer<Integer, PurchaseOrderReturn> {
        @Override
        public void accumulate(Map<Integer, PurchaseOrderReturn> map, RowView rowView) {
            PurchaseOrderReturn ro = map.computeIfAbsent(
                    rowView.getColumn("id", Integer.class),
                    row -> rowView.getRow(PurchaseOrderReturn.class)
            );

            if (rowView.getColumn("order_stock_id", Integer.class) != null)
                ro.getOrderReturned().getStocks().put(
                        rowView.getRow(Stock.class),
                        rowView.getColumn("order_order_quantity", Integer.class)
                );
        }
    }
}
