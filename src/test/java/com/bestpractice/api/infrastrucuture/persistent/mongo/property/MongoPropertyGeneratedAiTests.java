package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import static org.assertj.core.api.Assertions.assertThat;
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
        String expectedPlatformDatabase = "platform";

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
        String expectedPassword = "mongoPassword";

        // WHEN
        mongoProperty.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }

    @Test
    void givenNullHost_whenSetHost_thenGetHostReturnsNull() {
        // GIVEN
        String nullHost = null;

        // WHEN
        mongoProperty.setHost(nullHost);

        // THEN
        assertEquals(nullHost, mongoProperty.getHost());
    }

    @Test
    void givenZeroPort_whenSetPort_thenGetPortReturnsZero() {
        // GIVEN
        int zeroPort = 0;

        // WHEN
        mongoProperty.setPort(zeroPort);

        // THEN
        assertEquals(zeroPort, mongoProperty.getPort());
    }

    @Test
    void givenEmptyAuthDatabase_whenSetAuthDatabase_thenGetAuthDatabaseReturnsEmpty() {
        // GIVEN
        String emptyAuthDatabase = "";

        // WHEN
        mongoProperty.setAuthDatabase(emptyAuthDatabase);

        // THEN
        assertEquals(emptyAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void givenEmptyPlatformDatabase_whenSetPlatformDatabase_thenGetPlatformDatabaseReturnsEmpty() {
        // GIVEN
        String emptyPlatformDatabase = "";

        // WHEN
        mongoProperty.setPlatformDatabase(emptyPlatformDatabase);

        // THEN
        assertEquals(emptyPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void givenEmptyUser_whenSetUser_thenGetUserReturnsEmpty() {
        // GIVEN
        String emptyUser = "";

        // WHEN
        mongoProperty.setUser(emptyUser);

        // THEN
        assertEquals(emptyUser, mongoProperty.getUser());
    }

    @Test
    void givenEmptyPassword_whenSetPassword_thenGetPasswordReturnsEmpty() {
        // GIVEN
        String emptyPassword = "";

        // WHEN
        mongoProperty.setPassword(emptyPassword);

        // THEN
        assertEquals(emptyPassword, mongoProperty.getPassword());
    }
}
