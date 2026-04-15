package com.bestpractice.api.infrastrucuture;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;


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

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        localDbRepository = new InfrastructureBean.LocalDbRepository();
        rdbmsDbRepository = new InfrastructureBean.RdbmsDbRepository();
        cassandraDbRepository = new InfrastructureBean.CassandraDbRepository(cassandraProperty);
        mongoDbRepository = new InfrastructureBean.MongoDbRepository(mongoProperty);
        redisCacheRepository = new InfrastructureBean.RedisCacheRepository(redisProperty);
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
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
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "user");
        setPrivateField(rdbmsDbRepository, "password", "pass");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");

        // WHEN
        DriverManagerDataSource dataSource = rdbmsDbRepository.dataSource();

        // THEN
        assertThat(dataSource).isNotNull();
        assertThat(dataSource.getUrl()).contains("jdbc:h2:mem:testdb");
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
    void givenRdbmsDbRepository_whenInfoRepositoryCalled_thenReturnsRdbmsInfoPersistentRepository() throws Exception {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "user");
        setPrivateField(rdbmsDbRepository, "password", "pass");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");

        // WHEN
        InfoPersistentRepository result = rdbmsDbRepository.infoRepository();

        // THEN
        assertThat(result).isInstanceOf(RdbmsInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraDbRepository_whenCqlSessionCalled_thenReturnsCqlSession() {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(java.util.List.of("localhost:9042"));
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");

        // WHEN
        CqlSession session = cassandraDbRepository.cqlSession();

        // THEN
        assertThat(session).isNotNull();
        session.close();
    }

    @Test
    void givenCassandraDbRepository_whenInfoRepositoryCalled_thenReturnsCassandraInfoPersistentRepository() {
        // GIVEN
        CqlSession mockSession = mock(CqlSession.class);
        InfrastructureBean.CassandraDbRepository spyRepo = spy(cassandraDbRepository);
        doReturn(mockSession).when(spyRepo).cqlSession();

        // WHEN
        InfoPersistentRepository result = spyRepo.infoRepository();

        // THEN
        assertThat(result).isInstanceOf(CassandraInfoPersistentRepository.class);
    }

    @Test
    void givenMongoDbRepository_whenMongoClientCalled_thenReturnsMongoClient() {
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
        client.close();
    }

    @Test
    void givenMongoDbRepository_whenMongoDatabaseCalled_thenReturnsMongoDatabase() {
        // GIVEN
        MongoClient mockClient = mock(MongoClient.class);
        MongoDatabase mockDatabase = mock(MongoDatabase.class);
        InfrastructureBean.MongoDbRepository spyRepo = spy(mongoDbRepository);
        doReturn(mockClient).when(spyRepo).mongoClient();
        when(mockClient.getDatabase(anyString())).thenReturn(mockDatabase);
        when(mongoProperty.getPlatformDatabase()).thenReturn("testdb");

        // WHEN
        MongoDatabase result = spyRepo.mongoDatabase();

        // THEN
        assertThat(result).isNotNull();
    }

    @Test
    void givenMongoDbRepository_whenUserRepositoryCalled_thenReturnsMongoUserPersistentRepository() {
        // GIVEN
        MongoClient mockClient = mock(MongoClient.class);
        MongoDatabase mockDatabase = mock(MongoDatabase.class);
        InfrastructureBean.MongoDbRepository spyRepo = spy(mongoDbRepository);
        doReturn(mockClient).when(spyRepo).mongoClient();
        doReturn(mockDatabase).when(spyRepo).mongoDatabase();

        // WHEN
        UserPersistentRepository result = spyRepo.userRepository();

        // THEN
        assertThat(result).isInstanceOf(MongoUserPersistentRepository.class);
    }

    @Test
    void givenMongoDbRepository_whenInfoRepositoryCalled_thenReturnsMongoInfoPersistentRepository() {
        // GIVEN
        MongoClient mockClient = mock(MongoClient.class);
        MongoDatabase mockDatabase = mock(MongoDatabase.class);
        InfrastructureBean.MongoDbRepository spyRepo = spy(mongoDbRepository);
        doReturn(mockClient).when(spyRepo).mongoClient();
        doReturn(mockDatabase).when(spyRepo).mongoDatabase();

        // WHEN
        InfoPersistentRepository result = spyRepo.infoRepository();

        // THEN
        assertThat(result).isInstanceOf(MongoInfoPersistentRepository.class);
    }

    @Test
    void givenRedisCacheRepository_whenConstructed_thenInstanceIsCreated() {
        // GIVEN

        // WHEN
        InfrastructureBean.RedisCacheRepository result = new InfrastructureBean.RedisCacheRepository(redisProperty);

        // THEN
        assertThat(result).isNotNull();
    }
}