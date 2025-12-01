package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void givenHostValue_whenSetHost_thenGetHostReturnsSameValue() {
        // GIVEN
        String expectedHost = "localhost";

        // WHEN
        mongoProperty.setHost(expectedHost);

        // THEN
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void givenPortValue_whenSetPort_thenGetPortReturnsSameValue() {
        // GIVEN
        int expectedPort = 27017;

        // WHEN
        mongoProperty.setPort(expectedPort);

        // THEN
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void givenAuthDatabaseValue_whenSetAuthDatabase_thenGetAuthDatabaseReturnsSameValue() {
        // GIVEN
        String expectedAuthDatabase = "admin";

        // WHEN
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void givenPlatformDatabaseValue_whenSetPlatformDatabase_thenGetPlatformDatabaseReturnsSameValue() {
        // GIVEN
        String expectedPlatformDatabase = "platformDB";

        // WHEN
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void givenUserValue_whenSetUser_thenGetUserReturnsSameValue() {
        // GIVEN
        String expectedUser = "mongoUser";

        // WHEN
        mongoProperty.setUser(expectedUser);

        // THEN
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void givenPasswordValue_whenSetPassword_thenGetPasswordReturnsSameValue() {
        // GIVEN
        String expectedPassword = "mongoPass";

        // WHEN
        mongoProperty.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }

    @Test
    void givenNullHost_whenSetHost_thenGetHostReturnsNull() {
        // GIVEN
        String expectedHost = null;

        // WHEN
        mongoProperty.setHost(expectedHost);

        // THEN
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void givenNegativePort_whenSetPort_thenPortIsSetCorrectly() {
        // GIVEN
        int invalidPort = -1;

        // WHEN
        mongoProperty.setPort(invalidPort);

        // THEN
        assertEquals(invalidPort, mongoProperty.getPort());
    }

    @Test
    void givenNullAuthDatabase_whenSetAuthDatabase_thenGetAuthDatabaseReturnsNull() {
        // GIVEN
        String expectedAuthDatabase = null;

        // WHEN
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void givenNullPlatformDatabase_whenSetPlatformDatabase_thenGetPlatformDatabaseReturnsNull() {
        // GIVEN
        String expectedPlatformDatabase = null;

        // WHEN
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void givenNullUser_whenSetUser_thenGetUserReturnsNull() {
        // GIVEN
        String expectedUser = null;

        // WHEN
        mongoProperty.setUser(expectedUser);

        // THEN
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void givenNullPassword_whenSetPassword_thenGetPasswordReturnsNull() {
        // GIVEN
        String expectedPassword = null;

        // WHEN
        mongoProperty.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }
}
