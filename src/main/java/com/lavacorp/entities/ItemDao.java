package com.lavacorp.entities;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
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
        id   INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT UNIQUE NOT NULL
    )
    """)
    void createTable();

    @SqlUpdate("INSERT OR REPLACE INTO Item (name) VALUES (:name)")
    @GetGeneratedKeys
    int create(@NotNull String name);

    default int create(@NotNull Item item) {
        return create(item.name());
    }

    @SqlUpdate("DELETE FROM Item WHERE id = :id")
    void delete(int id);

    @SqlUpdate("DELETE FROM Item WHERE name = :name")
    void delete(@NotNull String name);

    default void delete(@NotNull Item item) {
        delete(item.name());
    }

    @SqlUpdate("DELETE FROM Item WHERE name LIKE :pattern")
    void deleteLike(@NotNull String pattern);

    @SqlQuery("SELECT name FROM Item WHERE id = :id")
    @RegisterConstructorMapper(Item.class)
    Optional<Item> getItem(int id);

    @SqlQuery("SELECT name FROM Item WHERE name = :name")
    @RegisterConstructorMapper(Item.class)
    Optional<Item> getItem(@NotNull String name);

    @SqlQuery("SELECT name FROM Item WHERE name LIKE :pattern")
    @RegisterConstructorMapper(Item.class)
    List<Item> getItemLike(@NotNull String pattern);

    @SqlQuery("SELECT name FROM Item")
    @RegisterConstructorMapper(Item.class)
    List<Item> getAllItem();

    @SqlQuery("SELECT id FROM Item WHERE name = :name")
    OptionalInt getItemId(String name);

    default OptionalInt getItemId(@NotNull Item item) {
        return getItemId(item.name());
    }
}