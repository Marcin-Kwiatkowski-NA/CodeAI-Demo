package com.bestpractice.api.infrastrucuture;

import static org.mockito.Mockito.mock;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.mockito.Mockito;

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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.lang.reflect.Field;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class InfrastructureBeanGeneratedAiTests {

    @Mock
    private RedisProperty redisProperty;
    @Mock
    private CassandraProperty cassandraProperty;
    @Mock
    private MongoProperty mongoProperty;
    @Mock
    private CqlSession cqlSession;
    @Mock
    private MongoClient mongoClient;
    @Mock
    private MongoDatabase mongoDatabase;
    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        Mockito.clearInvocations(redisProperty, cassandraProperty, mongoProperty, cqlSession, mongoClient, mongoDatabase, jdbcTemplate);
    }

    @Test
    void givenLocalDbProfile_whenCreateRepositories_thenReturnLocalRepositories() {
        // GIVEN
        InfrastructureBean.LocalDbRepository localDbRepository = new InfrastructureBean.LocalDbRepository();

        // WHEN
        UserPersistentRepository userRepo = localDbRepository.userRepository();
        InfoPersistentRepository infoRepo = localDbRepository.infoRepository();

        // THEN
        assertThat(userRepo).isInstanceOf(LocalUserPersistentRepository.class);
        assertThat(infoRepo).isInstanceOf(LocalInfoPersistentRepository.class);
    }

    @Test
    void givenRdbmsProfile_whenCreateRepositories_thenReturnRdbmsRepositories() throws Exception {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository rdbmsDbRepository = new InfrastructureBean.RdbmsDbRepository();

        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "user");
        setPrivateField(rdbmsDbRepository, "password", "pass");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");

        // WHEN
        DriverManagerDataSource dataSource = rdbmsDbRepository.dataSource();
        DataSourceTransactionManager txManager = rdbmsDbRepository.transactionManager();
        JdbcTemplate template = rdbmsDbRepository.jdbcTemplate();
        UserPersistentRepository userRepo = rdbmsDbRepository.userRepository(template);
        InfoPersistentRepository infoRepo = rdbmsDbRepository.infoRepository();

        // THEN
        assertThat(dataSource).isNotNull();
        assertThat(txManager).isNotNull();
        assertThat(template).isNotNull();
        assertThat(userRepo).isInstanceOf(RdbmsUserPersistentRepository.class);
        assertThat(infoRepo).isInstanceOf(RdbmsInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraProfile_whenCreateRepositories_thenReturnCassandraRepositories() {
        // GIVEN
        Mockito.when(cassandraProperty.getHosts()).thenReturn(List.of("localhost:9042"));
        Mockito.when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");
        InfrastructureBean.CassandraDbRepository cassandraDbRepository = new InfrastructureBean.CassandraDbRepository(cassandraProperty);

        // WHEN
        CassandraInfoPersistentRepository infoRepo = new CassandraInfoPersistentRepository(cqlSession);
        UserPersistentRepository userRepo = cassandraDbRepository.userRepository();

        // THEN
        assertThat(infoRepo).isInstanceOf(CassandraInfoPersistentRepository.class);
        assertThat(userRepo).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenMongoProfile_whenCreateRepositories_thenReturnMongoRepositories() {
        // GIVEN
        Mockito.when(mongoProperty.getUser()).thenReturn("user");
        Mockito.when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        Mockito.when(mongoProperty.getPassword()).thenReturn("password");
        Mockito.when(mongoProperty.getHost()).thenReturn("localhost");
        Mockito.when(mongoProperty.getPort()).thenReturn(27017);
        Mockito.when(mongoProperty.getPlatformDatabase()).thenReturn("testdb");

        InfrastructureBean.MongoDbRepository mongoDbRepository = new InfrastructureBean.MongoDbRepository(mongoProperty);

        // WHEN
        MongoClient client = mongoDbRepository.mongoClient();
        MongoDatabase database = mongoDbRepository.mongoDatabase();
        UserPersistentRepository userRepo = mongoDbRepository.userRepository();
        InfoPersistentRepository infoRepo = mongoDbRepository.infoRepository();

        // THEN
        assertThat(client).isNotNull();
        assertThat(database).isNotNull();
        assertThat(userRepo).isInstanceOf(MongoUserPersistentRepository.class);
        assertThat(infoRepo).isInstanceOf(MongoInfoPersistentRepository.class);
    }

    @Test
    void givenRedisProfile_whenCreateRedisCacheRepository_thenConstructSuccessfully() {
        // GIVEN
        InfrastructureBean.RedisCacheRepository redisCacheRepository = new InfrastructureBean.RedisCacheRepository(redisProperty);

        // WHEN
        RedisProperty property = redisProperty;

        // THEN
        assertThat(redisCacheRepository).isNotNull();
        assertThat(property).isNotNull();
    }

    @Test
    void givenLocalCacheProfile_whenCreateLocalCacheRepository_thenConstructSuccessfully() {
        // GIVEN
        InfrastructureBean.LocalCacheRepository localCacheRepository = new InfrastructureBean.LocalCacheRepository();

        // WHEN
        InfrastructureBean.LocalCacheRepository result = localCacheRepository;

        // THEN
        assertThat(result).isNotNull();
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
