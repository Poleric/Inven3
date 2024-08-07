package com.lavacorp.db.mapper;

import com.lavacorp.entities.Category;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category map(ResultSet rs, StatementContext ctx) throws SQLException {
        return Category.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .build();
    }
}
