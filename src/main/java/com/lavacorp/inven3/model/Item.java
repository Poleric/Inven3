package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.NamedEntity;
import org.jdbi.v3.core.mapper.Nested;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;

public class Item extends NamedEntity {
    @Nullable
    private String description;
    @Nullable
    private Double basePrice;
    @Nullable
    private String unit;
    @Nullable
    @Nested("category")
    private Category category;
    @Nullable
    private Integer minStock;

    @Nullable
    private Instant createdAt;
    @Nullable
    private Instant lastUpdatedAt;

    public Item() {
    }

    public @Nullable String getDescription() {
        return this.description;
    }

    public @Nullable Double getBasePrice() {
        return this.basePrice;
    }

    public @Nullable String getUnit() {
        return this.unit;
    }

    @Nested("category")
    public @Nullable Category getCategory() {
        return this.category;
    }

    public @Nullable Integer getMinStock() {
        return this.minStock;
    }

    public @Nullable Instant getCreatedAt() {
        return this.createdAt;
    }

    public @Nullable Instant getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public void setBasePrice(@Nullable Double basePrice) {
        this.basePrice = basePrice;
    }

    public void setUnit(@Nullable String unit) {
        this.unit = unit;
    }

    public void setCategory(@Nested("category") @Nullable Category category) {
        this.category = category;
    }

    public void setMinStock(@Nullable Integer minStock) {
        this.minStock = minStock;
    }

    public void setCreatedAt(@Nullable Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setLastUpdatedAt(@Nullable Instant lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public String toString() {
        return "Item(description=" + this.getDescription() + ", basePrice=" + this.getBasePrice() + ", unit=" + this.getUnit() + ", category=" + this.getCategory() + ", minStock=" + this.getMinStock() + ", createdAt=" + this.getCreatedAt() + ", lastUpdatedAt=" + this.getLastUpdatedAt() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Item)) return false;
        final Item other = (Item) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$basePrice = this.getBasePrice();
        final Object other$basePrice = other.getBasePrice();
        if (this$basePrice == null ? other$basePrice != null : !this$basePrice.equals(other$basePrice)) return false;
        final Object this$unit = this.getUnit();
        final Object other$unit = other.getUnit();
        if (this$unit == null ? other$unit != null : !this$unit.equals(other$unit)) return false;
        final Object this$category = this.getCategory();
        final Object other$category = other.getCategory();
        if (this$category == null ? other$category != null : !this$category.equals(other$category)) return false;
        final Object this$minStock = this.getMinStock();
        final Object other$minStock = other.getMinStock();
        if (this$minStock == null ? other$minStock != null : !this$minStock.equals(other$minStock)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Item;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $basePrice = this.getBasePrice();
        result = result * PRIME + ($basePrice == null ? 43 : $basePrice.hashCode());
        final Object $unit = this.getUnit();
        result = result * PRIME + ($unit == null ? 43 : $unit.hashCode());
        final Object $category = this.getCategory();
        result = result * PRIME + ($category == null ? 43 : $category.hashCode());
        final Object $minStock = this.getMinStock();
        result = result * PRIME + ($minStock == null ? 43 : $minStock.hashCode());
        return result;
    }
}
