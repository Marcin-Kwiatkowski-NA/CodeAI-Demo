package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void testSetAndGetHosts() {
        // GIVEN: an array of hosts
        String[] hosts = {"127.0.0.1", "192.168.1.1"};

        // WHEN: setting hosts in the property
        cassandraProperty.setHosts(hosts);

        // THEN: the retrieved hosts should match the set value
        assertArrayEquals(hosts, cassandraProperty.getHosts());
    }

    @Test
    void testSetAndGetKeyspace() {
        // GIVEN: a keyspace name
        String keyspace = "test_keyspace";

        // WHEN: setting keyspace in the property
        cassandraProperty.setKeyspace(keyspace);

        // THEN: the retrieved keyspace should match the set value
        assertEquals(keyspace, cassandraProperty.getKeyspace());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN: a username
        String user = "test_user";

        // WHEN: setting user in the property
        cassandraProperty.setUser(user);

        // THEN: the retrieved user should match the set value
        assertEquals(user, cassandraProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // SECURITY-SENSITIVE: password handling
        // GIVEN: a password
        String password = "secure_password";

        // WHEN: setting password in the property
        cassandraProperty.setPassword(password);

        // THEN: the retrieved password should match the set value
        assertEquals(password, cassandraProperty.getPassword());
    }
}
