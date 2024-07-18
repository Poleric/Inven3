package com.lavacorp.entities;

import org.jdbi.v3.sqlobject.CreateSqlObject;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlScript;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jetbrains.annotations.Range;

import java.util.OptionalInt;

public interface InventoryDao {
    @SqlScript("""
    CREATE TABLE IF NOT EXISTS Inventory (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        item_id      INTEGER NOT NULL UNIQUE REFERENCES Item (item_id),
        count        INTEGER NOT NULL DEFAULT 0 CHECK (count >= 0)
    );
    """)
    @SqlScript("""
    CREATE TRIGGER createInventoryRecord
        AFTER INSERT
        ON Item
        FOR EACH ROW
    BEGIN
        INSERT INTO Inventory (item_id, count) VALUES (new.id, 0);
    END;
    """)
    void createTable();

    @CreateSqlObject
    ItemDao itemDao();

    @SqlUpdate("""
    INSERT INTO Inventory (item_id) VALUES (:itemId)
    """)
    void initItem(int itemId);

    default int initItem(Item item) {
        int itemId = itemDao().getItemId(item)
                .orElseGet(() -> itemDao().create(item));

        initItem(itemId);

        return itemId;
    }

    @SqlUpdate("""
    UPDATE Inventory SET count = count + :count WHERE item_id = :itemId
    """)
    void increaseCount(int itemId, @Range(from = 1, to = Integer.MAX_VALUE) int count);

    @SqlUpdate("""
    UPDATE Inventory SET count = count - :count WHERE item_id = :itemId
    """)
    void decreaseCount(int itemId, @Range(from = 1, to = Integer.MAX_VALUE) int count);

    @SqlQuery("""
    SELECT count FROM Inventory WHERE item_id = :itemId
    """)
    OptionalInt queryCount(int itemId);
}
