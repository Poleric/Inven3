package com.lavacorp;

import com.lavacorp.db.Database;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Inventory {
    protected final static Logger LOGGER = LogManager.getLogger(Inventory.class);

    public Inventory() {
        Database.instance()
                .connect("inventory.db");

    }

}
