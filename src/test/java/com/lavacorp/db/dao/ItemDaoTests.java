package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoNamedTest;
import com.lavacorp.models.Category;
import com.lavacorp.models.Item;
import org.junit.jupiter.api.BeforeAll;

public class ItemDaoTests extends DaoNamedTest<Item, ItemDao> {
    public final static Item[] DATA = {
        Item.builder()
                .id(1)
                .name("Samseng FHD Smart Television")
                .description("The first model, black television from the year 2023, equipped with Full HD technology.")
                .basePrice(4000.0)
                .unit("pcs")
                .category(CategoryDaoTests.DATA[7]).build(),
        Item.builder()
                .id(2)
                .name("Samseng SmartBooK")
                .description("The second model, white laptop from the year 2021, designed for studying purposes.")
                .basePrice(3000.0)
                .unit("pcs")
                .category(CategoryDaoTests.DATA[6]).build(),
        Item.builder()
                .id(3)
                .name("Samseng Universe S23 Ultra")
                .description("The first and only model, pink smartphone from the year 2022, featuring high-end specs and bigger size.")
                .basePrice(5000.0)
                .unit("pcs")
                .category(CategoryDaoTests.DATA[8]).build(),
        Item.builder()
                .id(4)
                .name("Samseng SmartFridge")
                .description("The fifth model, silver smart fridge from the year 2023.")
                .basePrice(4000.0)
                .unit("pcs")
                .category(CategoryDaoTests.DATA[9]).build(),
        Item.builder()
                .id(5)
                .name("Samseng SmartWatch")
                .description("The second model, black smart watch from the year 2020.")
                .basePrice(500.0)
                .unit("pcs")
                .category(CategoryDaoTests.DATA[10]).build()
    };

    public ItemDaoTests() {
        super(ItemDao.class, DATA, null);
    }

    @BeforeAll
    static void setUp() {
        CategoryDaoTests categoryDaoTests = new CategoryDaoTests();

        categoryDaoTests.beforeEach();

        for (Category category : CategoryDaoTests.DATA)
            categoryDaoTests.dao.insert(category);

        categoryDaoTests.afterEach();
    }
}
