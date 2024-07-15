package com.lavacorp;

import com.lavacorp.database.Database;
import com.lavacorp.modules.storage.DatabaseStorage;
import com.lavacorp.modules.storage.Storage;
import com.lavacorp.modules.transaction.TransactionLogger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Inventory {
    protected Database database;
    protected Storage storage;
    protected TransactionLogger transactionLogger;
    protected final static Logger LOGGER = LogManager.getLogger(Inventory.class);

    public Inventory() {
        database = Database.getInstance();
        database.connect("inventory.db");

        storage = new DatabaseStorage();
//        transactionLogger = new TransactionLogger();
    }

    public void addItem(int itemId, int quantity) {
        storage.addItem(itemId, quantity);
    }

    public void removeItem(int itemId, int quantity) {
        storage.removeItem(itemId, quantity);
    }
}
