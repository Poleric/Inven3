package com.lavacorp.db;

import com.lavacorp.entities.category.Category;
import org.jdbi.v3.core.Handle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DatabaseExtension.class)
public class CategoryDaoTests {
    private static final Category[] DATA = {
            new Category("CPU"),
            new Category("Graphics card"),
            new Category("SSD"),
            new Category("USB Sticks"),
            new Category("Mouse"),
            new Category("Keyboard"),
    };

    @BeforeAll
    static void setUp() {
        try (Handle handle = Database.instance().jdbi().open()) {
            CategoryDao dao = handle.attach(CategoryDao.class);

            for (Category category : DATA)
                dao.create(category);
        }
    }

    @Test
    void testRetrieveAll() {
        try (Handle handle = Database.instance().jdbi().open()) {
            CategoryDao dao = handle.attach(CategoryDao.class);

            List<Category> result = dao.retrieveAll();
            for (int i = 0; i < DATA.length; i++) {
                Category expected = DATA[i];
                Category actual = result.get(i);

                assertEquals(expected.getName(), actual.getName());
            }
        }
    }

    @Test
    void testRetrieveByName() {
        try (Handle handle = Database.instance().jdbi().open()) {
            CategoryDao dao = handle.attach(CategoryDao.class);

            for (Category expected : DATA) {
                Category actual = dao.retrieve(expected.getName());

                assertEquals(expected.getName(), actual.getName());
            }
        }
    }
}
