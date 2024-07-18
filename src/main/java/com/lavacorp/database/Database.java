package com.lavacorp.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlite3.SQLitePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class Database {
    private static Database instance = null;
    private Jdbi jdbi = null;
    private final static Logger LOGGER = LogManager.getLogger(Database.class);

    public void connect(String filepath) {
        jdbi = Jdbi.create("jdbc:sqlite:" + filepath)
                .installPlugin(new SQLitePlugin())
                .installPlugin(new SqlObjectPlugin());

        LOGGER.info("Connected to SQLite database");
    }

    private Database() {}

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();

        return instance;
    }

    public Jdbi getJdbi() {
        if (jdbi == null)
            throw new IllegalStateException("Database not connected.");

        return jdbi;
    }
}
