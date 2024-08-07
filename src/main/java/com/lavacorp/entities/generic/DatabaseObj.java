package com.lavacorp.entities.generic;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.Nullable;

@Data
@SuperBuilder
@NoArgsConstructor
abstract public class DatabaseObj {
    @Nullable private Integer id;
}
