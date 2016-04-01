package com.mmt.shubh.database.configuration;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:persistence-mysql.properties"})
@ComponentScan({"com.mmt.shubh.database.repository", "com.mmt.shubh.database.entity"})
@EnableJpaRepositories(basePackages = "com.mmt.shubh.database.repository")
public class PersistenceJPAConfig {

    private static final String PROPERTY_KEY_DRIVER_NAME = "jdbc.driverClassName";
    private static final String PROPERTY_KEY_URL = "jdbc.url";
    private static final String PROPERTY_KEY_USER_NAME = "jdbc.user";
    private static final String PROPERTY_KEY_PASSWORD = "jdbc.pass";


    @Autowired
    private Environment env;

    public PersistenceJPAConfig() {
        super();
    }

    // beans

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.mmt.shubh.database.repository", "com.mmt.shubh.database.entity"});
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty(PROPERTY_KEY_DRIVER_NAME)));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty(PROPERTY_KEY_URL)));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty(PROPERTY_KEY_USER_NAME)));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty(PROPERTY_KEY_PASSWORD)));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        // hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");
        return hibernateProperties;
    }

}