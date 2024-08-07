package com.lavacorp.entities.generic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
abstract public class NamedDatabaseObj extends DatabaseObj {
    @NonNull private String name;
}
