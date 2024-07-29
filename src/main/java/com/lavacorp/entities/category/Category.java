package com.lavacorp.entities.category;

import lombok.Data;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

@Data
public class Category {
    @NonNull String name;
    @Nullable private String description;
}
