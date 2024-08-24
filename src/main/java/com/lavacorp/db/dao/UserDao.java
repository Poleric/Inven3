package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoNamed;
import com.lavacorp.models.User;
import com.lavacorp.models.UserType;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@RegisterBeanMapper(User.class)
public interface UserDao extends DaoNamed<User> {
    @SqlQuery
    List<User> selectByUserType(@NotNull UserType userType);
}
