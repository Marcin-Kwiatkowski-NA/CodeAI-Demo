package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Test
class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
        cassandraProperty.setHosts(new String[]{"host1", "host2"});
        cassandraProperty.setKeyspace("mykeyspace");
        cassandraProperty.setUser("user");
        cassandraProperty.setPassword("password");
    }

    @Test
    void getHosts() {
        // GIVEN: CassandraProperty object is initialized with host values.
        // WHEN: The getHosts() method is called.
        // THEN: The returned hosts array should match the initialized values.
        String[] expectedHosts = {"host1", "host2"};
        String[] actualHosts = cassandraProperty.getHosts();
        assertArrayEquals(expectedHosts, actualHosts);
    }

    @Test
    void getKeyspace() {
        // GIVEN: CassandraProperty object is initialized with keyspace value.
        // WHEN: The getKeyspace() method is called.
        // THEN: The returned keyspace string should match the initialized value.
        String expectedKeyspace = "mykeyspace";
        String actualKeyspace = cassandraProperty.getKeyspace();
        assertEquals(expectedKeyspace, actualKeyspace);
    }

    @Test
    void getUser() {
        // GIVEN: CassandraProperty object is initialized with user value.
        // WHEN: The getUser() method is called.
        // THEN: The returned user string should match the initialized value.
        String expectedUser = "user";
        String actualUser = cassandraProperty.getUser();
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getPassword() {
        // GIVEN: CassandraProperty object is initialized with password value.
        // WHEN: The getPassword() method is called.
        // THEN: The returned password string should match the initialized value.
        String expectedPassword = "password";
        String actualPassword = cassandraProperty.getPassword();
        assertEquals(expectedPassword, actualPassword);
    }
}
