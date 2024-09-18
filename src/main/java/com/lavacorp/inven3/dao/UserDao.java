package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.NamedDao;
import com.lavacorp.inven3.model.User;
import org.jdbi.v3.spring5.JdbiRepository;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JdbiRepository
@RegisterBeanMapper(User.class)
public interface UserDao extends NamedDao<User> {
    @SqlQuery("select")
    List<User> selectAllByRole(@Bind @Define @NotNull User.Role role);

    @SqlQuery("select")
    List<User> selectAllByRole(@Bind @Define @NotNull User.Role role, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<User> selectAllByRole(@Bind @Define @NotNull User.Role role, @Define String orderColumn, @Define String orderDirection, @Define int page, @Define int pageSize);
}
