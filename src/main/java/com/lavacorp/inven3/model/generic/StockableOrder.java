package com.lavacorp.inven3.model.generic;

import com.lavacorp.inven3.model.Stock;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class StockableOrder extends Order {
    Map<Stock, Integer> stocks = new HashMap<>();
}
