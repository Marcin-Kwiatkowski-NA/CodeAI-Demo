package com.bestpractice.api.infrastrucuture;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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


@ExtendWith(MockitoExtension.class)
public class InfrastructureBeanGeneratedAiTests {

    @Mock
    private RedisProperty redisProperty;

    @Mock
    private CassandraProperty cassandraProperty;

    @Mock
    private MongoProperty mongoProperty;

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private InfrastructureBean.LocalDbRepository localDbRepository;
    private InfrastructureBean.RdbmsDbRepository rdbmsDbRepository;
    private InfrastructureBean.CassandraDbRepository cassandraDbRepository;
    private InfrastructureBean.MongoDbRepository mongoDbRepository;
    private InfrastructureBean.RedisCacheRepository redisCacheRepository;

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
    void givenLocalDbProfile_whenCreateUserRepository_thenReturnLocalUserPersistentRepository() {
        // GIVEN
        // WHEN
        UserPersistentRepository result = localDbRepository.userRepository();
        // THEN
        assertThat(result).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenLocalDbProfile_whenCreateInfoRepository_thenReturnLocalInfoPersistentRepository() {
        // GIVEN
        // WHEN
        InfoPersistentRepository result = localDbRepository.infoRepository();
        // THEN
        assertThat(result).isInstanceOf(LocalInfoPersistentRepository.class);
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
    void givenRdbmsProfile_whenCreateJdbcTemplate_thenReturnJdbcTemplate() {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "user");
        setPrivateField(rdbmsDbRepository, "password", "pass");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");
        // WHEN
        JdbcTemplate template = rdbmsDbRepository.jdbcTemplate();
        // THEN
        assertThat(template).isNotNull();
    }

    @Test
    void givenRdbmsProfile_whenCreateUserRepository_thenReturnRdbmsUserPersistentRepository() {
        // GIVEN
        // WHEN
        UserPersistentRepository result = rdbmsDbRepository.userRepository(jdbcTemplate);
        // THEN
        assertThat(result).isInstanceOf(RdbmsUserPersistentRepository.class);
    }

    @Test
    void givenRdbmsProfile_whenCreateInfoRepository_thenReturnRdbmsInfoPersistentRepository() {
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
    void givenCassandraProfile_whenCreateCqlSession_thenReturnCqlSession() {
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
    void givenCassandraProfile_whenCreateUserRepository_thenReturnLocalUserPersistentRepository() {
        // GIVEN
        // WHEN
        UserPersistentRepository result = cassandraDbRepository.userRepository();
        // THEN
        assertThat(result).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenCassandraProfile_whenCreateInfoRepository_thenReturnCassandraInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.CassandraDbRepository spyRepo = spy(cassandraDbRepository);
        CqlSession mockSession = mock(CqlSession.class);
        doReturn(mockSession).when(spyRepo).cqlSession();
        // WHEN
        InfoPersistentRepository result = spyRepo.infoRepository();
        // THEN
        assertThat(result).isInstanceOf(CassandraInfoPersistentRepository.class);
    }

    @Test
    void givenMongoProfile_whenCreateMongoClient_thenReturnMongoClient() {
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
    void givenMongoProfile_whenCreateMongoDatabase_thenReturnMongoDatabase() {
        // GIVEN
        InfrastructureBean.MongoDbRepository spyRepo = spy(mongoDbRepository);
        doReturn(mongoClient).when(spyRepo).mongoClient();
        when(mongoProperty.getPlatformDatabase()).thenReturn("testdb");
        when(mongoClient.getDatabase("testdb")).thenReturn(mongoDatabase);
        // WHEN
        MongoDatabase result = spyRepo.mongoDatabase();
        // THEN
        assertThat(result).isEqualTo(mongoDatabase);
    }

    @Test
    void givenMongoProfile_whenCreateUserRepository_thenReturnMongoUserPersistentRepository() {
        // GIVEN
        InfrastructureBean.MongoDbRepository spyRepo = spy(mongoDbRepository);
        doReturn(mongoClient).when(spyRepo).mongoClient();
        doReturn(mongoDatabase).when(spyRepo).mongoDatabase();
        // WHEN
        UserPersistentRepository result = spyRepo.userRepository();
        // THEN
        assertThat(result).isInstanceOf(MongoUserPersistentRepository.class);
    }

    @Test
    void givenMongoProfile_whenCreateInfoRepository_thenReturnMongoInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.MongoDbRepository spyRepo = spy(mongoDbRepository);
        doReturn(mongoClient).when(spyRepo).mongoClient();
        doReturn(mongoDatabase).when(spyRepo).mongoDatabase();
        // WHEN
        InfoPersistentRepository result = spyRepo.infoRepository();
        // THEN
        assertThat(result).isInstanceOf(MongoInfoPersistentRepository.class);
    }

    @Test
    void givenRedisProfile_whenCreateRedisCacheRepository_thenInstanceCreated() {
        // GIVEN
        // WHEN
        InfrastructureBean.RedisCacheRepository result = new InfrastructureBean.RedisCacheRepository(redisProperty);
        // THEN
        assertThat(result).isNotNull();
    }
}
