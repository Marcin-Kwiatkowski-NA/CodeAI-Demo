package com.bestpractice.api.infrastrucuture;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;


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

import java.net.InetSocketAddress;
import java.util.Collections;

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
    void givenRdbmsDbRepository_whenUserRepositoryCalled_thenReturnsRdbmsUserPersistentRepository() {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository rdbmsDbRepository = new InfrastructureBean.RdbmsDbRepository();

        // WHEN
        UserPersistentRepository userRepository = rdbmsDbRepository.userRepository();

        // THEN
        assertThat(userRepository).isInstanceOf(RdbmsUserPersistentRepository.class);
    }

    @Test
    void givenRdbmsDbRepository_whenInfoRepositoryCalled_thenReturnsRdbmsInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository rdbmsDbRepository = new InfrastructureBean.RdbmsDbRepository();

        // WHEN
        InfoPersistentRepository infoRepository = rdbmsDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(RdbmsInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraDbRepository_whenInfoRepositoryCalled_thenReturnsCassandraInfoPersistentRepository() {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(Collections.singletonList("127.0.0.1:9042"));
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");
        InfrastructureBean.CassandraDbRepository cassandraDbRepository = new InfrastructureBean.CassandraDbRepository(cassandraProperty);

        // WHEN
        InfoPersistentRepository infoRepository = cassandraDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(CassandraInfoPersistentRepository.class);
    }

    @Test
    void givenMongoDbRepository_whenUserRepositoryCalled_thenReturnsMongoUserPersistentRepository() {
        // GIVEN
        InfrastructureBean.MongoDbRepository mongoDbRepository = new InfrastructureBean.MongoDbRepository(mongoProperty);

        // WHEN
        UserPersistentRepository userRepository = mongoDbRepository.userRepository();

        // THEN
        assertThat(userRepository).isInstanceOf(MongoUserPersistentRepository.class);
    }

    @Test
    void givenMongoDbRepository_whenInfoRepositoryCalled_thenReturnsMongoInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.MongoDbRepository mongoDbRepository = new InfrastructureBean.MongoDbRepository(mongoProperty);

        // WHEN
        InfoPersistentRepository infoRepository = mongoDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(MongoInfoPersistentRepository.class);
    }

    @Test
    void givenMongoDbRepository_whenMongoClientCalled_thenReturnsMongoClient() {
        // GIVEN
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getPassword()).thenReturn("password");
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
        when(mongoProperty.getPlatformDatabase()).thenReturn("test_db");
        InfrastructureBean.MongoDbRepository mongoDbRepository = new InfrastructureBean.MongoDbRepository(mongoProperty);

        // WHEN
        MongoDatabase database = mongoDbRepository.mongoDatabase();

        // THEN
        assertThat(database).isNotNull();
    }
}
