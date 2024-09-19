package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.StockableOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SalesOrder extends StockableOrder {
    LocalDateTime salesDate;
    @Nullable LocalDateTime shipmentDate;
    @Nullable LocalDateTime arrivedDate;
}
