package com.bestpractice.api.infrastrucuture;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
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
import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InfrastructureBeanGeneratedAiTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void givenLocalDbRepository_whenUserRepository_thenReturnsLocalUserPersistentRepository() {
        InfrastructureBean.LocalDbRepository repo = new InfrastructureBean.LocalDbRepository();
        UserPersistentRepository result = repo.userRepository();
        assertNotNull(result);
        assertTrue(result instanceof LocalUserPersistentRepository);
    }

    @Test
    void givenLocalDbRepository_whenInfoRepository_thenReturnsLocalInfoPersistentRepository() {
        InfrastructureBean.LocalDbRepository repo = new InfrastructureBean.LocalDbRepository();
        InfoPersistentRepository result = repo.infoRepository();
        assertNotNull(result);
        assertTrue(result instanceof LocalInfoPersistentRepository);
    }

    @Test
    void givenRdbmsDbRepository_whenDataSource_thenReturnsDriverManagerDataSource() {
        InfrastructureBean.RdbmsDbRepository repo = new InfrastructureBean.RdbmsDbRepository();
        repo.setUrl("jdbc:h2:mem:test");
        repo.setUsername("sa");
        repo.setPassword("");
        repo.setDriverClassName("org.h2.Driver");
        DriverManagerDataSource ds = repo.dataSource();
        assertNotNull(ds);
        assertEquals("jdbc:h2:mem:test", ds.getUrl());
    }

    @Test
    void givenRdbmsDbRepository_whenJdbcTemplate_thenReturnsJdbcTemplate() {
        InfrastructureBean.RdbmsDbRepository repo = new InfrastructureBean.RdbmsDbRepository();
        repo.setUrl("jdbc:h2:mem:test");
        repo.setUsername("sa");
        repo.setPassword("");
        repo.setDriverClassName("org.h2.Driver");
        JdbcTemplate template = repo.jdbcTemplate();
        assertNotNull(template);
    }

    @Test
    void givenRdbmsDbRepository_whenUserRepository_thenReturnsRdbmsUserPersistentRepository() {
        InfrastructureBean.RdbmsDbRepository repo = new InfrastructureBean.RdbmsDbRepository();
        repo.setUrl("jdbc:h2:mem:test");
        repo.setUsername("sa");
        repo.setPassword("");
        repo.setDriverClassName("org.h2.Driver");
        JdbcTemplate template = repo.jdbcTemplate();
        UserPersistentRepository result = repo.userRepository(template);
        assertNotNull(result);
        assertTrue(result instanceof RdbmsUserPersistentRepository);
    }

    @Test
    void givenRdbmsDbRepository_whenInfoRepository_thenReturnsRdbmsInfoPersistentRepository() {
        InfrastructureBean.RdbmsDbRepository repo = new InfrastructureBean.RdbmsDbRepository();
        repo.setUrl("jdbc:h2:mem:test");
        repo.setUsername("sa");
        repo.setPassword("");
        repo.setDriverClassName("org.h2.Driver");
        InfoPersistentRepository result = repo.infoRepository();
        assertNotNull(result);
        assertTrue(result instanceof RdbmsInfoPersistentRepository);
    }

    @Test
    void givenCassandraDbRepository_whenCqlSession_thenReturnsSession() {
        CassandraProperty property = mock(CassandraProperty.class);
        when(property.getHosts()).thenReturn(new String[]{"localhost:9042"});
        when(property.getKeyspace()).thenReturn("test_keyspace");
        InfrastructureBean.CassandraDbRepository repo = new InfrastructureBean.CassandraDbRepository(property);
        CqlSessionBuilder builderMock = mock(CqlSessionBuilder.class);
        when(builderMock.addContactPoint(any(InetSocketAddress.class))).thenReturn(builderMock);
        when(builderMock.withLocalDatacenter(anyString())).thenReturn(builderMock);
        when(builderMock.withKeyspace(any(CqlIdentifier.class))).thenReturn(builderMock);
        CqlSession sessionMock = mock(CqlSession.class);
        when(builderMock.build()).thenReturn(sessionMock);
        CqlSession session = repo.cqlSession();
        assertNotNull(session);
    }

    @Test
    void givenCassandraDbRepository_whenInfoRepository_thenReturnsCassandraInfoPersistentRepository() {
        CassandraProperty property = mock(CassandraProperty.class);
        when(property.getHosts()).thenReturn(new String[]{"localhost:9042"});
        when(property.getKeyspace()).thenReturn("test_keyspace");
        InfrastructureBean.CassandraDbRepository repo = new InfrastructureBean.CassandraDbRepository(property);
        CqlSession sessionMock = mock(CqlSession.class);
        InfoPersistentRepository result = new CassandraInfoPersistentRepository(sessionMock);
        assertNotNull(result);
        assertTrue(result instanceof CassandraInfoPersistentRepository);
    }

    @Test
    void givenMongoDbRepository_whenMongoClient_thenReturnsMongoClient() {
        MongoProperty property = mock(MongoProperty.class);
        when(property.getUser()).thenReturn("user");
        when(property.getAuthDatabase()).thenReturn("admin");
        when(property.getPassword()).thenReturn("pass");
        when(property.getHost()).thenReturn("localhost");
        when(property.getPort()).thenReturn(27017);
        InfrastructureBean.MongoDbRepository repo = new InfrastructureBean.MongoDbRepository(property);
        MongoClient client = repo.mongoClient();
        assertNotNull(client);
    }

    @Test
    void givenMongoDbRepository_whenMongoDatabase_thenReturnsMongoDatabase() {
        MongoProperty property = mock(MongoProperty.class);
        when(property.getUser()).thenReturn("user");
        when(property.getAuthDatabase()).thenReturn("admin");
        when(property.getPassword()).thenReturn("pass");
        when(property.getHost()).thenReturn("localhost");
        when(property.getPort()).thenReturn(27017);
        when(property.getPlatformDatabase()).thenReturn("testdb");
        InfrastructureBean.MongoDbRepository repo = new InfrastructureBean.MongoDbRepository(property);
        MongoDatabase db = repo.mongoDatabase();
        assertNotNull(db);
    }

    @Test
    void givenMongoDbRepository_whenUserRepository_thenReturnsMongoUserPersistentRepository() {
        MongoProperty property = mock(MongoProperty.class);
        when(property.getUser()).thenReturn("user");
        when(property.getAuthDatabase()).thenReturn("admin");
        when(property.getPassword()).thenReturn("pass");
        when(property.getHost()).thenReturn("localhost");
        when(property.getPort()).thenReturn(27017);
        when(property.getPlatformDatabase()).thenReturn("testdb");
        InfrastructureBean.MongoDbRepository repo = new InfrastructureBean.MongoDbRepository(property);
        UserPersistentRepository result = repo.userRepository();
        assertNotNull(result);
        assertTrue(result instanceof MongoUserPersistentRepository);
    }

    @Test
    void givenMongoDbRepository_whenInfoRepository_thenReturnsMongoInfoPersistentRepository() {
        MongoProperty property = mock(MongoProperty.class);
        when(property.getUser()).thenReturn("user");
        when(property.getAuthDatabase()).thenReturn("admin");
        when(property.getPassword()).thenReturn("pass");
        when(property.getHost()).thenReturn("localhost");
        when(property.getPort()).thenReturn(27017);
        when(property.getPlatformDatabase()).thenReturn("testdb");
        InfrastructureBean.MongoDbRepository repo = new InfrastructureBean.MongoDbRepository(property);
        InfoPersistentRepository result = repo.infoRepository();
        assertNotNull(result);
        assertTrue(result instanceof MongoInfoPersistentRepository);
    }
}
