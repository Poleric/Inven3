package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.NamedDao;
import com.lavacorp.inven3.model.Category;
import org.jdbi.v3.spring5.JdbiRepository;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.springframework.stereotype.Repository;

@Repository
@JdbiRepository
@RegisterBeanMapper(Category.class)
public interface CategoryDao extends NamedDao<Category> {
}
