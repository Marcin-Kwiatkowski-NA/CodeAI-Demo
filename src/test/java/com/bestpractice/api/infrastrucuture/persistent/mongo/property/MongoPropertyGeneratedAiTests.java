package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
    void givenNullHostValue_whenSetHost_thenHostIsSetToNull() {
        // GIVEN
        String nullHost = null;

        // WHEN
        mongoProperty.setHost(nullHost);

        // THEN
        assertEquals(nullHost, mongoProperty.getHost());
    }

    @Test
    void givenNegativePortValue_whenSetPort_thenPortIsSetToNegativeValue() {
        // GIVEN
        int negativePort = -1;

        // WHEN
        mongoProperty.setPort(negativePort);

        // THEN
        assertEquals(negativePort, mongoProperty.getPort());
    }

    @Test
    void givenEmptyAuthDatabaseValue_whenSetAuthDatabase_thenAuthDatabaseIsSetToEmpty() {
        // GIVEN
        String emptyAuthDatabase = "";

        // WHEN
        mongoProperty.setAuthDatabase(emptyAuthDatabase);

        // THEN
        assertEquals(emptyAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void givenNullPasswordValue_whenSetPassword_thenPasswordIsSetToNull() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        mongoProperty.setPassword(nullPassword);

        // THEN
        assertEquals(nullPassword, mongoProperty.getPassword());
    }

    @Test
    void givenNullUserValue_whenSetUser_thenUserIsSetToNull() {
        // GIVEN
        String nullUser = null;

        // WHEN
        mongoProperty.setUser(nullUser);

        // THEN
        assertEquals(nullUser, mongoProperty.getUser());
    }
}
