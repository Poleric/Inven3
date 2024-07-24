package com.lavacorp.entities.item;

import com.lavacorp.entities.tag.Taggable;
import com.lavacorp.entities.item.category.Category;

import lombok.Data;
import org.jdbi.v3.core.mapper.Nested;
import org.jetbrains.annotations.Nullable;

import javax.measure.Unit;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Item extends Taggable implements Serializable {
    private final int id;
    private String name;
    @Nullable private String description;
    private double basePrice;
    @Nested @Nullable private Unit<?> unit;
    @Nested @Nullable private Category category;
    @Nullable private LocalDateTime createdAt;
    @Nullable private LocalDateTime lastUpdatedAt;
}
