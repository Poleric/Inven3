package com.lavacorp.inven3.dao.generic;

import com.lavacorp.inven3.model.generic.Entity;
import org.jdbi.v3.freemarker.UseFreemarkerSqlLocator;
import org.jdbi.v3.sqlobject.customizer.AllowUnusedBindings;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jetbrains.annotations.Nullable;

import java.util.List;


@UseFreemarkerSqlLocator
@AllowUnusedBindings
public interface Dao<T extends Entity> {
    @SqlUpdate
    @GetGeneratedKeys
    T insert(@BindBean T obj);

    @SqlQuery("select")
    @Nullable T selectById(@Bind @Define int id);

    @SqlQuery("select")
    List<T> selectAll();

    @SqlQuery("select")
    int selectAll(@Define boolean count);

    @SqlQuery("select")
    List<T> selectAll(@Define String orderColumn, @Define String orderDirection);

    @SqlQuery("select")
    List<T> selectAll(@Define int page, @Define int pageSize);

    @SqlQuery("select")
    List<T> selectAll(@Define String orderColumn, @Define String orderDirection, @Define int page, @Define int pageSize);

    @SqlUpdate
    void update(@BindBean T obj);

    @SqlUpdate("delete")
    void deleteById(@Bind @Define int id);
}
