package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.NamedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Supplier extends NamedEntity {
    private String address;
    private String phoneNumber;
    private String email;
}
