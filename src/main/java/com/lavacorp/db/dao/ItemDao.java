package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoNamed;
import com.lavacorp.entities.Item;
import com.lavacorp.db.mapper.ItemMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;

@RegisterRowMapper(ItemMapper.class)
public interface ItemDao extends DaoNamed<Item> {
}