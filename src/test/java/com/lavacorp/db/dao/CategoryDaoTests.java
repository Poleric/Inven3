package com.lavacorp.db.dao;

import com.lavacorp.entities.Category;

public class CategoryDaoTests extends DaoNamedTest<Category, CategoryDao> {
    final static Category[] DATA = {
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

    public CategoryDaoTests() {
        super(CategoryDao.class, DATA, null);
    }
}
