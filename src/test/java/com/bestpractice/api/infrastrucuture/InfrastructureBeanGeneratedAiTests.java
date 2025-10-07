package com.bestpractice.api.infrastrucuture;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.cassandra.CassandraInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraProperty;
import com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.MongoUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoProperty;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Arrays;

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
        CassandraProperty cassandraProperty = new CassandraProperty();
        cassandraProperty.setHosts(new String[]{"localhost:9042"});
        cassandraProperty.setKeyspace("testKeyspace");
        cassandraDbRepository = new InfrastructureBean.CassandraDbRepository(cassandraProperty);
        MongoProperty mongoProperty = new MongoProperty();
        mongoProperty.setHost("localhost");
        mongoProperty.setPort(27017);
        mongoProperty.setAuthDatabase("admin");
        mongoProperty.setPlatformDatabase("platform");
        mongoProperty.setUser("user");
        mongoProperty.setPassword("pass");
        mongoDbRepository = new InfrastructureBean.MongoDbRepository(mongoProperty);
    }

    @Test
    public void testLocalDbRepositoryUserRepository() {
        // GIVEN

        // WHEN
        UserPersistentRepository repo = localDbRepository.userRepository();

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof LocalUserPersistentRepository);
    }

    @Test
    public void testLocalDbRepositoryInfoRepository() {
        // GIVEN

        // WHEN
        var repo = localDbRepository.infoRepository();

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof LocalInfoPersistentRepository);
    }

    @Test
    public void testRdbmsDbRepositoryDataSource() {
        // GIVEN
        DriverManagerDataSource dsInput = new DriverManagerDataSource();
        dsInput.setDriverClassName("org.h2.Driver");
        dsInput.setUrl("jdbc:h2:mem:test");
        dsInput.setUsername("sa");
        dsInput.setPassword("");

        // WHEN
        DriverManagerDataSource ds = new InfrastructureBean.RdbmsDbRepository() {{
            driverClassName = "org.h2.Driver";
            url = "jdbc:h2:mem:test";
            username = "sa";
            password = "";
        }}.dataSource();

        // THEN
        assertNotNull(ds);
        assertEquals("jdbc:h2:mem:test", ds.getUrl());
    }

    @Test
    public void testRdbmsDbRepositoryJdbcTemplate() {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository repoInstance = new InfrastructureBean.RdbmsDbRepository() {{
            driverClassName = "org.h2.Driver";
            url = "jdbc:h2:mem:test";
            username = "sa";
            password = "";
        }};

        // WHEN
        JdbcTemplate jdbcTemplate = repoInstance.jdbcTemplate();

        // THEN
        assertNotNull(jdbcTemplate);
    }

    @Test
    public void testRdbmsDbRepositoryUserRepository() {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository repoInstance = new InfrastructureBean.RdbmsDbRepository() {{
            driverClassName = "org.h2.Driver";
            url = "jdbc:h2:mem:test";
            username = "sa";
            password = "";
        }};
        JdbcTemplate jdbcTemplate = repoInstance.jdbcTemplate();

        // WHEN
        UserPersistentRepository repo = repoInstance.userRepository(jdbcTemplate);

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof RdbmsUserPersistentRepository);
    }

    @Test
    public void testRdbmsDbRepositoryInfoRepository() {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository repoInstance = new InfrastructureBean.RdbmsDbRepository(){{
            driverClassName = "org.h2.Driver";
            url = "jdbc:h2:mem:test";
            username = "sa";
            password = "";
        }};

        // WHEN
        var repo = repoInstance.infoRepository();

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof RdbmsInfoPersistentRepository);
    }

    @Test
    public void testCassandraDbRepositoryCqlSession() {
        // GIVEN

        // WHEN
        var session = cassandraDbRepository.cqlSession();

        // THEN
        assertNotNull(session);
        assertEquals("testKeyspace", session.getKeyspace().get().asInternal());
        session.close();
    }

    @Test
    public void testCassandraDbRepositoryUserRepository() {
        // GIVEN

        // WHEN
        UserPersistentRepository repo = cassandraDbRepository.userRepository();

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof LocalUserPersistentRepository);
    }

    @Test
    public void testCassandraDbRepositoryInfoRepository() {
        // GIVEN

        // WHEN
        var repo = cassandraDbRepository.infoRepository();

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof CassandraInfoPersistentRepository);
    }

    @Test
    public void testMongoDbRepositoryMongoClient() {
        // GIVEN

        // WHEN
        MongoClient client = mongoDbRepository.mongoClient();

        // THEN
        assertNotNull(client);
        client.close();
    }

    @Test
    public void testMongoDbRepositoryMongoDatabase() {
        // GIVEN

        // WHEN
        MongoDatabase db = mongoDbRepository.mongoDatabase();

        // THEN
        assertNotNull(db);
        assertEquals("platform", db.getName());
    }

    @Test
    public void testMongoDbRepositoryUserRepository() {
        // GIVEN

        // WHEN
        UserPersistentRepository repo = mongoDbRepository.userRepository();

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof MongoUserPersistentRepository);
    }

    @Test
    public void testMongoDbRepositoryInfoRepository() {
        // GIVEN

        // WHEN
        var repo = mongoDbRepository.infoRepository();

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof MongoInfoPersistentRepository);
    }
}
