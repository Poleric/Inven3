package com.lavacorp.entities.tag;

import com.lavacorp.entities.DatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Tag extends DatabaseObj {
    private String name;
    private String description;
}
