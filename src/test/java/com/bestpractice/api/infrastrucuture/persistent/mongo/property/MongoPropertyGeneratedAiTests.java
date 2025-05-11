package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DisplayName.*;

class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
        mongoProperty.setHost("localhost");
        mongoProperty.setPort(27017);
        mongoProperty.setAuthDatabase("authDB");
        mongoProperty.setPlatformDatabase("platformDB");
        mongoProperty.setUser("admin");
        mongoProperty.setPassword("password");
    }

    @Test
    @DisplayName("Test getHost")
    void testGetHost() {
        // GIVEN: A MongoProperty instance is created with default values.
        // WHEN: The getHost() method is called.
        // THEN: The host property returns 'localhost'.
        assertEquals("localhost", mongoProperty.getHost());
    }

    @Test
    @DisplayName("Test getPort")
    void testGetPort() {
        // GIVEN: A MongoProperty instance is created with default values.
        // WHEN: The getPort() method is called.
        // THEN: The port property returns 27017.
        assertEquals(27017, mongoProperty.getPort());
    }

    @Test
    @DisplayName("Test getAuthDatabase")
    void testGetAuthDatabase() {
        // GIVEN: A MongoProperty instance is created with default values.
        // WHEN: The getAuthDatabase() method is called.
        // THEN: The authDatabase property returns 'authDB'.
        assertEquals("authDB", mongoProperty.getAuthDatabase());
    }

    @Test
    @DisplayName("Test getPlatformDatabase")
    void testGetPlatformDatabase() {
        // GIVEN: A MongoProperty instance is created with default values.
        // WHEN: The getPlatformDatabase() method is called.
        // THEN: The platformDatabase property returns 'platformDB'.
        assertEquals("platformDB", mongoProperty.getPlatformDatabase());
    }

    @Test
    @DisplayName("Test getUser")
    void testGetUser() {
        // GIVEN: A MongoProperty instance is created with default values.
        // WHEN: The getUser() method is called.
        // THEN: The user property returns 'admin'.
        assertEquals("admin", mongoProperty.getUser());
    }

    @Test
    @DisplayName("Test getPassword")
    void testGetPassword() {
        // GIVEN: A MongoProperty instance is created with default values.
        // WHEN: The getPassword() method is called.
        // THEN: The password property returns 'password'.
        assertEquals("password", mongoProperty.getPassword());
    }
}
