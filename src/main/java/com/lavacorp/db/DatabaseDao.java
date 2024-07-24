package com.lavacorp.db;

import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlScript;

import java.util.List;

@UseClasspathSqlLocator
public interface DatabaseDao {
    @SqlScript
    void initTables();

    @SqlQuery
    List<String> getTables();
}
