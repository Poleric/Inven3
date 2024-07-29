package com.lavacorp.mapper;

import com.lavacorp.entities.category.Category;
import com.lavacorp.entities.item.Item;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class ItemMapper implements RowMapper<Item> {

    @Override
    public Item map(ResultSet rs, StatementContext ctx) throws SQLException {
        Item item = new Item(rs.getString("item.name"));
        item.setId(rs.getObject("item.id", Integer.class));
        item.setDescription(rs.getString("item.description"));
        item.setBasePrice(rs.getObject("item.base_price", Double.class));
        item.setUnit(rs.getString("item.unit"));

        Category category = new Category(rs.getString("category.name"));
        category.setDescription(rs.getString("category.description"));
        item.setCategory(category);

        item.setCreatedAt(rs.getObject("item.created_at", Instant.class));
        item.setLastUpdatedAt(rs.getObject("item.last_updated_at", Instant.class));
        return item;
    }
}

