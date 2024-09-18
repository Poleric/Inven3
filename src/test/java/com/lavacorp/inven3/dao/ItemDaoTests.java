package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.NamedDaoTests;
import com.lavacorp.inven3.model.Item;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemDaoTests extends NamedDaoTests<Item, ItemDao> {

    @Autowired
    public ItemDaoTests(ItemDao dao) {
        super(dao);
    }

}
