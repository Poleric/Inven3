package com.lavacorp.db;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.freemarker.FreemarkerEngine;
import org.jdbi.v3.sqlite3.SQLitePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Database {
    @Getter private static final Database instance = new Database();
    private Jdbi jdbi = null;

    public void connect(String filepath) {
        jdbi = Jdbi.create("jdbc:sqlite:" + filepath)
                .installPlugin(new SQLitePlugin())
                .installPlugin(new SqlObjectPlugin())
                .setTemplateEngine(FreemarkerEngine.instance());

        log.info("Connected to SQLite database");
    }

    public static Jdbi getJdbi() {
        if (instance.jdbi == null)
            throw new IllegalStateException("Database not connected.");

        return instance.jdbi;
    }
}
