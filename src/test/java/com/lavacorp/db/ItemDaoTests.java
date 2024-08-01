package com.lavacorp.db;

import com.lavacorp.entities.category.Category;
import com.lavacorp.entities.item.Item;
import org.jdbi.v3.core.Handle;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(DatabaseExtension.class)
public class ItemDaoTests {
    public static final Item[] DATA = {
            new Item(1, "Samseng FHD Smart Television", "The first model, black television from the year 2023, equipped with Full HD technology.", 4000.0, "pcs", CategoryDaoTests.DATA[7], null, null),
            new Item(2, "Samseng SmartBooK", "The second model, white laptop from the year 2021, designed for studying purposes.", 3000.0, "pcs", CategoryDaoTests.DATA[6], null, null),
            new Item(3, "Samseng Universe S23 Ultra", "The first and only model, pink smartphone from the year 2022, featuring high-end specs and bigger size.", 5000.0, "pcs", CategoryDaoTests.DATA[8], null, null),
            new Item(4, "Samseng SmartFridge", "The fifth model, silver smart fridge from the year 2023.", 4000.0, "pcs", CategoryDaoTests.DATA[9], null, null),
            new Item(5, "Samseng SmartWatch", "The second model, black smart watch from the year 2020.", 500.0, "pcs", CategoryDaoTests.DATA[10], null, null)
    };

    @BeforeAll
    static void setUp() {
        try (Handle handle = Database.getJdbi().open()) {
            CategoryDao dao = handle.attach(CategoryDao.class);

            for (Category category : CategoryDaoTests.DATA)
                dao.create(category);
        }
    }

    @Test
    @Order(0)
    void testCreate() {
        try (Handle handle = Database.getJdbi().open()) {
            ItemDao itemDao = handle.attach(ItemDao.class);

            for (Item item : DATA)
                itemDao.create(item);
        }
    }

    @Test
    void testRetrieveAll() {
        try (Handle handle = Database.getJdbi().open()) {
            ItemDao dao = handle.attach(ItemDao.class);

            List<Item> result = dao.retrieveAll();
            for (int i = 0; i < DATA.length; i++) {
                Item expected = DATA[i];
                Item actual = result.get(i);

                assertEquals(expected, actual);
            }
        }
    }

    @Test
    void testRetrieveByName() {
        try (Handle handle = Database.getJdbi().open()) {
            ItemDao dao = handle.attach(ItemDao.class);

            for (Item expected : DATA) {
                Item actual = dao.retrieve(expected.getName());

                assertEquals(expected, actual);
            }
        }
    }
}
