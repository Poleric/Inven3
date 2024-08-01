package com.lavacorp.db;

import com.lavacorp.entities.category.Category;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(DatabaseExtension.class)
public class CategoryDaoTests {
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

    static CategoryDao getDao() {
        return Database.getJdbi().onDemand(CategoryDao.class);
    }

    @Test
    @Order(0)
    void testCreate() {
        CategoryDao dao = getDao();

        for (Category category : DATA)
            dao.create(category);
    }

    @Test
    void testRetrieveAll() {
        CategoryDao dao = getDao();

        List<Category> result = dao.retrieveAll();
        for (int i = 0; i < DATA.length; i++) {
            Category expected = DATA[i];
            Category actual = result.get(i);

            assertEquals(expected, actual);
        }
    }

    @Test
    void testRetrieveByName() {
        CategoryDao dao = getDao();

        for (Category expected : DATA) {
            Category actual = dao.retrieve(expected.getName());

            assertEquals(expected, actual);
        }
    }
}
