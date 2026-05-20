package com.bestpractice.api.infrastrucuture;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.cache.redis.RedisProperty;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InfrastructureBeanGeneratedAiTests {

    @Mock
    private RedisProperty redisProperty;

    @Mock
    private CassandraProperty cassandraProperty;

    @Mock
    private MongoProperty mongoProperty;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    private InfrastructureBean.RdbmsDbRepository rdbmsDbRepository;

    @BeforeEach
    void setUp() throws Exception {
        rdbmsDbRepository = new InfrastructureBean.RdbmsDbRepository();
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "user");
        setPrivateField(rdbmsDbRepository, "password", "pass");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    void givenLocalDbProfile_whenCreateRepositories_thenBeansAreCreated() {
        InfrastructureBean.LocalDbRepository repo = new InfrastructureBean.LocalDbRepository();
        UserPersistentRepository userRepo = repo.userRepository();
        InfoPersistentRepository infoRepo = repo.infoRepository();
        assertThat(userRepo).isInstanceOf(LocalUserPersistentRepository.class);
        assertThat(infoRepo).isInstanceOf(LocalInfoPersistentRepository.class);
    }

    @Test
    void givenRdbmsProfile_whenCreateDataSource_thenDataSourceIsConfigured() {
        DriverManagerDataSource dataSource = rdbmsDbRepository.dataSource();
        assertThat(dataSource.getUrl()).isEqualTo("jdbc:h2:mem:testdb");
        assertThat(dataSource.getUsername()).isEqualTo("user");
        assertThat(dataSource.getPassword()).isEqualTo("pass");
    }

    @Test
    void givenRdbmsProfile_whenCreateTransactionManager_thenManagerIsCreated() {
        DriverManagerDataSource ds = rdbmsDbRepository.dataSource();
        DataSourceTransactionManager txManager = rdbmsDbRepository.transactionManager();
        assertThat(txManager.getDataSource()).isNotNull();
    }

    @Test
    void givenRdbmsProfile_whenCreateJdbcTemplate_thenTemplateIsCreated() {
        JdbcTemplate template = rdbmsDbRepository.jdbcTemplate();
        assertThat(template).isNotNull();
    }

    @Test
    void givenRdbmsProfile_whenCreateRepositories_thenRepositoriesAreCreated() {
        JdbcTemplate template = rdbmsDbRepository.jdbcTemplate();
        UserPersistentRepository userRepo = rdbmsDbRepository.userRepository(template);
        InfoPersistentRepository infoRepo = rdbmsDbRepository.infoRepository();
        assertThat(userRepo).isInstanceOf(RdbmsUserPersistentRepository.class);
        assertThat(infoRepo).isInstanceOf(RdbmsInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraProfile_whenCreateSession_thenSessionIsBuilt() {
        when(cassandraProperty.getHosts()).thenReturn(new String[]{"127.0.0.1:9042"});
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");
        InfrastructureBean.CassandraDbRepository repo = new InfrastructureBean.CassandraDbRepository(cassandraProperty);
        CqlSession session = repo.cqlSession();
        assertThat(session).isNotNull();
        session.close();
    }

    @Test
    void givenCassandraProfile_whenCreateRepositories_thenRepositoriesAreCreated() {
        when(cassandraProperty.getHosts()).thenReturn(new String[]{"127.0.0.1:9042"});
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");
        InfrastructureBean.CassandraDbRepository repo = new InfrastructureBean.CassandraDbRepository(cassandraProperty);
        UserPersistentRepository userRepo = repo.userRepository();
        InfoPersistentRepository infoRepo = repo.infoRepository();
        assertThat(userRepo).isInstanceOf(LocalUserPersistentRepository.class);
        assertThat(infoRepo).isInstanceOf(com.bestpractice.api.infrastrucuture.persistent.cassandra.CassandraInfoPersistentRepository.class);
    }

    @Test
    void givenMongoProfile_whenCreateMongoClient_thenClientIsCreated() {
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPassword()).thenReturn("pass");
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getPlatformDatabase()).thenReturn("platform");
        InfrastructureBean.MongoDbRepository repo = new InfrastructureBean.MongoDbRepository(mongoProperty);
        MongoClient client = repo.mongoClient();
        assertThat(client).isNotNull();
        client.close();
    }

    @Test
    void givenMongoProfile_whenCreateMongoDatabase_thenDatabaseIsCreated() {
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPassword()).thenReturn("pass");
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getPlatformDatabase()).thenReturn("platform");
        InfrastructureBean.MongoDbRepository repo = new InfrastructureBean.MongoDbRepository(mongoProperty);
        MongoDatabase db = repo.mongoDatabase();
        assertThat(db).isNotNull();
    }

    @Test
    void givenMongoProfile_whenCreateRepositories_thenRepositoriesAreCreated() {
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPassword()).thenReturn("pass");
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getPlatformDatabase()).thenReturn("platform");
        InfrastructureBean.MongoDbRepository repo = new InfrastructureBean.MongoDbRepository(mongoProperty);
        UserPersistentRepository userRepo = repo.userRepository();
        InfoPersistentRepository infoRepo = repo.infoRepository();
        assertThat(userRepo).isInstanceOf(MongoUserPersistentRepository.class);
        assertThat(infoRepo).isInstanceOf(MongoInfoPersistentRepository.class);
    }

    @Test
    void givenRedisProfile_whenCreateRedisCacheRepository_thenRepositoryIsCreated() {
        InfrastructureBean.RedisCacheRepository repo = new InfrastructureBean.RedisCacheRepository(redisProperty);
        assertThat(repo).isNotNull();
    }
}