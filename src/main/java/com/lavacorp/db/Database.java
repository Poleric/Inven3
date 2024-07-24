package com.lavacorp.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.UnableToCreateStatementException;
import org.jdbi.v3.freemarker.FreemarkerEngine;
import org.jdbi.v3.sqlite3.SQLitePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class Database {
    private static Database instance = null;
    private Jdbi jdbi = null;
    private final static Logger LOGGER = LogManager.getLogger(Database.class);

    public void connect(String filepath) {
        jdbi = Jdbi.create("jdbc:sqlite:" + filepath)
                .installPlugin(new SQLitePlugin())
                .installPlugin(new SqlObjectPlugin())
                .setTemplateEngine(FreemarkerEngine.instance());

        LOGGER.info("Connected to SQLite database");
    }

    private Database() {}

    public static Database instance() {
        if (instance == null)
            instance = new Database();

        return instance;
    }

    public Jdbi jdbi() {
        if (jdbi == null)
            throw new IllegalStateException("Database not connected.");

        return jdbi;
    }

    public void initTables() {
        try (Handle handle = jdbi.open()) {
            handle.attach(DatabaseDao.class).initTables();
        }
    }

    public void dropAll() {
        try (Handle handle = jdbi.open()) {
            DatabaseDao dao = handle.attach(DatabaseDao.class);

            for (String tableName : dao.getTables())
                try {
                    handle.execute("DROP TABLE IF EXISTS " + tableName);
                } catch (UnableToCreateStatementException e) {
                    LOGGER.warn(e.getMessage());
                }
        }
    }
}
