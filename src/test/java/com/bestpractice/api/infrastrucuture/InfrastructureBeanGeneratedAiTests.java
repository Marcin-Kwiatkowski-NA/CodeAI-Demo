package com.bestpractice.api.infrastrucuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class InfrastructureBeanGeneratedAiTests {

    private AnnotationConfigApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext();
    }

    @AfterEach
    void tearDown() {
        if (context != null) {
            context.close();
        }
    }

    @Test
    void testLocalDbRepositoryBeans() {
        // GIVEN
        ConfigurableEnvironment env = new StandardEnvironment();
        env.setActiveProfiles("db_local");
        context.setEnvironment(env);
        context.register(InfrastructureBean.class);
        // WHEN
        context.refresh();
        // THEN
        LocalUserPersistentRepository userRepo = context.getBean(LocalUserPersistentRepository.class);
        Assertions.assertThat(userRepo).isNotNull();
        LocalInfoPersistentRepository infoRepo = context.getBean(LocalInfoPersistentRepository.class);
        Assertions.assertThat(infoRepo).isNotNull();
    }

    @Test
    void testRdbmsDbRepositoryBeans() {
        // GIVEN
        ConfigurableEnvironment env = new StandardEnvironment();
        env.setActiveProfiles("db_rdbms");
        Map<String, Object> props = new HashMap<>();
        props.put("spring.datasource.url", "jdbc:h2:mem:testdb");
        props.put("spring.datasource.username", "sa");
        props.put("spring.datasource.password", "");
        props.put("spring.datasource.driver-class-name", "org.h2.Driver");
        MutablePropertySources sources = env.getPropertySources();
        sources.addFirst(new MapPropertySource("testProps", props));
        context.setEnvironment(env);
        context.register(InfrastructureBean.class);
        // WHEN
        context.refresh();
        // THEN
        DriverManagerDataSource dataSource = context.getBean(DriverManagerDataSource.class);
        Assertions.assertThat(dataSource).isNotNull();
        Assertions.assertThat(dataSource.getUrl()).isEqualTo("jdbc:h2:mem:testdb");
        Assertions.assertThat(dataSource.getUsername()).isEqualTo("sa");
        Assertions.assertThat(dataSource.getDriverClassName()).isEqualTo("org.h2.Driver");
        PlatformTransactionManager txManager = context.getBean(PlatformTransactionManager.class);
        Assertions.assertThat(txManager).isNotNull();
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        Assertions.assertThat(jdbcTemplate).isNotNull();
        RdbmsUserPersistentRepository userRepo = context.getBean(RdbmsUserPersistentRepository.class);
        Assertions.assertThat(userRepo).isNotNull();
        RdbmsInfoPersistentRepository infoRepo = context.getBean(RdbmsInfoPersistentRepository.class);
        Assertions.assertThat(infoRepo).isNotNull();
    }

    @Test
    void testCassandraDbRepositoryBeans() {
        // GIVEN
        ConfigurableEnvironment env = new StandardEnvironment();
        env.setActiveProfiles("db_cassandra");
        CassandraProperty cassandraPropertyMock = Mockito.mock(CassandraProperty.class);
        Mockito.when(cassandraPropertyMock.getHosts()).thenReturn(Collections.singletonList("127.0.0.1:9042"));
        Mockito.when(cassandraPropertyMock.getKeyspace()).thenReturn("test");
        context.setEnvironment(env);
        context.registerBean(CassandraProperty.class, () -> cassandraPropertyMock);
        context.register(InfrastructureBean.class);
        // WHEN
        context.refresh();
        // THEN
        CqlSession session = context.getBean(CqlSession.class);
        Assertions.assertThat(session).isNotNull();
        Assertions.assertThat(session.getKeyspace()).hasValue(CqlIdentifier.fromCql("test"));
        LocalUserPersistentRepository userRepo = context.getBean(LocalUserPersistentRepository.class);
        Assertions.assertThat(userRepo).isNotNull();
        CassandraInfoPersistentRepository infoRepo = context.getBean(CassandraInfoPersistentRepository.class);
        Assertions.assertThat(infoRepo).isNotNull();
    }

    @Test
    void testMongoDbRepositoryBeans() {
        // GIVEN
        ConfigurableEnvironment env = new StandardEnvironment();
        env.setActiveProfiles("db_mongo");
        MongoProperty mongoPropertyMock = Mockito.mock(MongoProperty.class);
        Mockito.when(mongoPropertyMock.getHost()).thenReturn("localhost");
        Mockito.when(mongoPropertyMock.getPort()).thenReturn(27017);
        Mockito.when(mongoPropertyMock.getAuthDatabase()).thenReturn("admin");
        Mockito.when(mongoPropertyMock.getPlatformDatabase()).thenReturn("testdb");
        Mockito.when(mongoPropertyMock.getUser()).thenReturn("user");
        Mockito.when(mongoPropertyMock.getPassword()).thenReturn("pass");
        context.setEnvironment(env);
        context.registerBean(MongoProperty.class, () -> mongoPropertyMock);
        context.register(InfrastructureBean.class);
        // WHEN
        context.refresh();
        // THEN
        MongoClient mongoClient = context.getBean(MongoClient.class);
        Assertions.assertThat(mongoClient).isNotNull();
        MongoDatabase mongoDatabase = context.getBean(MongoDatabase.class);
        Assertions.assertThat(mongoDatabase.getName()).isEqualTo("testdb");
        MongoUserPersistentRepository userRepo = context.getBean(MongoUserPersistentRepository.class);
        Assertions.assertThat(userRepo).isNotNull();
        MongoInfoPersistentRepository infoRepo = context.getBean(MongoInfoPersistentRepository.class);
        Assertions.assertThat(infoRepo).isNotNull();
    }

    @Test
    void testRedisCacheRepositoryBeans() {
        // GIVEN
        ConfigurableEnvironment env = new StandardEnvironment();
        env.setActiveProfiles("cache_redis");
        RedisProperty redisPropertyMock = Mockito.mock(RedisProperty.class);
        context.setEnvironment(env);
        context.registerBean(RedisProperty.class, () -> redisPropertyMock);
        context.register(InfrastructureBean.class);
        // WHEN
        context.refresh();
        // THEN
        InfrastructureBean.RedisCacheRepository redisCacheRepo = context.getBean(InfrastructureBean.RedisCacheRepository.class);
        Assertions.assertThat(redisCacheRepo).isNotNull();
    }
}
