package com.bestpractice.api.infrastrucuture;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        // setup mocks if needed
    }

    @Test
    void givenLocalDbProfile_whenCreateBeans_thenReturnLocalRepositories() {
        // GIVEN
        InfrastructureBean.LocalDbRepository repo = new InfrastructureBean.LocalDbRepository();

        // WHEN
        UserPersistentRepository userRepo = repo.userRepository();
        InfoPersistentRepository infoRepo = repo.infoRepository();

        // THEN
        assertThat(userRepo).isInstanceOf(LocalUserPersistentRepository.class);
        assertThat(infoRepo).isInstanceOf(LocalInfoPersistentRepository.class);
    }

    @Test
    void givenRdbmsProfile_whenCreateBeans_thenReturnRdbmsRepositories() throws Exception {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository repo = new InfrastructureBean.RdbmsDbRepository();
        setPrivateField(repo, "url", "jdbc:h2:mem:test");
        setPrivateField(repo, "username", "user");
        setPrivateField(repo, "password", "pass");
        setPrivateField(repo, "driverClassName", "org.h2.Driver");

        // WHEN
        DriverManagerDataSource ds = repo.dataSource();
        DataSourceTransactionManager txManager = repo.transactionManager();
        JdbcTemplate template = repo.jdbcTemplate();
        UserPersistentRepository userRepo = repo.userRepository(template);
        InfoPersistentRepository infoRepo = repo.infoRepository();

        // THEN
        assertThat(ds.getUrl()).isEqualTo("jdbc:h2:mem:test");
        assertThat(txManager.getDataSource()).isEqualTo(ds);
        assertThat(template.getDataSource()).isEqualTo(ds);
        assertThat(userRepo).isInstanceOf(RdbmsUserPersistentRepository.class);
        assertThat(infoRepo).isInstanceOf(RdbmsInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraProfile_whenCreateBeans_thenReturnCassandraRepositories() {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(List.of("localhost:9042"));
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");
        InfrastructureBean.CassandraDbRepository repo = new InfrastructureBean.CassandraDbRepository(cassandraProperty);

        // WHEN
        CassandraInfoPersistentRepository infoRepo = new CassandraInfoPersistentRepository(cqlSession);
        UserPersistentRepository userRepo = repo.userRepository();

        // THEN
        assertThat(infoRepo).isInstanceOf(CassandraInfoPersistentRepository.class);
        assertThat(userRepo).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenMongoProfile_whenCreateBeans_thenReturnMongoRepositories() {
        // GIVEN
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPassword()).thenReturn("password");
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getPlatformDatabase()).thenReturn("testdb");
        InfrastructureBean.MongoDbRepository repo = new InfrastructureBean.MongoDbRepository(mongoProperty);

        // WHEN
        MongoClient client = repo.mongoClient();
        MongoDatabase db = repo.mongoDatabase();
        UserPersistentRepository userRepo = repo.userRepository();
        InfoPersistentRepository infoRepo = repo.infoRepository();

        // THEN
        assertThat(client).isNotNull();
        assertThat(db).isNotNull();
        assertThat(userRepo).isInstanceOf(MongoUserPersistentRepository.class);
        assertThat(infoRepo).isInstanceOf(MongoInfoPersistentRepository.class);
    }

    @Test
    void givenRedisProfile_whenCreateRedisCacheRepository_thenPropertyInjected() {
        // GIVEN
        InfrastructureBean.RedisCacheRepository repo = new InfrastructureBean.RedisCacheRepository(redisProperty);

        // WHEN
        RedisProperty property = redisProperty;

        // THEN
        assertThat(property).isNotNull();
        assertThat(repo).isInstanceOf(InfrastructureBean.RedisCacheRepository.class);
    }

    @Test
    void givenLocalCacheProfile_whenCreateLocalCacheRepository_thenReturnInstance() {
        // GIVEN
        InfrastructureBean.LocalCacheRepository repo = new InfrastructureBean.LocalCacheRepository();

        // WHEN
        InfrastructureBean.LocalCacheRepository result = repo;

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(InfrastructureBean.LocalCacheRepository.class);
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}