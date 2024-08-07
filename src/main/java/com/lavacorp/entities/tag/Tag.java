package com.lavacorp.entities.tag;

import com.lavacorp.entities.DatabaseObj;
import com.lavacorp.entities.NamedDatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Tag extends NamedDatabaseObj {
    private String description;
}
