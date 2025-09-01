package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraProperty;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Test
class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    void testGetHosts() {
        // GIVEN
        String[] hosts = {"host1.example.com", "host2.example.com"};
        cassandraProperty.setHosts(hosts);

        // WHEN
        String[] retrievedHosts = cassandraProperty.getHosts();

        // THEN
        assertArrayEquals(hosts, retrievedHosts);
    }

    void testSetHosts() {
        // GIVEN
        String[] hosts = {"host1.example.com", "host2.example.com"};
        cassandraProperty.setHosts(hosts);

        // WHEN
        cassandraProperty.setHosts(new String[]{"new_host1.example.com", "new_host2.example.com"});

        // THEN
        assertArrayEquals(new String[]{"new_host1.example.com", "new_host2.example.com"}, cassandraProperty.getHosts());
    }

    void testGetKeyspace() {
        // GIVEN
        String keyspace = "mykeyspace";
        cassandraProperty.setKeyspace(keyspace);

        // WHEN
        String retrievedKeyspace = cassandraProperty.getKeyspace();

        // THEN
        assertEquals(keyspace, retrievedKeyspace);
    }

    void testSetKeyspace() {
        // GIVEN
        String keyspace = "mykeyspace";
        cassandraProperty.setKeyspace(keyspace);

        // WHEN
        cassandraProperty.setKeyspace("newkeyspace");

        // THEN
        assertEquals("newkeyspace", cassandraProperty.getKeyspace());
    }

    void testGetUser() {
        // GIVEN
        String user = "cassandrauser";
        cassandraProperty.setUser(user);

        // WHEN
        String retrievedUser = cassandraProperty.getUser();

        // THEN
        assertEquals(user, retrievedUser);
    }

    void testSetUser() {
        // GIVEN
        String user = "cassandrauser";
        cassandraProperty.setUser(user);

        // WHEN
        cassandraProperty.setUser("newuser");

        // THEN
        assertEquals("newuser", cassandraProperty.getUser());
    }

    void testGetPassword() {
        // GIVEN
        String password = "cassandra_password";
        cassandraProperty.setPassword(password);

        // WHEN
        String retrievedPassword = cassandraProperty.getPassword();

        // THEN
        assertEquals(password, retrievedPassword);
    }

    void testSetPassword() {
        // GIVEN
        String password = "cassandra_password";
        cassandraProperty.setPassword(password);

        // WHEN
        cassandraProperty.setPassword("newpassword");

        // THEN
        assertEquals("newpassword", cassandraProperty.getPassword());
    }
}
