package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.ReturnOrder;

public class PurchaseOrderReturn extends ReturnOrder<PurchaseOrder> {
    public PurchaseOrderReturn() {
    }

    public String toString() {
        return "PurchaseOrderReturn()";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PurchaseOrderReturn)) return false;
        final PurchaseOrderReturn other = (PurchaseOrderReturn) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PurchaseOrderReturn;
    }

    public int hashCode() {
        int result = super.hashCode();
        return result;
    }
}
