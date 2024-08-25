package com.lavacorp.db.dao.generic;

import com.lavacorp.db.Database;
import com.lavacorp.db.dao.DatabaseExtension;
import com.lavacorp.models.generic.DatabaseObj;
import org.jdbi.v3.core.Handle;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(DatabaseExtension.class)
abstract public class DaoTest<T extends DatabaseObj, K extends Dao<T>> {
    public Handle handle;
    public K dao;

    public final T[] DATA;
    public final T[] UPDATED_DATA;
    public final Class<K> DAO_TYPE;

    public DaoTest(Class<K> dao, T[] data, T[] updated_data) {
        this.DAO_TYPE = dao;
        this.DATA = data;
        this.UPDATED_DATA = updated_data;
    }

    public void assumeArrayNotEmpty(Object[] arr) {
        Assumptions.assumeTrue(arr != null);
        Assumptions.assumeTrue(arr.length > 0);
    }

    public Stream<T> getData() {
        assumeArrayNotEmpty(DATA);
        return Stream.of(DATA);
    }

    public Stream<Arguments> getToUpdateData() {
        Assumptions.assumeTrue(UPDATED_DATA != null);
        Assumptions.assumeTrue(DATA.length == UPDATED_DATA.length);

        Stream.Builder<Arguments> builder = Stream.builder();
        for (int i = 0; i < DATA.length; i++)
            builder.add(Arguments.of(DATA[i], UPDATED_DATA[i]));
        return builder.build();
    }

    @BeforeEach
    public void beforeEach() {
        handle = Database.getJdbi().open();
        dao = handle.attach(DAO_TYPE);

        handle.begin();
    }

    @AfterEach
    public void afterEach() {
        handle.commit();
        handle.close();

        dao = null;
    }

    @ParameterizedTest
    @Order(0)
    @MethodSource("getData")
    public void testInsert(T data) {
        T result = dao.insert(data);
        assertNotNull(result.getId());
    }

    @Test
    @Order(1)
    public void testSelectAll() {
        List<T> expected = getData().toList();
        List<T> actual = dao.selectAll();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @Order(1)
    @MethodSource("getData")
    public void testSelectById(T expected) {
        Integer id = expected.getId();
        assertNotNull(id);

        T actual = dao.selectById(id);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @Order(2)
    @MethodSource("getToUpdateData")
    public void testUpdate(T expected, T updated) {
        assertNotNull(expected.getId());
        assertEquals(expected.getId(), updated.getId());

        dao.update(updated);

        T actual = dao.selectById(expected.getId());
        assertEquals(updated, actual);

        handle.rollback();
    }

    @ParameterizedTest
    @Order(3)
    @MethodSource("getData")
    public void testDeleteById(T expected) {
        Integer id = expected.getId();
        assertNotNull(id);

        int ret = dao.deleteById(id);
        assertNull(dao.selectById(id));
        assertEquals(1, ret);

        handle.rollback();
    }

    @Test
    @Order(4)
    public void testDeleteByIdInvalid() {
        int ret = dao.deleteById(0);
        assertEquals(0, ret);
    }

}
