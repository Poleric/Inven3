package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.Dao;
import com.lavacorp.entities.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;

@RegisterBeanMapper(User.class)
public interface UserDao extends Dao<User> {
}
