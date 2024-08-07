package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoNamed;
import com.lavacorp.entities.Category;
import com.lavacorp.db.mapper.CategoryMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;


@RegisterRowMapper(CategoryMapper.class)
public interface CategoryDao extends DaoNamed<Category> {
}
