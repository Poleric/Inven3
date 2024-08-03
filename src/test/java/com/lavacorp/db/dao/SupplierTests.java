package com.lavacorp.db.dao;

import com.lavacorp.entities.company.Supplier;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SupplierTests extends DaoTest<Supplier, SupplierDao> {
    public static final Supplier[] DATA = {
            new Supplier(1, "Samseng", "Samseng factory, Road Samseng.", "0111 1111 1111", "support@samseng.com", null),
            new Supplier(2, "Logitek", "Lausanne, Switzerland", "+1 646-454-3200", "support@logitek.com", null),
    };
    public static final Class<SupplierDao> DAO = SupplierDao.class;

    public SupplierTests() {
        super(DATA, DAO);
    }


    @Test
    @Order(1)
    void testRetrieveByName() {
        SupplierDao dao = getDao();

        for (Supplier expected : DATA) {
            Supplier actual = dao.retrieve(expected.getName());

            assertEquals(expected, actual);
        }
    }

    @Test
    @Order(2)
    void testUpdateName() {
        SupplierDao dao = getDao();

        Supplier expected = DATA[0];
        assertNotNull(expected.getId());

        String before = expected.getName();
        expected.setName("AAAAA");

        dao.update(expected);

        Supplier actual = dao.retrieve(expected.getId());
        assertEquals(expected, actual);

        expected.setName(before);
        dao.update(expected);
    }

    @Test
    @Order(3)
    void testDeleteByName() {
        SupplierDao dao = getDao();

        dao.delete("Logitek");
        assertNull(dao.retrieve("Logitek"));
    }
}
