package com.bestpractice.api.infrastrucuture;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit5.PowerMockExtension;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.bestpractice.api.infrastrucuture.InfrastructureBean.CassandraDbRepository;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.MongoDbRepository;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RdbmsDbRepository;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.LocalInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.CassandraProperty;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.MongoProperty;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RdbmsUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.CassandraUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.MongoUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisProperty;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnection;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPool;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.Builder;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;
import com.bestpractice.api.infrastrucuture.InfrastructureBean.RedisConnectionPoolConfig.BuilderFactory;

@PrepareForTest({ CqlSession.class, MongoClients.class })
@ExtendWith(PowerMockExtension.class)
public class InfrastructureBeanTest {

    @Mock
    private CqlSessionBuilder mockBuilder;

    @Mock
    private CqlSession mockSession;

    @Mock
    private MongoClient mockMongoClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // mock static CqlSession.builder()
        PowerMockito.mockStatic(CqlSession.class);
        mockBuilder = PowerMockito.mock(CqlSessionBuilder.class);
        mockSession = PowerMockito.mock(CqlSession.class);
        PowerMockito.when(CqlSession.builder()).thenReturn(mockBuilder);
        PowerMockito.when(mockBuilder.build()).thenReturn(mockSession);

        // mock static MongoClients.create()
        PowerMockito.mockStatic(MongoClients.class);
        mockMongoClient = PowerMockito.mock(MongoClient.class);
        PowerMockito.when(MongoClients.create(Mockito.anyString())).thenReturn(mockMongoClient);
    }

    /* ------------------------------------------------------------------ */
    /* 1. Tests for the in‑memory persistence implementation              */
    /* ------------------------------------------------------------------ */

    @Test
    public void testLocalInfoPersistentRepository_CRUD() {
        LocalInfoPersistentRepository repo = new LocalInfoPersistentRepository();

        // create
        repo.create("key1", "value1");
        assertThat(repo.find("key1")).isEqualTo("value1");

        // replace
        repo.replace("key1", "value2");
        assertThat(repo.find("key1")).isEqualTo("value2");

        // remove
        boolean removed = repo.removeById("key1");
        assertThat(removed).isTrue();
        assertThat(repo.find("key1")).isNull();
    }

    /* ------------------------------------------------------------------ */
    /* 2. Tests for the RDBMS persistence implementation                  */
    /* ------------------------------------------------------------------ */

    @Test
    public void testRDBMSRepository_Integration() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(RDBMSRepositoryConfig.class);
        ctx.refresh();

        RDBMSRepositoryConfig config = ctx.getBean(RDBMSRepositoryConfig.class);
        Assertions.assertNotNull(config, "Configuration bean should be present");
        Assertions.assertNotNull(config.getDataSource(), "DataSource should be configured");
        ctx.close();
    }

    /* ------------------------------------------------------------------ */
    /* 3. Tests for the NoSQL persistence implementation                  */
    /* ------------------------------------------------------------------ */

    @Test
    public void testNoSQLRepository_Integration() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(NoSQLRepositoryConfig.class);
        ctx.refresh();

        NoSQLRepositoryConfig config = ctx.getBean(NoSQLRepositoryConfig.class);
        Assertions.assertNotNull(config, "Configuration bean should be present");
        Assertions.assertNotNull(config.getMongoTemplate(), "MongoTemplate should be configured");
        ctx.close();
    }

    /* ------------------------------------------------------------------ */
    /* 4. Tests for the InfluxDB persistence implementation                */
    /* ------------------------------------------------------------------ */

    @Test
    public void testInfluxDBRepository_Integration() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(InfluxDBRepositoryConfig.class);
        ctx.refresh();

        InfluxDBRepositoryConfig config = ctx.getBean(InfluxDBRepositoryConfig.class);
        Assertions.assertNotNull(config, "Configuration bean should be present");
        Assertions.assertNotNull(config.getInfluxDB(), "InfluxDB client should be configured");
        ctx.close();
    }

    /* ------------------------------------------------------------------ */
    /* 5. Tests for the MongoDB persistence implementation                */
    /* ------------------------------------------------------------------ */

    @Test
    public void testMongoDBRepository_Integration() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(MongoDBRepositoryConfig.class);
        ctx.refresh();

        MongoDBRepositoryConfig config = ctx.getBean(MongoDBRepositoryConfig.class);
        Assertions.assertNotNull(config, "Configuration bean should be present");
        Assertions.assertNotNull(config.getMongoTemplate(), "MongoTemplate should be configured");
        ctx.close();
    }

    /* ------------------------------------------------------------------ */
    /* 6. Tests for the Cassandra persistence implementation              */
    /* ------------------------------------------------------------------ */

    @Test
    public void testCassandraRepository_Integration() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(CassandraRepositoryConfig.class);
        ctx.refresh();

        CassandraRepositoryConfig config = ctx.getBean(CassandraRepositoryConfig.class);
        Assertions.assertNotNull(config, "Configuration bean should be present");
        Assertions.assertNotNull(config.getCqlSession(), "CQL session should be configured");
        ctx.close();
    }

    /* ------------------------------------------------------------------ */
    /* 7. Tests for the Elasticsearch persistence implementation          */
    /* ------------------------------------------------------------------ */

    @Test
    public void testElasticSearchRepository_Integration() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ElasticSearchRepositoryConfig.class);
        ctx.refresh();

        ElasticSearchRepositoryConfig config = ctx.getBean(ElasticSearchRepositoryConfig.class);
        Assertions.assertNotNull(config, "Configuration bean should be present");
        Assertions.assertNotNull(config.getElasticsearchOperations(), "ElasticsearchOperations should be configured");
        ctx.close();
    }

    /* ------------------------------------------------------------------ */
    /* 8. Tests for the MongoDB persistence implementation (alternative)  */
    /* ------------------------------------------------------------------ */

    @Test
    public void testMongoDBRepository_Integration_Alternative() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(MongoDBRepositoryConfigAlternative.class);
        ctx.refresh();

        MongoDBRepositoryConfigAlternative config = ctx.getBean(MongoDBRepositoryConfigAlternative.class);
        Assertions.assertNotNull(config, "Configuration bean should be present");
        Assertions.assertNotNull(config.getMongoTemplate(), "MongoTemplate should be configured");
        ctx.close();
    }

    /* ------------------------------------------------------------------ */
    /* 9. Tests for the InfluxDB persistence implementation (alternative)  */
    /* ------------------------------------------------------------------ */

    @Test
    public void testInfluxDBRepository_Integration_Alternative() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(InfluxDBRepositoryConfigAlternative.class);
        ctx.refresh();

        InfluxDBRepositoryConfigAlternative config = ctx.getBean(InfluxDBRepositoryConfigAlternative.class);
        Assertions.assertNotNull(config, "Configuration bean should be present");
        Assertions.assertNotNull(config.getInfluxDB(), "InfluxDB client should be configured");
        ctx.close();
    }
}
