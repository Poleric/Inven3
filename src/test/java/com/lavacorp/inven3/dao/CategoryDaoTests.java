package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.NamedDaoTests;
import com.lavacorp.inven3.model.Category;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryDaoTests extends NamedDaoTests<Category, CategoryDao> {

    @Autowired
    public CategoryDaoTests(CategoryDao dao) {
        super(dao);
    }

}
