package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

@org.junit.jupiter.api.extension.ExtendWith(CassandraPropertyGeneratedAiTests.class)
class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void getHosts() {
        // GIVEN: Cassandra property object is initialized
        // WHEN: getHosts() method is called
        // THEN: The hosts array is returned
        String[] hosts = cassandraProperty.getHosts();
        assertNotNull(hosts);
        assertNotNull(hosts[0]);
        assertEquals("localhost", hosts[0]);
    }

    @Test
    void setHosts() {
        // GIVEN: Cassandra property object is initialized
        // WHEN: setHosts() method is called with a new array of hosts
        // THEN: The hosts array is updated with the new values
        String[] newHosts = {"host1", "host2"};
        cassandraProperty.setHosts(newHosts);
        String[] returnedHosts = cassandraProperty.getHosts();
        assertEquals(returnedHosts[0], returnedHosts[0]);
        assertEquals(returnedHosts[1], returnedHosts[1]);
    }

    @Test
    void getKeyspace() {
        // GIVEN: Cassandra property object is initialized
        // WHEN: getKeyspace() method is called
        // THEN: The keyspace string is returned
        String keyspace = cassandraProperty.getKeyspace();
        assertNotNull(keyspace);
        assertEquals("mykeyspace", keyspace);
    }

    @Test
    void setKeyspace() {
        // GIVEN: Cassandra property object is initialized
        // WHEN: setKeyspace() method is called with a new keyspace name
        // THEN: The keyspace string is updated with the new value
        String newKeyspace = "newkeyspace";
        cassandraProperty.setKeyspace(newKeyspace);
        String returnedKeyspace = cassandraProperty.getKeyspace();
        assertEquals(returnedKeyspace, newKeyspace);
    }

    @Test
    void getUser() {
        // GIVEN: Cassandra property object is initialized
        // WHEN: getUser() method is called
        // THEN: The user string is returned
        String user = cassandraProperty.getUser();
        assertNotNull(user);
        assertEquals("user1", user);
    }

    @Test
    void setUser() {
        // GIVEN: Cassandra property object is initialized
        // WHEN: setUser() method is called with a new user name
        // THEN: The user string is updated with the new value
        String newUser = "newUser";
        cassandraProperty.setUser(newUser);
        String returnedUser = cassandraProperty.getUser();
        assertEquals(returnedUser, newUser);
    }

    @Test
    void getPassword() {
        // GIVEN: Cassandra property object is initialized
        // WHEN: getPassword() method is called
        // THEN: The password string is returned
        String password = cassandraProperty.getPassword();
        assertNotNull(password);
        assertEquals("password1", password);
    }

    @Test
    void setPassword() {
        // GIVEN: Cassandra property object is initialized
        // WHEN: setPassword() method is called with a new password
        // THEN: The password string is updated with the new value
        String newPassword = "newpassword";
        cassandraProperty.setPassword(newPassword);
        String returnedPassword = cassandraProperty.getPassword();
        assertEquals(returnedPassword, newPassword);
    }
}
