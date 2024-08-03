package com.lavacorp.entities.company;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Supplier extends Company {
    private String supplyHistory;

}
