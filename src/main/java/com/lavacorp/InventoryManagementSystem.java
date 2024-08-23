package com.lavacorp;

import com.lavacorp.db.Database;
import com.lavacorp.db.dao.UserDao;
import lombok.extern.log4j.Log4j2;
import org.jdbi.v3.core.Handle;

@Log4j2
public class InventoryManagementSystem {
    public static void main(String[] args) {
        Database.getInstance().connect("test.db");

        try ( Handle handle = Database.getJdbi().open()) {
            UserDao userDao = handle.attach(UserDao.class);
            System.out.println("userDao.retrieveAll() = " + userDao.selectAll());
        }
    }
}
