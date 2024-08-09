package com.lavacorp.entities;

import com.lavacorp.entities.generic.DatabaseObj;
import lombok.Data;

@Data

public class User extends DatabaseObj {
    private String name;
    private String password;

}
