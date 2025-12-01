package com.bestpractice.api.infrastrucuture;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class InfrastructureBeanGeneratedAiTests {

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

    @Mock
    private DriverManagerDataSource driverManagerDataSource;

    @Mock
    private CqlSession cqlSession;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenLocalDbRepository_whenUserRepositoryCalled_thenReturnsLocalUserPersistentRepository() {
        // GIVEN
        InfrastructureBean.LocalDbRepository localDbRepository = new InfrastructureBean.LocalDbRepository();

        // WHEN
        UserPersistentRepository userRepository = localDbRepository.userRepository();

        // THEN
        assertThat(userRepository).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenLocalDbRepository_whenInfoRepositoryCalled_thenReturnsLocalInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.LocalDbRepository localDbRepository = new InfrastructureBean.LocalDbRepository();

        // WHEN
        InfoPersistentRepository infoRepository = localDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(LocalInfoPersistentRepository.class);
    }

    @Test
    void givenRdbmsDbRepository_whenDataSourceCalled_thenReturnsDriverManagerDataSource() {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository rdbmsDbRepository = new InfrastructureBean.RdbmsDbRepository();
        rdbmsDbRepository.setUrl("jdbc:mysql://localhost:3306/testdb");
        rdbmsDbRepository.setUsername("testuser");
        rdbmsDbRepository.setPassword("testpassword");
        rdbmsDbRepository.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // WHEN
        DriverManagerDataSource dataSource = rdbmsDbRepository.dataSource();

        // THEN
        assertThat(dataSource).isNotNull();
        assertThat(dataSource.getUrl()).isEqualTo("jdbc:mysql://localhost:3306/testdb");
        assertThat(dataSource.getUsername()).isEqualTo("testuser");
        assertThat(dataSource.getPassword()).isEqualTo("testpassword");
        assertThat(dataSource.getDriverClassName()).isEqualTo("com.mysql.cj.jdbc.Driver");
    }

    @Test
    void givenRdbmsDbRepository_whenTransactionManagerCalled_thenReturnsDataSourceTransactionManager() {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository rdbmsDbRepository = spy(new InfrastructureBean.RdbmsDbRepository());
        doReturn(driverManagerDataSource).when(rdbmsDbRepository).dataSource();

        // WHEN
        var transactionManager = rdbmsDbRepository.transactionManager();

        // THEN
        assertThat(transactionManager).isNotNull();
        assertThat(transactionManager.getDataSource()).isEqualTo(driverManagerDataSource);
    }

    @Test
    void givenRdbmsDbRepository_whenJdbcTemplateCalled_thenReturnsJdbcTemplate() {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository rdbmsDbRepository = spy(new InfrastructureBean.RdbmsDbRepository());
        doReturn(driverManagerDataSource).when(rdbmsDbRepository).dataSource();

        // WHEN
        JdbcTemplate jdbcTemplate = rdbmsDbRepository.jdbcTemplate();

        // THEN
        assertThat(jdbcTemplate).isNotNull();
        assertThat(jdbcTemplate.getDataSource()).isEqualTo(driverManagerDataSource);
    }

    @Test
    void givenRdbmsDbRepository_whenUserRepositoryCalled_thenReturnsRdbmsUserPersistentRepository() {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository rdbmsDbRepository = spy(new InfrastructureBean.RdbmsDbRepository());
        doReturn(jdbcTemplate).when(rdbmsDbRepository).jdbcTemplate();

        // WHEN
        UserPersistentRepository userRepository = rdbmsDbRepository.userRepository(jdbcTemplate);

        // THEN
        assertThat(userRepository).isInstanceOf(RdbmsUserPersistentRepository.class);
    }

    @Test
    void givenRdbmsDbRepository_whenInfoRepositoryCalled_thenReturnsRdbmsInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository rdbmsDbRepository = spy(new InfrastructureBean.RdbmsDbRepository());
        doReturn(jdbcTemplate).when(rdbmsDbRepository).jdbcTemplate();

        // WHEN
        InfoPersistentRepository infoRepository = rdbmsDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(RdbmsInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraDbRepository_whenCqlSessionCalled_thenReturnsCqlSession() {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(List.of("127.0.0.1:9042"));
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");
        InfrastructureBean.CassandraDbRepository cassandraDbRepository = new InfrastructureBean.CassandraDbRepository(cassandraProperty);

        // WHEN
        CqlSession session = cassandraDbRepository.cqlSession();

        // THEN
        assertThat(session).isNotNull();
    }

    @Test
    void givenCassandraDbRepository_whenUserRepositoryCalled_thenReturnsLocalUserPersistentRepository() {
        // GIVEN
        InfrastructureBean.CassandraDbRepository cassandraDbRepository = new InfrastructureBean.CassandraDbRepository(cassandraProperty);

        // WHEN
        UserPersistentRepository userRepository = cassandraDbRepository.userRepository();

        // THEN
        assertThat(userRepository).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenCassandraDbRepository_whenInfoRepositoryCalled_thenReturnsCassandraInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.CassandraDbRepository cassandraDbRepository = spy(new InfrastructureBean.CassandraDbRepository(cassandraProperty));
        doReturn(cqlSession).when(cassandraDbRepository).cqlSession();

        // WHEN
        InfoPersistentRepository infoRepository = cassandraDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(CassandraInfoPersistentRepository.class);
    }

    @Test
    void givenMongoDbRepository_whenMongoClientCalled_thenReturnsMongoClient() {
        // GIVEN
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getUser()).thenReturn("test_user");
        when(mongoProperty.getPassword()).thenReturn("test_password");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        InfrastructureBean.MongoDbRepository mongoDbRepository = new InfrastructureBean.MongoDbRepository(mongoProperty);

        // WHEN
        MongoClient client = mongoDbRepository.mongoClient();

        // THEN
        assertThat(client).isNotNull();
    }

    @Test
    void givenMongoDbRepository_whenMongoDatabaseCalled_thenReturnsMongoDatabase() {
        // GIVEN
        InfrastructureBean.MongoDbRepository mongoDbRepository = spy(new InfrastructureBean.MongoDbRepository(mongoProperty));
        doReturn(mongoClient).when(mongoDbRepository).mongoClient();
        when(mongoProperty.getPlatformDatabase()).thenReturn("test_database");

        // WHEN
        MongoDatabase database = mongoDbRepository.mongoDatabase();

        // THEN
        assertThat(database).isNotNull();
    }

    @Test
    void givenMongoDbRepository_whenUserRepositoryCalled_thenReturnsMongoUserPersistentRepository() {
        // GIVEN
        InfrastructureBean.MongoDbRepository mongoDbRepository = spy(new InfrastructureBean.MongoDbRepository(mongoProperty));
        doReturn(mongoClient).when(mongoDbRepository).mongoClient();
        doReturn(mongoDatabase).when(mongoDbRepository).mongoDatabase();

        // WHEN
        UserPersistentRepository userRepository = mongoDbRepository.userRepository();

        // THEN
        assertThat(userRepository).isInstanceOf(MongoUserPersistentRepository.class);
    }

    @Test
    void givenMongoDbRepository_whenInfoRepositoryCalled_thenReturnsMongoInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.MongoDbRepository mongoDbRepository = spy(new InfrastructureBean.MongoDbRepository(mongoProperty));
        doReturn(mongoClient).when(mongoDbRepository).mongoClient();
        doReturn(mongoDatabase).when(mongoDbRepository).mongoDatabase();

        // WHEN
        InfoPersistentRepository infoRepository = mongoDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(MongoInfoPersistentRepository.class);
    }
}