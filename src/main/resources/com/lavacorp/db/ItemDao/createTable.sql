CREATE TABLE IF NOT EXISTS Item (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT UNIQUE NOT NULL,
    description     TEXT,
    base_price      REAL,
    unit            TEXT,
    category_id     TEXT REFERENCES Category (id),
    created_at      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER IF NOT EXISTS updateLastUpdate
    AFTER UPDATE
    ON Item
    FOR EACH ROW
BEGIN
    UPDATE Item
    SET last_updated_at = CURRENT_TIMESTAMP
    WHERE id = new.id;
END;