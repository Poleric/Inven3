package com.lavacorp.db;

import com.lavacorp.entities.category.Category;
import com.lavacorp.mapper.CategoryMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;


@RegisterRowMapper(CategoryMapper.class)
public interface CategoryDao extends Dao<Category> {
}
