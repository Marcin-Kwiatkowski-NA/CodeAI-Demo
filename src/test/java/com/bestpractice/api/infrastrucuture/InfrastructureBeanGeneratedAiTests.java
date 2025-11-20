package com.bestpractice.api.infrastrucuture;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
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

    private InfrastructureBean.LocalCacheRepository localCacheRepository;
    private InfrastructureBean.RedisCacheRepository redisCacheRepository;
    private InfrastructureBean.LocalDbRepository localDbRepository;
    private InfrastructureBean.RdbmsDbRepository rdbmsDbRepository;
    private InfrastructureBean.CassandraDbRepository cassandraDbRepository;
    private InfrastructureBean.MongoDbRepository mongoDbRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        localCacheRepository = new InfrastructureBean.LocalCacheRepository();
        redisCacheRepository = new InfrastructureBean.RedisCacheRepository(redisProperty);
        localDbRepository = new InfrastructureBean.LocalDbRepository();
        rdbmsDbRepository = spy(new InfrastructureBean.RdbmsDbRepository());
        cassandraDbRepository = spy(new InfrastructureBean.CassandraDbRepository(cassandraProperty));
        mongoDbRepository = spy(new InfrastructureBean.MongoDbRepository(mongoProperty));
    }

    @Test
    void givenLocalDbRepository_whenUserRepositoryCalled_thenReturnsLocalUserPersistentRepository() {
        // GIVEN
        // No specific setup required

        // WHEN
        UserPersistentRepository userRepository = localDbRepository.userRepository();

        // THEN
        assertThat(userRepository).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenLocalDbRepository_whenInfoRepositoryCalled_thenReturnsLocalInfoPersistentRepository() {
        // GIVEN
        // No specific setup required

        // WHEN
        InfoPersistentRepository infoRepository = localDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(LocalInfoPersistentRepository.class);
    }

    @Test
    void givenRdbmsDbRepository_whenUserRepositoryCalled_thenReturnsRdbmsUserPersistentRepository() {
        // GIVEN
        doReturn(jdbcTemplate).when(rdbmsDbRepository).jdbcTemplate();

        // WHEN
        UserPersistentRepository userRepository = rdbmsDbRepository.userRepository(jdbcTemplate);

        // THEN
        assertThat(userRepository).isInstanceOf(RdbmsUserPersistentRepository.class);
    }

    @Test
    void givenRdbmsDbRepository_whenInfoRepositoryCalled_thenReturnsRdbmsInfoPersistentRepository() {
        // GIVEN
        doReturn(jdbcTemplate).when(rdbmsDbRepository).jdbcTemplate();

        // WHEN
        InfoPersistentRepository infoRepository = rdbmsDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(RdbmsInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraDbRepository_whenInfoRepositoryCalled_thenReturnsCassandraInfoPersistentRepository() {
        // GIVEN
        doReturn(cqlSession).when(cassandraDbRepository).cqlSession();

        // WHEN
        InfoPersistentRepository infoRepository = cassandraDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(CassandraInfoPersistentRepository.class);
    }

    @Test    void givenMongoDbRepository_whenUserRepositoryCalled_thenReturnsMongoUserPersistentRepository() {
        // GIVEN
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
        doReturn(mongoClient).when(mongoDbRepository).mongoClient();
        doReturn(mongoDatabase).when(mongoDbRepository).mongoDatabase();

        // WHEN
        InfoPersistentRepository infoRepository = mongoDbRepository.infoRepository();

        // THEN
        assertThat(infoRepository).isInstanceOf(MongoInfoPersistentRepository.class);
    }
}
