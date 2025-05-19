package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DisplayName.*;

class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @Test
    void testGetHost() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getHost() method is called.
        // THEN: The host property returns 'localhost'.
        assertEquals("localhost", mongoProperty.getHost());
    }

    @Test
    void testGetPort() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getPort() method is called.
        // THEN: The port property returns 27017.
        assertEquals(27017, mongoProperty.getPort());
    }

    @Test
    void testGetAuthDatabase() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getAuthDatabase() method is called.
        // THEN: The authDatabase property returns 'admin'.
        assertEquals("admin", mongoProperty.getAuthDatabase());
    }

    @Test
    void testGetPlatformDatabase() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getPlatformDatabase() method is called.
        // THEN: The platformDatabase property returns 'test'.
        assertEquals("test", mongoProperty.getPlatformDatabase());
    }

    @Test
    void testGetUser() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getUser() method is called.
        // THEN: The user property returns 'testuser'.
        assertEquals("testuser", mongoProperty.getUser());
    }

    @Test
    void testGetPassword() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getPassword() method is called.
        // THEN: The password property returns 'testpassword'.
        assertEquals("testpassword", mongoProperty.getPassword());
    }
}
