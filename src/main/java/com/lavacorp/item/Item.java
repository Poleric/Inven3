package com.lavacorp.item;

public class Item {
    public String name;
    public int id;
    public double purchasePrice;
    public double salePrice;
    public ItemType type;

    public Item(String name, int id, double purchasePrice, double salePrice, ItemType type) {
        this.name = name;
        this.id = id;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.type = type;
    }
}
