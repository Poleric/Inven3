package com.lavacorp.inven3.model.generic;

import org.jetbrains.annotations.Nullable;

public abstract class Order extends Entity {
    @Nullable String reference;
    OrderStatus status;

    public Order() {
    }

    public @Nullable String getReference() {
        return this.reference;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setReference(@Nullable String reference) {
        this.reference = reference;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String toString() {
        return "Order(reference=" + this.getReference() + ", status=" + this.getStatus() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Order)) return false;
        final Order other = (Order) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$reference = this.getReference();
        final Object other$reference = other.getReference();
        if (this$reference == null ? other$reference != null : !this$reference.equals(other$reference)) return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Order;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $reference = this.getReference();
        result = result * PRIME + ($reference == null ? 43 : $reference.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        return result;
    }

    public enum OrderStatus {
        FULFILLED,
        PENDING,
        IN_TRANSIT,
        REFUNDED
    }
}
