package com.lavacorp;

import com.lavacorp.db.Database;
import com.lavacorp.db.InventoryDao;
import com.lavacorp.entities.Item;
import com.lavacorp.db.ItemDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;

public class InventoryManagementSystem {
    private final static Logger LOGGER = LogManager.getLogger(Inventory.class);

    public static void main(String[] args) {
        Database.getInstance()
                .connect("inventory.db");

        Database.getInstance().dropAll();

        try (Handle handle = Database.getInstance().getJdbi().open()) {
            ItemDao itemDao = handle.attach(ItemDao.class);
            itemDao.createTable();
            InventoryDao inventoryDao = handle.attach(InventoryDao.class);
            inventoryDao.createTable();

            Item[] items = {
                    new Item("Lava Cup Noodle", "Lava Cup Noodle 1pc", 3.0),
                    new Item("Magma Cup Noodle", "Magma Cup Noodle 1pc", 3.5),
                    new Item("LAVA Lava Cake", "LAVA Lava Cake 1packet", 4.0)
            };
            for (Item item : items)
                try {
                    itemDao.create(item.getName(), item.getDescription(), item.getBasePrice());
                } catch (UnableToExecuteStatementException e) {
                    LOGGER.error(e.getMessage());
                }

            for (Item item : itemDao.retrieveAll())
                System.out.println("item = " + item);

            inventoryDao.increaseCount(1, 2);
            inventoryDao.increaseCount(2, 4);
            inventoryDao.increaseCount(3, 5);
            inventoryDao.decreaseCount(3, 1);

            System.out.println();

            System.out.println("inventoryDao.queryCount(3) = " + inventoryDao.queryCount(3));
        }
    }
}
