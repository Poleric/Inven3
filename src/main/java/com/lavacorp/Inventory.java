package com.lavacorp;

import com.lavacorp.database.Database;
import com.lavacorp.modules.storage.Storage;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Inventory {
    protected Storage storage;
    protected final static Logger LOGGER = LogManager.getLogger(Inventory.class);

    public Inventory() {
        Database.getInstance()
                .connect("inventory.db");

    }

    public void addItem(int itemId, int quantity) {
        storage.addItem(itemId, quantity);
    }

    public void removeItem(int itemId, int quantity) {
        storage.removeItem(itemId, quantity);
    }
}
