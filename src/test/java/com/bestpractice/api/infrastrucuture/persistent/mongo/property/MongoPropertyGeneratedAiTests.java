package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.extension.junit.jupiter.api.ExtensionRegistry.createRegistry;

@ExtendWith(MyExtension.class)
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
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getHost() method is called.
        // THEN: The host property returns "localhost".
        assertEquals("localhost", mongoProperty.getHost());
    }

    @Test
    void setHost() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The setHost() method is called with a new host value.
        // THEN: The host property is updated to the new value.
        mongoProperty.setHost("new_host");
        assertEquals("new_host", mongoProperty.getHost());
    }

    @Test
    void getPort() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getPort() method is called.
        // THEN: The port property returns 27017.
        assertEquals(27017, mongoProperty.getPort());
    }

    @Test
    void setPort() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The setPort() method is called with a new port value.
        // THEN: The port property is updated to the new value.
        mongoProperty.setPort(3306);
        assertEquals(3306, mongoProperty.getPort());
    }

    @Test
    void getAuthDatabase() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getAuthDatabase() method is called.
        // THEN: The authDatabase property returns "auth".
        assertEquals("auth", mongoProperty.getAuthDatabase());
    }

    @Test
    void setAuthDatabase() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The setAuthDatabase() method is called with a new authDatabase value.
        // THEN: The authDatabase property is updated to the new value.
        mongoProperty.setAuthDatabase("new_auth");
        assertEquals("new_auth", mongoProperty.getAuthDatabase());
    }

    @Test
    void getPlatformDatabase() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getPlatformDatabase() method is called.
        // THEN: The platformDatabase property returns "platform".
        assertEquals("platform", mongoProperty.getPlatformDatabase());
    }

    @Test
    void setPlatformDatabase() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The setPlatformDatabase() method is called with a new platformDatabase value.
        // THEN: The platformDatabase property is updated to the new value.
        mongoProperty.setPlatformDatabase("new_platform");
        assertEquals("new_platform", mongoProperty.getPlatformDatabase());
    }

    @Test
    void getUser() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getUser() method is called.
        // THEN: The user property returns "user".
        assertEquals("user", mongoProperty.getUser());
    }

    @Test
    void setUser() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The setUser() method is called with a new user value.
        // THEN: The user property is updated to the new value.
        mongoProperty.setUser("new_user");
        assertEquals("new_user", mongoProperty.getUser());
    }

    @Test
    void getPassword() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The getPassword() method is called.
        // THEN: The password property returns "password".
        assertEquals("password", mongoProperty.getPassword());
    }

    @Test
    void setPassword() {
        // GIVEN: A MongoProperty instance is created.
        // WHEN: The setPassword() method is called with a new password value.
        // THEN: The password property is updatedjava
        // THEN: The password property is updated to the new value.
        mongoProperty.setPassword("new_password");
        assertEquals("new_password", mongoProperty.getPassword());
    }
}

class MyExtension implements org.junit.jupiter.api.extension.ExtensionContext.TestReference{

}
