CREATE TABLE IF NOT EXISTS Item (
    item_id   INTEGER PRIMARY KEY AUTOINCREMENT,
    item_name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Inventory (
    inventory_id INTEGER PRIMARY KEY AUTOINCREMENT,
    item_id      INTEGER NOT NULL REFERENCES Item (item_id),
    count        INTEGER NOT NULL CHECK (count >= 0)
);

CREATE TABLE IF NOT EXISTS ItemTransactionType (
    itemtransactiontype_id   INTEGER PRIMARY KEY AUTOINCREMENT,
    itemtransactiontype_name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS ItemTransaction (
    itemtransaction_id     INTEGER PRIMARY KEY AUTOINCREMENT,
    item_id                INTEGER   NOT NULL REFERENCES Item (item_id),
    itemtransactiontype_id INTEGER   NOT NULL REFERENCES ItemTransactionType (itemtransactiontype_id),
    timestamp              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    count                  INTEGER   NOT NULL CHECK (count >= 0),
    reference              TEXT, -- additional information to go with itemtransactiontype
    UNIQUE (item_id, itemtransaction_id, timestamp) ON CONFLICT REPLACE
);

CREATE TRIGGER createInventoryRecord
    AFTER INSERT
    ON Item
    FOR EACH ROW
BEGIN
    INSERT INTO Inventory (item_id, count) VALUES (new.item_id, 0);
END;