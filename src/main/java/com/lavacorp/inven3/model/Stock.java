package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.Entity;
import org.jdbi.v3.core.mapper.Nested;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.time.LocalDate;

public class Stock extends Entity {
    @Nested("item")
    private Item item;
    @Nested("supplier")
    @Nullable
    private Supplier supplier;
    @Nested("location")
    @Nullable
    private Location location;
    private int quantity;
    private StockStatus status;
    @Nullable
    private LocalDate expiryDate;
    @Nullable
    private String notes;
    @Nullable
    private Instant createdAt;
    @Nullable
    private Instant lastUpdatedAt;

    public Stock() {
    }

    @Nested("item")
    public Item getItem() {
        return this.item;
    }

    @Nested("supplier")
    public @Nullable Supplier getSupplier() {
        return this.supplier;
    }

    @Nested("location")
    public @Nullable Location getLocation() {
        return this.location;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public StockStatus getStatus() {
        return this.status;
    }

    public @Nullable LocalDate getExpiryDate() {
        return this.expiryDate;
    }

    public @Nullable String getNotes() {
        return this.notes;
    }

    public @Nullable Instant getCreatedAt() {
        return this.createdAt;
    }

    public @Nullable Instant getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public void setItem(@Nested("item") Item item) {
        this.item = item;
    }

    public void setSupplier(@Nested("supplier") @Nullable Supplier supplier) {
        this.supplier = supplier;
    }

    public void setLocation(@Nested("location") @Nullable Location location) {
        this.location = location;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(@NotNull StockStatus status) {
        this.status = status;
    }

    public void setExpiryDate(@Nullable LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setNotes(@Nullable String notes) {
        this.notes = notes;
    }

    public void setCreatedAt(@Nullable Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setLastUpdatedAt(@Nullable Instant lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public String toString() {
        return "Stock(item=" + this.getItem() + ", supplier=" + this.getSupplier() + ", location=" + this.getLocation() + ", quantity=" + this.getQuantity() + ", status=" + this.getStatus() + ", expiryDate=" + this.getExpiryDate() + ", notes=" + this.getNotes() + ", createdAt=" + this.getCreatedAt() + ", lastUpdatedAt=" + this.getLastUpdatedAt() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Stock)) return false;
        final Stock other = (Stock) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$item = this.getItem();
        final Object other$item = other.getItem();
        if (this$item == null ? other$item != null : !this$item.equals(other$item)) return false;
        final Object this$supplier = this.getSupplier();
        final Object other$supplier = other.getSupplier();
        if (this$supplier == null ? other$supplier != null : !this$supplier.equals(other$supplier)) return false;
        final Object this$location = this.getLocation();
        final Object other$location = other.getLocation();
        if (this$location == null ? other$location != null : !this$location.equals(other$location)) return false;
        if (this.getQuantity() != other.getQuantity()) return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$expiryDate = this.getExpiryDate();
        final Object other$expiryDate = other.getExpiryDate();
        if (this$expiryDate == null ? other$expiryDate != null : !this$expiryDate.equals(other$expiryDate))
            return false;
        final Object this$notes = this.getNotes();
        final Object other$notes = other.getNotes();
        if (this$notes == null ? other$notes != null : !this$notes.equals(other$notes)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Stock;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $item = this.getItem();
        result = result * PRIME + ($item == null ? 43 : $item.hashCode());
        final Object $supplier = this.getSupplier();
        result = result * PRIME + ($supplier == null ? 43 : $supplier.hashCode());
        final Object $location = this.getLocation();
        result = result * PRIME + ($location == null ? 43 : $location.hashCode());
        result = result * PRIME + this.getQuantity();
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $expiryDate = this.getExpiryDate();
        result = result * PRIME + ($expiryDate == null ? 43 : $expiryDate.hashCode());
        final Object $notes = this.getNotes();
        result = result * PRIME + ($notes == null ? 43 : $notes.hashCode());
        return result;
    }

    public enum StockStatus {
        OK,
        DAMAGED,
        REJECTED,
        LOST,
        RETURNED,
        IN_TRANSIT
    }
}
