package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.ReturnOrderDao;
import com.lavacorp.inven3.model.SalesOrderReturn;
import com.lavacorp.inven3.model.Stock;
import com.lavacorp.inven3.model.generic.Order;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;
import org.jdbi.v3.spring5.JdbiRepository;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
@JdbiRepository
@RegisterBeanMapper(SalesOrderReturn.class)
@RegisterBeanMapper(value = Stock.class, prefix = "order_stock")
public interface SalesOrderReturnDao extends ReturnOrderDao<SalesOrderReturn> {
    @Transaction
    default SalesOrderReturn insert(SalesOrderReturn order) {
        int orderId = insertOrderDetails(order);
        order.setId(orderId);

        order.getOrderReturned().getStocks().forEach(
                (key, value) -> {
                    assert key.getId() != null;
                    getStockDao().increaseStock(key.getId(), value);
                }
        );

        return order;
    }

    @SqlUpdate
    void delete(@Bind @Define int id);

    @Override
    default void deleteById(int id) {
        SalesOrderReturn sor = selectById(id);

        assert sor != null;
        sor.getOrderReturned().getStocks().forEach(
                (key, value) -> {
                    assert key.getId() != null;
                    getStockDao().decreaseStock(key.getId(), value);
                }
        );

        delete(id);
    }

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    @Nullable SalesOrderReturn selectById(int id);

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    List<SalesOrderReturn> selectAll();

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    List<SalesOrderReturn> selectAll(String orderColumn, OrderDirection orderDirection);

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    List<SalesOrderReturn> selectAll(int page, int pageSize);

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    List<SalesOrderReturn> selectAll(String orderColumn, OrderDirection orderDirection, int page, int pageSize);

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    List<SalesOrderReturn> selectAllByStatus(@Bind @Define Order.OrderStatus status);

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    List<SalesOrderReturn> selectAllByStatus(@Bind @Define Order.OrderStatus status, String orderColumn, OrderDirection orderDirection);

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    List<SalesOrderReturn> selectAllByStatus(@Bind @Define Order.OrderStatus status, int page, int pageSize);

    @Override
    @SqlQuery("select")
    @UseRowReducer(ReturnOrderRowReducer.class)
    List<SalesOrderReturn> selectAllByStatus(@Bind @Define Order.OrderStatus status, String orderColumn, OrderDirection orderDirection, int page, int pageSize);

    @Override
    @SqlQuery("select")
    @Nullable SalesOrderReturn selectByOrderReferenceId(@Bind @Define int referenceOrderId);
    
    class ReturnOrderRowReducer implements LinkedHashMapRowReducer<Integer, SalesOrderReturn> {
        @Override
        public void accumulate(Map<Integer, SalesOrderReturn> map, RowView rowView) {
            SalesOrderReturn ro = map.computeIfAbsent(
                    rowView.getColumn("id", Integer.class),
                    row -> rowView.getRow(SalesOrderReturn.class)
            );

            if (rowView.getColumn("order_stock_id", Integer.class) != null)
                ro.getOrderReturned().getStocks().put(
                        rowView.getRow(Stock.class),
                        rowView.getColumn("order_order_quantity", Integer.class)
                );
        }
    }
}
