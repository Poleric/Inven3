package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.StockableOrderDao;
import com.lavacorp.inven3.model.SalesOrder;
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
@RegisterBeanMapper(SalesOrder.class)
@RegisterBeanMapper(value = Stock.class, prefix = "stock")
public interface SalesOrderDao extends StockableOrderDao<SalesOrder> {
    @Transaction
    default SalesOrder insert(SalesOrder order) {
        int orderId = insertOrderDetails(order);
        order.setId(orderId);

        order.getStocks().forEach(
                (key, value) -> {
                    assert key.getId() != null;
                    Stock stock = getStockDao().selectById(key.getId());
                    assert stock != null;
                    assert value > stock.getQuantity();
                    stock.setQuantity(stock.getQuantity() - value);
                    getStockDao().update(stock);
                    insertOrderLine(orderId, key.getId(), value);
                }
        );

        return order;
    }

    @Override
    @SqlQuery("select")
    @UseRowReducer(SalesOrderRowReducer.class)
    @Nullable SalesOrder selectById(int id);

    @Override
    @SqlQuery("select")
    @UseRowReducer(SalesOrderRowReducer.class)
    List<SalesOrder> selectAll();

    @Override
    @SqlQuery("select")
    @UseRowReducer(SalesOrderRowReducer.class)
    List<SalesOrder> selectAll(String orderColumn, OrderDirection orderDirection);

    @Override
    @SqlQuery("select")
    @UseRowReducer(SalesOrderRowReducer.class)
    List<SalesOrder> selectAll(int page, int pageSize);

    @Override
    @SqlQuery("select")
    @UseRowReducer(SalesOrderRowReducer.class)
    List<SalesOrder> selectAll(String orderColumn, OrderDirection orderDirection, int page, int pageSize);

    @Override
    @SqlQuery("select")
    @UseRowReducer(SalesOrderRowReducer.class)
    List<SalesOrder> selectAllByStatus(@Bind @Define Order.OrderStatus status);

    @Override
    @SqlQuery("select")
    @UseRowReducer(SalesOrderRowReducer.class)
    List<SalesOrder> selectAllByStatus(@Bind @Define Order.OrderStatus status, String orderColumn, OrderDirection orderDirection);

    @Override
    @SqlQuery("select")
    @UseRowReducer(SalesOrderRowReducer.class)
    List<SalesOrder> selectAllByStatus(@Bind @Define Order.OrderStatus status, int page, int pageSize);

    @Override
    @SqlQuery("select")
    @UseRowReducer(SalesOrderRowReducer.class)
    List<SalesOrder> selectAllByStatus(@Bind @Define Order.OrderStatus status, String orderColumn, OrderDirection orderDirection, int page, int pageSize);

    @Override
    @SqlQuery("select")
    @UseRowReducer(SalesOrderRowReducer.class)
    @Nullable SalesOrder selectByStockId(@Bind @Define int stockId);

    @SqlQuery("select")
    @UseRowReducer(SalesOrderRowReducer.class)
    List<SalesOrder> selectAllByItemId(@Bind @Define int itemId);

    @SqlQuery("select")
    @UseRowReducer(SalesOrderRowReducer.class)
    List<SalesOrder> selectAllByItemId(@Bind @Define int itemId, String orderColumn, OrderDirection orderDirection);

    @SqlQuery("select")
    @UseRowReducer(SalesOrderRowReducer.class)
    List<SalesOrder> selectAllByItemId(@Bind @Define int itemId, int page, int pageSize);

    @SqlQuery("select")
    @UseRowReducer(SalesOrderRowReducer.class)
    List<SalesOrder> selectAllByItemId(@Bind @Define int itemId, String orderColumn, OrderDirection orderDirection, int page, int pageSize);

    class SalesOrderRowReducer implements LinkedHashMapRowReducer<Integer, SalesOrder> {
        @Override
        public void accumulate(Map<Integer, SalesOrder> map, RowView rowView) {
            SalesOrder po = map.computeIfAbsent(
                    rowView.getColumn("id", Integer.class),
                    row -> rowView.getRow(SalesOrder.class)
            );

            if (rowView.getColumn("stock_id", Integer.class) != null)
                po.getStocks().put(
                        rowView.getRow(Stock.class),
                        rowView.getColumn("order_quantity", Integer.class)
                );
        }
    }
}
