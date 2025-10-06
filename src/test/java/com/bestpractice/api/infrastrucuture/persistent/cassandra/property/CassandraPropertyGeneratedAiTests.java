package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void testSetAndGetHosts() {
        // GIVEN: an array of hosts
        String[] hosts = {"host1", "host2"};

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
        // GIVEN: a password (security-sensitive)
        String password = "secure_password";

        // WHEN: setting password in the property
        cassandraProperty.setPassword(password);

        // THEN: the retrieved password should match the set value
        assertEquals(password, cassandraProperty.getPassword());
    }

    @Test
    void testSetHostsWithNull() {
        // GIVEN: null hosts array
        String[] hosts = null;

        // WHEN: setting hosts to null
        cassandraProperty.setHosts(hosts);

        // THEN: the retrieved hosts should be null
        assertEquals(null, cassandraProperty.getHosts());
    }

    @Test
    void testSetKeyspaceWithNull() {
        // GIVEN: null keyspace
        String keyspace = null;

        // WHEN: setting keyspace to null
        cassandraProperty.setKeyspace(keyspace);

        // THEN: the retrieved keyspace should be null
        assertEquals(null, cassandraProperty.getKeyspace());
    }

    @Test
    void testSetUserWithNull() {
        // GIVEN: null user
        String user = null;

        // WHEN: setting user to null
        cassandraProperty.setUser(user);

        // THEN: the retrieved user should be null
        assertEquals(null, cassandraProperty.getUser());
    }

    @Test
    void testSetPasswordWithNull() {
        // GIVEN: null password (security-sensitive)
        String password = null;

        // WHEN: setting password to null
        cassandraProperty.setPassword(password);

        // THEN: the retrieved password should be null
        assertEquals(null, cassandraProperty.getPassword());
    }

    @Test
    void testAccessHostsThrowsExceptionWhenArrayContainsNullElement() {
        // GIVEN: hosts array with a null element
        String[] hosts = {"host1", null};

        // WHEN: setting hosts with a null element
        cassandraProperty.setHosts(hosts);

        // THEN: accessing a null element should throw NullPointerException
        assertThrows(NullPointerException.class, () -> {
            for (String host : cassandraProperty.getHosts()) {
                host.length();
            }
        });
    }
}
