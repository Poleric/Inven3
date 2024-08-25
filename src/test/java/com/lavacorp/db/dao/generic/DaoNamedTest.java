package com.lavacorp.db.dao.generic;

import com.lavacorp.models.generic.NamedDatabaseObj;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

abstract public class DaoNamedTest<T extends NamedDatabaseObj, K extends DaoNamed<T>> extends DaoTest<T, K> {
    public DaoNamedTest(Class<K> dao, T[] data, T[] updated_data) {
        super(dao, data, updated_data);
    }

    @ParameterizedTest
    @Order(1)
    @MethodSource("getData")
    void testSelectByName(T expected) {
        String name = expected.getName();

        T actual = dao.selectByName(name);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @Order(1)
    @MethodSource("getData")
    void testSelectByNameLike(T expected) {
        String name = expected.getName();

        List<T> actual = dao.selectByNameLike(name.toLowerCase());

        assertEquals(expected, actual.getFirst());
    }

    @ParameterizedTest
    @Order(3)
    @MethodSource("getData")
    void testDeleteByName(T expected) {
        String name = expected.getName();

        int ret = dao.deleteByName(name);
        assertNull(dao.selectByName(name));
        assertEquals(1, ret);

        handle.rollback();
    }

    @Order(3)
    @Test
    void testDeleteByNameInvalid() {
        int ret = dao.deleteByName("");
        assertEquals(0, ret);
    }
}
