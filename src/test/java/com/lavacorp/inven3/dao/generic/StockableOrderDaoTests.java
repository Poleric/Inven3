package com.lavacorp.inven3.dao.generic;

import com.lavacorp.inven3.model.generic.StockableOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class StockableOrderDaoTests<T extends StockableOrder, K extends StockableOrderDao<T>> extends OrderDaoTests<T, K> {

    public StockableOrderDaoTests(K dao) {
        super(dao);
    }

    @Test
    @Order(1)
    void testSelectByStockId() {
        T expected = getCachedData().getFirst();

        @SuppressWarnings("ConstantConditions")
        T actual = dao.selectByStockId(expected.getStocks().entrySet().iterator().next().getKey().getId());

        assertEquals(expected, actual);
    }
}
