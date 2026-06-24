package com.bestpractice.api.infrastrucuture;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.lang.reflect.Field;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InfrastructureBeanGeneratedAiTests {

    private InfrastructureBean.LocalDbRepository localDbRepository;
    private InfrastructureBean.RdbmsDbRepository rdbmsDbRepository;
    private InfrastructureBean.CassandraDbRepository cassandraDbRepository;
    private InfrastructureBean.MongoDbRepository mongoDbRepository;
    private InfrastructureBean.RedisCacheRepository redisCacheRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private CassandraProperty cassandraProperty;
    @Mock
    private MongoProperty mongoProperty;
    @Mock
    private RedisProperty redisProperty;
    @Mock
    private CqlSession cqlSession;
    @Mock
    private MongoClient mongoClient;
    @Mock
    private MongoDatabase mongoDatabase;

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
    void givenLocalProfile_whenCreateUserRepository_thenReturnLocalUserPersistentRepository() {
        // GIVEN
        // WHEN
        UserPersistentRepository repository = localDbRepository.userRepository();
        // THEN
        assertThat(repository).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenLocalProfile_whenCreateInfoRepository_thenReturnLocalInfoPersistentRepository() {
        // GIVEN
        // WHEN
        InfoPersistentRepository repository = localDbRepository.infoRepository();
        // THEN
        assertThat(repository).isInstanceOf(LocalInfoPersistentRepository.class);
    }

    @Test
    void givenRdbmsProfile_whenCreateDataSource_thenReturnDriverManagerDataSource() {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "user");
        setPrivateField(rdbmsDbRepository, "password", "pass");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");
        // WHEN
        DriverManagerDataSource dataSource = rdbmsDbRepository.dataSource();
        // THEN
        assertThat(dataSource).isNotNull();
        assertThat(dataSource.getUrl()).isEqualTo("jdbc:h2:mem:testdb");
    }

    @Test
    void givenRdbmsProfile_whenCreateUserRepository_thenReturnRdbmsUserPersistentRepository() {
        // GIVEN
        // WHEN
        UserPersistentRepository repository = rdbmsDbRepository.userRepository(jdbcTemplate);
        // THEN
        assertThat(repository).isInstanceOf(RdbmsUserPersistentRepository.class);
    }

    @Test
    void givenRdbmsProfile_whenCreateInfoRepository_thenReturnRdbmsInfoPersistentRepository() {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "user");
        setPrivateField(rdbmsDbRepository, "password", "pass");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");
        // WHEN
        InfoPersistentRepository repository = rdbmsDbRepository.infoRepository();
        // THEN
        assertThat(repository).isInstanceOf(RdbmsInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraProfile_whenCreateInfoRepository_thenReturnCassandraInfoPersistentRepository() {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(new String[]{"localhost:9042"});
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");
        // WHEN
        InfoPersistentRepository repository = cassandraDbRepository.infoRepository();
        // THEN
        assertThat(repository).isInstanceOf(CassandraInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraProfile_whenCreateUserRepository_thenReturnLocalUserPersistentRepository() {
        // GIVEN
        // WHEN
        UserPersistentRepository repository = cassandraDbRepository.userRepository();
        // THEN
        assertThat(repository).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenMongoProfile_whenCreateUserRepository_thenReturnMongoUserPersistentRepository() {
        // GIVEN
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPassword()).thenReturn("password");
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getPlatformDatabase()).thenReturn("testdb");
        // WHEN
        UserPersistentRepository repository = mongoDbRepository.userRepository();
        // THEN
        assertThat(repository).isInstanceOf(MongoUserPersistentRepository.class);
    }

    @Test
    void givenMongoProfile_whenCreateInfoRepository_thenReturnMongoInfoPersistentRepository() {
        // GIVEN
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPassword()).thenReturn("password");
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getPlatformDatabase()).thenReturn("testdb");
        // WHEN
        InfoPersistentRepository repository = mongoDbRepository.infoRepository();
        // THEN
        assertThat(repository).isInstanceOf(MongoInfoPersistentRepository.class);
    }

    @Test
    void givenRedisProfile_whenCreateRedisCacheRepository_thenInstanceCreated() {
        // GIVEN
        // WHEN
        InfrastructureBean.RedisCacheRepository repository = new InfrastructureBean.RedisCacheRepository(redisProperty);
        // THEN
        assertThat(repository).isNotNull();
    }
}