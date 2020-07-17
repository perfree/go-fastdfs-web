package com.perfree.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * SQLite的配置
 */
@Configuration
public class SQLiteConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(SQLiteConfiguration.class);

    @Autowired
    private ResourceLoader resourceLoader;

    // sqlite的数据库地址
    @Value("${sqlite.dbpath:db/go-fastdfs.db}")
    private String sqliteDbpath;

    @Bean
    public DataSource dataSource() throws IOException {
        logger.info("sqlite path地址 = {}", sqliteDbpath);
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        // 驱动名
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        // url在开发时可以从user.dir路径中找到，也可以在部署时在jar包路径下找到
        final String url = "jdbc:sqlite:" + sqliteDbpath;
        logger.info("最终配置的值spring.datasource.url = {}", url);
        dataSourceBuilder.url(url);
        return dataSourceBuilder.build();
    }

}
