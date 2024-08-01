package com.lavacorp.entities.item;

import com.lavacorp.entities.DatabaseObj;
import com.lavacorp.entities.company.Suppliable;
import com.lavacorp.entities.company.Supplier;
import com.lavacorp.entities.tag.Tag;
import com.lavacorp.entities.tag.Taggable;
import com.lavacorp.entities.category.Category;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jdbi.v3.core.mapper.Nested;
import org.jetbrains.annotations.Nullable;

import javax.measure.Unit;
import javax.measure.format.UnitFormat;
import javax.measure.spi.ServiceProvider;
import java.time.Instant;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Item extends DatabaseObj implements Taggable, Suppliable {
    @NonNull private String name;
    @Nullable private String description;
    @Nullable private Double basePrice;
    @Nullable private Unit<?> unit;
    @Nullable @Nested private Category category;
    @Nullable private Instant createdAt;
    @Nullable private Instant lastUpdatedAt;

    public Item(@Nullable Integer id, @NonNull String name, @Nullable String description, @Nullable Double basePrice, @Nullable Unit<?> unit, @Nullable Category category, @Nullable Instant createdAt, @Nullable Instant lastUpdatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.unit = unit;
        this.category = category;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public void setUnit(@Nullable Unit<?> unit) {
        this.unit = unit;
    }

    public void setUnit(String unit) {
        UnitFormat uf = ServiceProvider.current().getFormatService().getUnitFormat();
        this.unit = uf.parse(unit);
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
