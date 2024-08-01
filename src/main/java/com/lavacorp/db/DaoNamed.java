package com.lavacorp.db;

import com.lavacorp.entities.DatabaseObj;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jetbrains.annotations.NotNull;

public interface DaoNamed<T extends DatabaseObj> extends Dao<T> {
    @SqlQuery("retrieveByName")
    T retrieve(@NotNull String name);

    @SqlUpdate("deleteByName")
    void delete(@NotNull String name);
}
