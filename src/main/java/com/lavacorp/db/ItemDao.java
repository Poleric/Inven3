package com.lavacorp.db;

import com.lavacorp.entities.item.Item;
import com.lavacorp.entities.item.ItemMapper;
import org.jdbi.v3.freemarker.UseFreemarkerSqlLocator;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

@UseFreemarkerSqlLocator
@RegisterRowMapper(ItemMapper.class)
public interface ItemDao {
    @SqlUpdate
    void createTable();

    @SqlUpdate
    @GetGeneratedKeys("id")
    int create(@NotNull String name, @Nullable String description, double basePrice);

    @SqlQuery
    Optional<Item> retrieve(int id);

    @SqlQuery
    Optional<Item> retrieve(@NotNull String name);

    @SqlQuery("retrieve")
    List<Item> retrieveAll();

    @SqlQuery("retrieve")
    List<Item> retrieveAll(List<Integer> ids);

    @SqlQuery("retrieve")
    List<Item> retrieveAll(@NotNull String nameLike);

    @SqlUpdate
    void update(@BindBean Item item);

    @SqlUpdate
    void delete(int id);

    @SqlUpdate
    void delete(String name);
}