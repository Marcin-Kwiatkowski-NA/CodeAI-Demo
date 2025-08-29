package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@MockitoExtension
public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testGetAndSetHost() {
        // GIVEN: A new MongoProperty instance
        // WHEN: The host property is set to "localhost"
        mongoProperty.setHost("localhost");
        // THEN: The host property should be "localhost"
        assertEquals("localhost", mongoProperty.getHost());
    }

    @Test
    void testGetAndSetPort() {
        // GIVEN: A new MongoProperty instance
        // WHEN: The port property is set to 12345
        mongoProperty.setPort(12345);
        // THEN: The port property should be 12345
        assertEquals(12345, mongoProperty.getPort());
    }

    @Test
    void testGetAndSetAuthDatabase() {
        // GIVEN: A new MongoProperty instance
        // WHEN: The authDatabase property is set to "admin"
        mongoProperty.setAuthDatabase("admin");
        // THEN: The authDatabase property should be "admin"
        assertEquals("admin", mongoProperty.getAuthDatabase());
    }

    @Test
    void testGetAndSetPlatformDatabase() {
        // GIVEN: A new MongoProperty instance
        // WHEN: The platformDatabase property is set to "production"
        mongoProperty.setPlatformDatabase("production");
        // THEN: The platformDatabase property should be "production"
        assertEquals("production", mongoProperty.getPlatformDatabase());
    }

    @Test
    void testGetAndSetUser() {
        // GIVEN: A new MongoProperty instance
        // WHEN: The user property is set to "myuser"
        mongoProperty.setUser("myuser");
        // THEN: The user property should be "myuser"
        assertEquals("myuser", mongoProperty.getUser());
    }

    @Test
    void testGetAndSetPassword() {
        // GIVEN: A new MongoProperty instance
        // WHEN: The password property is set to "mypassword"
        mongoProperty.setPassword("mypassword");
        // THEN: The password property should be "mypassword"
        assertEquals("mypassword", mongoProperty.getPassword());
    }
}
