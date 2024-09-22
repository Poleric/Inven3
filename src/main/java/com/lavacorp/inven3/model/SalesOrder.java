package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.StockableOrder;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

public class SalesOrder extends StockableOrder {
    LocalDateTime salesDate;
    @Nullable LocalDateTime shipmentDate;
    @Nullable LocalDateTime arrivedDate;

    public SalesOrder() {
    }

    public LocalDateTime getSalesDate() {
        return this.salesDate;
    }

    public @Nullable LocalDateTime getShipmentDate() {
        return this.shipmentDate;
    }

    public @Nullable LocalDateTime getArrivedDate() {
        return this.arrivedDate;
    }

    public void setSalesDate(LocalDateTime salesDate) {
        this.salesDate = salesDate;
    }

    public void setShipmentDate(@Nullable LocalDateTime shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public void setArrivedDate(@Nullable LocalDateTime arrivedDate) {
        this.arrivedDate = arrivedDate;
    }

    public String toString() {
        return "SalesOrder(salesDate=" + this.getSalesDate() + ", shipmentDate=" + this.getShipmentDate() + ", arrivedDate=" + this.getArrivedDate() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SalesOrder)) return false;
        final SalesOrder other = (SalesOrder) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$salesDate = this.getSalesDate();
        final Object other$salesDate = other.getSalesDate();
        if (this$salesDate == null ? other$salesDate != null : !this$salesDate.equals(other$salesDate)) return false;
        final Object this$shipmentDate = this.getShipmentDate();
        final Object other$shipmentDate = other.getShipmentDate();
        if (this$shipmentDate == null ? other$shipmentDate != null : !this$shipmentDate.equals(other$shipmentDate))
            return false;
        final Object this$arrivedDate = this.getArrivedDate();
        final Object other$arrivedDate = other.getArrivedDate();
        if (this$arrivedDate == null ? other$arrivedDate != null : !this$arrivedDate.equals(other$arrivedDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SalesOrder;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $salesDate = this.getSalesDate();
        result = result * PRIME + ($salesDate == null ? 43 : $salesDate.hashCode());
        final Object $shipmentDate = this.getShipmentDate();
        result = result * PRIME + ($shipmentDate == null ? 43 : $shipmentDate.hashCode());
        final Object $arrivedDate = this.getArrivedDate();
        result = result * PRIME + ($arrivedDate == null ? 43 : $arrivedDate.hashCode());
        return result;
    }
}
