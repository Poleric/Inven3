package com.lavacorp.entities.item;

import com.lavacorp.entities.company.Suppliable;
import com.lavacorp.entities.company.Supplier;
import com.lavacorp.entities.tag.Tag;
import com.lavacorp.entities.tag.Taggable;
import com.lavacorp.entities.category.Category;

import lombok.Data;
import org.jdbi.v3.core.mapper.Nested;
import org.jetbrains.annotations.Nullable;

import javax.measure.Unit;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Item implements Taggable, Suppliable {
    private final int id;
    private String name;
    @Nullable private String description;
    private double basePrice;
    @Nested @Nullable private Unit<?> unit;
    @Nested @Nullable private Category category;
    @Nullable private LocalDateTime createdAt;
    @Nullable private LocalDateTime lastUpdatedAt;

    @Nullable List<Tag> tags;
    @Nullable private List<Supplier> suppliers;
}
