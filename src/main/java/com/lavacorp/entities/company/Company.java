package com.lavacorp.entities.company;

import com.lavacorp.entities.DatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
abstract public class Company extends DatabaseObj {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
}
