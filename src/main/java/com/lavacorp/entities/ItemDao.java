package com.lavacorp.entities;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
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
        id          INTEGER PRIMARY KEY AUTOINCREMENT,
        name        TEXT UNIQUE NOT NULL,
        description TEXT,
        base_price  REAL
    )
    """)
    void createTable();

    @SqlUpdate("INSERT INTO Item (name, description, base_price) VALUES (:name, :description, :basePrice)")
    @GetGeneratedKeys
    int create(@BindBean @NotNull Item item);

    @SqlUpdate("DELETE FROM Item WHERE id = :id")
    void delete(int id);

    @SqlUpdate("DELETE FROM Item WHERE name = :name")
    void delete(@NotNull String name);

    default void delete(@BindBean @NotNull Item item) {
        delete(item.getName());
    }

    @SqlUpdate("DELETE FROM Item WHERE name LIKE :pattern")
    void deleteLike(@NotNull String pattern);

    @SqlQuery("SELECT name, description, base_price FROM Item WHERE id = :id")
    @RegisterBeanMapper(Item.class)
    Optional<Item> getItem(int id);

    @SqlQuery("SELECT name, description, base_price FROM Item WHERE name = :name")
    @RegisterBeanMapper(Item.class)
    Optional<Item> getItem(@NotNull String name);

    @SqlQuery("SELECT name, description, base_price FROM Item WHERE name LIKE :pattern")
    @RegisterBeanMapper(Item.class)
    List<Item> getItemLike(@NotNull String pattern);

    @SqlQuery("SELECT name, description, base_price FROM Item")
    @RegisterBeanMapper(Item.class)
    List<Item> getAllItem();

    @SqlQuery("SELECT id FROM Item WHERE name = :name")
    OptionalInt getItemId(String name);

    default OptionalInt getItemId(@NotNull Item item) {
        return getItemId(item.getName());
    }
}