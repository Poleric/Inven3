package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.ReturnOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PurchaseOrderReturn extends ReturnOrder<PurchaseOrder> {
}
