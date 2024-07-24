package com.lavacorp.entities.category;

import lombok.Data;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

@Data
public class Category {
    @ColumnName("name")
    private String name;
    @ColumnName("description")
    private String description;
}
