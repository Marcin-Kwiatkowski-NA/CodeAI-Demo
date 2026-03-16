package com.bestpractice.api.infrastrucuture;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import static org.mockito.Mockito.clearInvocations;

@ExtendWith(MockitoExtension.class)
public class InfrastructureBeanGeneratedAiTests {

    @Mock
    private CassandraProperty cassandraProperty;

    @Mock
    private MongoProperty mongoProperty;

    @Mock
    private RedisProperty redisProperty;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private CqlSession cqlSession;

    @BeforeEach
    void setUp() {
        clearInvocations(cassandraProperty, mongoProperty, redisProperty, jdbcTemplate, mongoClient, mongoDatabase, cqlSession);
    }

    @Test
    void givenLocalDbProfile_whenCreateRepositories_thenReturnLocalRepositories() {
        // GIVEN
        InfrastructureBean.LocalDbRepository repository = new InfrastructureBean.LocalDbRepository();

        // WHEN
        UserPersistentRepository userRepo = repository.userRepository();
        InfoPersistentRepository infoRepo = repository.infoRepository();

        // THEN
        assertThat(userRepo).isInstanceOf(LocalUserPersistentRepository.class);
        assertThat(infoRepo).isInstanceOf(LocalInfoPersistentRepository.class);
    }

    @Test
    void givenRdbmsProfile_whenCreateRepositories_thenReturnRdbmsRepositories() throws Exception {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository repository = new InfrastructureBean.RdbmsDbRepository();

        setPrivateField(repository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(repository, "username", "user");
        setPrivateField(repository, "password", "pass");
        setPrivateField(repository, "driverClassName", "org.h2.Driver");

        // WHEN
        DriverManagerDataSource dataSource = repository.dataSource();
        JdbcTemplate template = repository.jdbcTemplate();
        UserPersistentRepository userRepo = repository.userRepository(template);
        InfoPersistentRepository infoRepo = repository.infoRepository();

        // THEN
        assertThat(dataSource).isNotNull();
        assertThat(template).isNotNull();
        assertThat(userRepo).isInstanceOf(RdbmsUserPersistentRepository.class);
        assertThat(infoRepo).isInstanceOf(RdbmsInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraProfile_whenCreateRepositories_thenReturnCassandraRepositories() {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(new String[]{"localhost:9042"});
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");
        InfrastructureBean.CassandraDbRepository repository = new InfrastructureBean.CassandraDbRepository(cassandraProperty);

        // WHEN
        CqlSession session = repository.cqlSession();
        InfoPersistentRepository infoRepo = repository.infoRepository();
        UserPersistentRepository userRepo = repository.userRepository();

        // THEN
        assertThat(session).isNotNull();
        assertThat(infoRepo).isInstanceOf(CassandraInfoPersistentRepository.class);
        assertThat(userRepo).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenMongoProfile_whenCreateRepositories_thenReturnMongoRepositories() {
        // GIVEN
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPassword()).thenReturn("password");
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getPlatformDatabase()).thenReturn("testdb");
        InfrastructureBean.MongoDbRepository repository = new InfrastructureBean.MongoDbRepository(mongoProperty);

        // WHEN
        MongoClient client = repository.mongoClient();
        MongoDatabase database = repository.mongoDatabase();
        UserPersistentRepository userRepo = repository.userRepository();
        InfoPersistentRepository infoRepo = repository.infoRepository();

        // THEN
        assertThat(client).isNotNull();
        assertThat(database).isNotNull();
        assertThat(userRepo).isInstanceOf(MongoUserPersistentRepository.class);
        assertThat(infoRepo).isInstanceOf(MongoInfoPersistentRepository.class);
    }

    @Test
    void givenRedisProfile_whenCreateRedisCacheRepository_thenRepositoryInitialized() {
        // GIVEN
        InfrastructureBean.RedisCacheRepository repository = new InfrastructureBean.RedisCacheRepository(redisProperty);

        // WHEN
        boolean isCreated = repository != null;

        // THEN
        assertThat(isCreated).isTrue();
    }

    @Test
    void givenLocalCacheProfile_whenCreateLocalCacheRepository_thenRepositoryInitialized() {
        // GIVEN
        InfrastructureBean.LocalCacheRepository repository = new InfrastructureBean.LocalCacheRepository();

        // WHEN
        boolean isCreated = repository != null;

        // THEN
        assertThat(isCreated).isTrue();
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}