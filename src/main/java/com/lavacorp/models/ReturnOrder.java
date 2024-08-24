package com.lavacorp.models;

import com.lavacorp.models.generic.DatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReturnOrder extends DatabaseObj {
    SalesOrder salesOrder;
    LocalDateTime returnDate;
    OrderStatus status;
    @Nullable String reference;
}
