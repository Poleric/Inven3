package com.lavacorp.entities.location;

import com.lavacorp.entities.tag.Tag;
import com.lavacorp.entities.tag.Taggable;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

@Data
public class Location implements Serializable, Taggable {
    private String name;
    private String description;
    private LocationType type;

    @Nullable List<Tag> tags;
}
