package com.lavacorp.entities;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
abstract public class DatabaseObj {
    @Nullable protected Integer id;
}
