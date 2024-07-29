package com.lavacorp.entities.item;

import com.lavacorp.entities.company.Suppliable;
import com.lavacorp.entities.company.Supplier;
import com.lavacorp.entities.tag.Tag;
import com.lavacorp.entities.tag.Taggable;
import com.lavacorp.entities.category.Category;

import lombok.Data;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import javax.measure.Unit;
import javax.measure.format.UnitFormat;
import javax.measure.spi.ServiceProvider;
import java.time.Instant;
import java.util.List;

@Data
public class Item implements Taggable, Suppliable {
    @Nullable private Integer id;
    @NonNull private String name;
    @Nullable private String description;
    @Nullable private Double basePrice;
    @Nullable private Unit<?> unit;
    @Nullable private Category category;
    @Nullable private Instant createdAt;
    @Nullable private Instant lastUpdatedAt;

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
