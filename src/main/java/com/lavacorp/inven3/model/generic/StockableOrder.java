package com.lavacorp.inven3.model.generic;

import com.lavacorp.inven3.model.Stock;

import java.util.HashMap;
import java.util.Map;

public abstract class StockableOrder extends Order {
    Map<Stock, Integer> stocks = new HashMap<>();

    public StockableOrder() {
    }

    public Map<Stock, Integer> getStocks() {
        return this.stocks;
    }

    public void setStocks(Map<Stock, Integer> stocks) {
        this.stocks = stocks;
    }

    public String toString() {
        return "StockableOrder(stocks=" + this.getStocks() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof StockableOrder)) return false;
        final StockableOrder other = (StockableOrder) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$stocks = this.getStocks();
        final Object other$stocks = other.getStocks();
        if (this$stocks == null ? other$stocks != null : !this$stocks.equals(other$stocks)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof StockableOrder;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $stocks = this.getStocks();
        result = result * PRIME + ($stocks == null ? 43 : $stocks.hashCode());
        return result;
    }
}
