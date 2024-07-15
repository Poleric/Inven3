package com.lavacorp.modules;

import com.lavacorp.database.Database;

import java.sql.Connection;

abstract public class DatabaseModule {
    protected static Connection getConnection() {
        return Database.getInstance().getConnection();
    }

    abstract protected void initTables();
}
