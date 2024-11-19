package io.zeecode.connectmeapi.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "io.zeecode.connectmeapi.services.connection",
        entityManagerFactoryRef = "connectionsEntityManagerFactory",
        transactionManagerRef = "connectionsTransactionManager"
)
public class ConnectionDataSourceConfig {

    @Bean(name = "connectionsDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.connections")
    public DataSource connectionsDataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean connectionsEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("connectionsDataSource") DataSource dataSource) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return builder
                .dataSource(dataSource)
                .packages("io.zeecode.connectmeapi.services.connection.model")
                .properties(properties)
                .persistenceUnit("connections")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager connectionsTransactionManager(
            @Qualifier("connectionsEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}