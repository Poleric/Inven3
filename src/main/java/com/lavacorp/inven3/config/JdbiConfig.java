package com.lavacorp.inven3.config;

import org.jdbi.v3.core.ConnectionFactory;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Slf4JSqlLogger;
import org.jdbi.v3.freemarker.FreemarkerEngine;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.spring5.SpringConnectionFactory;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class JdbiConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public Jdbi jdbi(DataSource ds) {
        ConnectionFactory cf = new SpringConnectionFactory(ds);
        return Jdbi.create(cf)
                .installPlugin(new SqlObjectPlugin())
                .installPlugin(new PostgresPlugin())
                .setSqlLogger(new Slf4JSqlLogger())
                .setTemplateEngine(FreemarkerEngine.instance());
    }

    @Bean
    public DataSourceTransactionManager transactionManager()
    {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource());
        return txManager;
    }
}
