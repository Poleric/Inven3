package com.lavacorp.entities;

import com.lavacorp.entities.generic.DatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.jdbi.v3.core.mapper.Nested;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Stock extends DatabaseObj {
    @Nested("item") @NonNull private Item item;
    @Nested("supplier") @Nullable private Supplier supplier;
    @Nested("location") @Nullable private Location location;
    private int quantity;
    @NonNull private StockStatus status;
    @Nullable private LocalDate expiryDate;
    @Nullable private String notes;
    @EqualsAndHashCode.Exclude @Nullable private Instant createdAt;
    @EqualsAndHashCode.Exclude @Nullable private Instant lastUpdatedAt;
}
