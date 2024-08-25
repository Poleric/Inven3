package com.lavacorp;

import com.lavacorp.db.Database;
import com.lavacorp.db.dao.SupplierDao;
import com.lavacorp.db.dao.UserDao;
import com.lavacorp.models.Supplier;
import com.lavacorp.models.User;
import lombok.extern.log4j.Log4j2;
import org.jdbi.v3.core.Handle;

@Log4j2
public class InventoryManagementSystem {
    public static void main(String[] args) {
        Database.getInstance().connect("test.db");
        LoginUI.DisplayUI();
        // Insert data
//        Supplier[] DATA = {
//                Supplier.builder()
//                        .id(1)
//                        .name("Samsen")
//                        .address("Samseng Factory, Road Samseng")
//                        .phoneNumber("0111 1111 1111")
//                        .email("support@samseng.com").build(),
//                Supplier.builder()
//                        .id(2)
//                        .name("Logitek")
//                        .address("Lausanne, Switzerland")
//                        .phoneNumber("+1 646-454-3200")
//                        .email("support@logitek.com").build()
//        };
//
//        SupplierDao dao = Database.getJdbi().onDemand(SupplierDao.class);
//        for (Supplier supplier : DATA) {
//            dao.insert(supplier);
//        }

//        DataManagement.viewSupplier();

    }

}
