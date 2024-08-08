package com.lavacorp.entities;

import com.lavacorp.entities.generic.NamedDatabaseObj;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jdbi.v3.core.mapper.Nested;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Item extends NamedDatabaseObj implements Taggable, Suppliable {
    @Nullable private String description;
    @Nullable private Double basePrice;
    @Nullable private String unit;
    @Nullable @Nested("category") private Category category;

    @EqualsAndHashCode.Exclude @Nullable private Instant createdAt;
    @EqualsAndHashCode.Exclude @Nullable private Instant lastUpdatedAt;

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
