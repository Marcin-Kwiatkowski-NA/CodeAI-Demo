package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
    void testSetAndGetHost() {
        // GIVEN
        String expectedHost = "localhost";

        // WHEN
        mongoProperty.setHost(expectedHost);

        // THEN
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN
        int expectedPort = 27017;

        // WHEN
        mongoProperty.setPort(expectedPort);

        // THEN
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN
        String expectedAuthDatabase = "admin";

        // WHEN
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN
        String expectedPlatformDatabase = "platformDB";

        // WHEN
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN
        String expectedUser = "testUser";

        // WHEN
        mongoProperty.setUser(expectedUser);

        // THEN
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "securePassword"; // Security-sensitive

        // WHEN
        mongoProperty.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }

    @Test
    void testSetHostWithNullValueStoresNull() {
        // GIVEN
        String nullHost = null;

        // WHEN
        mongoProperty.setHost(nullHost);

        // THEN
        assertEquals(nullHost, mongoProperty.getHost());
    }

    @Test
    void testSetAuthDatabaseWithNullValueStoresNull() {
        // GIVEN
        String nullAuthDatabase = null;

        // WHEN
        mongoProperty.setAuthDatabase(nullAuthDatabase);

        // THEN
        assertEquals(nullAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetPlatformDatabaseWithNullValueStoresNull() {
        // GIVEN
        String nullPlatformDatabase = null;

        // WHEN
        mongoProperty.setPlatformDatabase(nullPlatformDatabase);

        // THEN
        assertEquals(nullPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetUserWithNullValueStoresNull() {
        // GIVEN
        String nullUser = null;

        // WHEN
        mongoProperty.setUser(nullUser);

        // THEN
        assertEquals(nullUser, mongoProperty.getUser());
    }

    @Test
    void testSetPasswordWithNullValueStoresNull() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        mongoProperty.setPassword(nullPassword);

        // THEN
        assertEquals(nullPassword, mongoProperty.getPassword());
    }

    @Test
    void testSetPortWithNegativeValueStoresValueBecauseNoValidation() {
        // GIVEN
        int invalidPort = -1;

        // WHEN
        mongoProperty.setPort(invalidPort);

        // THEN
        assertEquals(invalidPort, mongoProperty.getPort());
    }
}
