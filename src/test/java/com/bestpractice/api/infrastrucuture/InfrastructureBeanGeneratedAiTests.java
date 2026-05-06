package com.bestpractice.api.infrastrucuture;

import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void givenLocalDbProfile_whenUserRepositoryCalled_thenReturnLocalUserPersistentRepository() {
        // GIVEN
        // WHEN
        UserPersistentRepository repo = localDbRepository.userRepository();
        // THEN
        assertThat(repo).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenLocalDbProfile_whenInfoRepositoryCalled_thenReturnLocalInfoPersistentRepository() {
        // GIVEN
        // WHEN
        InfoPersistentRepository repo = localDbRepository.infoRepository();
        // THEN
        assertThat(repo).isInstanceOf(LocalInfoPersistentRepository.class);
    }

    @Test
    void givenRdbmsProfile_whenDataSourceCreated_thenReturnValidDataSource() {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "user");
        setPrivateField(rdbmsDbRepository, "password", "pass");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");
        // WHEN
        DriverManagerDataSource ds = rdbmsDbRepository.dataSource();
        // THEN
        assertThat(ds.getUrl()).isEqualTo("jdbc:h2:mem:testdb");
        assertThat(ds.getUsername()).isEqualTo("user");
    }

    @Test
    void givenRdbmsProfile_whenUserRepositoryCalled_thenReturnRdbmsUserPersistentRepository() {
        // GIVEN
        JdbcTemplate template = mock(JdbcTemplate.class);
        // WHEN
        UserPersistentRepository repo = rdbmsDbRepository.userRepository(template);
        // THEN
        assertThat(repo).isInstanceOf(RdbmsUserPersistentRepository.class);
    }

    @Test
    void givenRdbmsProfile_whenInfoRepositoryCalled_thenReturnRdbmsInfoPersistentRepository() {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "user");
        setPrivateField(rdbmsDbRepository, "password", "pass");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");
        // WHEN
        InfoPersistentRepository repo = rdbmsDbRepository.infoRepository();
        // THEN
        assertThat(repo).isInstanceOf(RdbmsInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraProfile_whenCqlSessionCreated_thenReturnNonNullSession() {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(new String[]{"localhost:9042"});
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");
        // WHEN
        CqlSession session = cassandraDbRepository.cqlSession();
        // THEN
        assertThat(session).isNotNull();
        session.close();
    }

    @Test
    void givenCassandraProfile_whenInfoRepositoryCalled_thenReturnCassandraInfoPersistentRepository() {
        // GIVEN
        CqlSession session = mock(CqlSession.class);
        InfrastructureBean.CassandraDbRepository repo = spy(new InfrastructureBean.CassandraDbRepository(cassandraProperty));
        doReturn(session).when(repo).cqlSession();
        // WHEN
        InfoPersistentRepository infoRepo = repo.infoRepository();
        // THEN
        assertThat(infoRepo).isInstanceOf(CassandraInfoPersistentRepository.class);
    }

    @Test
    void givenMongoProfile_whenMongoClientCreated_thenReturnNonNullClient() {
        // GIVEN
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPassword()).thenReturn("pass");
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        // WHEN
        MongoClient client = mongoDbRepository.mongoClient();
        // THEN
        assertThat(client).isNotNull();
        client.close();
    }

    @Test
    void givenMongoProfile_whenMongoDatabaseCreated_thenReturnNonNullDatabase() {
        // GIVEN
        InfrastructureBean.MongoDbRepository repo = spy(new InfrastructureBean.MongoDbRepository(mongoProperty));
        doReturn(mongoClient).when(repo).mongoClient();
        when(mongoProperty.getPlatformDatabase()).thenReturn("testdb");
        when(mongoClient.getDatabase("testdb")).thenReturn(mongoDatabase);
        // WHEN
        MongoDatabase db = repo.mongoDatabase();
        // THEN
        assertThat(db).isNotNull();
    }

    @Test
    void givenMongoProfile_whenUserRepositoryCalled_thenReturnMongoUserPersistentRepository() {
        // GIVEN
        InfrastructureBean.MongoDbRepository repo = spy(new InfrastructureBean.MongoDbRepository(mongoProperty));
        doReturn(mongoClient).when(repo).mongoClient();
        doReturn(mongoDatabase).when(repo).mongoDatabase();
        // WHEN
        UserPersistentRepository userRepo = repo.userRepository();
        // THEN
        assertThat(userRepo).isInstanceOf(MongoUserPersistentRepository.class);
    }

    @Test
    void givenMongoProfile_whenInfoRepositoryCalled_thenReturnMongoInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.MongoDbRepository repo = spy(new InfrastructureBean.MongoDbRepository(mongoProperty));
        doReturn(mongoClient).when(repo).mongoClient();
        doReturn(mongoDatabase).when(repo).mongoDatabase();
        // WHEN
        InfoPersistentRepository infoRepo = repo.infoRepository();
        // THEN
        assertThat(infoRepo).isInstanceOf(MongoInfoPersistentRepository.class);
    }

    @Test
    void givenRedisProfile_whenConstructed_thenNotNull() {
        // GIVEN
        // WHEN
        InfrastructureBean.RedisCacheRepository repo = new InfrastructureBean.RedisCacheRepository(redisProperty);
        // THEN
        assertThat(repo).isNotNull();
    }
}
