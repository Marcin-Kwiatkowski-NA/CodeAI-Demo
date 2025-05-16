package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DisplayName.*;

@ExtendWith(MyExtension.class)
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
    @DisplayName("Test getHost()") {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getHost() method is called.
        // THEN: The host property is returned.
        String host = mongoProperty.getHost();
        assertEquals("localhost", host);
    }

    @Test
    @DisplayName("Test getPort()") {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getPort() method is called.
        // THEN: The port property is returned.
        int port = mongoProperty.getPort();
        assertEquals(27017, port);
    }

    @Test
    @DisplayName("Test getAuthDatabase()") {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getAuthDatabase() method is called.
        // THEN: The authDatabase property is returned.
        String authDatabase = mongoProperty.getAuthDatabase();
        assertEquals("authDB", authDatabase);
    }

    @Test
    @DisplayName("Test getPlatformDatabase()") {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getPlatformDatabase() method is called.
        // THEN: The platformDatabase property is returned.
        String platformDatabase = mongoProperty.getPlatformDatabase();
        assertEquals("platformDB", platformDatabase);
    }

    @Test
    @DisplayName("Test getUser()") {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getUser() method is called.
        // THEN: The user property is returned.
        String user = mongoProperty.getUser();
        assertEquals("admin", user);
    }

    @Test
    @DisplayName("Test getPassword()") {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getPassword() method is called.
        // THEN: The password property is returned.
        String password = mongoProperty.getPassword();
        assertEquals("password", password);
    }
}

class MyExtension implements ExtensionContext.Generated.Extension {
}