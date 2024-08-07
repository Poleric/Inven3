package com.lavacorp.db.dao;

import com.lavacorp.entities.Location;

public class LocationDaoTests extends DaoNamedTest<Location, LocationDao> {
    static final Location[] DATA = {
            new Location(1, "Aisle 1", null),
            new Location(2, "Aisle 2", null),
            new Location(3, "Aisle 3", null),
            new Location(4, "Aisle 4", null),
            new Location(5, "Aisle 5", null),
            new Location(6, "Aisle 6", null),
            new Location(7, "Counter", null)
    };

    public LocationDaoTests() {
        super(LocationDao.class, DATA, null);
    }
}
