package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.NamedDaoTests;
import com.lavacorp.inven3.model.Location;
import org.springframework.beans.factory.annotation.Autowired;

public class LocationDaoTests extends NamedDaoTests<Location, LocationDao> {
    @Autowired
    public LocationDaoTests(LocationDao dao) {
        super(dao);
    }

}
