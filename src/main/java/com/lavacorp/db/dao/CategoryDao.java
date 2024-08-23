package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoNamed;
import com.lavacorp.models.Category;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;


@RegisterBeanMapper(Category.class)
public interface CategoryDao extends DaoNamed<Category> {
}
