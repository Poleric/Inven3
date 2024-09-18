package com.lavacorp.inven3.config;

import org.jdbi.v3.core.ConnectionFactory;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.freemarker.FreemarkerEngine;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.spring5.SpringConnectionFactory;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@TestConfiguration
public class TestConfig {

}
