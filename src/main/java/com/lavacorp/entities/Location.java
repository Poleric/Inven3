package com.lavacorp.entities;

import com.lavacorp.entities.tag.Tag;
import com.lavacorp.entities.tag.Taggable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Location extends DatabaseObj implements Taggable {
    @NonNull private String name;
    @Nullable private String description;

    public Location(Integer id, @NonNull String name, @Nullable String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

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
