package com.lavacorp.db.mapper;

import com.lavacorp.entities.company.Supplier;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierMapper implements RowMapper<Supplier> {
    @Override
    public Supplier map(ResultSet rs, StatementContext ctx) throws SQLException {
        Supplier supplier = new Supplier();
        supplier.setId(rs.getObject("id", Integer.class));
        supplier.setName(rs.getString("name"));
        supplier.setAddress(rs.getString("address"));
        supplier.setPhoneNumber(rs.getString("phone_number"));
        supplier.setEmail(rs.getString("email"));
        return supplier;
    }
}
