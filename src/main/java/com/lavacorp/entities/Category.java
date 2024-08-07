package com.lavacorp.entities;

import com.lavacorp.entities.generic.NamedDatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Category extends NamedDatabaseObj {
    @Nullable private String description;

    public Category(@Nullable Integer id, @NonNull String name, @Nullable String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
