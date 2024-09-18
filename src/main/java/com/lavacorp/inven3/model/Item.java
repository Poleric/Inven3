package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.NamedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.mapper.Nested;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Item extends NamedEntity {
    @Nullable private String description;
    @Nullable private Double basePrice;
    @Nullable private String unit;
    @Nullable @Nested("category") private Category category;
    @Nullable private Integer minStock;

    @EqualsAndHashCode.Exclude @Nullable private Instant createdAt;
    @EqualsAndHashCode.Exclude @Nullable private Instant lastUpdatedAt;
}
