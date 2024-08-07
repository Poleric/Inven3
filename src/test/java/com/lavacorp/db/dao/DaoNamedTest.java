package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoNamed;
import com.lavacorp.entities.generic.NamedDatabaseObj;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

abstract public class DaoNamedTest<T extends NamedDatabaseObj, K extends DaoNamed<T>> extends DaoTest<T, K> {
    public DaoNamedTest(Class<K> dao, T[] data, T[] updated_data) {
        super(dao, data, updated_data);
    }

    @ParameterizedTest
    @Order(1)
    @MethodSource("getData")
    void testRetrieveByName(T expected) {
        String name = expected.getName();

        T actual = dao.retrieve(name);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @Order(3)
    @MethodSource("getData")
    void testDeleteByName(T expected) {
        String name = expected.getName();

        dao.delete(name);
        assertNull(dao.retrieve(name));

        handle.rollback();
    }
}
