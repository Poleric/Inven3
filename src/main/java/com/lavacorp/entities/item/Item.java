package com.lavacorp.entities.item;

import com.lavacorp.entities.item.category.Category;
import com.lavacorp.entities.tag.Tag;
import lombok.Data;
import org.jdbi.v3.core.mapper.Nested;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jetbrains.annotations.Nullable;

import javax.measure.Unit;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Item implements Serializable {
    @ColumnName("id")
    private final int id;
    @ColumnName("name")
    private String name;
    @ColumnName("description") @Nullable
    private String description;
    @ColumnName("base_price")
    private double basePrice;
    @ColumnName("created_at") @Nullable
    private LocalDateTime createdAt;
    @ColumnName("last_update_at") @Nullable
    private LocalDateTime lastUpdatedAt;
    @ColumnName("category") @Nested @Nullable
    private Category category;

    @ColumnName("unit") @Nested @Nullable
    private Unit<?> unit;
    @Nullable
    private List<Tag> tags;
}
