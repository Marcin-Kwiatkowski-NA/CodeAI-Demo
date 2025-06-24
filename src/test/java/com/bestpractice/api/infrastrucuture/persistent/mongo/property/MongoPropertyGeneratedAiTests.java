package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.extension.junit.jupiter.api.ExtensionRegistry.createRegistry;

class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void getHost_returnsHostValue() {
        // GIVEN: A new MongoProperty instance is created.
        // WHEN: The getHost() method is called.
        // THEN: The host property returns the assigned value.
        mongoProperty.setHost("localhost");
        assertEquals("localhost", mongoProperty.getHost());
    }

    @Test
    void getPort_returnsPortValue() {
        // GIVEN: A new MongoProperty instance is created.
        // WHEN: The getPort() method is called.
        // THEN: The port property returns the assigned value.
        mongoProperty.setPort(27017);
        assertEquals(27017, mongoProperty.getPort());
    }

    @Test
    void getAuthDatabase_returnsAuthDatabaseValue() {
        // GIVEN: A new MongoProperty instance is created.
        // WHEN: The getAuthDatabase() method is called.
        // THEN: The authDatabase property returns the assigned value.
        mongoProperty.setAuthDatabase("auth_db");
        assertEquals("auth_db", mongoProperty.getAuthDatabase());
    }

    @Test
    void getPlatformDatabase_returnsPlatformDatabaseValue() {
        // GIVEN: A new MongoProperty instance is created.
        // WHEN: The getPlatformDatabase() method is called.
        // THEN: The platformDatabase property returns the assigned value.
        mongoProperty.setPlatformDatabase("platform_db");
        assertEquals("platform_db", mongoProperty.getPlatformDatabase());
    }

    @Test
    void getUser_returnsUserValue() {
        // GIVEN: A new MongoProperty instance is created.
        // WHEN: The getUser() method is called.
        // THEN: The user property returns the assigned value.
        mongoProperty.setUser("admin");
        assertEquals("admin", mongoProperty.getUser());
    }

    @Test
    void getPassword_returnsPasswordValue() {
        // GIVEN: A new MongoProperty instance is created.
        // WHEN: The getPassword() method is called.
        // THEN: The password property returns the assigned value.
        mongoProperty.setPassword("secret");
        assertEquals("secret", mongoProperty.getPassword());
    }
}
