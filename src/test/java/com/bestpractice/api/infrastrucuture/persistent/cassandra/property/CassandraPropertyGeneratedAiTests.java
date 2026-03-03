package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void testDefaultValues() {
        // GIVEN a newly created CassandraProperty instance
        // WHEN no setters are called
        // THEN all properties should be null
        assertThat(cassandraProperty.getHosts()).isNull();
        assertThat(cassandraProperty.getKeyspace()).isNull();
        assertThat(cassandraProperty.getUser()).isNull();
        assertThat(cassandraProperty.getPassword()).isNull();
    }

    @Test
    void testSetAndGetHosts() {
        // GIVEN a CassandraProperty instance
        String[] hosts = {"127.0.0.1", "192.168.1.10"};
        // WHEN setting hosts
        cassandraProperty.setHosts(hosts);
        // THEN retrieving hosts should return the same array
        assertThat(cassandraProperty.getHosts()).isSameAs(hosts);
        assertThat(cassandraProperty.getHosts()).containsExactly("127.0.0.1", "192.168.1.10");
    }

    @Test
    void testSetAndGetHostsEmptyArray() {
        // GIVEN a CassandraProperty instance
        String[] hosts = {};
        // WHEN setting an empty array
        cassandraProperty.setHosts(hosts);
        // THEN retrieving hosts should return an empty array
        assertThat(cassandraProperty.getHosts()).isSameAs(hosts);
        assertThat(cassandraProperty.getHosts()).isEmpty();
    }

    @Test
    void testSetAndGetHostsNull() {
        // GIVEN a CassandraProperty instance
        // WHEN setting hosts to null
        cassandraProperty.setHosts(null);
        // THEN retrieving hosts should be null
        assertThat(cassandraProperty.getHosts()).isNull();
    }

    @Test
    void testSetAndGetKeyspace() {
        // GIVEN a CassandraProperty instance
        String keyspace = "test_keyspace";
        // WHEN setting keyspace
        cassandraProperty.setKeyspace(keyspace);
        // THEN retrieving keyspace should return the set value
        assertThat(cassandraProperty.getKeyspace()).isEqualTo("test_keyspace");
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN a CassandraProperty instance
        String user = "admin";
        // WHEN setting user
        cassandraProperty.setUser(user);
        // THEN retrieving user should return the set value
        assertThat(cassandraProperty.getUser()).isEqualTo("admin");
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN a CassandraProperty instance
        String password = "secret";
        // WHEN setting password
        cassandraProperty.setPassword(password);
        // THEN retrieving password should return the set value
        assertThat(cassandraProperty.getPassword()).isEqualTo("secret");
    }

    @Test
    void testIndependentStateAfterReset() {
        // GIVEN a CassandraProperty instance with values set
        cassandraProperty.setHosts(new String[]{"host1"});
        cassandraProperty.setKeyspace("ks");
        cassandraProperty.setUser("user");
        cassandraProperty.setPassword("pwd");
        // WHEN a new instance is created
        CassandraProperty newInstance = new CassandraProperty();
        // THEN the new instance should have null values
        assertThat(newInstance.getHosts()).isNull();
        assertThat(newInstance.getKeyspace()).isNull();
        assertThat(newInstance.getUser()).isNull();
        assertThat(newInstance.getPassword()).isNull();
    }
}
