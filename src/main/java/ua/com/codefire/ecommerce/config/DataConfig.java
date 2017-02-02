package ua.com.codefire.ecommerce.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * Created by human on 1/31/17.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("ua.com.codefire.ecommerce.data")
public class DataConfig {

    @Autowired
    private Environment env;

    @Bean
    public HikariDataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(env.getProperty("database.driver"));
        config.setJdbcUrl(env.getProperty("database.url"));
        config.setUsername(env.getProperty("database.username"));
        config.setPassword(env.getProperty("database.password"));

        return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        Properties props = new Properties();
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        factory.setJpaProperties(props);

        factory.setPersistenceProvider(new HibernatePersistenceProvider());
        factory.setPackagesToScan("ua.com.codefire.ecommerce.data.entity");
        factory.setDataSource(getDataSource());

        return factory;
    }

    @Bean
    public JpaTransactionManager getTransactionManager() {
        return new JpaTransactionManager();
    }
}
