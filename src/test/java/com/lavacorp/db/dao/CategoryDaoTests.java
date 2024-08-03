package com.lavacorp.db.dao;

import com.lavacorp.entities.Category;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryDaoTests extends DaoTest<Category, CategoryDao> {
    public static final Category[] DATA = {
            new Category(1, "CPU", null),
            new Category(2, "Graphics card", null),
            new Category(3, "SSD", null),
            new Category(4, "USB Sticks", null),
            new Category(5, "Mouse", null),
            new Category(6, "Keyboard", null),
            new Category(7, "Laptop", null),
            new Category(8, "Television", null),
            new Category(9, "Smartphone", null),
            new Category(10, "Console", null),
            new Category(11, "Smartwatch", null)
    };
    public static final Class<CategoryDao> DAO = CategoryDao.class;

    public CategoryDaoTests() {
        super(DATA, DAO);
    }

    @Test
    @Order(1)
    void testRetrieveByName() {
        CategoryDao dao = getDao();

        for (Category expected : DATA) {
            Category actual = dao.retrieve(expected.getName());

            assertEquals(expected, actual);
        }
    }

    @Test
    @Order(2)
    void testUpdateName() {
        CategoryDao dao = getDao();

        Category expected = DATA[0];
        assertNotNull(expected.getId());

        String before = expected.getName();
        expected.setName("AAAAA");

        dao.update(expected);

        Category actual = dao.retrieve(expected.getId());
        assertEquals(expected, actual);

        expected.setName(before);
        dao.update(expected);
    }

    @Test
    @Order(3)
    void testDeleteByName() {
        CategoryDao dao = getDao();

        dao.delete("Graphics Card");
        assertNull(dao.retrieve("Graphics Card"));
    }
}
