package com.bestpractice.api.infrastrucuture;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
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
    private CqlSession cqlSession;

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private DriverManagerDataSource dataSource;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private InfrastructureBean.LocalDbRepository localDbRepository;
    private InfrastructureBean.RdbmsDbRepository rdbmsDbRepository;
    private InfrastructureBean.CassandraDbRepository cassandraDbRepository;
    private InfrastructureBean.MongoDbRepository mongoDbRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        localDbRepository = new InfrastructureBean.LocalDbRepository();
        rdbmsDbRepository = new InfrastructureBean.RdbmsDbRepository();
        cassandraDbRepository = new InfrastructureBean.CassandraDbRepository(cassandraProperty);
        mongoDbRepository = new InfrastructureBean.MongoDbRepository(mongoProperty);
    }

    @Test
    void givenLocalDbRepository_whenUserRepositoryCalled_thenReturnsLocalUserPersistentRepository() {
        // GIVEN

        // WHEN
        UserPersistentRepository userRepository = localDbRepository.userRepository();

        // THEN
        assertThat(userRepository).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenLocalDbRepository_whenInfoRepositoryCalled_thenReturnsLocalInfoPersistentRepository() {
        // GIVEN

        // WHEN
        InfoPersistentRepository infoRepository = localDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(LocalInfoPersistentRepository.class);
    }

    @Test
    void givenRdbmsDbRepository_whenUserRepositoryCalled_thenReturnsRdbmsUserPersistentRepository() {
        // GIVEN
        rdbmsDbRepository.url = "jdbc:mysql://localhost:3306/test";
        rdbmsDbRepository.username = "root";
        rdbmsDbRepository.password = "password";
        rdbmsDbRepository.driverClassName = "com.mysql.cj.jdbc.Driver";

        when(dataSource.getConnection()).thenReturn(null);
        when(jdbcTemplate.getDataSource()).thenReturn(dataSource);

        // WHEN
        UserPersistentRepository userRepository = rdbmsDbRepository.userRepository(jdbcTemplate);

        // THEN
        assertThat(userRepository).isInstanceOf(RdbmsUserPersistentRepository.class);
    }

    @Test
    void givenRdbmsDbRepository_whenInfoRepositoryCalled_thenReturnsRdbmsInfoPersistentRepository() {
        // GIVEN
        rdbmsDbRepository.url = "jdbc:mysql://localhost:3306/test";
        rdbmsDbRepository.username = "root";
        rdbmsDbRepository.password = "password";
        rdbmsDbRepository.driverClassName = "com.mysql.cj.jdbc.Driver";

        when(dataSource.getConnection()).thenReturn(null);
        when(jdbcTemplate.getDataSource()).thenReturn(dataSource);

        // WHEN
        InfoPersistentRepository infoRepository = rdbmsDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(RdbmsInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraDbRepository_whenInfoRepositoryCalled_thenReturnsCassandraInfoPersistentRepository() {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(List.of("localhost:9042"));
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");

        // WHEN
        InfoPersistentRepository infoRepository = cassandraDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(CassandraInfoPersistentRepository.class);
    }

    @Test
    void givenMongoDbRepository_whenUserRepositoryCalled_thenReturnsMongoUserPersistentRepository() {
        // GIVEN
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getUser()).thenReturn("test_user");
        when(mongoProperty.getPassword()).thenReturn("test_password");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPlatformDatabase()).thenReturn("test_database");

        // WHEN
        UserPersistentRepository userRepository = mongoDbRepository.userRepository();

        // THEN
        assertThat(userRepository).isInstanceOf(MongoUserPersistentRepository.class);
    }

    @Test
    void givenMongoDbRepository_whenInfoRepositoryCalled_thenReturnsMongoInfoPersistentRepository() {
        // GIVEN
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getUser()).thenReturn("test_user");
        when(mongoProperty.getPassword()).thenReturn("test_password");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPlatformDatabase()).thenReturn("test_database");

        // WHEN
        InfoPersistentRepository infoRepository = mongoDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(MongoInfoPersistentRepository.class);
    }
}