package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
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
    void testGetAndSetHost() {
        // GIVEN: a host value
        String expectedHost = "localhost";

        // WHEN: setting the host
        mongoProperty.setHost(expectedHost);

        // THEN: the host should be retrieved correctly
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testGetAndSetPort() {
        // GIVEN: a port value
        int expectedPort = 27017;

        // WHEN: setting the port
        mongoProperty.setPort(expectedPort);

        // THEN: the port should be retrieved correctly
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testGetAndSetAuthDatabase() {
        // GIVEN: an auth database value
        String expectedAuthDatabase = "admin";

        // WHEN: setting the auth database
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN: the auth database should be retrieved correctly
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testGetAndSetPlatformDatabase() {
        // GIVEN: a platform database value
        String expectedPlatformDatabase = "platformDB";

        // WHEN: setting the platform database
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN: the platform database should be retrieved correctly
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testGetAndSetUser() {
        // GIVEN: a user value
        String expectedUser = "testUser";

        // WHEN: setting the user
        mongoProperty.setUser(expectedUser);

        // THEN: the user should be retrieved correctly
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testGetAndSetPassword() {
        // GIVEN: a password value (security-sensitive)
        String expectedPassword = "securePassword";

        // WHEN: setting the password
        mongoProperty.setPassword(expectedPassword);

        // THEN: the password should be retrieved correctly
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }
}
