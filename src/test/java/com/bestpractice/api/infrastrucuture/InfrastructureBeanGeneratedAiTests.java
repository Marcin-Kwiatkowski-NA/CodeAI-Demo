package com.bestpractice.api.infrastrucuture;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.cache.redis.RedisProperty;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraProperty;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoProperty;
import com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsUserPersistentRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.datastax.oss.driver.api.core.CqlSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InfrastructureBeanGeneratedAiTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void givenLocalDbProfile_whenUserRepository_thenReturnsLocalUserPersistentRepository() {
        // GIVEN
        InfrastructureBean.LocalDbRepository repo = new InfrastructureBean.LocalDbRepository();

        // WHEN
        UserPersistentRepository result = repo.userRepository();

        // THEN
        assertNotNull(result);
        assertTrue(result instanceof LocalUserPersistentRepository);
    }

    @Test
    void givenLocalDbProfile_whenInfoRepository_thenReturnsLocalInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.LocalDbRepository repo = new InfrastructureBean.LocalDbRepository();

        // WHEN
        InfoPersistentRepository result = repo.infoRepository();

        // THEN
        assertNotNull(result);
        assertTrue(result instanceof LocalInfoPersistentRepository);
    }

    @Test
    void givenRdbmsProfile_whenDataSource_thenReturnsDriverManagerDataSource() {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository repo = new InfrastructureBean.RdbmsDbRepository();
        repo.url = "jdbc:h2:mem:testdb";
        repo.username = "sa";
        repo.password = "";
        repo.driverClassName = "org.h2.Driver";

        // WHEN
        DriverManagerDataSource ds = repo.dataSource();

        // THEN
        assertNotNull(ds);
        assertEquals("jdbc:h2:mem:testdb", ds.getUrl());
    }

    @Test
    void givenRdbmsProfile_whenJdbcTemplate_thenReturnsJdbcTemplate() {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository repo = new InfrastructureBean.RdbmsDbRepository();
        repo.url = "jdbc:h2:mem:testdb";
        repo.username = "sa";
        repo.password = "";
        repo.driverClassName = "org.h2.Driver";

        // WHEN
        JdbcTemplate jdbcTemplate = repo.jdbcTemplate();

        // THEN
        assertNotNull(jdbcTemplate);
    }

    @Test
    void givenRdbmsProfile_whenUserRepository_thenReturnsRdbmsUserPersistentRepository() {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository repo = new InfrastructureBean.RdbmsDbRepository();
        repo.url = "jdbc:h2:mem:testdb";
        repo.username = "sa";
        repo.password = "";
        repo.driverClassName = "org.h2.Driver";
        JdbcTemplate jdbcTemplate = repo.jdbcTemplate();

        // WHEN
        UserPersistentRepository result = repo.userRepository(jdbcTemplate);

        // THEN
        assertNotNull(result);
        assertTrue(result instanceof RdbmsUserPersistentRepository);
    }

    @Test
    void givenRdbmsProfile_whenInfoRepository_thenReturnsRdbmsInfoPersistentRepository() {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository repo = new InfrastructureBean.RdbmsDbRepository();
        repo.url = "jdbc:h2:mem:testdb";
        repo.username = "sa";
        repo.password = "";
        repo.driverClassName = "org.h2.Driver";

        // WHEN
        InfoPersistentRepository result = repo.infoRepository();

        // THEN
        assertNotNull(result);
        assertTrue(result instanceof RdbmsInfoPersistentRepository);
    }
}