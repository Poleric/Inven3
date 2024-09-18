package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReturnOrder extends Order {
    Order orderReturned;
    LocalDateTime returnDate;
}
