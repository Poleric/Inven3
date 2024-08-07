package com.lavacorp.entities;

import com.lavacorp.entities.generic.NamedDatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Tag extends NamedDatabaseObj {
    private String description;
}
