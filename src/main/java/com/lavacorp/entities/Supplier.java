package com.lavacorp.entities;

import com.lavacorp.entities.generic.NamedDatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Supplier extends NamedDatabaseObj {
    private String address;
    private String phoneNumber;
    private String email;
    private String supplyHistory;
}
