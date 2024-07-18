package com.lavacorp.entities;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlScript;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface ItemDao {
    @SqlScript("""
    CREATE TABLE IF NOT EXISTS Item (
        item_id   INTEGER PRIMARY KEY AUTOINCREMENT,
        item_name TEXT UNIQUE NOT NULL
    )
    """)
    void createTable();

    @SqlUpdate("""
    INSERT OR REPLACE INTO Item (item_name) VALUES (:name)
    """)
    @GetGeneratedKeys
    int create(@NotNull String name);

    default int create(@NotNull Item item) {
        return create(item.name());
    }

    @SqlUpdate("""
    DELETE FROM Item WHERE item_id = :id
    """)
    void delete(int id);

    @SqlUpdate("""
    DELETE FROM Item WHERE item_name = :name
    """)
    void delete(@NotNull String name);

    default void delete(@NotNull Item item) {
        delete(item.name());
    }

    @SqlUpdate("""
    DELETE FROM Item WHERE item_name LIKE :pattern
    """)
    void deleteLike(@NotNull String pattern);

    @SqlQuery("""
    SELECT item_name FROM Item WHERE item_id = :id
    """)
    @RegisterConstructorMapper(Item.class)
    Optional<Item> getItem(int id);

    @SqlQuery("""
    SELECT item_name FROM Item WHERE item_name = :name
    """)
    @RegisterConstructorMapper(Item.class)
    Optional<Item> getItem(@NotNull String name);

    @SqlQuery("""
    SELECT item_name FROM Item WHERE item_name LIKE :pattern
    """)
    @RegisterConstructorMapper(Item.class)
    List<Item> getItemLike(@NotNull String pattern);

    @SqlQuery("""
    SELECT item_name FROM Item
    """)
    @RegisterConstructorMapper(Item.class)
    List<Item> getAllItem();

    @SqlQuery("""
    SELECT item_id FROM Item WHERE item_name = :name
    """)
    OptionalInt getItemId(String name);

    default OptionalInt getItemId(@NotNull Item item) {
        return getItemId(item.name());
    }
}