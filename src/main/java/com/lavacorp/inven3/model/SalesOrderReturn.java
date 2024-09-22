package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.ReturnOrder;

public class SalesOrderReturn extends ReturnOrder<SalesOrder> {
    public SalesOrderReturn() {
    }

    public String toString() {
        return "SalesOrderReturn()";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SalesOrderReturn)) return false;
        final SalesOrderReturn other = (SalesOrderReturn) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SalesOrderReturn;
    }

    public int hashCode() {
        int result = super.hashCode();
        return result;
    }
}
