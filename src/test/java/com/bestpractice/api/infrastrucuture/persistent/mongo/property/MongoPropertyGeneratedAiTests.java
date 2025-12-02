package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MongoPropertyGeneratedAiTests {

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
        String expectedPassword = "securePassword";

        // WHEN
        mongoProperty.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }

    @Test
    void givenNullHostValue_whenSetHost_thenGetHostReturnsNull() {
        // GIVEN
        String nullHost = null;

        // WHEN
        mongoProperty.setHost(nullHost);

        // THEN
        assertEquals(nullHost, mongoProperty.getHost());
    }

    @Test
    void givenNegativePortValue_whenSetPort_thenGetPortReturnsNegativeValue() {
        // GIVEN
        int negativePort = -1;

        // WHEN
        mongoProperty.setPort(negativePort);

        // THEN
        assertEquals(negativePort, mongoProperty.getPort());
    }

    @Test
    void givenEmptyAuthDatabaseValue_whenSetAuthDatabase_thenGetAuthDatabaseReturnsEmptyValue() {
        // GIVEN
        String emptyAuthDatabase = "";

        // WHEN
        mongoProperty.setAuthDatabase(emptyAuthDatabase);

        // THEN
        assertEquals(emptyAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void givenEmptyPlatformDatabaseValue_whenSetPlatformDatabase_thenGetPlatformDatabaseReturnsEmptyValue() {
        // GIVEN
        String emptyPlatformDatabase = "";

        // WHEN
        mongoProperty.setPlatformDatabase(emptyPlatformDatabase);

        // THEN
        assertEquals(emptyPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void givenEmptyUserValue_whenSetUser_thenGetUserReturnsEmptyValue() {
        // GIVEN
        String emptyUser = "";

        // WHEN
        mongoProperty.setUser(emptyUser);

        // THEN
        assertEquals(emptyUser, mongoProperty.getUser());
    }

    @Test
    void givenEmptyPasswordValue_whenSetPassword_thenGetPasswordReturnsEmptyValue() {
        // GIVEN
        String emptyPassword = "";

        // WHEN
        mongoProperty.setPassword(emptyPassword);

        // THEN
        assertEquals(emptyPassword, mongoProperty.getPassword());
    }
}
