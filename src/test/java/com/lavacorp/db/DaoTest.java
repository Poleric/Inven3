package com.lavacorp.db;

import com.lavacorp.entities.DatabaseObj;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(DatabaseExtension.class)
@AllArgsConstructor
abstract public class DaoTest<T extends DatabaseObj, K extends Dao<T>> {
    public final T[] DATA;
    public final Class<K> DAO;

    public K getDao() {
        return Database.getJdbi().onDemand(DAO);
    }

    @Test
    @Order(0)
    void testCreate() {
        K dao = getDao();

        for (T expected: DATA)
            dao.create(expected);
    }

    @Test
    @Order(1)
    void testRetrieveAll() {
        K dao = getDao();

        List<T> result = dao.retrieveAll();
        for (int i = 0; i < DATA.length; i++) {
            T expected = DATA[i];
            T actual = result.get(i);

            assertEquals(expected, actual);
        }
    }

    @Test
    @Order(1)
    void testRetrieveById() {
        K dao = getDao();

        for (T expected : DATA) {
            T actual = dao.retrieve(expected.getId());

            assertEquals(expected, actual);
        }
    }

    @Test
    @Order(3)
    void testDeleteById() {
        K dao = getDao();

        dao.delete(1);
        assertNull(dao.retrieve(1));
    }

}
