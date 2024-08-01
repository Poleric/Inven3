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
        Category category = new Category(
                rs.getObject("category_id", Integer.class),
                rs.getString("category_name"),
                rs.getString("category_description")
        );

        return new Item(
                rs.getObject("item_id", Integer.class),
                rs.getString("item_name"),
                rs.getString("item_description"),
                rs.getObject("item_base_price", Double.class),
                rs.getString("item_unit"),
                category,
                rs.getObject("item_created_at", Instant.class),
                rs.getObject("item_last_updated_at", Instant.class)
        );
    }
}

