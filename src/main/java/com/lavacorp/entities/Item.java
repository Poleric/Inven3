package com.lavacorp.entities;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public record Item(String name, @ColumnName("base_price") double basePrice) {
}
