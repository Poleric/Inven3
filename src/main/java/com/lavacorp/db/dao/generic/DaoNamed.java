package com.lavacorp.db.dao.generic;

import com.lavacorp.models.generic.DatabaseObj;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DaoNamed<T extends DatabaseObj> extends Dao<T> {
    @SqlQuery
    @Nullable T selectByName(@NotNull String name);

    @SqlUpdate("deleteByName")
    void delete(@NotNull String name);
}
