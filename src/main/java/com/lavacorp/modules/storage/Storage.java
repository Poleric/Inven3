package com.lavacorp.modules.storage;

import com.lavacorp.Item;

import java.util.ArrayList;

public interface Storage {
    void addItemInfo(String ... itemNames);
    void deleteItemInfo(int ... itemIds);
    Item queryItemInfo(int itemId);
    ArrayList<Item> queryItemInfo(String itemName);

    void addItem(int itemId, int quantity);
    void removeItem(int itemId, int quantity);
    int queryItemCount(int itemId);
}
