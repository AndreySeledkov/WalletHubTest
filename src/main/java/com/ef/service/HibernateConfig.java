package com.ef.service;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@PropertySource({"persistence.properties"})
public class HibernateConfig
{
    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClass(this.env.getRequiredProperty("datasource.driver"));
        dataSource.setJdbcUrl(this.env.getRequiredProperty("datasource.url"));
        dataSource.setUser(this.env.getRequiredProperty("datasource.username"));
        dataSource.setPassword(this.env.getRequiredProperty("datasource.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory()
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.ef.entity" });
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    private Properties getHibernateProperties()
    {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", this.env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", this.env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.jdbc.batch_size", this.env.getRequiredProperty("hibernate.batch.size"));
        properties.put("hibernate.hbm2ddl.auto", this.env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.current_session_context_class", this.env.getRequiredProperty("hibernate.current.session.context.class"));
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
    {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
}
