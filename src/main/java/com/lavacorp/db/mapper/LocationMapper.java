package com.lavacorp.db.mapper;

import com.lavacorp.entities.Location;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Location> {
    @Override
    public Location map(ResultSet rs, StatementContext ctx) throws SQLException {
        return Location.builder()
                .id(rs.getObject("id", Integer.class))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .build();
    }
}
