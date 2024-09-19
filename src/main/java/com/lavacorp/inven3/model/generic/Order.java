package com.lavacorp.inven3.model.generic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class Order extends Entity {
    @Nullable String reference;
    OrderStatus status;

    public enum OrderStatus {
        FULFILLED,
        PENDING,
        IN_TRANSIT,
        REFUNDED
    }
}
