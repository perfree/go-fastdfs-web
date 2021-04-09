package com.perfree.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @description SQLite的配置
 * @author Perfree
 * @date 2021/3/22 15:07
 */
@Configuration
public class SQLiteConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(SQLiteConfiguration.class);

    @Value("${SQLite.dbPath:/db/go-fastDfs.db}")
    private String SQLiteDbPath;

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        String projectPath = System.getProperty("user.dir");
        final String url = "jdbc:sqlite:" + projectPath + SQLiteDbPath;
        dataSourceBuilder.url(url);
        return dataSourceBuilder.build();
    }
}
