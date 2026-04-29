package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN
        String expectedHost = "localhost";

        // WHEN
        mongoProperty.setHost(expectedHost);
        String actualHost = mongoProperty.getHost();

        // THEN
        assertEquals(expectedHost, actualHost);
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN
        int expectedPort = 27017;

        // WHEN
        mongoProperty.setPort(expectedPort);
        int actualPort = mongoProperty.getPort();

        // THEN
        assertEquals(expectedPort, actualPort);
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN
        String expectedAuthDatabase = "admin";

        // WHEN
        mongoProperty.setAuthDatabase(expectedAuthDatabase);
        String actualAuthDatabase = mongoProperty.getAuthDatabase();

        // THEN
        assertEquals(expectedAuthDatabase, actualAuthDatabase);
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN
        String expectedPlatformDatabase = "platformDB";

        // WHEN
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);
        String actualPlatformDatabase = mongoProperty.getPlatformDatabase();

        // THEN
        assertEquals(expectedPlatformDatabase, actualPlatformDatabase);
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN
        String expectedUser = "testUser";

        // WHEN
        mongoProperty.setUser(expectedUser);
        String actualUser = mongoProperty.getUser();

        // THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "securePassword"; // Security-sensitive field

        // WHEN
        mongoProperty.setPassword(expectedPassword);
        String actualPassword = mongoProperty.getPassword();

        // THEN
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testSetNullValues() {
        // GIVEN
        String expectedHost = null;
        String expectedAuthDatabase = null;
        String expectedPlatformDatabase = null;
        String expectedUser = null;
        String expectedPassword = null;

        // WHEN
        mongoProperty.setHost(expectedHost);
        mongoProperty.setAuthDatabase(expectedAuthDatabase);
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);
        mongoProperty.setUser(expectedUser);
        mongoProperty.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedHost, mongoProperty.getHost());
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
        assertEquals(expectedUser, mongoProperty.getUser());
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }

    @Test
    void testSetNegativePortValue() {
        // GIVEN
        int invalidPort = -1;

        // WHEN & THEN
        mongoProperty.setPort(invalidPort);
        assertEquals(invalidPort, mongoProperty.getPort());
    }

    @Test
    void testDefaultValuesBeforeSettersCalled() {
        // GIVEN
        MongoProperty newMongoProperty = new MongoProperty();

        // WHEN
        String host = newMongoProperty.getHost();
        int port = newMongoProperty.getPort();
        String authDatabase = newMongoProperty.getAuthDatabase();
        String platformDatabase = newMongoProperty.getPlatformDatabase();
        String user = newMongoProperty.getUser();
        String password = newMongoProperty.getPassword();

        // THEN
        assertEquals(null, host);
        assertEquals(0, port);
        assertEquals(null, authDatabase);
        assertEquals(null, platformDatabase);
        assertEquals(null, user);
        assertEquals(null, password);
    }
}
