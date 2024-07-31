package com.lavacorp.db;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DatabaseExtension implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        Database db = Database.instance();
        db.connect("test.db");
        db.dropAll();
        db.initTables();
    }
}
