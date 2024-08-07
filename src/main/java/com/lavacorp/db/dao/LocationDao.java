package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoNamed;
import com.lavacorp.db.mapper.LocationMapper;
import com.lavacorp.entities.Location;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;

@RegisterRowMapper(LocationMapper.class)
public interface LocationDao extends DaoNamed<Location> {
}
