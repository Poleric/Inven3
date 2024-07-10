package com.lavacorp.inventory;

import com.lavacorp.item.Item;

public interface Inventory {
     void addStock(Item item, int quantity);
     void removeStock(Item item, int quantity);

     int queryStockAmount(Item item);

}
