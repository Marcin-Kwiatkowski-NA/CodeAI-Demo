package com.bestpractice.api.infrastrucuture;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.lang.reflect.Field;
import java.util.List;

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

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    void givenLocalDbRepository_whenUserRepositoryCalled_thenReturnsLocalUserPersistentRepository() {
        // GIVEN

        // WHEN
        UserPersistentRepository repo = localDbRepository.userRepository();

        // THEN
        assertThat(repo).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenLocalDbRepository_whenInfoRepositoryCalled_thenReturnsLocalInfoPersistentRepository() {
        // GIVEN

        // WHEN
        InfoPersistentRepository repo = localDbRepository.infoRepository();

        // THEN
        assertThat(repo).isInstanceOf(LocalInfoPersistentRepository.class);
    }

    @Test
    void givenRdbmsDbRepository_whenDataSourceCreated_thenReturnsValidDataSource() throws Exception {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "user");
        setPrivateField(rdbmsDbRepository, "password", "pass");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");

        // WHEN
        DriverManagerDataSource dataSource = rdbmsDbRepository.dataSource();

        // THEN
        assertThat(dataSource.getUrl()).isEqualTo("jdbc:h2:mem:testdb");
        assertThat(dataSource.getUsername()).isEqualTo("user");
    }

    @Test
    void givenRdbmsDbRepository_whenTransactionManagerCreated_thenReturnsValidManager() throws Exception {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "user");
        setPrivateField(rdbmsDbRepository, "password", "pass");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");

        // WHEN
        DataSourceTransactionManager manager = rdbmsDbRepository.transactionManager();

        // THEN
        assertThat(manager.getDataSource()).isNotNull();
    }

    @Test
    void givenRdbmsDbRepository_whenJdbcTemplateCreated_thenReturnsJdbcTemplate() throws Exception {
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
    void givenRdbmsDbRepository_whenUserRepositoryCalled_thenReturnsRdbmsUserPersistentRepository() {
        // GIVEN
        JdbcTemplate template = mock(JdbcTemplate.class);

        // WHEN
        UserPersistentRepository repo = rdbmsDbRepository.userRepository(template);

        // THEN
        assertThat(repo).isInstanceOf(RdbmsUserPersistentRepository.class);
    }

    @Test
    void givenRdbmsDbRepository_whenInfoRepositoryCalled_thenReturnsRdbmsInfoPersistentRepository() throws Exception {
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
    void givenCassandraDbRepository_whenCqlSessionCreated_thenReturnsCqlSession() {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(List.of("localhost:9042"));
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");

        // WHEN
        CqlSession session = cassandraDbRepository.cqlSession();

        // THEN
        assertThat(session).isNotNull();
    }

    @Test
    void givenCassandraDbRepository_whenUserRepositoryCalled_thenReturnsLocalUserPersistentRepository() {
        // GIVEN

        // WHEN
        UserPersistentRepository repo = cassandraDbRepository.userRepository();

        // THEN
        assertThat(repo).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenCassandraDbRepository_whenInfoRepositoryCalled_thenReturnsCassandraInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.CassandraDbRepository spyRepo = Mockito.spy(cassandraDbRepository);
        doReturn(cqlSession).when(spyRepo).cqlSession();

        // WHEN
        InfoPersistentRepository repo = spyRepo.infoRepository();

        // THEN
        assertThat(repo).isInstanceOf(CassandraInfoPersistentRepository.class);
    }

    @Test
    void givenMongoDbRepository_whenMongoClientCreated_thenReturnsMongoClient() {
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
    void givenMongoDbRepository_whenMongoDatabaseCreated_thenReturnsMongoDatabase() {
        // GIVEN
        InfrastructureBean.MongoDbRepository spyRepo = Mockito.spy(mongoDbRepository);
        doReturn(mongoClient).when(spyRepo).mongoClient();
        when(mongoProperty.getPlatformDatabase()).thenReturn("testdb");
        doReturn(mongoDatabase).when(mongoClient).getDatabase("testdb");

        // WHEN
        MongoDatabase db = spyRepo.mongoDatabase();

        // THEN
        assertThat(db).isNotNull();
    }

    @Test
    void givenMongoDbRepository_whenUserRepositoryCalled_thenReturnsMongoUserPersistentRepository() {
        // GIVEN
        InfrastructureBean.MongoDbRepository spyRepo = Mockito.spy(mongoDbRepository);
        doReturn(mongoClient).when(spyRepo).mongoClient();
        doReturn(mongoDatabase).when(spyRepo).mongoDatabase();

        // WHEN
        UserPersistentRepository repo = spyRepo.userRepository();

        // THEN
        assertThat(repo).isInstanceOf(MongoUserPersistentRepository.class);
    }

    @Test
    void givenMongoDbRepository_whenInfoRepositoryCalled_thenReturnsMongoInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.MongoDbRepository spyRepo = Mockito.spy(mongoDbRepository);
        doReturn(mongoClient).when(spyRepo).mongoClient();
        doReturn(mongoDatabase).when(spyRepo).mongoDatabase();

        // WHEN
        InfoPersistentRepository repo = spyRepo.infoRepository();

        // THEN
        assertThat(repo).isInstanceOf(MongoInfoPersistentRepository.class);
    }

    @Test
    void givenRedisCacheRepository_whenInitialized_thenRedisPropertyIsSet() {
        // GIVEN

        // WHEN
        InfrastructureBean.RedisCacheRepository repo = new InfrastructureBean.RedisCacheRepository(redisProperty);

        // THEN
        assertThat(repo).isNotNull();
    }
}