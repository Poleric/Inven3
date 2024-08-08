CREATE TABLE IF NOT EXISTS Item (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT UNIQUE NOT NULL,
    description     TEXT,
    base_price      REAL,
    unit            TEXT,
    category_id     INTEGER REFERENCES Category (id),

    created_at      DATETIME    NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP,
    last_updated_at DATETIME    NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Category (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    name        TEXT UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS Supplier (
    id           INTEGER PRIMARY KEY AUTOINCREMENT,
    name         TEXT UNIQUE NOT NULL,
    address      TEXT,
    phone_number TEXT,
    email        TEXT
);

CREATE TABLE IF NOT EXISTS Location (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    name        TEXT UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS Stock (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    item_id         INTEGER  NOT NULL REFERENCES Item (id),
    supplier_id     INTEGER  NOT NULL REFERENCES Supplier (id),
    location_id     INTEGER  NOT NULL REFERENCES Location (id),
    quantity        INTEGER  NOT NULL,
    status          TEXT     NOT NULL,
    expiry_date     DATE,
    notes           TEXT,
    created_at      DATETIME NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP,
    last_updated_at DATETIME NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS PurchaseOrder (
    id            INTEGER PRIMARY KEY AUTOINCREMENT,
    purchase_date DATETIME NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP,
    supplier_id   INTEGER  NOT NULL REFERENCES Supplier (id),
    target_date   DATETIME,
    arrived_date  DATETIME,
    status        TEXT     NOT NULL,
    reference     TEXT
);

CREATE TABLE IF NOT EXISTS PurchaseOrderLine (
    purchase_order_id INTEGER NOT NULL REFERENCES PurchaseOrder (id),
    stock_id          INTEGER NOT NULL REFERENCES Stock (id),
    quantity          INTEGER NOT NULL,
    PRIMARY KEY (purchase_order_id, stock_id)
);

CREATE TABLE IF NOT EXISTS SalesOrder (
    id            INTEGER PRIMARY KEY AUTOINCREMENT,
    sales_date    DATETIME NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP,
    status        TEXT     NOT NULL,
    shipment_date DATETIME,
    reference     TEXT
);

CREATE TABLE IF NOT EXISTS SalesOrderLine (
    sales_order_id INTEGER NOT NULL REFERENCES SalesOrder (id),
    stock_id       INTEGER NOT NULL REFERENCES Stock (id),
    quantity       INTEGER NOT NULL,
    PRIMARY KEY (sales_order_id, stock_id)
);

CREATE TABLE IF NOT EXISTS ReturnOrder (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    order_id    INTEGER  NOT NULL REFERENCES SalesOrder (id),
    return_date DATETIME NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP,
    status      TEXT     NOT NULL,
    reference   TEXT
);

CREATE TABLE IF NOT EXISTS ItemSupplier (
    item_id     INTEGER  NOT NULL REFERENCES Item (id),
    supplier_id INTEGER  NOT NULL REFERENCES Supplier (id),
    sold_price  REAL     NOT NULL,
    last_added  DATETIME NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (item_id, supplier_id)
);

CREATE TABLE IF NOT EXISTS Tag (
    id   INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS ItemTag (
    item_id    INTEGER  NOT NULL REFERENCES Item (id),
    tag_id     INTEGER  NOT NULL REFERENCES Tag (id),
    last_added DATETIME NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (item_id, tag_id)
);

CREATE TABLE IF NOT EXISTS LocationTag (
    location_id INTEGER  NOT NULL REFERENCES Location (id),
    tag_id      INTEGER  NOT NULL REFERENCES Tag (id),
    last_added  DATETIME NOT NULL ON CONFLICT REPLACE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (location_id, tag_id)
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