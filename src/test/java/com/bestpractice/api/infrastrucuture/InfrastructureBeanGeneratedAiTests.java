package com.bestpractice.api.infrastrucuture;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.cache.redis.RedisProperty;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraProperty;
import com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoProperty;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class InfrastructureBeanGeneratedAiTests {

    private InfrastructureBean.RdbmsDbRepository rdbmsDbRepository;
    private InfrastructureBean.LocalDbRepository localDbRepository;
    private InfrastructureBean.CassandraDbRepository cassandraDbRepository;
    private InfrastructureBean.MongoDbRepository mongoDbRepository;
    private InfrastructureBean.RedisCacheRepository redisCacheRepository;

    @Mock
    private CassandraProperty cassandraProperty;

    @Mock
    private MongoProperty mongoProperty;

    @Mock
    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        rdbmsDbRepository = new InfrastructureBean.RdbmsDbRepository();
        localDbRepository = new InfrastructureBean.LocalDbRepository();
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
    void givenLocalDbProfile_whenUserRepositoryCalled_thenReturnLocalUserPersistentRepository() {
        // GIVEN
        // WHEN
        UserPersistentRepository repo = localDbRepository.userRepository();
        // THEN
        assertThat(repo).isNotNull();
    }

    @Test
    void givenLocalDbProfile_whenInfoRepositoryCalled_thenReturnLocalInfoPersistentRepository() {
        // GIVEN
        // WHEN
        InfoPersistentRepository repo = localDbRepository.infoRepository();
        // THEN
        assertThat(repo).isNotNull();
    }

    @Test
    void givenRdbmsProfile_whenDataSourceCreated_thenReturnValidDataSource() throws Exception {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "sa");
        setPrivateField(rdbmsDbRepository, "password", "");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");
        // WHEN
        DriverManagerDataSource ds = rdbmsDbRepository.dataSource();
        // THEN
        assertThat(ds).isNotNull();
        assertThat(ds.getUrl()).contains("jdbc:h2:mem:testdb");
    }

    @Test
    void givenRdbmsProfile_whenJdbcTemplateCreated_thenReturnJdbcTemplate() throws Exception {
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
    void givenRdbmsProfile_whenUserRepositoryCreated_thenReturnRdbmsUserPersistentRepository() {
        // GIVEN
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        // WHEN
        UserPersistentRepository repo = rdbmsDbRepository.userRepository(jdbcTemplate);
        // THEN
        assertThat(repo).isNotNull();
    }

    @Test
    void givenRdbmsProfile_whenInfoRepositoryCreated_thenReturnRdbmsInfoPersistentRepository() throws Exception {
        // GIVEN
        setPrivateField(rdbmsDbRepository, "url", "jdbc:h2:mem:testdb");
        setPrivateField(rdbmsDbRepository, "username", "sa");
        setPrivateField(rdbmsDbRepository, "password", "");
        setPrivateField(rdbmsDbRepository, "driverClassName", "org.h2.Driver");
        // WHEN
        InfoPersistentRepository repo = rdbmsDbRepository.infoRepository();
        // THEN
        assertThat(repo).isNotNull();
    }

    @Test
    void givenCassandraProfile_whenCqlSessionCreated_thenReturnSession() {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(new String[]{"localhost:9042"});
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");
        // WHEN
        var session = cassandraDbRepository.cqlSession();
        // THEN
        assertThat(session).isNotNull();
        session.close();
    }

    @Test
    void givenCassandraProfile_whenInfoRepositoryCreated_thenReturnCassandraInfoPersistentRepository() {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(new String[]{"localhost:9042"});
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");
        // WHEN
        InfoPersistentRepository repo = cassandraDbRepository.infoRepository();
        // THEN
        assertThat(repo).isNotNull();
    }

    @Test
    void givenMongoProfile_whenMongoClientCreated_thenReturnMongoClient() {
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
    void givenMongoProfile_whenMongoDatabaseCreated_thenReturnMongoDatabase() {
        // GIVEN
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPassword()).thenReturn("pass");
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getPlatformDatabase()).thenReturn("testdb");
        // WHEN
        MongoDatabase db = mongoDbRepository.mongoDatabase();
        // THEN
        assertThat(db).isNotNull();
    }

    @Test
    void givenMongoProfile_whenUserRepositoryCreated_thenReturnMongoUserPersistentRepository() {
        // GIVEN
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPassword()).thenReturn("pass");
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getPlatformDatabase()).thenReturn("testdb");
        // WHEN
        UserPersistentRepository repo = mongoDbRepository.userRepository();
        // THEN
        assertThat(repo).isNotNull();
    }

    @Test
    void givenMongoProfile_whenInfoRepositoryCreated_thenReturnMongoInfoPersistentRepository() {
        // GIVEN
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getPassword()).thenReturn("pass");
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getPlatformDatabase()).thenReturn("testdb");
        // WHEN
        InfoPersistentRepository repo = mongoDbRepository.infoRepository();
        // THEN
        assertThat(repo).isNotNull();
    }

    @Test
    void givenRedisProfile_whenRedisCacheRepositoryCreated_thenReturnInstance() {
        // GIVEN
        // WHEN
        InfrastructureBean.RedisCacheRepository repo = new InfrastructureBean.RedisCacheRepository(redisProperty);
        // THEN
        assertThat(repo).isNotNull();
    }
}