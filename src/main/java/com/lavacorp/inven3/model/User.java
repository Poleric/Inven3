package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.NamedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends NamedEntity {
    private String hashedPassword;
    private Role role;

    public enum Role {
        ROLE_ADMIN,
        ROLE_STAFF
    }
}
