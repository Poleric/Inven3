package com.lavacorp.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database implements DatabaseConnectable {
    private static Database instance = null;
    private Connection conn = null;

    private final String DB_URL = "jdbc:sqlite:";
    private final static Logger LOGGER = LogManager.getLogger(Database.class);

    @Override
    public void connect(String filepath) {
        try {
            conn = DriverManager.getConnection(DB_URL + filepath);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.info("Connected to SQLite database");
    }

    @Override
    public void close() {
        if (conn == null)
            return;

        LOGGER.info("Closing SQLite database");
        try {
            conn.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    private Database() {}

    public Connection getConnection() {
        if (conn == null)
            throw new IllegalStateException("Not connected");
        return conn;
    }

    public static Database getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed())
                instance = new Database();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return instance;
    }
}
