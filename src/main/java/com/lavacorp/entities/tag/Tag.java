package com.lavacorp.entities.tag;

import com.lavacorp.entities.generic.NamedDatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Tag extends NamedDatabaseObj {
    private String description;
}
