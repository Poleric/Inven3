package com.lavacorp;

import com.lavacorp.database.Database;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Inventory {
    protected final static Logger LOGGER = LogManager.getLogger(Inventory.class);

    public Inventory() {
        Database.getInstance()
                .connect("inventory.db");

    }

}
