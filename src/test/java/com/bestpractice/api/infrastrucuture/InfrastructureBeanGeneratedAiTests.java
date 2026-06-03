package com.bestpractice.api.infrastrucuture;

import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.bestpractice.api.infrastrucuture.cache.redis.RedisProperty;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraProperty;
import com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoProperty;
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
import java.net.InetSocketAddress;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class InfrastructureBeanGeneratedAiTests {

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
    void givenLocalProfile_whenCreateUserRepository_thenReturnLocalUserPersistentRepository() {
        // GIVEN
        // WHEN
        UserPersistentRepository repository = localDbRepository.userRepository();
        // THEN
        assertThat(repository).isNotNull();
    }

    @Test
    void givenLocalProfile_whenCreateInfoRepository_thenReturnLocalInfoPersistentRepository() {
        // GIVEN
        // WHEN
        InfoPersistentRepository repository = localDbRepository.infoRepository();
        // THEN
        assertThat(repository).isNotNull();
    }

    @Test
    void givenRdbmsProfile_whenCreateDataSource_thenReturnDriverManagerDataSource() throws Exception {
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
    void givenRdbmsProfile_whenCreateTransactionManager_thenReturnDataSourceTransactionManager() throws Exception {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "sa");
        setPrivateField(rdbmsDbRepository, "password", "");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");
        // WHEN
        DataSourceTransactionManager txManager = rdbmsDbRepository.transactionManager();
        // THEN
        assertThat(txManager).isNotNull();
    }

    @Test
    void givenRdbmsProfile_whenCreateJdbcTemplate_thenReturnJdbcTemplate() throws Exception {
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
    void givenRdbmsProfile_whenCreateUserRepository_thenReturnRdbmsUserPersistentRepository() {
        // GIVEN
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        // WHEN
        UserPersistentRepository repository = rdbmsDbRepository.userRepository(jdbcTemplate);
        // THEN
        assertThat(repository).isNotNull();
    }

    @Test
    void givenRdbmsProfile_whenCreateInfoRepository_thenReturnRdbmsInfoPersistentRepository() throws Exception {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "sa");
        setPrivateField(rdbmsDbRepository, "password", "");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");
        // WHEN
        InfoPersistentRepository repository = rdbmsDbRepository.infoRepository();
        // THEN
        assertThat(repository).isNotNull();
    }

    @Test
    void givenCassandraProfile_whenCreateCqlSession_thenReturnCqlSession() {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(List.of("localhost:9042"));
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");
        // WHEN
        CqlSession session = cassandraDbRepository.cqlSession();
        // THEN
        assertThat(session).isNotNull();
        session.close();
    }

    @Test
    void givenCassandraProfile_whenCreateInfoRepository_thenReturnCassandraInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.CassandraDbRepository cassandraRepo = mock(InfrastructureBean.CassandraDbRepository.class);
        when(cassandraRepo.cqlSession()).thenReturn(cqlSession);
        when(cassandraRepo.infoRepository()).thenCallRealMethod();
        // WHEN
        InfoPersistentRepository repository = cassandraRepo.infoRepository();
        // THEN
        assertThat(repository).isNotNull();
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
        InfrastructureBean.MongoDbRepository mongoRepo = mock(InfrastructureBean.MongoDbRepository.class);
        when(mongoRepo.mongoClient()).thenReturn(mongoClient);
        when(mongoRepo.mongoDatabase()).thenCallRealMethod();
        when(mongoProperty.getPlatformDatabase()).thenReturn("testdb");
        when(mongoClient.getDatabase("testdb")).thenReturn(mongoDatabase);
        // WHEN
        MongoDatabase db = mongoRepo.mongoDatabase();
        // THEN
        assertThat(db).isNotNull();
    }

    @Test
    void givenMongoProfile_whenCreateUserRepository_thenReturnMongoUserPersistentRepository() {
        // GIVEN
        InfrastructureBean.MongoDbRepository mongoRepo = mock(InfrastructureBean.MongoDbRepository.class);
        when(mongoRepo.mongoClient()).thenReturn(mongoClient);
        when(mongoRepo.mongoDatabase()).thenReturn(mongoDatabase);
        when(mongoRepo.userRepository()).thenCallRealMethod();
        // WHEN
        UserPersistentRepository repository = mongoRepo.userRepository();
        // THEN
        assertThat(repository).isNotNull();
    }

    @Test
    void givenMongoProfile_whenCreateInfoRepository_thenReturnMongoInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.MongoDbRepository mongoRepo = mock(InfrastructureBean.MongoDbRepository.class);
        when(mongoRepo.mongoClient()).thenReturn(mongoClient);
        when(mongoRepo.mongoDatabase()).thenReturn(mongoDatabase);
        when(mongoRepo.infoRepository()).thenCallRealMethod();
        // WHEN
        InfoPersistentRepository repository = mongoRepo.infoRepository();
        // THEN
        assertThat(repository).isNotNull();
    }

    @Test
    void givenRedisProfile_whenCreateRedisCacheRepository_thenInstanceCreated() {
        // GIVEN
        // WHEN
        InfrastructureBean.RedisCacheRepository repo = new InfrastructureBean.RedisCacheRepository(redisProperty);
        // THEN
        assertThat(repo).isNotNull();
    }
}
