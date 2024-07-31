package com.lavacorp.entities.category;

import com.lavacorp.entities.DatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Category extends DatabaseObj {
    @NonNull private String name;
    @Nullable private String description;

    public Category(@Nullable Integer id, @NonNull String name, @Nullable String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
