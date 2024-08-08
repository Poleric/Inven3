package com.lavacorp.db.mapper;

import com.lavacorp.entities.Category;
import com.lavacorp.entities.Item;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements RowMapper<Item> {

    @Override
    public Item map(ResultSet rs, StatementContext ctx) throws SQLException {
        return Item.builder()
                .id(rs.getInt("item_id"))
                .name(rs.getString("item_name"))
                .description(rs.getString("item_description"))
                .basePrice(rs.getObject("item_base_price", Double.class))
                .unit(rs.getString("item_unit"))
                .category(BeanMapper.of(Category.class, "category").map(rs, ctx))
                .createdAt(rs.getTimestamp("item_created_at").toInstant())
                .lastUpdatedAt(rs.getTimestamp("item_last_updated_at").toInstant())
                .build();
    }
}

