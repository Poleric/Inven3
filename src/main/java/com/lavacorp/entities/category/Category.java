package com.lavacorp.entities.category;

import com.lavacorp.entities.DatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

@EqualsAndHashCode(callSuper = true)
@Data
public class Category extends DatabaseObj {
    @NonNull String name;
    @Nullable private String description;
}
