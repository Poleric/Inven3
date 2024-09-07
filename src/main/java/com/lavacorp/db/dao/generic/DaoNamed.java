package com.lavacorp.db.dao.generic;

import com.lavacorp.models.generic.NamedDatabaseObj;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface DaoNamed<T extends NamedDatabaseObj> extends Dao<T> {
    @SqlQuery
    @Nullable T selectByName(@NotNull String name);

    @SqlQuery
    List<T> selectByNameLike(@NotNull String name);

    @SqlUpdate
    int deleteByName(@NotNull String name);
}
