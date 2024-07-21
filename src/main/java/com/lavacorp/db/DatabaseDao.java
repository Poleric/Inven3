package com.lavacorp.db;

import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@UseClasspathSqlLocator
public interface DatabaseDao {
    @SqlQuery
    List<String> getTables();
}
