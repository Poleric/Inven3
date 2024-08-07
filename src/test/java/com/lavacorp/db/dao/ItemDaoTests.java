package com.lavacorp.db.dao;

import com.lavacorp.entities.Category;
import com.lavacorp.entities.Item;
import org.junit.jupiter.api.BeforeAll;

public class ItemDaoTests extends DaoNamedTest<Item, ItemDao> {
    final static Item[] DATA = {
            new Item(1, "Samseng FHD Smart Television", "The first model, black television from the year 2023, equipped with Full HD technology.", 4000.0, "pcs", CategoryDaoTests.DATA[7], null, null),
            new Item(2, "Samseng SmartBooK", "The second model, white laptop from the year 2021, designed for studying purposes.", 3000.0, "pcs", CategoryDaoTests.DATA[6], null, null),
            new Item(3, "Samseng Universe S23 Ultra", "The first and only model, pink smartphone from the year 2022, featuring high-end specs and bigger size.", 5000.0, "pcs", CategoryDaoTests.DATA[8], null, null),
            new Item(4, "Samseng SmartFridge", "The fifth model, silver smart fridge from the year 2023.", 4000.0, "pcs", CategoryDaoTests.DATA[9], null, null),
            new Item(5, "Samseng SmartWatch", "The second model, black smart watch from the year 2020.", 500.0, "pcs", CategoryDaoTests.DATA[10], null, null)
    };

    public ItemDaoTests() {
        super(ItemDao.class, DATA, null);
    }

    @BeforeAll
    static void setUp() {
        CategoryDaoTests categoryDaoTests = new CategoryDaoTests();

        categoryDaoTests.beforeEach();

        for (Category category : CategoryDaoTests.DATA)
            categoryDaoTests.dao.create(category);

        categoryDaoTests.afterEach();
    }
}
