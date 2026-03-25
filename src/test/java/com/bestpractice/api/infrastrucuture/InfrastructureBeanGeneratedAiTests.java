package com.bestpractice.api.infrastrucuture;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.cache.redis.RedisProperty;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.cassandra.CassandraInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraProperty;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.MongoUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoProperty;
import com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsUserPersistentRepository;
import com.datastax.oss.driver.api.core.CqlSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

public class InfrastructureBeanGeneratedAiTests {

    private RedisProperty redisProperty;
    private CassandraProperty cassandraProperty;
    private MongoProperty mongoProperty;
    private JdbcTemplate jdbcTemplate;
    private CqlSession cqlSession;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    private InfrastructureBean.LocalDbRepository localDbRepository;
    private InfrastructureBean.RdbmsDbRepository rdbmsDbRepository;
    private InfrastructureBean.CassandraDbRepository cassandraDbRepository;
    private InfrastructureBean.MongoDbRepository mongoDbRepository;
    private InfrastructureBean.RedisCacheRepository redisCacheRepository;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();

        cassandraProperty = new CassandraProperty();
        cassandraProperty.setHosts(new String[]{"localhost:9042"});
        cassandraProperty.setKeyspace("test_keyspace");

        mongoProperty = new MongoProperty();
        mongoProperty.setUser("user");
        mongoProperty.setAuthDatabase("admin");
        mongoProperty.setPassword("password");
        mongoProperty.setHost("localhost");
        mongoProperty.setPort(27017);
        mongoProperty.setPlatformDatabase("testdb");

        jdbcTemplate = new JdbcTemplate();
        cqlSession = null;
        mongoClient = null;
        mongoDatabase = null;

        localDbRepository = new InfrastructureBean.LocalDbRepository();
        rdbmsDbRepository = new InfrastructureBean.RdbmsDbRepository();
        cassandraDbRepository = new InfrastructureBean.CassandraDbRepository(cassandraProperty);
        mongoDbRepository = new InfrastructureBean.MongoDbRepository(mongoProperty);
        redisCacheRepository = new InfrastructureBean.RedisCacheRepository(redisProperty);
    }

    @Test
    void givenLocalDbRepository_whenUserRepositoryCalled_thenReturnsLocalUserPersistentRepository() {
        // GIVEN
        // WHEN
        UserPersistentRepository result = localDbRepository.userRepository();
        // THEN
        assertThat(result).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenLocalDbRepository_whenInfoRepositoryCalled_thenReturnsLocalInfoPersistentRepository() {
        // GIVEN
        // WHEN
        InfoPersistentRepository result = localDbRepository.infoRepository();
        // THEN
        assertThat(result).isInstanceOf(LocalInfoPersistentRepository.class);
    }

    @Test
    void givenRdbmsDbRepository_whenDataSourceCalled_thenReturnsDriverManagerDataSource() throws Exception {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:test");
        setPrivateField(rdbmsDbRepository, "username", "user");
        setPrivateField(rdbmsDbRepository, "password", "pass");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");
        // WHEN
        DriverManagerDataSource dataSource = rdbmsDbRepository.dataSource();
        // THEN
        assertThat(dataSource).isNotNull();
        assertThat(dataSource.getUrl()).isEqualTo("jdbc:h2:mem:test");
    }

    @Test
    void givenRdbmsDbRepository_whenUserRepositoryCalled_thenReturnsRdbmsUserPersistentRepository() {
        // GIVEN
        // WHEN
        UserPersistentRepository result = rdbmsDbRepository.userRepository(jdbcTemplate);
        // THEN
        assertThat(result).isInstanceOf(RdbmsUserPersistentRepository.class);
    }

    @Test
    void givenRdbmsDbRepository_whenInfoRepositoryCalled_thenReturnsRdbmsInfoPersistentRepository() {
        // GIVEN
        // WHEN
        InfoPersistentRepository result = rdbmsDbRepository.infoRepository();
        // THEN
        assertThat(result).isInstanceOf(RdbmsInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraDbRepository_whenInfoRepositoryCalled_thenReturnsCassandraInfoPersistentRepository() {
        // GIVEN
        // WHEN
        InfoPersistentRepository result = cassandraDbRepository.infoRepository();
        // THEN
        assertThat(result).isInstanceOf(CassandraInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraDbRepository_whenUserRepositoryCalled_thenReturnsLocalUserPersistentRepository() {
        // GIVEN
        // WHEN
        UserPersistentRepository result = cassandraDbRepository.userRepository();
        // THEN
        assertThat(result).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenMongoDbRepository_whenUserRepositoryCalled_thenReturnsMongoUserPersistentRepository() {
        // GIVEN
        // WHEN
        UserPersistentRepository result = mongoDbRepository.userRepository();
        // THEN
        assertThat(result).isInstanceOf(MongoUserPersistentRepository.class);
    }

    @Test
    void givenMongoDbRepository_whenInfoRepositoryCalled_thenReturnsMongoInfoPersistentRepository() {
        // GIVEN
        // WHEN
        InfoPersistentRepository result = mongoDbRepository.infoRepository();
        // THEN
        assertThat(result).isInstanceOf(MongoInfoPersistentRepository.class);
    }

    @Test
    void givenRedisCacheRepository_whenConstructed_thenNotNull() {
        // GIVEN
        // WHEN
        InfrastructureBean.RedisCacheRepository result = new InfrastructureBean.RedisCacheRepository(redisProperty);
        // THEN
        assertThat(result).isNotNull();
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
