package com.lavacorp.entities.location;

import com.lavacorp.entities.tag.Tag;
import com.lavacorp.entities.tag.Taggable;
import lombok.Data;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Data
public class Location implements Taggable {
    @NonNull private String name;
    @Nullable private String description;
    @NonNull private LocationType type;

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
