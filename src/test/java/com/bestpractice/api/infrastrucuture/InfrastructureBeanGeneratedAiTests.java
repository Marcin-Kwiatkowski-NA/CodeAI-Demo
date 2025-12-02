package com.bestpractice.api.infrastrucuture;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

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

@ExtendWith(MockitoExtension.class)
class InfrastructureBeanGeneratedAiTests {

    @Mock
    private RedisProperty redisProperty;

    @Mock
    private CassandraProperty cassandraProperty;

    @Mock
    private MongoProperty mongoProperty;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private DriverManagerDataSource driverManagerDataSource;

    @Mock
    private CqlSession cqlSession;

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    private InfrastructureBean.LocalDbRepository localDbRepository;
    private InfrastructureBean.RdbmsDbRepository rdbmsDbRepository;
    private InfrastructureBean.CassandraDbRepository cassandraDbRepository;
    private InfrastructureBean.MongoDbRepository mongoDbRepository;

    @BeforeEach
    void setUp() {
        localDbRepository = new InfrastructureBean.LocalDbRepository();
        rdbmsDbRepository = spy(new InfrastructureBean.RdbmsDbRepository());
        cassandraDbRepository = new InfrastructureBean.CassandraDbRepository(cassandraProperty);
        mongoDbRepository = new InfrastructureBean.MongoDbRepository(mongoProperty);
    }

    @Test
    void givenLocalDbRepository_whenUserRepositoryCalled_thenReturnsLocalUserPersistentRepository() {
        // GIVEN
        // No setup required

        // WHEN
        UserPersistentRepository userRepository = localDbRepository.userRepository();

        // THEN
        assertThat(userRepository).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenLocalDbRepository_whenInfoRepositoryCalled_thenReturnsLocalInfoPersistentRepository() {
        // GIVEN
        // No setup required

        // WHEN
        InfoPersistentRepository infoRepository = localDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(LocalInfoPersistentRepository.class);
    }

    @Test
    void givenRdbmsDbRepository_whenDataSourceCalled_thenReturnsDriverManagerDataSource() {
        // GIVEN
        doReturn("jdbc:mysql://localhost:3306/test").when(rdbmsDbRepository).getUrl();
        doReturn("root").when(rdbmsDbRepository).getUsername();
        doReturn("password").when(rdbmsDbRepository).getPassword();
        doReturn("com.mysql.cj.jdbc.Driver").when(rdbmsDbRepository).getDriverClassName();

        // WHEN
        DriverManagerDataSource dataSource = rdbmsDbRepository.dataSource();

        // THEN
        assertThat(dataSource).isNotNull();
        assertThat(dataSource.getUrl()).isEqualTo("jdbc:mysql://localhost:3306/test");
        assertThat(dataSource.getUsername()).isEqualTo("root");
        assertThat(dataSource.getPassword()).isEqualTo("password");
        assertThat(dataSource.getDriverClassName()).isEqualTo("com.mysql.cj.jdbc.Driver");
    }

    @Test
    void givenRdbmsDbRepository_whenUserRepositoryCalled_thenReturnsRdbmsUserPersistentRepository() {
        // GIVEN
        // No setup required

        // WHEN
        UserPersistentRepository userRepository = rdbmsDbRepository.userRepository(jdbcTemplate);

        // THEN
        assertThat(userRepository).isInstanceOf(RdbmsUserPersistentRepository.class);
    }

    @Test
    void givenRdbmsDbRepository_whenInfoRepositoryCalled_thenReturnsRdbmsInfoPersistentRepository() {
        // GIVEN
        // No setup required

        // WHEN
        InfoPersistentRepository infoRepository = rdbmsDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(RdbmsInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraDbRepository_whenCqlSessionCalled_thenReturnsCqlSession() {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(java.util.Arrays.asList("127.0.0.1:9042"));
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");

        // WHEN
        CqlSession session = cassandraDbRepository.cqlSession();

        // THEN
        assertThat(session).isNotNull();
    }

    @Test
    void givenCassandraDbRepository_whenInfoRepositoryCalled_thenReturnsCassandraInfoPersistentRepository() {
        // GIVEN
        // No setup required

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

        // WHEN
        MongoClient client = mongoDbRepository.mongoClient();

        // THEN
        assertThat(client).isNotNull();
    }

    @Test
    void givenMongoDbRepository_whenMongoDatabaseCalled_thenReturnsMongoDatabase() {
        // GIVEN
        when(mongoProperty.getPlatformDatabase()).thenReturn("test_db");
        when(mongoClient.getDatabase("test_db")).thenReturn(mongoDatabase);

        // WHEN
        MongoDatabase database = mongoDbRepository.mongoDatabase();

        // THEN
        assertThat(database).isNotNull();
    }

    @Test
    void givenMongoDbRepository_whenUserRepositoryCalled_thenReturnsMongoUserPersistentRepository() {
        // GIVEN
        when(mongoClient.getDatabase(anyString())).thenReturn(mongoDatabase);

        // WHEN
        UserPersistentRepository userRepository = mongoDbRepository.userRepository();

        // THEN
        assertThat(userRepository).isInstanceOf(MongoUserPersistentRepository.class);
    }

    @Test
    void givenMongoDbRepository_whenInfoRepositoryCalled_thenReturnsMongoInfoPersistentRepository() {
        // GIVEN
        when(mongoClient.getDatabase(anyString())).thenReturn(mongoDatabase);

        // WHEN
        InfoPersistentRepository infoRepository = mongoDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(MongoInfoPersistentRepository.class);
    }
}
