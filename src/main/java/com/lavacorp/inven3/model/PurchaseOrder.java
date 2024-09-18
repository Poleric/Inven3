package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.Entity;
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
public class PurchaseOrder extends Entity {
    @Nested("supplier") Supplier supplier;
    LocalDateTime purchaseDate;
    @Nullable LocalDateTime targetDate;
    @Nullable LocalDateTime arrivedDate;
    OrderStatus status;
    @Nullable String reference;
    Map<Stock, Integer> stocksPurchase;
}
