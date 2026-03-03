package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Security-sensitive: this test class exercises the password field of MongoProperty
public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    void defaultValuesAreNullOrZero() {
        // GIVEN a new MongoProperty instance
        // WHEN accessing its properties
        // THEN all String properties should be null and port should be 0
        assertThat(mongoProperty.getHost()).isNull();
        assertThat(mongoProperty.getAuthDatabase()).isNull();
        assertThat(mongoProperty.getPlatformDatabase()).isNull();
        assertThat(mongoProperty.getUser()).isNull();
        assertThat(mongoProperty.getPassword()).isNull();
        assertEquals(0, mongoProperty.getPort());
    }

    @Test
    void setAndGetHost() {
        // GIVEN a host value
        String host = "localhost";
        // WHEN setting the host
        mongoProperty.setHost(host);
        // THEN retrieving the host returns the same value
        assertThat(mongoProperty.getHost()).isEqualTo(host);
    }

    @Test
    void setAndGetPort() {
        // GIVEN a port value
        int port = 27017;
        // WHEN setting the port
        mongoProperty.setPort(port);
        // THEN retrieving the port returns the same value
        assertEquals(port, mongoProperty.getPort());
    }

    @Test
    void setAndGetAuthDatabase() {
        // GIVEN an authDatabase value
        String authDb = "admin";
        // WHEN setting the authDatabase
        mongoProperty.setAuthDatabase(authDb);
        // THEN retrieving the authDatabase returns the same value
        assertThat(mongoProperty.getAuthDatabase()).isEqualTo(authDb);
    }

    @Test
    void setAndGetPlatformDatabase() {
        // GIVEN a platformDatabase value
        String platformDb = "platform";
        // WHEN setting the platformDatabase
        mongoProperty.setPlatformDatabase(platformDb);
        // THEN retrieving the platformDatabase returns the same value
        assertThat(mongoProperty.getPlatformDatabase()).isEqualTo(platformDb);
    }

    @Test
    void setAndGetUser() {
        // GIVEN a user value
        String user = "adminUser";
        // WHEN setting the user
        mongoProperty.setUser(user);
        // THEN retrieving the user returns the same value
        assertThat(mongoProperty.getUser()).isEqualTo(user);
    }

    @Test
    void setAndGetPassword() {
        // GIVEN a password value
        String password = "secret";
        // WHEN setting the password
        mongoProperty.setPassword(password);
        // THEN retrieving the password returns the same value
        assertThat(mongoProperty.getPassword()).isEqualTo(password);
    }

    @Test
    void independentProperties() {
        // GIVEN values for all properties
        String host = "localhost";
        int port = 27017;
        String authDb = "admin";
        String platformDb = "platform";
        String user = "adminUser";
        String password = "secret";

        // WHEN setting all properties
        mongoProperty.setHost(host);
        mongoProperty.setPort(port);
        mongoProperty.setAuthDatabase(authDb);
        mongoProperty.setPlatformDatabase(platformDb);
        mongoProperty.setUser(user);
        mongoProperty.setPassword(password);

        // THEN each getter returns its corresponding value
        assertThat(mongoProperty.getHost()).isEqualTo(host);
        assertEquals(port, mongoProperty.getPort());
        assertThat(mongoProperty.getAuthDatabase()).isEqualTo(authDb);
        assertThat(mongoProperty.getPlatformDatabase()).isEqualTo(platformDb);
        assertThat(mongoProperty.getUser()).isEqualTo(user);
        assertThat(mongoProperty.getPassword()).isEqualTo(password);
    }

    @Test
    void setNullValues() {
        // GIVEN null values for string properties
        // WHEN setting them to null
        mongoProperty.setHost(null);
        mongoProperty.setAuthDatabase(null);
        mongoProperty.setPlatformDatabase(null);
        mongoProperty.setUser(null);
        mongoProperty.setPassword(null);

        // THEN getters return null
        assertThat(mongoProperty.getHost()).isNull();
        assertThat(mongoProperty.getAuthDatabase()).isNull();
        assertThat(mongoProperty.getPlatformDatabase()).isNull();
        assertThat(mongoProperty.getUser()).isNull();
        assertThat(mongoProperty.getPassword()).isNull();
    }

    @Test
    void setNegativePort() {
        // GIVEN a negative port value
        int negativePort = -1;
        // WHEN setting the port
        mongoProperty.setPort(negativePort);
        // THEN retrieving the port returns the same negative value
        assertEquals(negativePort, mongoProperty.getPort());
    }
}
