package com.jessy.user.config;

import com.jessy.user.context.DatabaseEnvironment;
import com.jessy.user.context.MasterSlaveRoutingDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfiguration {
    @Value("${datasource.master.jdbc-url}")
    private String masterJdbcUrl;

    @Value("${datasource.master.username}")
    private String masterUsername;

    @Value("${datasource.master.password}")
    private String masterPassword;

    public DataSource masterDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(masterJdbcUrl);
        hikariDataSource.setUsername(masterUsername);
        hikariDataSource.setPassword(masterPassword);
        return hikariDataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.master")
    public HikariConfig hikariMasterConfig() {
        HikariConfig config = new HikariConfig();
        return config;
    }



    @Bean
    @ConfigurationProperties(prefix = "datasource.slave")
    public HikariConfig hikariSlaveConfig() {
        HikariConfig config = new HikariConfig();
        return config;
    }

    // Slave Datasource (읽기)
    public DataSource slaveDataSource() {
        return new HikariDataSource(hikariSlaveConfig());
    }

    @Bean
    public DataSource dataSource(){
        MasterSlaveRoutingDataSource masterSlaveRoutingDataSource = new MasterSlaveRoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseEnvironment.UPDATABLE, masterDataSource());
        targetDataSources.put(DatabaseEnvironment.READONLY, slaveDataSource());
        masterSlaveRoutingDataSource.setTargetDataSources(targetDataSources);

        // Set as all transaction point to master
        masterSlaveRoutingDataSource.setDefaultTargetDataSource(masterDataSource());
        return masterSlaveRoutingDataSource;
    }
}
