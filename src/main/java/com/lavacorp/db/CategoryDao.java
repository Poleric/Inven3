package com.lavacorp.db;

import com.lavacorp.entities.category.Category;
import com.lavacorp.mapper.CategoryMapper;
import org.jdbi.v3.freemarker.UseFreemarkerSqlLocator;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.AllowUnusedBindings;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@UseFreemarkerSqlLocator
@AllowUnusedBindings
@RegisterRowMapper(CategoryMapper.class)
public interface CategoryDao {
    @SqlUpdate
    @GetGeneratedKeys("id")
    int create(@BindBean Category category);

    @SqlQuery
    Category retrieve(int id);

    @SqlQuery
    Category retrieve(@NotNull String name);

    @SqlQuery("retrieve")
    List<Category> retrieveAll();
}
