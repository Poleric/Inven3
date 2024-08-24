package com.lavacorp.models;

import com.lavacorp.models.generic.DatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jdbi.v3.core.mapper.Nested;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PurchaseOrder extends DatabaseObj {
    @Nested("supplier") Supplier supplier;
    LocalDateTime purchaseDate;
    @Nullable LocalDateTime targetDate;
    @Nullable LocalDateTime arrivedDate;
    OrderStatus status;
    @Nullable String reference;
    Map<Stock, Integer> stocksPurchase;
}
