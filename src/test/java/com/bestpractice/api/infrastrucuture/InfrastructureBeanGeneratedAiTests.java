package com.bestpractice.api.infrastrucuture;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.cassandra.CassandraInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraProperty;
import com.bestpractice.api.infrastrucuture.persistent.mongo.MongoUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoProperty;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.datastax.oss.driver.api.core.CqlSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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
        cassandraProperty.setHosts(new String[]{"localhost:9042"});
        cassandraProperty.setKeyspace("test_keyspace");
        cassandraDbRepository = new InfrastructureBean.CassandraDbRepository(cassandraProperty);
        MongoProperty mongoProperty = new MongoProperty();
        mongoProperty.setHost("localhost");
        mongoProperty.setPort(27017);
        mongoProperty.setUser("user");
        mongoProperty.setPassword("pass");
        mongoProperty.setAuthDatabase("admin");
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

        // WHEN
        DriverManagerDataSource ds = rdbmsDbRepository.dataSource();

        // THEN
        assertNotNull(ds);
    }

    @Test
    void givenRdbmsDbRepository_whenJdbcTemplate_thenReturnsJdbcTemplate() {
        // GIVEN

        // WHEN
        JdbcTemplate jdbcTemplate = rdbmsDbRepository.jdbcTemplate();

        // THEN
        assertNotNull(jdbcTemplate);
    }

    @Test
    void givenRdbmsDbRepository_whenUserRepository_thenReturnsRdbmsUserPersistentRepository() {
        // GIVEN
        JdbcTemplate jdbcTemplate = rdbmsDbRepository.jdbcTemplate();

        // WHEN
        UserPersistentRepository repo = rdbmsDbRepository.userRepository(jdbcTemplate);

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof RdbmsUserPersistentRepository);
    }

    @Test
    void givenRdbmsDbRepository_whenInfoRepository_thenReturnsRdbmsInfoPersistentRepository() {
        // GIVEN

        // WHEN
        InfoPersistentRepository repo = rdbmsDbRepository.infoRepository();

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof RdbmsInfoPersistentRepository);
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

    @Test
    void givenCassandraDbRepository_whenUserRepository_thenReturnsLocalUserPersistentRepository() {
        // GIVEN

        // WHEN
        UserPersistentRepository repo = cassandraDbRepository.userRepository();

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof LocalUserPersistentRepository);
    }

   @Test
    void givenCassandraDbRepository_whenInfoRepository_thenReturnsCassandraInfoPersistentRepository() {
        // GIVEN

        // WHEN
        InfoPersistentRepository repo = cassandraDbRepository.infoRepository();

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof CassandraInfoPersistentRepository);
    }

    @Test
    void givenMongoDbRepository_whenMongoClient_thenReturnsMongoClient() {
        // GIVEN

        // WHEN
        MongoClient client = mongoDbRepository.mongoClient();

        // THEN
        assertNotNull(client);
        client.close();
    }

    @Test
    void givenMongoDbRepository_whenMongoDatabase_thenReturnsMongoDatabase() {
        // GIVEN

        // WHEN
        MongoDatabase db = mongoDbRepository.mongoDatabase();

        // THEN
        assertNotNull(db);
        assertEquals("platform", db.getName());
    }

    @Test
    void givenMongoDbRepository_whenUserRepository_thenReturnsMongoUserPersistentRepository() {
        // GIVEN

        // WHEN
        UserPersistentRepository repo = mongoDbRepository.userRepository();

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof MongoUserPersistentRepository);
    }

    @Test
    void givenMongoDbRepository_whenInfoRepository_thenReturnsMongoInfoPersistentRepository() {
        // GIVEN

        // WHEN
        InfoPersistentRepository repo = mongoDbRepository.infoRepository();

        // THEN
        assertNotNull(repo);
        assertTrue(repo instanceof MongoInfoPersistentRepository);
    }
}
