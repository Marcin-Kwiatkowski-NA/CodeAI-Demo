package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

String authDatabase = mongoProperty.getAuthDatabase();
        assertNotNull(authDatabase);
    }

    @Test
    void setAuthDatabase() {
        // GIVEN: A new MongoProperty instance is created.
        // WHEN: The setAuthDatabase() method is called with a value.
        // THEN: The authDatabase property is set to the given value.
        mongoProperty.setAuthDatabase("admin");
        assertEquals("admin", mongoProperty.getAuthDatabase());
    }

    @Test
    void getPlatformDatabase() {
        // GIVEN: A new MongoProperty instance is created.
        // WHEN: The getPlatformDatabase() method is called.
        // THEN: The platformDatabase property is returned.
        String platformDatabase = mongoProperty.getPlatformDatabase();
        assertNotNull(platformDatabase);
    }

    @Test
    void setPlatformDatabase() {
        // GIVEN: A new MongoProperty instance is created.
        // WHEN: The setPlatformDatabase() method is called with a value.
        // THEN: The platformDatabase property is set to the given value.
        mongoProperty.setPlatformDatabase("test");
        assertEquals("test", mongoProperty.getPlatformDatabase());
    }

    @Test
    void getUser() {
        // GIVEN: A new MongoProperty instance is created.
        // WHEN: The getUser() method is called.
        // THEN: The user property is returned.
        String user = mongoProperty.getUser();
        assertNotNull(user);
    }

    @Test
    void setUser() {
        // GIVEN: A new MongoProperty instance is created.
        // WHEN: The setUser() method is called with a value.
        // THEN: The user property is set to the given value.
        mongoProperty.setUser("root");
        assertEquals("root", mongoProperty.getUser());
    }

    @Test
    void getPassword() {
        // GIVEN: A new MongoProperty instance is created.
        // WHEN: The getPassword() method is called.
        // THEN: The password property is returned.
        String password = mongoProperty.getPassword();
        assertNotNull(password);
    }

    @Test
    void setPassword() {
        // GIVEN: A new MongoProperty instance is created.
        // WHEN: The setPassword() method is called with a value.
        // THEN: The password property is set to the given value.
        mongoProperty.setPassword("password");
        assertEquals("password", mongoProperty.getPassword());
    }
}
