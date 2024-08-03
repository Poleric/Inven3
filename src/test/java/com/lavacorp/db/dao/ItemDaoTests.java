package com.lavacorp.db.dao;

import com.lavacorp.db.Database;
import com.lavacorp.entities.category.Category;
import com.lavacorp.entities.item.Item;
import org.jdbi.v3.core.Handle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ItemDaoTests extends DaoTest<Item, ItemDao> {
    public static final Item[] DATA = {
            new Item(1, "Samseng FHD Smart Television", "The first model, black television from the year 2023, equipped with Full HD technology.", 4000.0, "pcs", CategoryDaoTests.DATA[7], null, null),
            new Item(2, "Samseng SmartBooK", "The second model, white laptop from the year 2021, designed for studying purposes.", 3000.0, "pcs", CategoryDaoTests.DATA[6], null, null),
            new Item(3, "Samseng Universe S23 Ultra", "The first and only model, pink smartphone from the year 2022, featuring high-end specs and bigger size.", 5000.0, "pcs", CategoryDaoTests.DATA[8], null, null),
            new Item(4, "Samseng SmartFridge", "The fifth model, silver smart fridge from the year 2023.", 4000.0, "pcs", CategoryDaoTests.DATA[9], null, null),
            new Item(5, "Samseng SmartWatch", "The second model, black smart watch from the year 2020.", 500.0, "pcs", CategoryDaoTests.DATA[10], null, null)
    };
    public static final Class<ItemDao> DAO = ItemDao.class;

    public ItemDaoTests() {
        super(DATA, DAO);
    }

    @BeforeAll
    static void setUp() {
        try (Handle handle = Database.getJdbi().open()) {
            CategoryDao dao = handle.attach(CategoryDao.class);

            for (Category category : CategoryDaoTests.DATA)
                dao.create(category);
        }
    }

    @Test
    @Order(1)
    void testRetrieveByName() {
        ItemDao dao = getDao();

        for (Item expected : DATA) {
            Item actual = dao.retrieve(expected.getName());

            assertEquals(expected, actual);
        }
    }

    @Test
    @Order(2)
    void testUpdateName() {
        ItemDao dao = getDao();

        Item expected = DATA[0];
        assertNotNull(expected.getId());

        String before = expected.getName();
        expected.setName("AAAAA");

        dao.update(expected);

        Item actual = dao.retrieve(expected.getId());
        assertEquals(expected, actual);

        expected.setName(before);
        dao.update(expected);
    }

    @Test
    @Order(3)
    void testDeleteByName() {
        ItemDao dao = getDao();

        dao.delete("Graphics Card");
        assertNull(dao.retrieve("Graphics Card"));
    }
}
