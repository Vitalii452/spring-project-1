package com.javarush.budiak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("com.javarush.budiak.domain");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setJpaProperties(hibernateProperties());

        return entityManagerFactory;
    }

    @Bean
    public DataSource dataSource() {
        String databaseHost = System.getenv().getOrDefault("DATABASE_HOST", "localhost");
        String databasePort = System.getenv().getOrDefault("DATABASE_PORT", "3306");
        String databaseName = System.getenv().getOrDefault("DATABASE_NAME", "todo");
        String databaseUser = System.getenv().getOrDefault("DATABASE_USER", "root");
        String databasePassword = System.getenv().getOrDefault("DATABASE_PASSWORD", "456321");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(String.format("jdbc:mysql://%s:%s/%s", databaseHost, databasePort, databaseName));
        dataSource.setUsername(databaseUser);
        dataSource.setPassword(databasePassword);

        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
}
