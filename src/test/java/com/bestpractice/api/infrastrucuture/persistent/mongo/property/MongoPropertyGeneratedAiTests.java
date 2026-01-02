package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testHostGetterSetter() {
        // GIVEN a host value
        String host = "localhost";
        // WHEN setting the host
        mongoProperty.setHost(host);
        // THEN the getter should return the same value
        assertThat(mongoProperty.getHost()).isEqualTo(host);
    }

    @Test
    void testPortGetterSetter() {
        // GIVEN a port value
        int port = 27017;
        // WHEN setting the port
        mongoProperty.setPort(port);
        // THEN the getter should return the same value
        assertThat(mongoProperty.getPort()).isEqualTo(port);
    }

    @Test
    void testAuthDatabaseGetterSetter() {
        // GIVEN an auth database value
        String authDb = "admin";
        // WHEN setting the auth database
        mongoProperty.setAuthDatabase(authDb);
        // THEN the getter should return the same value
        assertThat(mongoProperty.getAuthDatabase()).isEqualTo(authDb);
    }

    @Test
    void testPlatformDatabaseGetterSetter() {
        // GIVEN a platform database value
        String platformDb = "platform";
        // WHEN setting the platform database
        mongoProperty.setPlatformDatabase(platformDb);
        // THEN the getter should return the same value
        assertThat(mongoProperty.getPlatformDatabase()).isEqualTo(platformDb);
    }

    @Test
    void testUserGetterSetter() {
        // GIVEN a user value
        String user = "admin";
        // WHEN setting the user
        mongoProperty.setUser(user);
        // THEN the getter should return the same value
        assertThat(mongoProperty.getUser()).isEqualTo(user);
    }

    @Test
    void testPasswordGetterSetter() {
        // GIVEN a password value
        String password = "secret";
        // WHEN setting the password
        mongoProperty.setPassword(password);
        // THEN the getter should return the same value
        assertThat(mongoProperty.getPassword()).isEqualTo(password);
    }

    @Test
    void testAllPropertiesSetTogether() {
        // GIVEN all property values
        String host = "127.0.0.1";
        int port = 27018;
        String authDb = "authDb";
        String platformDb = "platformDb";
        String user = "user";
        String password = "pass";
        // WHEN setting all properties
        mongoProperty.setHost(host);
        mongoProperty.setPort(port);
        mongoProperty.setAuthDatabase(authDb);
        mongoProperty.setPlatformDatabase(platformDb);
        mongoProperty.setUser(user);
        mongoProperty.setPassword(password);
        // THEN each getter should return the corresponding value
        assertThat(mongoProperty.getHost()).isEqualTo(host);
        assertThat(mongoProperty.getPort()).isEqualTo(port);
        assertThat(mongoProperty.getAuthDatabase()).isEqualTo(authDb);
        assertThat(mongoProperty.getPlatformDatabase()).isEqualTo(platformDb);
        assertThat(mongoProperty.getUser()).isEqualTo(user);
        assertThat(mongoProperty.getPassword()).isEqualTo(password);
    }

    @Test
    void testNullValuesHandling() {
        // GIVEN null values for string properties
        // WHEN setting null values
        mongoProperty.setHost(null);
        mongoProperty.setAuthDatabase(null);
        mongoProperty.setPlatformDatabase(null);
        mongoProperty.setUser(null);
        mongoProperty.setPassword(null);
        // THEN getters should return null
        assertThat(mongoProperty.getHost()).isNull();
        assertThat(mongoProperty.getAuthDatabase()).isNull();
        assertThat(mongoProperty.getPlatformDatabase()).isNull();
        assertThat(mongoProperty.getUser()).isNull();
        assertThat(mongoProperty.getPassword()).isNull();
    }

    @Test
    void testNegativePortValue() {
        // GIVEN a negative port value
        int negativePort = -1;
        // WHEN setting the negative port
        mongoProperty.setPort(negativePort);
        // THEN the getter should return the same negative value
        assertThat(mongoProperty.getPort()).isEqualTo(negativePort);
    }

    @Test
    void testDefaultValues() {
        // GIVEN a newly instantiated object
        // WHEN no properties are set
        // THEN defaults should be null for strings and 0 for int
        assertThat(mongoProperty.getHost()).isNull();
        assertThat(mongoProperty.getPort()).isEqualTo(0);
        assertThat(mongoProperty.getAuthDatabase()).isNull();
        assertThat(mongoProperty.getPlatformDatabase()).isNull();
        assertThat(mongoProperty.getUser()).isNull();
        assertThat(mongoProperty.getPassword()).isNull();
    }

    @Test
    void testPortLargePositiveValue() {
        // GIVEN a large positive port value
        int largePort = 65535;
        // WHEN setting the port
        mongoProperty.setPort(largePort);
        // THEN the getter should return the same value
        assertThat(mongoProperty.getPort()).isEqualTo(largePort);
    }
}
