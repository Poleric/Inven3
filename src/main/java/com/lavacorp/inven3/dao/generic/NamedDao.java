package com.lavacorp.inven3.dao.generic;

import com.lavacorp.inven3.model.generic.Entity;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface NamedDao<T extends Entity> extends Dao<T> {
    @SqlQuery("select")
    @Nullable T selectByName(@Bind @Define String name);

    @SqlQuery("select")
    List<T> selectAllByNameLike(@Bind @Define String nameLike);

    @SqlQuery("select")
    int selectAllByNameLike(@Bind @Define String nameLike, @Define boolean count);

    @SqlQuery("select")
    List<T> selectAllByNameLike(@Bind @Define String name, @Define int page, @Define int pageSize);

   @SqlQuery("select")
    List<T> selectAllByNameLike(@Bind @Define String name, @Define String orderColumn, @Define String orderDirection);

    @SqlQuery("select")
    List<T> selectAllByNameLike(@Bind @Define String name, @Define String orderColumn, @Define String orderDirection, @Define int page, @Define int pageSize);

    @SqlUpdate("delete")
    void deleteByName(@Bind @Define @NotNull String name);
}
