package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

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

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testDefaultValues() {
        // GIVEN
        // WHEN
        // THEN
        assertThat(mongoProperty.getHost()).isNull();
        assertThat(mongoProperty.getPort()).isEqualTo(0);
        assertThat(mongoProperty.getAuthDatabase()).isNull();
        assertThat(mongoProperty.getPlatformDatabase()).isNull();
        assertThat(mongoProperty.getUser()).isNull();
        assertThat(mongoProperty.getPassword()).isNull();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN
        String host = "localhost";
        // WHEN
        mongoProperty.setHost(host);
        // THEN
        assertThat(mongoProperty.getHost()).isEqualTo(host);
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN
        int port = 27017;
        // WHEN
        mongoProperty.setPort(port);
        // THEN
        assertThat(mongoProperty.getPort()).isEqualTo(port);
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN
        String authDb = "admin";
        // WHEN
        mongoProperty.setAuthDatabase(authDb);
        // THEN
        assertThat(mongoProperty.getAuthDatabase()).isEqualTo(authDb);
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN
        String platformDb = "platform";
        // WHEN
        mongoProperty.setPlatformDatabase(platformDb);
        // THEN
        assertThat(mongoProperty.getPlatformDatabase()).isEqualTo(platformDb);
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN
        String user = "user";
        // WHEN
        mongoProperty.setUser(user);
        // THEN
        assertThat(mongoProperty.getUser()).isEqualTo(user);
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN
        String password = "password";
        // WHEN
        mongoProperty.setPassword(password);
        // THEN
        assertThat(mongoProperty.getPassword()).isEqualTo(password);
    }

    @Test
    void testSetNullValues() {
        // GIVEN
        // WHEN
        mongoProperty.setHost(null);
        mongoProperty.setAuthDatabase(null);
        mongoProperty.setPlatformDatabase(null);
        mongoProperty.setUser(null);
        mongoProperty.setPassword(null);
        // THEN
        assertThat(mongoProperty.getHost()).isNull();
        assertThat(mongoProperty.getAuthDatabase()).isNull();
        assertThat(mongoProperty.getPlatformDatabase()).isNull();
        assertThat(mongoProperty.getUser()).isNull();
        assertThat(mongoProperty.getPassword()).isNull();
    }

    @Test
    void testMultipleSetters() {
        // GIVEN
        String host = "127.0.0.1";
        int port = 12345;
        String user = "admin";
        String password = "secret";
        // WHEN
        mongoProperty.setHost(host);
        mongoProperty.setPort(port);
        mongoProperty.setUser(user);
        mongoProperty.setPassword(password);
        // THEN
        assertThat(mongoProperty.getHost()).isEqualTo(host);
        assertThat(mongoProperty.getPort()).isEqualTo(port);
        assertThat(mongoProperty.getUser()).isEqualTo(user);
        assertThat(mongoProperty.getPassword()).isEqualTo(password);
    }
}
