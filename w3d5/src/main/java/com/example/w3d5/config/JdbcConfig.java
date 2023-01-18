package com.example.w3d5.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class JdbcConfig {

//    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/beaconfire";
//    static final String USER = "root";
//    static final String PASSWORD = "root123456";
//
//    @Bean
//    public DataSource jdbcDataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(JDBC_DRIVER);
//        dataSource.setUrl(DB_URL);
//        dataSource.setUsername(USER);
//        dataSource.setPassword(PASSWORD);
//        return dataSource;
//    }

    @Bean
    public DataSource jdbcDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(getJdbcDriver());
        driverManagerDataSource.setUrl(getJdbcUrl());
        driverManagerDataSource.setUsername(getJdbcUserName());
        // make sure you set your own password
        driverManagerDataSource.setPassword(getJdbcPassword());
        return driverManagerDataSource;
    }
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(jdbcDataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(jdbcDataSource());
    }


    @Value("${database.jdbc.driver}")
    private String jdbcDriver;

    @Value("${database.jdbc.url}")
    private String jdbcUrl;

    @Value("${database.jdbc.username}")
    private String jdbcUserName;

    @Value("${database.jdbc.password}")
    private String jdbcPassword;

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUserName() {
        return jdbcUserName;
    }

    public void setJdbcUserName(String jdbcUserName) {
        this.jdbcUserName = jdbcUserName;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }







}
