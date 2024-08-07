package com.lavacorp.db.mapper;

import com.lavacorp.entities.Supplier;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierMapper implements RowMapper<Supplier> {
    @Override
    public Supplier map(ResultSet rs, StatementContext ctx) throws SQLException {
        return Supplier.builder()
                .id(rs.getObject("id", Integer.class))
                .name(rs.getString("name"))
                .address(rs.getString("address"))
                .phoneNumber(rs.getString("phone_number"))
                .email(rs.getString("email"))
                .build();
    }
}
