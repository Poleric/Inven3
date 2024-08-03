package com.lavacorp.db.dao;

import org.jdbi.v3.core.statement.UnableToCreateStatementException;
import org.jdbi.v3.freemarker.UseFreemarkerSqlLocator;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlScript;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@UseClasspathSqlLocator
public interface DatabaseDao {
    @SqlScript
    void initTables();

    @SqlQuery
    List<String> getTables();

    @SqlUpdate
    @UseFreemarkerSqlLocator
    void dropTable(@Define String tableName);

    default void dropAllTables() {
        for (String tableName : getTables())
            try {dropTable(tableName);}
            catch (UnableToCreateStatementException _) {}
    }
}
