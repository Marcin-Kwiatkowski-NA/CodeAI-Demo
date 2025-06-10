package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    public void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    public void testSetAndGetHosts() {
        // GIVEN
        String[] expectedHosts = {"host1", "host2"};

        // WHEN
        cassandraProperty.setHosts(expectedHosts);
        String[] actualHosts = cassandraProperty.getHosts();

        // THEN
        assertArrayEquals(expectedHosts, actualHosts);
    }

    @Test
    public void testSetAndGetKeyspace() {
        // GIVEN
        String expectedKeyspace = "test_keyspace";

        // WHEN
        cassandraProperty.setKeyspace(expectedKeyspace);
        String actualKeyspace = cassandraProperty.getKeyspace();

        // THEN
        assertEquals(expectedKeyspace, actualKeyspace);
    }

    @Test
    public void testSetAndGetUser() {
        // GIVEN
        String expectedUser = "test_user";

        // WHEN
        cassandraProperty.setUser(expectedUser);
        String actualUser = cassandraProperty.getUser();

        // THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "test_password";

        // WHEN
        cassandraProperty.setPassword(expectedPassword);
        String actualPassword = cassandraProperty.getPassword();

        // THEN
        assertEquals(expectedPassword, actualPassword);
    }
}
