package com.lavacorp.entities.location;

import com.lavacorp.entities.tag.Taggable;
import lombok.Data;

import java.io.Serializable;

@Data
public class Location extends Taggable implements Serializable {
    private String name;
    private String description;
    private LocationType type;
}
