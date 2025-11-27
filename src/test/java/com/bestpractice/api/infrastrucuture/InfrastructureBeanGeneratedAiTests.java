package com.bestpractice.api.infrastrucuture;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.net.InetSocketAddress;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class InfrastructureBeanGeneratedAiTests {

    @InjectMocks
    private InfrastructureBean.MongoDbRepository mongoDbRepository;

    @InjectMocks
    private InfrastructureBean.CassandraDbRepository cassandraDbRepository;

    @InjectMocks
    private InfrastructureBean.RdbmsDbRepository rdbmsDbRepository;

    @InjectMocks
    private InfrastructureBean.LocalDbRepository localDbRepository;

    @Mock
    private MongoProperty mongoProperty;

    @Mock
    private CassandraProperty cassandraProperty;

    @Mock
    private RedisProperty redisProperty;

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private CqlSession cqlSession;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private DriverManagerDataSource driverManagerDataSource;

    @BeforeEach
    void setUp() {
        // Removed clearInvocations method as it does not exist
    }

    @Test
    void givenMongoProperty_whenMongoClientCreated_thenClientIsNotNull() {
        // GIVEN
        Mockito.when(mongoProperty.getUser()).thenReturn("testUser");
        Mockito.when(mongoProperty.getAuthDatabase()).thenReturn("authDb");
        Mockito.when(mongoProperty.getPassword()).thenReturn("password");
        Mockito.when(mongoProperty.getHost()).thenReturn("localhost");
        Mockito.when(mongoProperty.getPort()).thenReturn(27017);
        Mockito.when(mongoProperty.getPlatformDatabase()).thenReturn("platformDb");

        // WHEN
        MongoClient client = mongoDbRepository.mongoClient();

        // THEN
        assertThat(client).isNotNull();
    }

    @Test
    void givenMongoClient_whenMongoDatabaseCreated_thenDatabaseIsNotNull() {
        // GIVEN
        Mockito.when(mongoDbRepository.mongoClient()).thenReturn(mongoClient);
        Mockito.when(mongoClient.getDatabase("platformDb")).thenReturn(mongoDatabase);

        // WHEN
        MongoDatabase database = mongoDbRepository.mongoDatabase();

        // THEN
        assertThat(database).isNotNull();
    }

    @Test
    void givenMongoClient_whenUserRepositoryCreated_thenRepositoryIsNotNull() {
        // GIVEN
        Mockito.when(mongoDbRepository.mongoClient()).thenReturn(mongoClient);
        Mockito.when(mongoDbRepository.mongoDatabase()).thenReturn(mongoDatabase);

        // WHEN
        UserPersistentRepository repository = mongoDbRepository.userRepository();

        // THEN
        assertThat(repository).isInstanceOf(MongoUserPersistentRepository.class);
    }

    @Test
    void givenMongoClient_whenInfoRepositoryCreated_thenRepositoryIsNotNull() {
        // GIVEN
        Mockito.when(mongoDbRepository.mongoClient()).thenReturn(mongoClient);
        Mockito.when(mongoDbRepository.mongoDatabase()).thenReturn(mongoDatabase);

        // WHEN
        InfoPersistentRepository repository = mongoDbRepository.infoRepository();

        // THEN
        assertThat(repository).isInstanceOf(MongoInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraProperty_whenCqlSessionCreated_thenSessionIsNotNull() {
        // GIVEN
        Mockito.when(cassandraProperty.getHosts()).thenReturn(List.of("localhost:9042"));
        Mockito.when(cassandraProperty.getKeyspace()).thenReturn("testKeyspace");

        //WHEN
        CqlSession session = cassandraDbRepository.cqlSession();

        // THEN
        assertThat(session).isNotNull();
    }

    @Test
    void givenCqlSession_whenInfoRepositoryCreated_thenRepositoryIsNotNull() {
        // GIVEN
        Mockito.when(cassandraDbRepository.cqlSession()).thenReturn(cqlSession);

        // WHEN
        InfoPersistentRepository repository = cassandraDbRepository.infoRepository();

        // THEN
        assertThat(repository).isInstanceOf(CassandraInfoPersistentRepository.class);
    }

    @Test
    void givenLocalDbRepository_whenUserRepositoryCreated_thenRepositoryIsNotNull() {
        // GIVEN
        // No specific setup required for LocalDbRepository

        // WHEN
        UserPersistentRepository repository = localDbRepository.userRepository();

        // THEN
        assertThat(repository).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenLocalDbRepository_whenInfoRepositoryCreated_thenRepositoryIsNotNull() {
        // GIVEN
        // No specific setup required for LocalDbRepository

        // WHEN
        InfoPersistentRepository repository = localDbRepository.infoRepository();

        // THEN
        assertThat(repository).isInstanceOf(LocalInfoPersistentRepository.class);
    }

    @Test
    void givenRdbmsDbRepository_whenDataSourceCreated_thenDataSourceIsNotNull() {
        // GIVEN
        Mockito.when(rdbmsDbRepository.dataSource()).thenReturn(driverManagerDataSource);

        // WHEN
        DriverManagerDataSource dataSource = rdbmsDbRepository.dataSource();

        // THEN
        assertThat(dataSource).isNotNull();
    }

    @Test
    void givenRdbmsDbRepository_whenTransactionManagerCreated_thenTransactionManagerIsNotNull() {
        // GIVEN
        Mockito.when(rdbmsDbRepository.dataSource()).thenReturn(driverManagerDataSource);

        // WHEN
        var transactionManager = rdbmsDbRepository.transactionManager();

        // THEN
        assertThat(transactionManager).isNotNull();
    }

    @Test
    void givenRdbmsDbRepository_whenJdbcTemplateCreated_thenJdbcTemplateIsNotNull() {
        // GIVEN
        Mockito.when(rdbmsDbRepository.dataSource()).thenReturn(driverManagerDataSource);

        // WHEN
        JdbcTemplate jdbcTemplate = rdbmsDbRepository.jdbcTemplate();

        // THEN
        assertThat(jdbcTemplate).isNotNull();
    }

    @Test
    void givenJdbcTemplate_whenUserRepositoryCreated_thenRepositoryIsNotNull() {
        // GIVEN
        Mockito.when(rdbmsDbRepository.jdbcTemplate()).thenReturn(jdbcTemplate);

        // WHEN
        UserPersistentRepository repository = rdbmsDbRepository.userRepository(jdbcTemplate);

        // THEN
        assertThat(repository).isInstanceOf(RdbmsUserPersistentRepository.class);
    }

    @Test
    void givenJdbcTemplate_whenInfoRepositoryCreated_thenRepositoryIsNotNull() {
        // GIVEN
        Mockito.when(rdbmsDbRepository.jdbcTemplate()).thenReturn(jdbcTemplate);

        // WHEN
        InfoPersistentRepository repository = rdbmsDbRepository.infoRepository();

        // THEN
        assertThat(repository).isInstanceOf(RdbmsInfoPersistentRepository.class);
    }
}
