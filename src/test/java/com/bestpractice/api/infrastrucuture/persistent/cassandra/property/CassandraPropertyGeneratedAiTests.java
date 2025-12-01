package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    // Test for getHosts and setHosts
    @Test
    void givenHosts_whenSetHosts_thenGetHostsReturnsSameValue() {
        // GIVEN
        String[] hosts = {"127.0.0.1", "192.168.1.1"};

        // WHEN
        cassandraProperty.setHosts(hosts);

        // THEN
        assertThat(cassandraProperty.getHosts()).isEqualTo(hosts);
    }

    // Test for getKeyspace and setKeyspace
    @Test
    void givenKeyspace_whenSetKeyspace_thenGetKeyspaceReturnsSameValue() {
        // GIVEN
        String keyspace = "test_keyspace";

        // WHEN
        cassandraProperty.setKeyspace(keyspace);

        // THEN
        assertThat(cassandraProperty.getKeyspace()).isEqualTo(keyspace);
    }

    // Test for getUser and setUser
    @Test
    void givenUser_whenSetUser_thenGetUserReturnsSameValue() {
        // GIVEN
        String user = "test_user";

        // WHEN
        cassandraProperty.setUser(user);

        // THEN
        assertThat(cassandraProperty.getUser()).isEqualTo(user);
    }

    // Test for getPassword and setPassword
    @Test
    void givenPassword_whenSetPassword_thenGetPasswordReturnsSameValue() {
        // GIVEN
        String password = "test_password";

        // WHEN
        cassandraProperty.setPassword(password);

        // THEN
        assertThat(cassandraProperty.getPassword()).isEqualTo(password);
    }
}
