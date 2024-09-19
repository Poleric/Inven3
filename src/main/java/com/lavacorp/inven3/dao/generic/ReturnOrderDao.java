package com.lavacorp.inven3.dao.generic;

import com.lavacorp.inven3.model.generic.ReturnOrder;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jetbrains.annotations.Nullable;

public interface ReturnOrderDao<T extends ReturnOrder<?>> extends OrderDao<T> {
    @SqlQuery("select")
    @Nullable T selectByOrderReferenceId(@Bind @Define int referenceOrderId);
}
