package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoNamedTest;
import com.lavacorp.entities.Category;

public class CategoryDaoTests extends DaoNamedTest<Category, CategoryDao> {
    final static Category[] DATA = {
            Category.builder().id(1).name("CPU").build(),
            Category.builder().id(2).name("Graphics card").build(),
            Category.builder().id(3).name("SSD").build(),
            Category.builder().id(4).name("USB Sticks").build(),
            Category.builder().id(5).name("Mouse").build(),
            Category.builder().id(6).name("Keyboard").build(),
            Category.builder().id(7).name("Laptop").build(),
            Category.builder().id(8).name("Television").build(),
            Category.builder().id(9).name("Smartphone").build(),
            Category.builder().id(10).name("Console").build(),
            Category.builder().id(11).name("Smartwatch").build()
    };

    public CategoryDaoTests() {
        super(CategoryDao.class, DATA, null);
    }
}
