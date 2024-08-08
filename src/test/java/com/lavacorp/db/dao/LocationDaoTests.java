package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoNamedTest;
import com.lavacorp.entities.Location;

public class LocationDaoTests extends DaoNamedTest<Location, LocationDao> {
    static final Location[] DATA = {
        Location.builder().id(1).name("Storage").build(),
        Location.builder().id(2).name("Aisle 1").build(),
        Location.builder().id(3).name("Aisle 2").build(),
        Location.builder().id(4).name("Aisle 3").build(),
        Location.builder().id(5).name("Aisle 4").build(),
        Location.builder().id(6).name("Aisle 5").build(),
        Location.builder().id(7).name("Counter").build()
    };

    public LocationDaoTests() {
        super(LocationDao.class, DATA, null);
    }
}
