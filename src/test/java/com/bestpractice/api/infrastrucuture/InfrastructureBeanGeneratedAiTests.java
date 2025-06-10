package com.bestpractice.api.infrastrucuture;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraProperty;
import com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InfrastructureBeanGeneratedAiTests {

    @InjectMocks
    private InfrastructureBean infrastructureBean;

    @Mock
    private CassandraProperty cassandraProperty;

    @Mock
    private MongoProperty mongoProperty;

    @BeforeEach
    public void setUp() {
        // Reset any state before each test
    }

    @Test
    public void testCassandraDbRepository() {
        // GIVEN
        when(cassandraProperty.getHosts()).thenReturn(new String[]{"localhost:9042"});
        when(cassandraProperty.getKeyspace()).thenReturn("test_keyspace");

        InfrastructureBean.CassandraDbRepository cassandraDbRepository = new InfrastructureBean.CassandraDbRepository(cassandraProperty);

        // WHEN
        var cqlSession = cassandraDbRepository.cqlSession();

        // THEN
        assertNotNull(cqlSession);
    }

    @Test
    public void testMongoDbRepository() {
        // GIVEN
        when(mongoProperty.getHost()).thenReturn("localhost");
        when(mongoProperty.getPort()).thenReturn(27017);

        InfrastructureBean.MongoDbRepository mongoDbRepository = new InfrastructureBean.MongoDbRepository(mongoProperty);

        // WHEN
        var mongoClient = mongoDbRepository.mongoClient();

        // THEN
        assertNotNull(mongoClient);
    }
}
