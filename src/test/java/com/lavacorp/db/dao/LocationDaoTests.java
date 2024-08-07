package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoNamedTest;
import com.lavacorp.entities.Location;

public class LocationDaoTests extends DaoNamedTest<Location, LocationDao> {
//    static final Location[] DATA = {
//            new Location(1, "Aisle 1", null),
//            new Location(2, "Aisle 2", null),
//            new Location(3, "Aisle 3", null),
//            new Location(4, "Aisle 4", null),
//            new Location(5, "Aisle 5", null),
//            new Location(6, "Aisle 6", null),
//            new Location(7, "Counter", null)
//    };
    static final Location[] DATA = {
        Location.builder().id(1).name("Aisle 1").build(),
        Location.builder().id(2).name("Aisle 2").build(),
        Location.builder().id(3).name("Aisle 3").build(),
        Location.builder().id(4).name("Aisle 4").build(),
        Location.builder().id(5).name("Aisle 5").build(),
        Location.builder().id(6).name("Aisle 6").build(),
        Location.builder().id(7).name("Counter").build()
    };

    public LocationDaoTests() {
        super(LocationDao.class, DATA, null);
    }
}
