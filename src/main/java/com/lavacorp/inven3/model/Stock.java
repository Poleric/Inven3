package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jdbi.v3.core.mapper.Nested;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Stock extends Entity {
    @Nested("item") @NonNull private Item item;
    @Nested("supplier") @Nullable private Supplier supplier;
    @Nested("location") @Nullable private Location location;
    private int quantity;
    @NonNull private StockStatus status;
    @Nullable private LocalDate expiryDate;
    @Nullable private String notes;
    @EqualsAndHashCode.Exclude @Nullable private Instant createdAt;
    @EqualsAndHashCode.Exclude @Nullable private Instant lastUpdatedAt;

    public enum StockStatus {
        OK,
        DAMAGED,
        REJECTED,
        LOST,
        RETURNED,
        IN_TRANSIT
    }
}
