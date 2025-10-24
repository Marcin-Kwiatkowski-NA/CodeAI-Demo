package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@ExtendWith(MockitoExtension.class)
public class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void testGetAndSetHosts() {
        // GIVEN: a set of hosts
        String[] hosts = {"127.0.0.1", "192.168.1.1"};

        // WHEN: setting the hosts
        cassandraProperty.setHosts(hosts);

        // THEN: the retrieved hosts should match the set value
        assertArrayEquals(hosts, cassandraProperty.getHosts());
    }

    @Test
    void testGetAndSetKeyspace() {
        // GIVEN: a keyspace name
        String keyspace = "test_keyspace";

        // WHEN: setting the keyspace
        cassandraProperty.setKeyspace(keyspace);

        // THEN: the retrieved keyspace should match the set value
        assertEquals(keyspace, cassandraProperty.getKeyspace());
    }

    @Test
    void testGetAndSetUser() {
        // GIVEN: a username
        String user = "test_user";

        // WHEN: setting the user
        cassandraProperty.setUser(user);

        // THEN: the retrieved user should match the set value
        assertEquals(user, cassandraProperty.getUser());
    }

    @Test
    void testGetAndSetPassword() {
        // GIVEN: a password (security-sensitive)
        String password = "secure_password";

        // WHEN: setting the password
        cassandraProperty.setPassword(password);

        // THEN: the retrieved password should match the set value
        assertEquals(password, cassandraProperty.getPassword());
    }

    @Test
    void testSetHostsWithNull() {
        // GIVEN: null hosts
        String[] hosts = null;

        // WHEN: setting null hosts
        cassandraProperty.setHosts(hosts);

        // THEN: the retrieved hosts should be null
        assertEquals(null, cassandraProperty.getHosts());
    }

    @Test
    void testSetKeyspaceWithNull() {
        // GIVEN: null keyspace
        String keyspace = null;

        // WHEN: setting null keyspace
        cassandraProperty.setKeyspace(keyspace);

        // THEN: the retrieved keyspace should be null
        assertEquals(null, cassandraProperty.getKeyspace());
    }

    @Test
    void testSetUserWithNull() {
        // GIVEN: null user
        String user = null;

        // WHEN: setting null user
        cassandraProperty.setUser(user);

        // THEN: the retrieved user should be null
        assertEquals(null, cassandraProperty.getUser());
    }

    @Test
    void testSetPasswordWithNull() {
        // GIVEN: null password (security-sensitive)
        String password = null;

        // WHEN: setting null password
        cassandraProperty.setPassword(password);

        // THEN: the retrieved password should be null
        assertEquals(null, cassandraProperty.getPassword());
    }

    @Test
    void testSetHostsWithEmptyArray() {
        // GIVEN: empty hosts array
        String[] hosts = {};

        // WHEN: setting empty hosts
        cassandraProperty.setHosts(hosts);

        // THEN: the retrieved hosts should be empty
        assertEquals(0, cassandraProperty.getHosts().length);
    }

    @Test
    void testSetHostsWithSingleHost() {
        // GIVEN: single host
        String[] hosts = {"127.0.0.1"};

        // WHEN: setting single host
        cassandraProperty.setHosts(hosts);

        // THEN: the retrieved hosts should contain exactly one element
        assertEquals(1, cassandraProperty.getHosts().length);
        assertEquals("127.0.0.1", cassandraProperty.getHosts()[0]);
    }
}
