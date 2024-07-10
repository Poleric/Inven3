package com.lavacorp.item;

public class Item {
    String name;
    int id;

    double price;
    ItemType type;

    public Item(String name, int id, double price, ItemType type) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.type = type;
    }
}
