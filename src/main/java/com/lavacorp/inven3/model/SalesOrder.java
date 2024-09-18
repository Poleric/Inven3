package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SalesOrder extends Order {
    LocalDateTime salesDate;
    @Nullable LocalDateTime shipmentDate;
    @Nullable LocalDateTime arrivedDate;
    Map<Stock, Integer> stocksSold;
}
