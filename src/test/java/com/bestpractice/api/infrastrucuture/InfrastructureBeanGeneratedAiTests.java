package com.bestpractice.api.infrastrucuture;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

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
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class InfrastructureBeanGeneratedAiTests {

    @Mock
    private CassandraProperty cassandraProperty;

    @Mock
    private MongoProperty mongoProperty;

    @Mock
    private RedisProperty redisProperty;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private InfrastructureBean.LocalDbRepository localDbRepository;
    private InfrastructureBean.RdbmsDbRepository rdbmsDbRepository;
    private InfrastructureBean.CassandraDbRepository cassandraDbRepository;
    private InfrastructureBean.MongoDbRepository mongoDbRepository;
    private InfrastructureBean.RedisCacheRepository redisCacheRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
    void givenLocalDbRepository_whenUserRepositoryCalled_thenReturnsLocalUserPersistentRepository() {
        // GIVEN

        // WHEN
        UserPersistentRepository result = localDbRepository.userRepository();

        // THEN
        assertThat(result).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenLocalDbRepository_whenInfoRepositoryCalled_thenReturnsLocalInfoPersistentRepository() {
        // GIVEN

        // WHEN
        InfoPersistentRepository result = localDbRepository.infoRepository();

        // THEN
        assertThat(result).isInstanceOf(LocalInfoPersistentRepository.class);
    }

    @Test
    void givenRdbmsDbRepository_whenDataSourceCalled_thenReturnsDriverManagerDataSource() {
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
    void givenRdbmsDbRepository_whenUserRepositoryCalled_thenReturnsRdbmsUserPersistentRepository() {
        // GIVEN
        when(jdbcTemplate.getDataSource()).thenReturn(new DriverManagerDataSource());

        // WHEN
        UserPersistentRepository result = rdbmsDbRepository.userRepository(jdbcTemplate);

        // THEN
        assertThat(result).isInstanceOf(RdbmsUserPersistentRepository.class);
    }

    @Test
    void givenRdbmsDbRepository_whenInfoRepositoryCalled_thenReturnsRdbmsInfoPersistentRepository() {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "user");
        setPrivateField(rdbmsDbRepository, "password", "pass");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");

        // WHEN
        InfoPersistentRepository result = rdbmsDbRepository.infoRepository();

        // THEN
        assertThat(result).isInstanceOf(RdbmsInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraDbRepository_whenInfoRepositoryCalled_thenReturnsCassandraInfoPersistentRepository() {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(new String[]{"localhost:9042"});
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");

        // WHEN
        InfoPersistentRepository result = cassandraDbRepository.infoRepository();

        // THEN
        assertThat(result).isInstanceOf(CassandraInfoPersistentRepository.class);
    }

    @Test
    void givenCassandraDbRepository_whenUserRepositoryCalled_thenReturnsLocalUserPersistentRepository() {
        // GIVEN

        // WHEN
        UserPersistentRepository result = cassandraDbRepository.userRepository();

        // THEN
        assertThat(result).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    void givenMongoDbRepository_whenUserRepositoryCalled_thenReturnsMongoUserPersistentRepository() {
        // GIVEN
        MongoClient mockClient = Mockito.mock(MongoClient.class);
        MongoDatabase mockDatabase = Mockito.mock(MongoDatabase.class);
        InfrastructureBean.MongoDbRepository spyRepo = Mockito.spy(mongoDbRepository);
        when(spyRepo.mongoClient()).thenReturn(mockClient);
        when(spyRepo.mongoDatabase()).thenReturn(mockDatabase);

        // WHEN
        UserPersistentRepository result = spyRepo.userRepository();

        // THEN
        assertThat(result).isInstanceOf(MongoUserPersistentRepository.class);
    }

    @Test
    void givenMongoDbRepository_whenInfoRepositoryCalled_thenReturnsMongoInfoPersistentRepository() {
        // GIVEN
        MongoClient mockClient = Mockito.mock(MongoClient.class);
        MongoDatabase mockDatabase = Mockito.mock(MongoDatabase.class);
        InfrastructureBean.MongoDbRepository spyRepo = Mockito.spy(mongoDbRepository);
        when(spyRepo.mongoClient()).thenReturn(mockClient);
        when(spyRepo.mongoDatabase()).thenReturn(mockDatabase);

        // WHEN
        InfoPersistentRepository result = spyRepo.infoRepository();

        // THEN
        assertThat(result).isInstanceOf(MongoInfoPersistentRepository.class);
    }

    @Test
    void givenRedisCacheRepository_whenConstructed_thenNotNull() {
        // GIVEN

        // WHEN
        InfrastructureBean.RedisCacheRepository result = redisCacheRepository;

        // THEN
        assertThat(result).isNotNull();
    }
}
