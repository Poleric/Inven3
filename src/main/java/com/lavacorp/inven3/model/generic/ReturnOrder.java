package com.lavacorp.inven3.model.generic;

import org.jdbi.v3.core.mapper.Nested;

import java.time.LocalDateTime;

public abstract class ReturnOrder<T extends StockableOrder> extends Order {
    @Nested("order")
    T orderReturned;
    LocalDateTime returnDate;

    public ReturnOrder() {
    }

    @Nested("order")
    public T getOrderReturned() {
        return this.orderReturned;
    }

    public LocalDateTime getReturnDate() {
        return this.returnDate;
    }

    public void setOrderReturned(@Nested("order") T orderReturned) {
        this.orderReturned = orderReturned;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public String toString() {
        return "ReturnOrder(orderReturned=" + this.getOrderReturned() + ", returnDate=" + this.getReturnDate() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ReturnOrder)) return false;
        final ReturnOrder<?> other = (ReturnOrder<?>) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$orderReturned = this.getOrderReturned();
        final Object other$orderReturned = other.getOrderReturned();
        if (this$orderReturned == null ? other$orderReturned != null : !this$orderReturned.equals(other$orderReturned))
            return false;
        final Object this$returnDate = this.getReturnDate();
        final Object other$returnDate = other.getReturnDate();
        if (this$returnDate == null ? other$returnDate != null : !this$returnDate.equals(other$returnDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ReturnOrder;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $orderReturned = this.getOrderReturned();
        result = result * PRIME + ($orderReturned == null ? 43 : $orderReturned.hashCode());
        final Object $returnDate = this.getReturnDate();
        result = result * PRIME + ($returnDate == null ? 43 : $returnDate.hashCode());
        return result;
    }
}
