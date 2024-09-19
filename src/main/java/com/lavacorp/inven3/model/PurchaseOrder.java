package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.mapper.Nested;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PurchaseOrder extends Order {
    @Nested("supplier") Supplier supplier;
    LocalDateTime purchaseDate;
    @Nullable LocalDateTime targetDate;
    @Nullable LocalDateTime arrivedDate;
    Map<Stock, Integer> stocks;
}
