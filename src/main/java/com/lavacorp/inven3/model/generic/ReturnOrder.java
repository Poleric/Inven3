package com.lavacorp.inven3.model.generic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.mapper.Nested;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class ReturnOrder<T extends StockableOrder> extends Order {
    @Nested("order") T orderReturned;
    LocalDateTime returnDate;
}
