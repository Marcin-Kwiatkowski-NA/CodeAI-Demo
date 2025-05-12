package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.ExtensionTest;

import com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoProperty;

@ExtensionTest
public class MongoPropertyTest {

    @Testdotenv("MongoProperty.properties")
    public void testSetHost() {
        MongoProperty mongoProperty = new MongoProperty();
        mongoProperty.setHost("127.0.0.1");
        assertEquals("127.0.0.1", mongoProperty.getHost());
    }

    @Testdotenv("MongoProperty.properties")
    public void testSetPort() {
        MongoProperty mongoProperty = new MongoProperty();
        mongoProperty.setPort(8080);
        assertEquals(8080, mongoProperty.getPort());
    }

    @Testdotenv("MongoProperty.properties")
    public void testSetAuthDatabase() {
        MongoProperty mongoProperty = new MongoProperty();
        mongoProperty.setAuthDatabase("db1");
        assertEquals("db1", mongoProperty.getAuthDatabase());
    }

    @Testdotenv("MongoProperty.properties")
    public void testSetPlatformDatabase() {
        MongoProperty mongoProperty = new MongoProperty();
        mongoProperty.setPlatformDatabase("prod");
        assertEquals("prod", mongoProperty.getPlatformDatabase());
    }

    @Testdotenv("MongoProperty.properties")
    public void testSetUser() {
        MongoProperty mongoProperty = new MongoProperty();
        mongoProperty.setUser("testuser");
        assertEquals("testuser", mongoProperty.getUser());
    }

    @Testdotenv("MongoProperty.properties")
    public void testSetPassword() {
        MongoProperty mongoProperty = new MongoProperty();
        mongoProperty.setPassword("password123");
        assertEquals("password123", mongoProperty.getPassword());
    }

    @Testdotenv("MongoProperty.properties")
    public void testSetHost() {
        MongoProperty mongoProperty = new MongoProperty();
        assertEquals("testuser", mongoProperty.getHost());
    }

    @Testdotenv("MongoProperty.properties")
    public void testSetPort() {
        MongoProperty mongoProperty = new MongoProperty();
        assertEquals(8080, mongoProperty.getPort());
    }

    @Testdotenv("MongoProperty.properties")
    public void testSetAuthDatabase() {
        MongoProperty mongoProperty = new MongoProperty();
        assertEquals("db1", mongoProperty.getAuthDatabase());
    }

    @Testdotenv("MongoProperty.properties")
    public void testSetPlatformDatabase() {
        MongoProperty mongoProperty = new MongoProperty();
        assertEquals("prod", mongoProperty.getPlatformDatabase());
    }

    @Testdotenv("MongoProperty.properties")
    public void testSetUser() {
        MongoProperty mongoProperty = new MongoProperty();
        assertEquals("testuser", mongoProperty.getUser());
    }

    @Testdotenv("MongoProperty.properties")
    public void testSetPassword() {
        MongoProperty mongoProperty = new MongoProperty();
        assertEquals("password123", mongoProperty.getPassword());
    }

    @Testdotenv("MongoProperty.properties")
    public void testSetHost() {
        MongoProperty mongoProperty = new MongoProperty();
        assertEquals("testuser", mongoProperty.getHost());
    }

    @Testdotenv("MongoProperty.properties")
    public void testSetPort() {
        MongoProperty mongoProperty = new MongoProperty();
        assertEquals(8080, mongoProperty.getPort());
    }

    @Testdotenv("MongoProperty.properties")
    public void testSetAuthDatabase() {
        MongoProperty mongoProperty = new MongoProperty();
        assertEquals("db1", mongoProperty.getAuthDatabase());
    }

    @Testdotenv("MongoProperty.properties")
    public void testSetPlatformDatabase() {
        MongoProperty mongoProperty = new MongoProperty();
        assertEquals("prod", mongoProperty.getPlatformDatabase());
    }

    @Testdotenv("MongoProperty.properties")
    public void testSetUser() {
        MongoProperty mongoProperty = new MongoProperty();
        assertEquals("testuser", mongoProperty.getUser());
    }
}
