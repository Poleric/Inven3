package com.lavacorp;

import com.lavacorp.database.Database;
import com.lavacorp.modules.storage.Storage;
import com.lavacorp.modules.transaction.TransactionLogger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Inventory {
    protected Database database;
    protected Storage storage;
    protected TransactionLogger transactionLogger;
    protected final static Logger LOGGER = LogManager.getLogger(Inventory.class);


}
