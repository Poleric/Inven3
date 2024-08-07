package com.lavacorp.db.dao;

import com.lavacorp.entities.Location;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LocationDaoTests extends DaoTest<Location, LocationDao> {
    public static final Location[] DATA = {
            new Location(1, "Aisle 1", null),
            new Location(2, "Aisle 2", null),
            new Location(3, "Aisle 3", null),
            new Location(4, "Aisle 4", null),
            new Location(5, "Aisle 5", null),
            new Location(6, "Aisle 6", null),
            new Location(7, "Counter", null)
    };
    public static final Class<LocationDao> DAO = LocationDao.class;

    public LocationDaoTests() {
        super(DATA, DAO);
    }

    @Test
    @Order(1)
    void testRetrieveByName() {
        LocationDao dao = getDao();

        for (Location expected : DATA) {
            Location actual = dao.retrieve(expected.getName());

            assertEquals(expected, actual);
        }
    }

    @Test
    @Order(2)
    void testUpdateName() {
        LocationDao dao = getDao();

        Location expected = DATA[0];
        assertNotNull(expected.getId());

        String before = expected.getName();
        expected.setName("AAAAA");

        dao.update(expected);

        Location actual = dao.retrieve(expected.getId());
        assertEquals(expected, actual);

        expected.setName(before);
        dao.update(expected);
    }

    @Test
    @Order(3)
    void testDeleteByName() {
        LocationDao dao = getDao();

        dao.delete("Aisle 1");
        assertNull(dao.retrieve("Aisle 1"));
    }
}
