package com.lavacorp.db;

import com.lavacorp.entities.item.Item;
import com.lavacorp.mapper.ItemMapper;
import org.jdbi.v3.freemarker.UseFreemarkerSqlLocator;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.AllowUnusedBindings;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@UseFreemarkerSqlLocator
@AllowUnusedBindings
@RegisterRowMapper(ItemMapper.class)
public interface ItemDao {
    @SqlUpdate
    @GetGeneratedKeys("id")
    int create(@BindBean Item item);

    @SqlQuery("retrieveById")
    Item retrieve(int id);

    @SqlQuery("retrieveByName")
    Item retrieve(@NotNull String name);

    @SqlQuery("retrieve")
    List<Item> retrieveAll();

    @SqlUpdate
    void update(@BindBean Item item);

    @SqlUpdate("deleteById")
    void delete(int id);

    @SqlUpdate("deleteByName")
    void delete(String name);
}