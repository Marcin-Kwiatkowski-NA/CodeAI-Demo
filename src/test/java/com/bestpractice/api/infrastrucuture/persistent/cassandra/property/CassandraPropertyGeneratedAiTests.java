package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class CassandraPropertyGeneratedAiTests {

    @InjectMocks
    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void shouldGetAndSetHosts() {
        // GIVEN
        String[] hosts = {"localhost", "127.0.0.1"};

        // WHEN
        cassandraProperty.setHosts(hosts);

        // THEN
        assertEquals(hosts, cassandraProperty.getHosts());
    }

    @Test
    void shouldGetAndSetKeyspace() {
        // GIVEN
        String keyspace = "test_ks";

        // WHEN
        cassandraProperty.setKeyspace(keyspace);

        // THEN
        assertEquals(keyspace, cassandraProperty.getKeyspace());
    }

    @Test
    void shouldGetAndSetUser() {
        // GIVEN
        String user = "test_user";

        // WHEN
        cassandraProperty.setUser(user);

        // THEN
        assertEquals(user, cassandraProperty.getUser());
    }

    @Test
    void shouldGetAndSetPassword() {
        // GIVEN
        String password = "test_password";

        // WHEN
        cassandraProperty.setPassword(password);

        // THEN
        assertEquals(password, cassandraProperty.getPassword());
    }

    @Test
    void shouldHandleNullValues() {
        // GIVEN
        String[] hosts = null;
        String keyspace = null;
        String user = null;
        String password = null;

        // WHEN
        cassandraProperty.setHosts(hosts);
        cassandraProperty.setKeyspace(keyspace);
        cassandraProperty.setUser(user);
        cassandraProperty.setPassword(password);

        // THEN
        assertNull(cassandraProperty.getHosts());
        assertNull(cassandraProperty.getKeyspace());
        assertNull(cassandraProperty.getUser());
        assertNull(cassandraProperty.getPassword());
    }

    @Test
    void shouldHandleEmptyValues() {
        // GIVEN
        String[] hosts = new String[0];
        String keyspace = "";
        String user = "";
        String password = "";

        // WHEN
        cassandraProperty.setHosts(hosts);
        cassandraProperty.setKeyspace(keyspace);
        cassandraProperty.setUser(user);
        cassandraProperty.setPassword(password);

        // THEN
        assertEquals(0, cassandraProperty.getHosts().length);
        assertEquals(keyspace, cassandraProperty.getKeyspace());
        assertEquals(user, cassandraProperty.getUser());
        assertEquals(password, cassandraProperty.getPassword());
    }
}
