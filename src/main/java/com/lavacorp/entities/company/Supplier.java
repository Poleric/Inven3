package com.lavacorp.entities.company;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Supplier extends Company {
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
