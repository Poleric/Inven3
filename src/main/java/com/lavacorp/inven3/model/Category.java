package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.NamedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Category extends NamedEntity {
    @Nullable private String description;
}
