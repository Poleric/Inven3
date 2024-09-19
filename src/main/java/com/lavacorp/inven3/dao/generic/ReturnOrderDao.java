package com.lavacorp.inven3.dao.generic;

import com.lavacorp.inven3.model.ReturnOrder;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jetbrains.annotations.Nullable;

@RegisterBeanMapper(ReturnOrder.class)
public interface ReturnOrderDao extends OrderDao<ReturnOrder> {
    @SqlQuery("select")
    @Nullable ReturnOrder selectByOrderReferenceId(@Bind @Define int referenceOrderId);
}
