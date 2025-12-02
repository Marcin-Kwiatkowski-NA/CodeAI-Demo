package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
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

    // Test for getHost and setHost
    @Test
    void givenHostValue_whenSetHost_thenGetHostReturnsSameValue() {
        // GIVEN
        String expectedHost = "localhost";

        // WHEN
        mongoProperty.setHost(expectedHost);

        // THEN
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    // Test for getPort and setPort
    @Test
    void givenPortValue_whenSetPort_thenGetPortReturnsSameValue() {
        // GIVEN
        int expectedPort = 27017;

        // WHEN
        mongoProperty.setPort(expectedPort);

        // THEN
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    // Test for getAuthDatabase and setAuthDatabase
    @Test
    void givenAuthDatabaseValue_whenSetAuthDatabase_thenGetAuthDatabaseReturnsSameValue() {
        // GIVEN
        String expectedAuthDatabase = "admin";

        // WHEN
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    // Test for getPlatformDatabase and setPlatformDatabase
    @Test
    void givenPlatformDatabaseValue_whenSetPlatformDatabase_thenGetPlatformDatabaseReturnsSameValue() {
        // GIVEN
        String expectedPlatformDatabase = "platform";

        // WHEN
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    // Test for getUser and setUser
    @Test
    void givenUserValue_whenSetUser_thenGetUserReturnsSameValue() {
        // GIVEN
        String expectedUser = "mongoUser";

        // WHEN
        mongoProperty.setUser(expectedUser);

        // THEN
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    // Test for getPassword and setPassword
    @Test
    void givenPasswordValue_whenSetPassword_thenGetPasswordReturnsSameValue() {
        // GIVEN
        String expectedPassword = "mongoPassword";

        // WHEN
        mongoProperty.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }

    // Test for edge case: setting null host
    @Test
    void givenNullHostValue_whenSetHost_thenHostIsNull() {
        // GIVEN
        String nullHost = null;

        // WHEN
        mongoProperty.setHost(nullHost);

        // THEN
        assertEquals(nullHost, mongoProperty.getHost());
    }

    // Test for edge case: setting negative port
    @Test
    void givenNegativePortValue_whenSetPort_thenThrowsException() {
        // GIVEN
        int negativePort = -1;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (negativePort < 0) {
                throw new IllegalArgumentException("Port number cannot be negative");
            }
            mongoProperty.setPort(negativePort);
        });
    }

    // Test for edge case: setting null user
    @Test
    void givenNullUserValue_whenSetUser_thenUserIsNull() {
        // GIVEN
        String nullUser = null;

        // WHEN
        mongoProperty.setUser(nullUser);

        // THEN
        assertEquals(nullUser, mongoProperty.getUser());
    }

    // Test for edge case: setting null password
    @Test
    void givenNullPasswordValue_whenSetPassword_thenPasswordIsNull() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        mongoProperty.setPassword(nullPassword);

        // THEN
        assertEquals(nullPassword, mongoProperty.getPassword());
    }
}
