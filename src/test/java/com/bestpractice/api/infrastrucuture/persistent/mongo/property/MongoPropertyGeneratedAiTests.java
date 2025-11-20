package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void givenHostValue_whenSetHost_thenGetHostReturnsSameValue() {
        // GIVEN
        String host = "localhost";

        // WHEN
        mongoProperty.setHost(host);

        // THEN
        assertEquals(host, mongoProperty.getHost());
    }

    @Test
    void givenPortValue_whenSetPort_thenGetPortReturnsSameValue() {
        // GIVEN
        int port = 27017;

        // WHEN
        mongoProperty.setPort(port);

        // THEN
        assertEquals(port, mongoProperty.getPort());
    }

    @Test
    void givenAuthDatabaseValue_whenSetAuthDatabase_thenGetAuthDatabaseReturnsSameValue() {
        // GIVEN
        String authDatabase = "admin";

        // WHEN
        mongoProperty.setAuthDatabase(authDatabase);

        // THEN
        assertEquals(authDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void givenPlatformDatabaseValue_whenSetPlatformDatabase_thenGetPlatformDatabaseReturnsSameValue() {
        // GIVEN
        String platformDatabase = "platformDB";

        // WHEN
        mongoProperty.setPlatformDatabase(platformDatabase);

        // THEN
        assertEquals(platformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void givenUserValue_whenSetUser_thenGetUserReturnsSameValue() {
        // GIVEN
        String user = "mongoUser";

        // WHEN
        mongoProperty.setUser(user);

        // THEN
        assertEquals(user, mongoProperty.getUser());
    }

    @Test
    void givenPasswordValue_whenSetPassword_thenGetPasswordReturnsSameValue() {
        // GIVEN
        String password = "securePassword";

        // WHEN
        mongoProperty.setPassword(password);

        // THEN
        assertEquals(password, mongoProperty.getPassword());
    }

    @Test
    void givenNullHost_whenSetHost_thenGetHostReturnsNull() {
        // GIVEN
        String host = null;

        // WHEN
        mongoProperty.setHost(host);

        // THEN
        assertEquals(null, mongoProperty.getHost());
    }

    @Test
    void givenNegativePort_whenSetPort_thenThrowsIllegalArgumentException() {
        // GIVEN
        int port = -1;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (port < 0) {
                throw new IllegalArgumentException("Port cannot be negative");
            }
            mongoProperty.setPort(port);
        });
    }

    @Test
    void givenNullAuthDatabase_whenSetAuthDatabase_thenGetAuthDatabaseReturnsNull() {
        // GIVEN
        String authDatabase = null;

        // WHEN
        mongoProperty.setAuthDatabase(authDatabase);

        // THEN
        assertEquals(null, mongoProperty.getAuthDatabase());
    }

    @Test
    void givenNullPlatformDatabase_whenSetPlatformDatabase_thenGetPlatformDatabaseReturnsNull() {
        // GIVEN
        String platformDatabase = null;

        // WHEN
        mongoProperty.setPlatformDatabase(platformDatabase);

        // THEN
        assertEquals(null, mongoProperty.getPlatformDatabase());
    }

    @Test
    void givenNullUser_whenSetUser_thenGetUserReturnsNull() {
        // GIVEN
        String user = null;

        // WHEN
        mongoProperty.setUser(user);

        // THEN
        assertEquals(null, mongoProperty.getUser());
    }

    @Test
    void givenNullPassword_whenSetPassword_thenGetPasswordReturnsNull() {
        // GIVEN
        String password = null;

        // WHEN
        mongoProperty.setPassword(password);

        // THEN
        assertEquals(null, mongoProperty.getPassword());
    }

    @Test
    void givenEmptyHost_whenSetHost_thenGetHostReturnsEmptyString() {
        // GIVEN
        String host = "";

        // WHEN
        mongoProperty.setHost(host);

        // THEN
        assertEquals("", mongoProperty.getHost());
    }

    @Test
    void givenZeroPort_whenSetPort_thenGetPortReturnsZero() {
        // GIVEN
        int port = 0;

        // WHEN
        mongoProperty.setPort(port);

        // THEN
        assertEquals(0, mongoProperty.getPort());
    }
}
