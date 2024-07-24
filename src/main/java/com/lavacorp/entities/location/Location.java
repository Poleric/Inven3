package com.lavacorp.entities.location;

import com.lavacorp.entities.tag.Tag;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Data
public class Location {
    private String name;
    private String description;
    private LocationType type;

    @Nullable
    private List<Tag> tags;
}
