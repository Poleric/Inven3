package com.lavacorp.db;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DatabaseExtension implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) {
        Database.getInstance().connect("test.db");

        Database.getJdbi().useExtension(DatabaseDao.class, db -> {
            db.dropAllTables();
            db.initTables();
        });
    }
}
