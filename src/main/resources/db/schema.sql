-- https://stackoverflow.com/questions/70739480/change-postgres-to-case-insensitive
CREATE COLLATION IF NOT EXISTS english_ci (
    PROVIDER = 'icu',
    LOCALE = 'en-US@colStrength=secondary',
    DETERMINISTIC = FALSE
);

CREATE TABLE IF NOT EXISTS Category (
    id          SERIAL PRIMARY KEY,
    name        TEXT UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS Item (
    id              SERIAL PRIMARY KEY,
    name            TEXT UNIQUE NOT NULL ,
    description     TEXT,
    base_price      NUMERIC,
    unit            TEXT,
    category_id     INTEGER REFERENCES Category (id),

    created_at      TIMESTAMP DEFAULT NOW(),
    last_updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS Supplier (
    id           SERIAL PRIMARY KEY,
    name         TEXT UNIQUE NOT NULL,
    address      TEXT,
    phone_number TEXT,
    email        TEXT
);

CREATE TABLE IF NOT EXISTS Location (
    id          SERIAL PRIMARY KEY,
    name        TEXT UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS Stock (
    id              SERIAL PRIMARY KEY,
    item_id         INTEGER NOT NULL REFERENCES Item (id),
    supplier_id     INTEGER NOT NULL REFERENCES Supplier (id),
    location_id     INTEGER NOT NULL REFERENCES Location (id),
    quantity        INTEGER NOT NULL CHECK ( quantity >= 0 ),
    status          TEXT    NOT NULL,
    expiry_date     DATE,
    notes           TEXT,
    created_at      TIMESTAMP DEFAULT NOW(),
    last_updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS PurchaseOrder (
    id            SERIAL PRIMARY KEY,
    purchase_date TIMESTAMP DEFAULT NOW(),
    supplier_id   INTEGER NOT NULL REFERENCES Supplier (id),
    target_date   TIMESTAMP,
    arrived_date  TIMESTAMP,
    status        TEXT    NOT NULL,
    reference     TEXT
);

CREATE TABLE IF NOT EXISTS PurchaseOrderLine (
    purchase_order_id INTEGER NOT NULL REFERENCES PurchaseOrder (id),
    stock_id          INTEGER NOT NULL REFERENCES Stock (id),
    order_quantity    INTEGER NOT NULL,
    PRIMARY KEY (purchase_order_id, stock_id)
);

CREATE TABLE IF NOT EXISTS SalesOrder (
    id            SERIAL PRIMARY KEY,
    sales_date    TIMESTAMP DEFAULT NOW(),
    status        TEXT NOT NULL,
    shipment_date TIMESTAMP,
    reference     TEXT
);

CREATE TABLE IF NOT EXISTS SalesOrderLine (
    sales_order_id INTEGER NOT NULL REFERENCES SalesOrder (id),
    stock_id       INTEGER NOT NULL REFERENCES Stock (id),
    order_quantity INTEGER NOT NULL,
    PRIMARY KEY (sales_order_id, stock_id)
);

CREATE TABLE IF NOT EXISTS ReturnOrder (
    id          SERIAL PRIMARY KEY,
    order_id    INTEGER NOT NULL REFERENCES SalesOrder (id),
    return_date TIMESTAMP DEFAULT NOW(),
    status      TEXT    NOT NULL,
    reference   TEXT
);

CREATE TABLE IF NOT EXISTS ItemSupplier (
    item_id         INTEGER NOT NULL REFERENCES Item (id),
    supplier_id     INTEGER NOT NULL REFERENCES Supplier (id),
    sold_price      NUMERIC NOT NULL,
    last_updated_at TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (item_id, supplier_id)
);

CREATE TABLE IF NOT EXISTS Users (
    id              SERIAL PRIMARY KEY,
    name            TEXT UNIQUE NOT NULL,
    hashed_password CHAR(60)    NOT NULL,
    role            TEXT        NOT NULL
);

CREATE OR REPLACE FUNCTION updateLastUpdateAt() RETURNS TRIGGER AS
$updateLastUpdateAt$
BEGIN
    NEW.last_updated_at := NOW();

    RETURN NEW;
END;
$updateLastUpdateAt$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER updateItemLastUpdate
    AFTER UPDATE
    ON Item
    FOR EACH ROW
EXECUTE FUNCTION updateLastUpdateAt();

CREATE OR REPLACE TRIGGER updateStockLastUpdate
    AFTER UPDATE
    ON Stock
    FOR EACH ROW
EXECUTE FUNCTION updateLastUpdateAt();

CREATE OR REPLACE TRIGGER updateItemSupplierLastUpdate
    AFTER UPDATE
    ON ItemSupplier
    FOR EACH ROW
EXECUTE FUNCTION updateLastUpdateAt();