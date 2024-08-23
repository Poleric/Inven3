package com.lavacorp.db.dao.generic;

import com.lavacorp.models.generic.DatabaseObj;
import org.jdbi.v3.freemarker.UseFreemarkerSqlLocator;
import org.jdbi.v3.sqlobject.customizer.AllowUnusedBindings;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jetbrains.annotations.Nullable;

import java.util.List;


@UseFreemarkerSqlLocator
@AllowUnusedBindings
public interface Dao<T extends DatabaseObj> {
    @SqlUpdate
    @GetGeneratedKeys("id")
    int insert(@BindBean T obj);

    @SqlQuery
    @Nullable T selectById(int id);

    @SqlQuery("select")
    List<T> selectAll();

    @SqlUpdate
    void update(@BindBean T obj);

    @SqlUpdate("deleteById")
    void delete(int id);
}
