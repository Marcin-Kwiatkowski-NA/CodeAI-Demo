package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void testDefaultValues() {
        // GIVEN a newly instantiated MongoProperty

        // WHEN getters are called without setting any values

        // THEN all string properties should be null and port should be 0
        assertThat(mongoProperty.getHost()).isNull();
        assertThat(mongoProperty.getAuthDatabase()).isNull();
        assertThat(mongoProperty.getPlatformDatabase()).isNull();
        assertThat(mongoProperty.getUser()).isNull();
        assertThat(mongoProperty.getPassword()).isNull();
        assertThat(mongoProperty.getPort()).isEqualTo(0);
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN a MongoProperty with a host value
        String host = "localhost";
        mongoProperty.setHost(host);

        // WHEN getHost is called

        // THEN the returned host should match the set value
        assertThat(mongoProperty.getHost()).isEqualTo(host);
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN a MongoProperty with a port value
        int port = 27017;
        mongoProperty.setPort(port);

        // WHEN getPort is called

        // THEN the returned port should match the set value
        assertThat(mongoProperty.getPort()).isEqualTo(port);
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN a MongoProperty with an authDatabase value
        String authDb = "admin";
        mongoProperty.setAuthDatabase(authDb);

        // WHEN getAuthDatabase is called

        // THEN the returned value should match the set value
        assertThat(mongoProperty.getAuthDatabase()).isEqualTo(authDb);
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN a MongoProperty with a platformDatabase value
        String platformDb = "platform";
        mongoProperty.setPlatformDatabase(platformDb);

        // WHEN getPlatformDatabase is called

        // THEN the returned value should match the set value
        assertThat(mongoProperty.getPlatformDatabase()).isEqualTo(platformDb);
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN a MongoProperty with a user value
        String user = "testUser";
        mongoProperty.setUser(user);

        // WHEN getUser is called

        // THEN the returned value should match the set value
        assertThat(mongoProperty.getUser()).isEqualTo(user);
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN a MongoProperty with a password value
        String password = "secret";
        mongoProperty.setPassword(password);

        // WHEN getPassword is called

        // THEN the returned value should match the set value
        assertThat(mongoProperty.getPassword()).isEqualTo(password);
    }

    @Test
    void testAllPropertiesSet() {
        // GIVEN a MongoProperty with all properties set
        mongoProperty.setHost("127.0.0.1");
        mongoProperty.setPort(28017);
        mongoProperty.setAuthDatabase("authDb");
        mongoProperty.setPlatformDatabase("platformDb");
        mongoProperty.setUser("user");
        mongoProperty.setPassword("pass");

        // WHEN getters are called

        // THEN all returned values should match the set values
        assertThat(mongoProperty.getHost()).isEqualTo("127.0.0.1");
        assertThat(mongoProperty.getPort()).isEqualTo(28017);
        assertThat(mongoProperty.getAuthDatabase()).isEqualTo("authDb");
        assertThat(mongoProperty.getPlatformDatabase()).isEqualTo("platformDb");
        assertThat(mongoProperty.getUser()).isEqualTo("user");
        assertThat(mongoProperty.getPassword()).isEqualTo("pass");
    }

    @Test
    void testResetBeforeEach() {
        // GIVEN a MongoProperty that has been modified in a previous test
        mongoProperty.setHost("modifiedHost");

        // WHEN a new test runs and setUp() is called

        // THEN the host should be reset to null
        assertThat(mongoProperty.getHost()).isNull();
    }
}
