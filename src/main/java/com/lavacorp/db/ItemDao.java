package com.lavacorp.db;

import com.lavacorp.entities.item.Item;
import com.lavacorp.mapper.ItemMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;

@RegisterRowMapper(ItemMapper.class)
public interface ItemDao extends DaoNamed<Item> {
}