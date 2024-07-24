package com.lavacorp.entities.tag;

import lombok.Data;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

@Data
public class Tag {
    @ColumnName("name")
    private String name;
    @ColumnName("description")
    private String description;
}
