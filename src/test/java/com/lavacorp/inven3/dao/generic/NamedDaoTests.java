package com.lavacorp.inven3.dao.generic;

import com.lavacorp.inven3.model.generic.NamedEntity;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public abstract class NamedDaoTests<T extends NamedEntity, K extends NamedDao<T>> extends DaoTests<T, K> {

    public NamedDaoTests(K dao) {
        super(dao);
    }

    @Test
    @Order(1)
    void testSelectAllOrderedName() {
        List<T> expected = getCachedData();
        List<T> actual = dao.selectAll("name", "ASC");

        assertEquals(expected.size(), actual.size());
        assertNotEquals(expected, actual);
    }

    @Test
    @Order(1)
    void testSelectByNameCaseInsensitive() {
        T expected = getCachedData().getFirst();
        T actual = dao.selectByName(expected.getName().toUpperCase());

        assertEquals(expected, actual);
    }

    @Test
    @Order(1)
    void testSelectByNameLike() {
        T expected = getCachedData().getFirst();

        String name = expected.getName();
        String sliced_name = name.substring((int) (name.length() * 0.25), (int) (name.length() * 0.75));
        assumeFalse(sliced_name.isEmpty());

        List<T> actual = dao.selectAllByNameLike(sliced_name);

        assertTrue(actual.contains(expected));
    }

    @Test
    @Order(2)
    void testUpdateName() {
        T expected = getCachedData().getFirst();
        assertNotNull(expected.getId());

        Instant now = Instant.now();
        expected.setName(now.toString());

        dao.update(expected);

        T actual = dao.selectById(expected.getId());
        assertEquals(expected, actual);
    }


    @Test
    @Order(3)
    void testDeleteByName() {
        T expected = getTestData().getFirst();
        assertNotNull(expected.getName());

        try {
            dao.deleteByName(expected.getName());
        } catch (UnableToExecuteStatementException e) {
            assertTrue(e.getMessage().contains("violates foreign key constraint"));
            assumeTrue(false);
        }

        T actual = dao.selectByName(expected.getName());
        assertNull(actual);
    }
}
