package com.lavacorp.entities;

import com.lavacorp.entities.generic.NamedDatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.Nullable;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Category extends NamedDatabaseObj {
    @Nullable private String description;
}
