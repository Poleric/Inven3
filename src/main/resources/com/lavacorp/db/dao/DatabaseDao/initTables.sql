CREATE TABLE IF NOT EXISTS Item (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT UNIQUE NOT NULL,
    description     TEXT,
    base_price      REAL,
    unit            TEXT,
    category_id     TEXT REFERENCES Category (id),

    created_at      DATETIME    NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP,
    last_updated_at DATETIME    NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Category (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    name        TEXT UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS Tag (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    name        TEXT UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS ItemTag (
    item_id    INTEGER  NOT NULL REFERENCES Item (id),
    tag_id     INTEGER  NOT NULL REFERENCES Tag (id),
    last_added DATETIME NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (item_id, tag_id)
);

CREATE TABLE IF NOT EXISTS Supplier (
    id           INTEGER PRIMARY KEY AUTOINCREMENT,
    name         TEXT UNIQUE NOT NULL,
    address      TEXT,
    phone_number TEXT,
    email        TEXT
);

CREATE TABLE IF NOT EXISTS PurchaseOrder (
    id               INTEGER PRIMARY KEY AUTOINCREMENT,
    stock_id         INTEGER  NOT NULL REFERENCES Stock (id),
    purchase_date    DATETIME NOT NULL,
    amount           INTEGER  NOT NULL,
    quantity         INTEGER  NOT NULL,
    supplier_id INTEGER NOT NULL REFERENCES Supplier(id)
);

CREATE TABLE IF NOT EXISTS PurchaseOrderLine (
    id                INTEGER PRIMARY KEY AUTOINCREMENT,
    purchase_order_id INTEGER NOT NULL REFERENCES PurchaseOrder (id),
    stock_id          INTEGER NOT NULL REFERENCES Stock (id)
);

CREATE TABLE IF NOT EXISTS SalesOrder (
    id               INTEGER PRIMARY KEY AUTOINCREMENT,
    sales_date       DATETIME NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP,
    status           TEXT     NOT NULL,
    amount           INTEGER  NOT NULL,
    item_id          INTEGER  NOT NULL REFERENCES Item (id),
    pay_method       TEXT     NOT NULL
);

CREATE TABLE IF NOT EXISTS SalesOrderLine (
    id               INTEGER PRIMARY KEY AUTOINCREMENT,
    sales_order_id   INTEGER NOT NULL REFERENCES SalesOrder (id),
    stock_id         INTEGER NOT NULL REFERENCES Stock (id),
    amount           INTEGER NOT NULL,
    item_id          INTEGER NOT NULL REFERENCES Item (id)
);

CREATE TABLE IF NOT EXISTS ReturnOrder (
    id            INTEGER PRIMARY KEY AUTOINCREMENT,
    order_id      INTEGER NOT NULL REFERENCES SalesOrder (id),
    item_id       INTEGER NOT NULL,
    return_amount INTEGER,
    status        TEXT    NOT NULL
);

CREATE TABLE IF NOT EXISTS LocationType (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    name        TEXT NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS Location (
    id               INTEGER PRIMARY KEY AUTOINCREMENT,
    name             TEXT UNIQUE NOT NULL,
    description      TEXT,
    location_type_id INTEGER REFERENCES LocationType (id)
);

CREATE TABLE IF NOT EXISTS LocationTag (
    location_id INTEGER  NOT NULL REFERENCES Location (id),
    tag_id      INTEGER  NOT NULL REFERENCES Tag (id),
    last_added  DATETIME NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (location_id, tag_id)
);

CREATE TABLE IF NOT EXISTS Stock (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    item_id         INTEGER  NOT NULL REFERENCES Item (id),
    supplier_id     INTEGER  NOT NULL REFERENCES Supplier (id),
    location_id     INTEGER  NOT NULL REFERENCES Location (id),
    quantity        INTEGER  NOT NULL,
    status          TEXT     NOT NULL,
    expiry_date     DATETIME,
    notes           TEXT,
    created_at      DATETIME NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP,
    last_updated_at DATETIME NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS StockSupplier (
    item_id     INTEGER  NOT NULL REFERENCES Item (id),
    supplier_id INTEGER  NOT NULL REFERENCES Supplier (id),
    sold_price  REAL     NOT NULL,
    last_added  DATETIME NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (item_id, supplier_id)
);

CREATE TABLE IF NOT EXISTS StockTransaction (
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    item_id    INTEGER  NOT NULL REFERENCES Item (id),
    quantity   INTEGER  NOT NULL,
    created_at DATETIME NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP,
    reference  TEXT
);

CREATE TRIGGER IF NOT EXISTS updateItemLastUpdate
    AFTER UPDATE
    ON Item
    FOR EACH ROW
BEGIN
    UPDATE Item
    SET last_updated_at = CURRENT_TIMESTAMP
    WHERE id = new.id;
END;

CREATE TRIGGER IF NOT EXISTS updateStockLastUpdate
    AFTER UPDATE
    ON Stock
    FOR EACH ROW
BEGIN
    UPDATE Stock
    SET last_updated_at = CURRENT_TIMESTAMP
    WHERE id = new.id;
END;