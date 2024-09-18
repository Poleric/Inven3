package com.lavacorp.inven3.model.generic;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

@Data
@NoArgsConstructor
abstract public class Entity {
    @Nullable private Integer id;
}
