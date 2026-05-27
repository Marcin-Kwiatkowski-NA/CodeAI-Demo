package com.bestpractice.api.infrastrucuture;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.cache.redis.RedisProperty;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraProperty;
import com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoProperty;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class InfrastructureBeanGeneratedAiTests {

    private InfrastructureBean.LocalDbRepository localDbRepository;
    private InfrastructureBean.RdbmsDbRepository rdbmsDbRepository;
    private InfrastructureBean.CassandraDbRepository cassandraDbRepository;
    private InfrastructureBean.MongoDbRepository mongoDbRepository;
    private InfrastructureBean.RedisCacheRepository redisCacheRepository;

    @Mock
    private CassandraProperty cassandraProperty;
    @Mock
    private MongoProperty mongoProperty;
    @Mock
    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        localDbRepository = new InfrastructureBean.LocalDbRepository();
        rdbmsDbRepository = new InfrastructureBean.RdbmsDbRepository();
        cassandraDbRepository = new InfrastructureBean.CassandraDbRepository(cassandraProperty);
        mongoDbRepository = new InfrastructureBean.MongoDbRepository(mongoProperty);
        redisCacheRepository = new InfrastructureBean.RedisCacheRepository(redisProperty);
    }

    private void setPrivateField(Object target, String fieldName, Object value) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void givenLocalDbRepository_whenCreateUserRepository_thenReturnsNonNull() {
        // GIVEN

        // WHEN
        UserPersistentRepository repo = localDbRepository.userRepository();

        // THEN
        assertThat(repo).isNotNull();
    }

    @Test
    void givenLocalDbRepository_whenCreateInfoRepository_thenReturnsNonNull() {
        // GIVEN

        // WHEN
        InfoPersistentRepository repo = localDbRepository.infoRepository();

        // THEN
        assertThat(repo).isNotNull();
    }

    @Test
    void givenRdbmsDbRepository_whenCreateDataSource_thenReturnsValidDataSource() {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "sa");
        setPrivateField(rdbmsDbRepository, "password", "");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");

        // WHEN
        DriverManagerDataSource dataSource = rdbmsDbRepository.dataSource();

        // THEN
        assertThat(dataSource).isNotNull();
        assertThat(dataSource.getUrl()).isEqualTo("jdbc:h2:mem:testdb");
    }

    @Test
    void givenRdbmsDbRepository_whenCreateJdbcTemplate_thenReturnsNonNull() {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "sa");
        setPrivateField(rdbmsDbRepository, "password", "");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");

        // WHEN
        JdbcTemplate jdbcTemplate = rdbmsDbRepository.jdbcTemplate();

        // THEN
        assertThat(jdbcTemplate).isNotNull();
    }

    @Test
    void givenRdbmsDbRepository_whenCreateUserRepository_thenReturnsNonNull() {
        // GIVEN
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

        // WHEN
        UserPersistentRepository repo = rdbmsDbRepository.userRepository(jdbcTemplate);

        // THEN
        assertThat(repo).isNotNull();
    }

    @Test
    void givenRdbmsDbRepository_whenCreateInfoRepository_thenReturnsNonNull() {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "sa");
        setPrivateField(rdbmsDbRepository, "password", "");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");

        // WHEN
        InfoPersistentRepository repo = rdbmsDbRepository.infoRepository();

        // THEN
        assertThat(repo).isNotNull();
    }

    @Test
    void givenCassandraDbRepository_whenCreateInfoRepository_thenReturnsNonNull() {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(java.util.List.of("localhost:9042"));
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");

        // WHEN
        InfoPersistentRepository repo = cassandraDbRepository.infoRepository();

        // THEN
        assertThat(repo).isNotNull();
    }

    @Test
    void givenCassandraDbRepository_whenCreateUserRepository_thenReturnsNonNull() {
        // GIVEN

        // WHEN
        UserPersistentRepository repo = cassandraDbRepository.userRepository();

        // THEN
        assertThat(repo).isNotNull();
    }

    @Test
    void givenMongoDbRepository_whenCreateMongoClient_thenReturnsNonNull() {
        // GIVEN
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPassword()).thenReturn("password");
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);

        // WHEN
        MongoClient client = mongoDbRepository.mongoClient();

        // THEN
        assertThat(client).isNotNull();
    }

    @Test
    void givenMongoDbRepository_whenCreateMongoDatabase_thenReturnsNonNull() {
        // GIVEN
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPassword()).thenReturn("password");
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getPlatformDatabase()).thenReturn("testdb");

        // WHEN
        MongoDatabase db = mongoDbRepository.mongoDatabase();

        // THEN
        assertThat(db).isNotNull();
    }

    @Test
    void givenMongoDbRepository_whenCreateUserRepository_thenReturnsNonNull() {
        // GIVEN
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPassword()).thenReturn("password");
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getPlatformDatabase()).thenReturn("testdb");

        // WHEN
        UserPersistentRepository repo = mongoDbRepository.userRepository();

        // THEN
        assertThat(repo).isNotNull();
    }

    @Test
    void givenMongoDbRepository_whenCreateInfoRepository_thenReturnsNonNull() {
        // GIVEN
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPassword()).thenReturn("password");
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getPlatformDatabase()).thenReturn("testdb");

        // WHEN
        InfoPersistentRepository repo = mongoDbRepository.infoRepository();

        // THEN
        assertThat(repo).isNotNull();
    }

    @Test
    void givenRedisCacheRepository_whenInitialized_thenNotNull() {
        // GIVEN

        // WHEN
        InfrastructureBean.RedisCacheRepository repo = redisCacheRepository;

        // THEN
        assertThat(repo).isNotNull();
    }
}