package com.lavacorp.db.mapper;

import com.lavacorp.entities.Location;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Location> {
    @Override
    public Location map(ResultSet rs, StatementContext ctx) throws SQLException {
        Location location = new Location();
        location.setId(rs.getObject("id", Integer.class));
        location.setName(rs.getString("name"));
        location.setDescription(rs.getString("description"));
        return location;
    }
}
