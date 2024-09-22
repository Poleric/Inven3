package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.StockableOrder;
import org.jdbi.v3.core.mapper.Nested;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

public class PurchaseOrder extends StockableOrder {
    @Nested("supplier")
    Supplier supplier;
    LocalDateTime purchaseDate;
    @Nullable LocalDateTime targetDate;
    @Nullable LocalDateTime arrivedDate;

    public PurchaseOrder() {
    }

    @Nested("supplier")
    public Supplier getSupplier() {
        return this.supplier;
    }

    public LocalDateTime getPurchaseDate() {
        return this.purchaseDate;
    }

    public @Nullable LocalDateTime getTargetDate() {
        return this.targetDate;
    }

    public @Nullable LocalDateTime getArrivedDate() {
        return this.arrivedDate;
    }

    public void setSupplier(@Nested("supplier") Supplier supplier) {
        this.supplier = supplier;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setTargetDate(@Nullable LocalDateTime targetDate) {
        this.targetDate = targetDate;
    }

    public void setArrivedDate(@Nullable LocalDateTime arrivedDate) {
        this.arrivedDate = arrivedDate;
    }

    public String toString() {
        return "PurchaseOrder(supplier=" + this.getSupplier() + ", purchaseDate=" + this.getPurchaseDate() + ", targetDate=" + this.getTargetDate() + ", arrivedDate=" + this.getArrivedDate() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PurchaseOrder)) return false;
        final PurchaseOrder other = (PurchaseOrder) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$supplier = this.getSupplier();
        final Object other$supplier = other.getSupplier();
        if (this$supplier == null ? other$supplier != null : !this$supplier.equals(other$supplier)) return false;
        final Object this$purchaseDate = this.getPurchaseDate();
        final Object other$purchaseDate = other.getPurchaseDate();
        if (this$purchaseDate == null ? other$purchaseDate != null : !this$purchaseDate.equals(other$purchaseDate))
            return false;
        final Object this$targetDate = this.getTargetDate();
        final Object other$targetDate = other.getTargetDate();
        if (this$targetDate == null ? other$targetDate != null : !this$targetDate.equals(other$targetDate))
            return false;
        final Object this$arrivedDate = this.getArrivedDate();
        final Object other$arrivedDate = other.getArrivedDate();
        if (this$arrivedDate == null ? other$arrivedDate != null : !this$arrivedDate.equals(other$arrivedDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PurchaseOrder;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $supplier = this.getSupplier();
        result = result * PRIME + ($supplier == null ? 43 : $supplier.hashCode());
        final Object $purchaseDate = this.getPurchaseDate();
        result = result * PRIME + ($purchaseDate == null ? 43 : $purchaseDate.hashCode());
        final Object $targetDate = this.getTargetDate();
        result = result * PRIME + ($targetDate == null ? 43 : $targetDate.hashCode());
        final Object $arrivedDate = this.getArrivedDate();
        result = result * PRIME + ($arrivedDate == null ? 43 : $arrivedDate.hashCode());
        return result;
    }
}
