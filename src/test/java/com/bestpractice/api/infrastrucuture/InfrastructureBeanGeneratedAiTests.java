package com.bestpractice.api.infrastrucuture;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.cache.redis.RedisCacheRepository;
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
import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.net.InetSocketAddress;
import java.util.List;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit.jupiter.PowerMockExtension;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith({MockitoExtension.class, PowerMockExtension.class})
@PrepareForTest({MongoClients.class, CqlSession.class})
public class InfrastructureBeanGeneratedAiTests {

    @Mock
    private MongoProperty mongoProperty;

    @Mock
    private CassandraProperty cassandraProperty;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /* ---------- LocalDbRepository Tests ---------- */

    @Test
    public void localDbRepository_userRepository_returnsLocalUserPersistentRepository() {
        // GIVEN
        InfrastructureBean.LocalDbRepository repo = new InfrastructureBean.LocalDbRepository();

        // WHEN
        UserPersistentRepository userRepo = repo.userRepository();

        // THEN
        assertThat(userRepo).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    public void localDbRepository_infoRepository_returnsLocalInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.LocalDbRepository repo = new InfrastructureBean.LocalDbRepository();

        // WHEN
        InfoPersistentRepository infoRepo = repo.infoRepository();

        // THEN
        assertThat(infoRepo).isInstanceOf(LocalInfoPersistentRepository.class);
    }

    /* ---------- RdbmsDbRepository Tests ---------- */

    @Test
    public void rdbmsDbRepository_dataSourceConfiguredCorrectly() throws Exception {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository repo = new InfrastructureBean.RdbmsDbRepository();
        setPrivateField(repo, "url", "jdbc:h2:mem:testdb");
        setPrivateField(repo, "username", "sa");
        setPrivateField(repo, "password", "");
        setPrivateField(repo, "driverClassName", "org.h2.Driver");

        // WHEN
        DriverManagerDataSource ds = repo.dataSource();

        // THEN
        assertThat(ds).isNotNull();
        assertThat(ds.getUrl()).isEqualTo("jdbc:h2:mem:testdb");
        assertThat(ds.getUsername()).isEqualTo("sa");
        assertThat(ds.getDriverClassName()).isEqualTo("org.h2.Driver");
    }

    @Test
    public void rdbmsDbRepository_transactionManagerUsesDataSource() throws Exception {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository repo = new InfrastructureBean.RdbmsDbRepository();
        setPrivateField(repo, "url", "jdbc:h2:mem:testdb");
        setPrivateField(repo, "username", "sa");
        setPrivateField(repo, "password", "");
        setPrivateField(repo, "driverClassName", "org.h2.Driver");

        // WHEN
        DataSourceTransactionManager tm = repo.transactionManager();

        // THEN
        assertThat(tm).isNotNull();
        assertThat(tm.getDataSource()).isSameAs(repo.dataSource());
    }

    @Test
    public void rdbmsDbRepository_jdbcTemplateUsesDataSource() throws Exception {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository repo = new InfrastructureBean.RdbmsDbRepository();
        setPrivateField(repo, "url", "jdbc:h2:mem:testdb");
        setPrivateField(repo, "username", "sa");
        setPrivateField(repo, "password", "");
        setPrivateField(repo, "driverClassName", "org.h2.Driver");

        // WHEN
        JdbcTemplate jt = repo.jdbcTemplate();

        // THEN
        assertThat(jt).isNotNull();
        assertThat(jt.getDataSource()).isSameAs(repo.dataSource());
    }

    @Test
    public void rdbmsDbRepository_userRepositoryReturnsRdbmsUserPersistentRepository() throws Exception {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository repo = new InfrastructureBean.RdbmsDbRepository();
        setPrivateField(repo, "url", "jdbc:h2:mem:testdb");
        setPrivateField(repo, "username", "sa");
        setPrivateField(repo, "password", "");
        setPrivateField(repo, "driverClassName", "org.h2.Driver");

        // WHEN
        UserPersistentRepository userRepo = repo.userRepository(repo.jdbcTemplate());

        // THEN
        assertThat(userRepo).isInstanceOf(RdbmsUserPersistentRepository.class);
    }

    @Test
    public void rdbmsDbRepository_infoRepositoryReturnsRdbmsInfoPersistentRepository() throws Exception {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository repo = new InfrastructureBean.RdbmsDbRepository();
        setPrivateField(repo, "url", "jdbc:h2:mem:testdb");
        setPrivateField(repo, "username", "sa");
        setPrivateField(repo, "password", "");
        setPrivateField(repo, "driverClassName", "org.h2.Driver");

        // WHEN
        InfoPersistentRepository infoRepo = repo.infoRepository();

        // THEN
        assertThat(infoRepo).isInstanceOf(RdbmsInfoPersistentRepository.class);
    }

    /* ---------- CassandraDbRepository Tests ---------- */

    @Test
    public void cassandraDbRepository_cqlSessionReturnsMockedSession() throws Exception {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(List.of("127.0.0.1:9042"));
        when(cassandraProperty.getKeyspace()).thenReturn("testks");

        InfrastructureBean.CassandraDbRepository repo = new InfrastructureBean.CassandraDbRepository(cassandraProperty);

        CqlSessionBuilder mockBuilder = mock(CqlSessionBuilder.class);
        when(mockBuilder.addContactPoint(any(InetSocketAddress.class))).thenReturn(mockBuilder);
        when(mockBuilder.withLocalDatacenter(anyString())).thenReturn(mockBuilder);
        when(mockBuilder.withKeyspace(any(CqlIdentifier.class))).thenReturn(mockBuilder);
        CqlSession mockSession = mock(CqlSession.class);
        when(mockBuilder.build()).thenReturn(mockSession);

        PowerMockito.mockStatic(CqlSession.class);
        when(CqlSession.builder()).thenReturn(mockBuilder);

        // WHEN
        CqlSession session = repo.cqlSession();

        // THEN
        assertThat(session).isSameAs(mockSession);
        verify(mockBuilder).addContactPoint(new InetSocketAddress("127.0.0.1", 9042));
        verify(mockBuilder).withLocalDatacenter("dc01");
        verify(mockBuilder).withKeyspace(CqlIdentifier.fromCql("testks"));
    }

    @Test
    public void cassandraDbRepository_userRepositoryReturnsLocalUserPersistentRepository() {
        // GIVEN
        InfrastructureBean.CassandraDbRepository repo = new InfrastructureBean.CassandraDbRepository(cassandraProperty);

        // WHEN
        UserPersistentRepository userRepo = repo.userRepository();

        // THEN
        assertThat(userRepo).isInstanceOf(LocalUserPersistentRepository.class);
    }

    @Test
    public void cassandraDbRepository_infoRepositoryReturnsCassandraInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.CassandraDbRepository repo = new InfrastructureBean.CassandraDbRepository(cassandraProperty);

        // WHEN
        InfoPersistentRepository infoRepo = repo.infoRepository();

        // THEN
        assertThat(infoRepo).isInstanceOf(CassandraInfoPersistentRepository.class);
    }

    /* ---------- MongoDbRepository Tests ---------- */

    @Test
    public void mongoDbRepository_mongoClientReturnsMockedClient() throws Exception {
        // GIVEN
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);
        when(mongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mongoProperty.getUser()).thenReturn("user");
        when(mongoProperty.getPassword()).thenReturn("pass");

        InfrastructureBean.MongoDbRepository repo = new InfrastructureBean.MongoDbRepository(mongoProperty);

        MongoClient mockClient = mock(MongoClient.class);
        PowerMockito.mockStatic(MongoClients.class);
        when(MongoClients.create(any(MongoClientSettings.class))).thenReturn(mockClient);

        // WHEN
        MongoClient client = repo.mongoClient();

        // THEN
        assertThat(client).isSameAs(mockClient);
        verifyStatic(MongoClients.class);
        MongoClients.create(any(MongoClientSettings.class));
    }

    @Test
    public void mongoDbRepository_mongoDatabaseReturnsMockedDatabase() throws Exception {
        // GIVEN
        when(mongoProperty.getPlatformDatabase()).thenReturn("platformdb");
        InfrastructureBean.MongoDbRepository repo = new InfrastructureBean.MongoDbRepository(mongoProperty);

        MongoClient mockClient = mock(MongoClient.class);
        MongoDatabase mockDatabase = mock(MongoDatabase.class);
        when(mockClient.getDatabase("platformdb")).thenReturn(mockDatabase);
        // Mock mongoClient() to return mockClient
        InfrastructureBean.MongoDbRepository spyRepo = Mockito.spy(repo);
        doReturn(mockClient).when(spyRepo).mongoClient();

        // WHEN
        MongoDatabase db = spyRepo.mongoDatabase();

        // THEN
        assertThat(db).isSameAs(mockDatabase);
        verify(mockClient).getDatabase("platformdb");
    }

    @Test
    public void mongoDbRepository_userRepositoryReturnsMongoUserPersistentRepository() throws Exception {
        // GIVEN
        InfrastructureBean.MongoDbRepository repo = new InfrastructureBean.MongoDbRepository(mongoProperty);
        MongoClient mockClient = mock(MongoClient.class);
        MongoDatabase mockDatabase = mock(MongoDatabase.class);
        InfrastructureBean.MongoDbRepository spyRepo = Mockito.spy(repo);
        doReturn(mockClient).when(spyRepo).mongoClient();
        doReturn(mockDatabase).when(spyRepo).mongoDatabase();

        // WHEN
        UserPersistentRepository userRepo = spyRepo.userRepository();

        // THEN
        assertThat(userRepo).isInstanceOf(MongoUserPersistentRepository.class);
    }

    @Test
    public void mongoDbRepository_infoRepositoryReturnsMongoInfoPersistentRepository() throws Exception {
        // GIVEN
        InfrastructureBean.MongoDbRepository repo = new InfrastructureBean.MongoDbRepository(mongoProperty);
        MongoClient mockClient = mock(MongoClient.class);
        MongoDatabase mockDatabase = mock(MongoDatabase.class);
        InfrastructureBean.MongoDbRepository spyRepo = Mockito.spy(repo);
        doReturn(mockClient).when(spyRepo).mongoClient();
        doReturn(mockDatabase).when(spyRepo).mongoDatabase();

        // WHEN
        InfoPersistentRepository infoRepo = spyRepo.infoRepository();

        // THEN
        assertThat(infoRepo).isInstanceOf(MongoInfoPersistentRepository.class);
    }

    /* ---------- Helper Methods ---------- */

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
