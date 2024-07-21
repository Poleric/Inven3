package com.lavacorp.db;

import com.lavacorp.entities.TransactionType;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlScript;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface TransactionTypeDao {
    @SqlScript("""
    CREATE TABLE IF NOT EXISTS ItemTransactionType (
        id   INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT    UNIQUE NOT NULL
    );
    """)
    void createTable();

    @SqlUpdate("INSERT OR REPLACE INTO ItemTransactionType (name) VALUES (:name)")
    @GetGeneratedKeys
    int create(@NotNull String name);

    default int create(@NotNull TransactionType transactionType) {
        return create(transactionType.name());
    }

    @SqlUpdate("DELETE FROM ItemTransactionType WHERE id = :id")
    void delete(int id);

    @SqlUpdate("DELETE FROM ItemTransactionType WHERE name = :name")
    void delete(@NotNull String name);

    default void delete(@NotNull TransactionType transactionType) {
        delete(transactionType.name());
    }

    @SqlUpdate("DELETE FROM ItemTransactionType WHERE name LIKE :pattern")
    void deleteLike(@NotNull String pattern);

    @SqlQuery("SELECT name from ItemTransactionType WHERE id = :id")
    @RegisterConstructorMapper(TransactionType.class)
    Optional<TransactionType> getType(int id);

    @SqlQuery("SELECT name from ItemTransactionType WHERE name = :name")
    @RegisterConstructorMapper(TransactionType.class)
    Optional<TransactionType> getType(@NotNull String name);

    @SqlQuery("SELECT name from ItemTransactionType WHERE name LIKE :name")
    @RegisterConstructorMapper(TransactionType.class)
    Optional<TransactionType> getTypeLike(@NotNull String pattern);

    @SqlQuery("SELECT name from ItemTransactionType")
    @RegisterConstructorMapper(TransactionType.class)
    List<TransactionType> getAllType();

    @SqlQuery("SELECT id FROM ItemTransactionType WHERE name = :name")
    OptionalInt getTypeId(@NotNull String name);

    default OptionalInt getTypeId(@NotNull TransactionType transactionType) {
        return getTypeId(transactionType.name());
    }
}
