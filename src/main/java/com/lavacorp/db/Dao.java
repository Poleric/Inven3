package com.lavacorp.db;

import com.lavacorp.entities.DatabaseObj;
import org.jdbi.v3.freemarker.UseFreemarkerSqlLocator;
import org.jdbi.v3.sqlobject.customizer.AllowUnusedBindings;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@UseFreemarkerSqlLocator
@AllowUnusedBindings
public interface Dao<T extends DatabaseObj> {
    @SqlUpdate
    @GetGeneratedKeys("id")
    int create(@BindBean T obj);

    @SqlQuery("retrieveById")
    T retrieve(int id);

    @SqlQuery("retrieve")
    List<T> retrieveAll();

    @SqlUpdate
    void update(@BindBean T obj);

    @SqlUpdate("deleteById")
    void delete(int id);
}
