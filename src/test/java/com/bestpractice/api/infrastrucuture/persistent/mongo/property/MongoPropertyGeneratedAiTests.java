package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.DisplayNameGenerator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DisplayName.*;

@ExtendWith(DisplayNameGenerator.class)
class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    @DisplayName("Test getHost method")
    void testGetHostMethod() {
        // GIVEN: Initialize MongoProperty instance
        // WHEN: Access the host property
        String host = mongoProperty.getHost();
        // THEN: Verify that the host property is returned
        assertNotNull(host);
    }

    @Test
    @DisplayName("Test getPort method")
    void testGetPortMethod() {
        // GIVEN: Initialize MongoProperty instance and set port value
        mongoProperty.setPort(27017);
        // WHEN: Access the port property
        int port = mongoProperty.getPort();
        // THEN: Verify that the port property is returned
        assertEquals(27017, port);
    }

    @Test
    @DisplayName("Test getAuthDatabase method")
    void testGetAuthDatabaseMethod() {
        // GIVEN: Initialize MongoProperty instance and set authDatabase value
        mongoProperty.setAuthDatabase("auth");
        // WHEN: Access the authDatabase property
        String authDatabase = mongoProperty.getAuthDatabase();
        // THEN: Verify that the authDatabase property is returned
        assertEquals("auth", authDatabase);
    }

    @Test
    @DisplayName("Test getPlatformDatabase method")
    void testGetPlatformDatabaseMethod() {
        // GIVEN: Initialize MongoProperty instance and set platformDatabase value
        mongoProperty.setPlatformDatabase("platform");
        // WHEN: Access the platformDatabase property
        String platformDatabase = mongoProperty.getPlatformDatabase();
        // THEN: Verify that the platformDatabase property is returned
        assertEquals("platform", platformDatabase);
    }

    @Test
    @DisplayName("Test getUser method")
    void testGetUserMethod() {
        // GIVEN: Initialize MongoProperty instance and set user value
        mongoProperty.setUser("user");
        // WHEN: Access the user property
        String user = mongoProperty.getUser();
        // THEN: Verify that the user property is returned
        assertEquals("user", user);
    }

    @Test
    @DisplayName("Test getPassword method")
    void testGetPasswordMethod() {
        // GIVEN: Initialize MongoProperty instance and set password value
        mongoProperty.setPassword("password");
        // WHEN: Access the password property
        String password = mongoProperty.getPassword();
        // THEN: Verify that the password property is returned
        assertEquals("password", password);
    }
}
