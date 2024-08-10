package com.lavacorp.entities;

import com.lavacorp.entities.generic.DatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends DatabaseObj {
    private String name;
    private String password;

}
