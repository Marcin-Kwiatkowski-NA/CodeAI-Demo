package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
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
    void testSetAndGetHosts() {
        // GIVEN
        String[] hosts = {"127.0.0.1", "192.168.1.10"};

        // WHEN
        cassandraProperty.setHosts(hosts);
        String[] result = cassandraProperty.getHosts();

        // THEN
        assertThat(result).isSameAs(hosts);
        assertThat(result).containsExactly("127.0.0.1", "192.168.1.10");
    }

    @Test
    void testSetAndGetKeyspace() {
        // GIVEN
        String keyspace = "test_keyspace";

        // WHEN
        cassandraProperty.setKeyspace(keyspace);
        String result = cassandraProperty.getKeyspace();

        // THEN
        assertThat(result).isEqualTo("test_keyspace");
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN
        String user = "cassandra_user";

        // WHEN
        cassandraProperty.setUser(user);
        String result = cassandraProperty.getUser();

        // THEN
        assertThat(result).isEqualTo("cassandra_user");
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN
        String password = "secret";

        // WHEN
        cassandraProperty.setPassword(password);
        String result = cassandraProperty.getPassword();

        // THEN
        assertThat(result).isEqualTo("secret");
    }

    @Test
    void testSetHostsWithNull() {
        // GIVEN
        String[] nullHosts = null;

        // WHEN
        cassandraProperty.setHosts(nullHosts);
        String[] result = cassandraProperty.getHosts();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testSetKeyspaceWithNull() {
        // GIVEN
        String nullKeyspace = null;

        // WHEN
        cassandraProperty.setKeyspace(nullKeyspace);
        String result = cassandraProperty.getKeyspace();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testSetUserWithNull() {
        // GIVEN
        String nullUser = null;

        // WHEN
        cassandraProperty.setUser(nullUser);
        String result = cassandraProperty.getUser();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testSetPasswordWithNull() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        cassandraProperty.setPassword(nullPassword);
        String result = cassandraProperty.getPassword();

        // THEN
        assertThat(result).isNull();
    }
}
