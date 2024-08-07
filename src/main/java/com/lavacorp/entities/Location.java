package com.lavacorp.entities;

import com.lavacorp.entities.generic.NamedDatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Location extends NamedDatabaseObj implements Taggable {
    @Nullable private String description;

    @Override
    public List<Tag> getTags() {
        return List.of();
    }

    @Override
    public void addTag(Tag tag) {

    }

    @Override
    public void removeTag(Tag tag) {

    }
}
