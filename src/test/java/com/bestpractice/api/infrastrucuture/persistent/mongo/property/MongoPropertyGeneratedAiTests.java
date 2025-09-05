package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@org.junit.jupiter.api.ExtensionRegistry.registerExtension
class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
        mongoProperty.setHost("localhost");
        mongoProperty.setPort(27017);
        mongoProperty.setAuthDatabase("auth");
        mongoProperty.setPlatformDatabase("platform");
        mongoProperty.setUser("user");
        mongoProperty.setPassword("password");
    }

    @Test
    void getHost() {
        // GIVEN: The MongoProperty object is initialized with default values.
        // WHEN: The getHost() method is called.
        // THEN: The host property returns "localhost".
        assertEquals("localhost", mongoProperty.getHost());
    }

    @Test
    void getPort() {
        // GIVEN: The MongoProperty object is initialized with default values.
        // WHEN: The getPort() method is called.
        // THEN: The port property returns 27017.
        assertEquals(27017, mongoProperty.getPort());
    }

    @Test
    void getAuthDatabase() {
        // GIVEN: The MongoProperty object is initialized with default values.
        // WHEN: The getAuthDatabase() method is called.
        // THEN: The authDatabase property returns "auth".
        assertEquals("auth", mongoProperty.getAuthDatabase());
    }

    @Test
    void getPlatformDatabase() {
        // GIVEN: The MongoProperty object is initialized with default values.
        // WHEN: The getPlatformDatabase() method is called.
        // THEN: The platformDatabase property returns "platform".
        assertEquals("platform", mongoProperty.getPlatformDatabase());
    }

    @Test
    void getUser() {
        // GIVEN: The MongoProperty object is initialized with default values.
        // WHEN: The getUser() method is called.
        // THEN: The user property returns "user".
        assertEquals("user", mongoProperty.getUser());
    }

    @Test
    void getPassword() {
        // GIVEN: The MongoProperty object is initialized with default values.
        // WHEN: The getPassword() method is called.
        // THEN: The password property returns "password".
        assertEquals("password", mongoProperty.getPassword());
    }
}
