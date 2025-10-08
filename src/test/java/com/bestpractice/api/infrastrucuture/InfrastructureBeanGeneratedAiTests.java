package com.bestpractice.api.infrastrucuture;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
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
    void setUp() {
        localDbRepository = new InfrastructureBean.LocalDbRepository();
        rdbmsDbRepository = new InfrastructureBean.RdbmsDbRepository();
        CassandraProperty cassandraProperty = new CassandraProperty();
        cassandraProperty.setHosts(List.of("localhost:9042"));
        cassandraProperty.setKeyspace("test_keyspace");
        cassandraDbRepository = new InfrastructureBean.CassandraDbRepository(cassandraProperty);
        MongoProperty mongoProperty = new MongoProperty();
        mongoProperty.setUser("user");
        mongoProperty.setAuthDatabase("admin");
        mongoProperty.setPassword("pass");
        mongoProperty.setHost("localhost");
        mongoProperty.setPort(27017);
        mongoProperty.setPlatformDatabase("platform");
        mongoDbRepository = new InfrastructureBean.MongoDbRepository(mongoProperty);
    }

    @Test
    void givenLocalDbRepository_whenUserRepository_thenReturnsLocalUserPersistentRepository() {
        // GIVEN

        // WHEN
        UserPersistentRepository repo = localDbRepository.userRepository();

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof LocalUserPersistentRepository);
    }

    @Test
    void givenLocalDbRepository_whenInfoRepository_thenReturnsLocalInfoPersistentRepository() {
        // GIVEN

        // WHEN
        InfoPersistentRepository repo = localDbRepository.infoRepository();

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof LocalInfoPersistentRepository);
    }

    @Test
    void givenRdbmsDbRepository_whenDataSource_thenReturnsDriverManagerDataSource() {
        // GIVEN
        rdbmsDbRepository.url = "jdbc:h2:mem:testdb";
        rdbmsDbRepository.username = "sa";
        rdbmsDbRepository.password = "";
        rdbmsDbRepository.driverClassName = "org.h2.Driver";

        // WHEN
        DriverManagerDataSource ds = rdbmsDbRepository.dataSource();

        // THEN
        assertNotNull(ds);
        assertEquals("jdbc:h2:mem:testdb", ds.getUrl());
    }

    @Test
    void givenRdbmsDbRepository_whenJdbcTemplate_thenReturnsJdbcTemplate() {
        // GIVEN
        rdbmsDbRepository.url = "jdbc:h2:mem:testdb";
        rdbmsDbRepository.username = "sa";
        rdbmsDbRepository.password = "";
        rdbmsDbRepository.driverClassName = "org.h2.Driver";

        // WHEN
        JdbcTemplate jdbcTemplate = rdbmsDbRepository.jdbcTemplate();

        // THEN
        assertNotNull(jdbcTemplate);
    }

    @Test
    void givenCassandraDbRepository_whenCqlSession_thenReturnsCqlSession() {
        // GIVEN

        // WHEN
        CqlSession session = cassandraDbRepository.cqlSession();

        // THEN
        assertNotNull(session);
        session.close();
    }
}