package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void testGetSetHosts() {
        // GIVEN: CassandraProperty object is created
        // WHEN: hosts array is set to {"host1", "host2"}
        cassandraProperty.setHosts(new String[]{"host1", "host2"});
        // THEN: The hosts array should be equal to {"host1", "host2"}
        String[] expectedHosts = {"host1", "host2"};
        assertEquals(expectedHosts, cassandraProperty.getHosts());
    }

    @Test
    void testGetSetKeyspace() {
        // GIVEN: CassandraProperty object is created
        // WHEN: keyspace is set to "mykeyspace"
        cassandraProperty.setKeyspace("mykeyspace");
        // THEN: The keyspace should be equal to "mykeyspace"
        String expectedKeyspace = "mykeyspace";
        assertEquals(expectedKeyspace, cassandraProperty.getKeyspace());
    }

    @Test
    void testGetSetUser() {
        // GIVEN: CassandraProperty object is created
        // WHEN: user is set to "cassandrauser"
        cassandraProperty.setUser("cassandrauser");
        // THEN: The user should be equal to "cassandrauser"
        String expectedUser = "cassandrauser";
        assertEquals(expectedUser, cassandraProperty.getUser());
    }

    @Test
    void testGetSetPassword() {
        // GIVEN: CassandraProperty object is created
        // WHEN: password is set to "secretpassword"
        cassandraProperty.setPassword("secretpassword");
        // THEN: The password should be equal to "secretpassword"
        String expectedPassword = "secretpassword";
        assertEquals(expectedPassword, cassandraProperty.getPassword());
    }
}
