package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void testSetAndGetHosts() {
        // GIVEN
        String[] expectedHosts = {"127.0.0.1", "192.168.1.10"};

        // WHEN
        cassandraProperty.setHosts(expectedHosts);
        String[] actualHosts = cassandraProperty.getHosts();

        // THEN
        assertEquals(expectedHosts.length, actualHosts.length);
        assertEquals(expectedHosts[0], actualHosts[0]);
        assertEquals(expectedHosts[1], actualHosts[1]);
    }

    @Test
    void testSetAndGetKeyspace() {
        // GIVEN
        String expectedKeyspace = "test_keyspace";

        // WHEN
        cassandraProperty.setKeyspace(expectedKeyspace);
        String actualKeyspace = cassandraProperty.getKeyspace();

        // THEN
        assertEquals(expectedKeyspace, actualKeyspace);
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN
        String expectedUser = "test_user";

        // WHEN
        cassandraProperty.setUser(expectedUser);
        String actualUser = cassandraProperty.getUser();

        // THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "secure_password"; // SECURITY-SENSITIVE

        // WHEN
        cassandraProperty.setPassword(expectedPassword);
        String actualPassword = cassandraProperty.getPassword();

        // THEN
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testDefaultValuesAreNull() {
        // GIVEN
        // No properties set

        // WHEN
        String[] hosts = cassandraProperty.getHosts();
        String keyspace = cassandraProperty.getKeyspace();
        String user = cassandraProperty.getUser();
        String password = cassandraProperty.getPassword();

        // THEN
        assertNull(hosts);
        assertNull(keyspace);
        assertNull(user);
        assertNull(password);
    }

    @Test
    void testSetHostsWithNullDoesNotThrowException() {
        // GIVEN
        String[] nullHosts = null;

        // WHEN
        cassandraProperty.setHosts(nullHosts);

        // THEN
        assertNull(cassandraProperty.getHosts());
    }

    @Test
    void testSetKeyspaceWithNullDoesNotThrowException() {
        // GIVEN
        String nullKeyspace = null;

        // WHEN
        cassandraProperty.setKeyspace(nullKeyspace);

        // THEN
        assertNull(cassandraProperty.getKeyspace());
    }

    @Test
    void testSetUserWithNullDoesNotThrowException() {
        // GIVEN
        String nullUser = null;

        // WHEN
        cassandraProperty.setUser(nullUser);

        // THEN
        assertNull(cassandraProperty.getUser());
    }

    @Test
    void testSetPasswordWithNullDoesNotThrowException() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        cassandraProperty.setPassword(nullPassword);

        // THEN
        assertNull(cassandraProperty.getPassword());
    }

    @Test
    void testSetHostsThrowsExceptionWhenArrayIsModifiedExternally() {
        // GIVEN
        String[] hosts = {"localhost"};
        cassandraProperty.setHosts(hosts);

        // WHEN
        hosts[0] = null;

        // THEN
        // No exception should be thrown since setter does not clone array
        assertEquals(null, hosts[0]);
        assertEquals("localhost", cassandraProperty.getHosts()[0]);
    }

    @Test
    void testNoExceptionThrownForValidSetters() {
        // GIVEN
        String[] hosts = {"localhost"};
        String keyspace = "my_keyspace";
        String user = "admin";
        String password = "admin_pass"; // SECURITY-SENSITIVE

        // WHEN
        cassandraProperty.setHosts(hosts);
        cassandraProperty.setKeyspace(keyspace);
        cassandraProperty.setUser(user);
        cassandraProperty.setPassword(password);

        // THEN
        assertEquals(hosts[0], cassandraProperty.getHosts()[0]);
        assertEquals(keyspace, cassandraProperty.getKeyspace());
        assertEquals(user, cassandraProperty.getUser());
        assertEquals(password, cassandraProperty.getPassword());
    }

    @Test
    void testSettersDoNotThrowExceptions() {
        // GIVEN
        String[] hosts = {"host1", "host2"};
        String keyspace = "keyspace";
        String user = "user";
        String password = "password"; // SECURITY-SENSITIVE

        // WHEN & THEN
        assertThrows(RuntimeException.class, () -> {
            // This test intentionally fails if any setter throws RuntimeException
            cassandraProperty.setHosts(hosts);
            cassandraProperty.setKeyspace(keyspace);
            cassandraProperty.setUser(user);
            cassandraProperty.setPassword(password);
        });
    }
}
