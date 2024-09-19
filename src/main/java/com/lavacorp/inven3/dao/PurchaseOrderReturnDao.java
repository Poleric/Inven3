package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.ReturnOrderDao;
import com.lavacorp.inven3.model.PurchaseOrder;
import com.lavacorp.inven3.model.ReturnOrder;
import com.lavacorp.inven3.model.Stock;
import com.lavacorp.inven3.model.generic.Order;
import org.jdbi.v3.spring5.JdbiRepository;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JdbiRepository
@RegisterBeanMapper(PurchaseOrder.class)
@RegisterBeanMapper(value = Stock.class, prefix = "stock")
public interface PurchaseOrderReturnDao extends ReturnOrderDao {
    @Override
    @SqlQuery("select")
    @UseRowReducer(PurchaseOrderDao.PurchaseOrderRowReducer.class)
    @Nullable ReturnOrder selectById(int id);

    @Override
    @SqlQuery("select")
    @UseRowReducer(PurchaseOrderDao.PurchaseOrderRowReducer.class)
    List<ReturnOrder> selectAll();

    @Override
    @SqlQuery("select")
    @UseRowReducer(PurchaseOrderDao.PurchaseOrderRowReducer.class)
    List<ReturnOrder> selectAll(String orderColumn, OrderDirection orderDirection);

    @Override
    @SqlQuery("select")
    @UseRowReducer(PurchaseOrderDao.PurchaseOrderRowReducer.class)
    List<ReturnOrder> selectAll(int page, int pageSize);

    @Override
    @SqlQuery("select")
    @UseRowReducer(PurchaseOrderDao.PurchaseOrderRowReducer.class)
    List<ReturnOrder> selectAll(String orderColumn, OrderDirection orderDirection, int page, int pageSize);

    @Override
    @SqlQuery("select")
    @UseRowReducer(PurchaseOrderDao.PurchaseOrderRowReducer.class)
    List<ReturnOrder> selectAllByStatus(@Bind @Define Order.OrderStatus status);

    @Override
    @SqlQuery("select")
    @UseRowReducer(PurchaseOrderDao.PurchaseOrderRowReducer.class)
    List<ReturnOrder> selectAllByStatus(@Bind @Define Order.OrderStatus status, String orderColumn, OrderDirection orderDirection);

    @Override
    @SqlQuery("select")
    @UseRowReducer(PurchaseOrderDao.PurchaseOrderRowReducer.class)
    List<ReturnOrder> selectAllByStatus(@Bind @Define Order.OrderStatus status, int page, int pageSize);

    @Override
    @SqlQuery("select")
    @UseRowReducer(PurchaseOrderDao.PurchaseOrderRowReducer.class)
    List<ReturnOrder> selectAllByStatus(@Bind @Define Order.OrderStatus status, String orderColumn, OrderDirection orderDirection, int page, int pageSize);

    @SqlQuery("select")
    @UseRowReducer(PurchaseOrderDao.PurchaseOrderRowReducer.class)
    @Nullable ReturnOrder selectByOrderReferenceId(@Bind @Define int referenceOrderId);
}
