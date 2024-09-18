package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SalesOrder extends Entity {
    LocalDateTime salesDate;
    @Nullable LocalDateTime shipmentDate;
    OrderStatus status;
    @Nullable String reference;
    Map<Stock, Integer> stocksSold;
}
