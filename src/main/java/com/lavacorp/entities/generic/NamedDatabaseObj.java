package com.lavacorp.entities.generic;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
abstract public class NamedDatabaseObj extends DatabaseObj {
    protected String name;
}
