package com.bestpractice.api.infrastrucuture;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraProperty;
import com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoProperty;
import com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalUserPersistentRepository;
import com.datastax.oss.driver.api.core.CqlSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InfrastructureBeanGeneratedAiTests {

    private InfrastructureBean.LocalDbRepository localDbRepository;
    private InfrastructureBean.RdbmsDbRepository rdbmsDbRepository;
    private InfrastructureBean.CassandraDbRepository cassandraDbRepository;
    private InfrastructureBean.MongoDbRepository mongoDbRepository;

    @BeforeEach
    public void setUp() {
        localDbRepository = new InfrastructureBean.LocalDbRepository();
        rdbmsDbRepository = new InfrastructureBean.RdbmsDbRepository();
        rdbmsDbRepository.url = "jdbc:h2:mem:testdb";
        rdbmsDbRepository.username = "sa";
        rdbmsDbRepository.password = "";
        rdbmsDbRepository.driverClassName = "org.h2.Driver";

        CassandraProperty cassandraProperty = new CassandraProperty();
        cassandraProperty.setHosts(List.of("localhost:9042"));
        cassandraProperty.setKeyspace("testkeyspace");
        cassandraDbRepository = new InfrastructureBean.CassandraDbRepository(cassandraProperty);

        MongoProperty mongoProperty = new MongoProperty();
        mongoProperty.setHost("localhost");
        mongoProperty.setPort(27017);
        mongoProperty.setAuthDatabase("admin");
        mongoProperty.setPlatformDatabase("testdb");
        mongoProperty.setUser("user");
        mongoProperty.setPassword("pass");
        mongoDbRepository = new InfrastructureBean.MongoDbRepository(mongoProperty);
    }

    @Test
    public void givenLocalDbRepository_whenUserRepository_thenReturnsLocalUserPersistentRepository() {
        UserPersistentRepository repo = localDbRepository.userRepository();
        assertNotNull(repo);
        assertTrue(repo instanceof LocalUserPersistentRepository);
    }

    @Test
    public void givenLocalDbRepository_whenInfoRepository_thenReturnsLocalInfoPersistentRepository() {
        InfoPersistentRepository repo = localDbRepository.infoRepository();
        assertNotNull(repo);
        assertTrue(repo instanceof LocalInfoPersistentRepository);
    }

    @Test
    public void givenRdbmsDbRepository_whenDataSource_thenReturnsDriverManagerDataSource() {
        DriverManagerDataSource ds = rdbmsDbRepository.dataSource();
        assertNotNull(ds);
        assertEquals(rdbmsDbRepository.url, ds.getUrl());
    }

    @Test
    public void givenRdbmsDbRepository_whenJdbcTemplate_thenReturnsJdbcTemplate() {
        JdbcTemplate jdbcTemplate = rdbmsDbRepository.jdbcTemplate();
        assertNotNull(jdbcTemplate);
    }

    @Test
    public void givenRdbmsDbRepository_whenUserRepository_thenReturnsRdbmsUserPersistentRepository() {
        JdbcTemplate jdbcTemplate = rdbmsDbRepository.jdbcTemplate();
        UserPersistentRepository repo = rdbmsDbRepository.userRepository(jdbcTemplate);
        assertNotNull(repo);
        assertTrue(repo instanceof RdbmsUserPersistentRepository);
    }

    @Test
    public void givenRdbmsDbRepository_whenInfoRepository_thenReturnsRdbmsInfoPersistentRepository() {
        InfoPersistentRepository repo = rdbmsDbRepository.infoRepository();
        assertNotNull(repo);
        assertTrue(repo instanceof RdbmsInfoPersistentRepository);
    }

    @Test
    public void givenCassandraDbRepository_whenCqlSession_thenReturnsCqlSession() {
        CqlSession session = cassandraDbRepository.cqlSession();
        assertNotNull(session);
        session.close();
    }

    @Test
    public void givenCassandraDbRepository_whenUserRepository_thenReturnsLocalUserPersistentRepository() {
        UserPersistentRepository repo = cassandraDbRepository.userRepository();
        assertNotNull(repo);
        assertTrue(repo instanceof LocalUserPersistentRepository);
    }

    @Test
    public void givenCassandraDbRepository_whenInfoRepository_thenReturnsInfoPersistentRepository() {
        InfoPersistentRepository repo = cassandraDbRepository.infoRepository();
        assertNotNull(repo);
    }
