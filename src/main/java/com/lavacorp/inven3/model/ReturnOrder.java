package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.mapper.Nested;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReturnOrder extends Order {
    @Nested("order") Order orderReturned;
    LocalDateTime returnDate;
}
