package com.lavacorp.entities.location;

import com.lavacorp.entities.DatabaseObj;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LocationType extends DatabaseObj {
    private String name;
    private String description;
}
