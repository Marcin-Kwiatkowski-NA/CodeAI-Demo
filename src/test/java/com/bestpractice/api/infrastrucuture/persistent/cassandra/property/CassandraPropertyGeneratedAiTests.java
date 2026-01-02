package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void testHostsGetterAndSetter() {
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
    void testEmptyHostsArray() {
        // GIVEN
        String[] emptyHosts = {};

        // WHEN
        cassandraProperty.setHosts(emptyHosts);
        String[] result = cassandraProperty.getHosts();

        // THEN
        assertThat(result).isSameAs(emptyHosts);
        assertThat(result).isEmpty();
    }

    @Test
    void testNullHostsArray() {
        // GIVEN
        String[] nullHosts = null;

        // WHEN
        cassandraProperty.setHosts(nullHosts);
        String[] result = cassandraProperty.getHosts();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testKeyspaceGetterAndSetter() {
        // GIVEN
        String keyspace = "test_keyspace";

        // WHEN
        cassandraProperty.setKeyspace(keyspace);
        String result = cassandraProperty.getKeyspace();

        // THEN
        assertThat(result).isEqualTo(keyspace);
    }

    @Test
    void testKeyspaceNullValue() {
        // GIVEN
        String nullKeyspace = null;

        // WHEN
        cassandraProperty.setKeyspace(nullKeyspace);
        String result = cassandraProperty.getKeyspace();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testUserGetterAndSetter() {
        // GIVEN
        String user = "cassandra_user";

        // WHEN
        cassandraProperty.setUser(user);
        String result = cassandraProperty.getUser();

        // THEN
        assertThat(result).isEqualTo(user);
    }

    @Test
    void testUserNullValue() {
        // GIVEN
        String nullUser = null;

        // WHEN
        cassandraProperty.setUser(nullUser);
        String result = cassandraProperty.getUser();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testPasswordGetterAndSetter() {
        // GIVEN
        String password = "secret";

        // WHEN
        cassandraProperty.setPassword(password);
        String result = cassandraProperty.getPassword();

        // THEN
        assertThat(result).isEqualTo(password);
    }

    @Test
    void testPasswordNullValue() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        cassandraProperty.setPassword(nullPassword);
        String result = cassandraProperty.getPassword();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testMultiplePropertiesSet() {
        // GIVEN
        String[] hosts = {"10.0.0.1"};
        String keyspace = "ks";
        String user = "usr";
        String password = "pwd";

        // WHEN
        cassandraProperty.setHosts(hosts);
        cassandraProperty.setKeyspace(keyspace);
        cassandraProperty.setUser(user);
        cassandraProperty.setPassword(password);

        // THEN
        assertThat(cassandraProperty.getHosts()).isSameAs(hosts);
        assertThat(cassandraProperty.getKeyspace()).isEqualTo(keyspace);
        assertThat(cassandraProperty.getUser()).isEqualTo(user);
        assertThat(cassandraProperty.getPassword()).isEqualTo(password);
    }

    @Test
    void testHostsArrayModificationAfterSet() {
        // GIVEN
        String[] hosts = {"host1"};
        cassandraProperty.setHosts(hosts);

        // WHEN
        hosts[0] = "modifiedHost";

        // THEN
        // The internal array should reflect the change because the same array reference is stored
        assertThat(cassandraProperty.getHosts()).containsExactly("modifiedHost");
    }

    @Test
    void testHostsArrayReassignmentAfterNull() {
        // GIVEN
        String[] initialHosts = {"initial"};
        cassandraProperty.setHosts(initialHosts);
        cassandraProperty.setHosts(null);

        // WHEN
        String[] newHosts = {"newHost"};
        cassandraProperty.setHosts(newHosts);

        // THEN
        assertThat(cassandraProperty.getHosts()).isSameAs(newHosts);
        assertThat(cassandraProperty.getHosts()).containsExactly("newHost");
    }
}
