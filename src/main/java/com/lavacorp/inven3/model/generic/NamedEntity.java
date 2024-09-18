package com.lavacorp.inven3.model.generic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
abstract public class NamedEntity extends Entity {
    @NonNull private String name;
}
