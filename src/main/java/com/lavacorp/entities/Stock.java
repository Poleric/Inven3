package com.lavacorp.entities;

import com.lavacorp.entities.generic.DatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class Stock extends DatabaseObj {
    private Item item;
    @Nullable private Supplier supplier;
    @Nullable private Location location;
    private int quantity;
    private StockStatus status;
    @Nullable private LocalDateTime expiryDate;
    @Nullable private String notes;
    @Nullable private LocalDateTime createdAt;
    @Nullable private LocalDateTime lastUpdatedAt;
}
