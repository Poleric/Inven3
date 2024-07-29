package com.lavacorp.entities.item;

import com.lavacorp.entities.company.Suppliable;
import com.lavacorp.entities.company.Supplier;
import com.lavacorp.entities.tag.Tag;
import com.lavacorp.entities.tag.Taggable;
import com.lavacorp.entities.category.Category;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

import javax.measure.Unit;
import java.time.Instant;
import java.util.List;

@Data
public class Item implements Taggable, Suppliable {
    private final int id;
    private String name;
    @Nullable private String description;
    private double basePrice;
    @Nullable private Unit<?> unit;
    @Nullable private Category category;
    @Nullable private Instant createdAt;
    @Nullable private Instant lastUpdatedAt;

    @Nullable List<Tag> tags;
    @Nullable private List<Supplier> suppliers;
}
