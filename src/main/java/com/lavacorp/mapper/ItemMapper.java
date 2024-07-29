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
        Item item = new Item(rs.getString("item_name"));
        item.setId(rs.getObject("item_id", Integer.class));
        item.setDescription(rs.getString("item_description"));
        item.setBasePrice(rs.getObject("item_base_price", Double.class));
        item.setUnit(rs.getString("item_unit"));

        Category category = new Category(rs.getString("category_name"));
        category.setDescription(rs.getString("category_description"));
        item.setCategory(category);

        item.setCreatedAt(rs.getObject("item_created_at", Instant.class));
        item.setLastUpdatedAt(rs.getObject("item_last_updated_at", Instant.class));
        return item;
    }
}

