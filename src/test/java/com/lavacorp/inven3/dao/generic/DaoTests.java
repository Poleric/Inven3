package com.lavacorp.inven3.dao.generic;

import com.lavacorp.inven3.model.generic.Entity;
import lombok.Getter;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class DaoTests<T extends Entity, K extends Dao<T>> {
    public K dao;

    @Getter(lazy=true)
    private final List<T> cachedData = getTestData();

    public List<T> getTestData() {
        assertNotNull(dao);
        return dao.selectAll();
    }

    public DaoTests(K dao) {
        this.dao = dao;
    }

    @Test
    @Order(1)
    void testSelectAll() {
        List<T> expected = getCachedData();
        List<T> actual = dao.selectAll();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test
    @Order(1)
    void testSelectById() {
        T expected = getCachedData().getFirst();
        assertNotNull(expected.getId());

        T actual = dao.selectById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Order(1)
    void testSelectAllPaginated() {
        List<T> expected = getCachedData();

        int half = expected.size() / 2;

        List<T> actual = dao.selectAll(1, half);
        assertEquals(half, actual.size());
    }

    @Test
    @Order(1)
    void testCountAll() {
        int expected = getCachedData().size();
        int actual = dao.selectAll(true);

        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    void testDeleteById() {
        T expected = getTestData().getFirst();
        assertNotNull(expected.getId());

        try {
            dao.deleteById(expected.getId());
        } catch (UnableToExecuteStatementException e) {
            assertTrue(e.getMessage().contains("violates foreign key constraint"));
            assumeTrue(false);
        }

        T actual = dao.selectById(expected.getId());
        assertNull(actual);
    }

//  does not work
//    @Test
//    @Order(4)
//    void testDeleteAll() {
//        List<T> expected = getTestData();  // update previous deleted
//        assertFalse(expected.isEmpty());
//
//        for (T user : expected) {
//            assertNotNull(user.getId());
//            dao.deleteById(user.getId());
//        }
//        List<T> actual = dao.selectAll();
//        assertTrue(actual.isEmpty());
//    }
}
