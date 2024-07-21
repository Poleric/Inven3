CREATE TABLE IF NOT EXISTS Item (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    name        TEXT UNIQUE NOT NULL,
    description TEXT,
    base_price  REAL
)