package com.lavacorp.inven3.dao.generic;

import com.lavacorp.inven3.model.generic.ReturnOrder;
import com.lavacorp.inven3.model.generic.StockableOrder;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ReturnOrderDaoTests<T extends ReturnOrder<?>, K extends ReturnOrderDao<T>> extends OrderDaoTests<T, K> {

    public ReturnOrderDaoTests(K dao) {
        super(dao);
    }

    @Test
    @Order(1)
    void testSelectByOrderReferenceId() {
        T expected = getCachedData().getFirst();

        assertNotNull(expected.getOrderReturned());
        assertNotNull(expected.getOrderReturned().getId());

        T actual = dao.selectByOrderReferenceId(expected.getOrderReturned().getId());

        assertEquals(expected, actual);
    }
}
