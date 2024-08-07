package com.lavacorp.entities;

import com.lavacorp.entities.company.Suppliable;
import com.lavacorp.entities.company.Supplier;
import com.lavacorp.entities.generic.NamedDatabaseObj;
import com.lavacorp.entities.tag.Tag;
import com.lavacorp.entities.tag.Taggable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jdbi.v3.core.mapper.Nested;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Item extends NamedDatabaseObj implements Taggable, Suppliable {
    @Nullable private String description;
    @Nullable private Double basePrice;
    @Nullable private String unit;
    @Nullable @Nested private Category category;
    @Nullable private Instant createdAt;
    @Nullable private Instant lastUpdatedAt;

    public Item(@Nullable Integer id, @NonNull String name, @Nullable String description, @Nullable Double basePrice, @Nullable String unit, @Nullable Category category, @Nullable Instant createdAt, @Nullable Instant lastUpdatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.unit = unit;
        this.category = category;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(name, item.name) && Objects.equals(description, item.description) && Objects.equals(basePrice, item.basePrice) && Objects.equals(unit, item.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, basePrice, unit, category, createdAt, lastUpdatedAt);
    }

    @Override
    public List<Supplier> getSuppliers() {
        return List.of();
    }

    @Override
    public void addSupplier(Supplier supplier) {

    }

    @Override
    public void removeSupplier(Supplier supplier) {

    }

    @Override
    public List<Tag> getTags() {
        return List.of();
    }

    @Override
    public void addTag(Tag tag) {

    }

    @Override
    public void removeTag(Tag tag) {

    }
}
