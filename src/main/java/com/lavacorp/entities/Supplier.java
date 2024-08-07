package com.lavacorp.entities;

import com.lavacorp.entities.generic.NamedDatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Supplier extends NamedDatabaseObj {
    private String address;
    private String phoneNumber;
    private String email;
    private String supplyHistory;

    public Supplier(Integer id, String name, String address, String phoneNumber, String email, String supplyHistory) {
        setId(id);
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setSupplyHistory(supplyHistory);
    }
}
