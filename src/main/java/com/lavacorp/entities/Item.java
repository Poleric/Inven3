package com.lavacorp.entities;

import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public class Item implements Serializable {
    @ColumnName("id") @Nullable private Integer id;
    @ColumnName("name") private String name;
    @ColumnName("description") @Nullable private String description;
    @ColumnName("base_price") private float basePrice;

    public Item() {}

    public Item(String name, @Nullable String description, float basePrice) {
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
    }

    public Item(@Nullable Integer id, String name, @Nullable String description, float basePrice) {
        this(name, description, basePrice);
        this.id = id;
    }

    public @Nullable Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @Nullable String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
