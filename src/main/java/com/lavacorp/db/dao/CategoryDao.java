package com.lavacorp.db.dao;

import com.lavacorp.entities.category.Category;
import com.lavacorp.db.mapper.CategoryMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;


@RegisterRowMapper(CategoryMapper.class)
public interface CategoryDao extends DaoNamed<Category> {
}
