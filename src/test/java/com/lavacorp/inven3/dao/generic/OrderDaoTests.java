package com.lavacorp.inven3.dao.generic;

import com.lavacorp.inven3.model.generic.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public abstract class OrderDaoTests<T extends Order, K extends OrderDao<T>> extends DaoTests<T, K> {

    public OrderDaoTests(K dao) {
        super(dao);
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void testSelectAllByStatus() {
        List<T> expected = getCachedData();

        Order.OrderStatus status = expected.getFirst().getStatus();

        expected = expected.stream().filter((T) -> T.getStatus() == status).toList();
        List<T> actual = dao.selectAllByStatus(status);

        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }
}
