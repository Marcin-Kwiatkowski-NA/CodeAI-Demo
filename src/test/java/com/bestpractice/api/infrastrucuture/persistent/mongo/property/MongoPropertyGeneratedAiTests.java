package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    public void setUp() {
        mongoProperty = new MongoProperty();
    }

    @Test
    public void givenValidHostAndPort_whenSetThenHostAndPortAreSetCorrectly() {
        // GIVEN
        String host = "localhost";
        int port = 27017;

        // WHEN
        mongoProperty.setHost(host);
        mongoProperty.setPort(port);

        // THEN
        assertThat(mongoProperty.getHost()).isEqualTo(host);
        assertThat(mongoProperty.getPort()).isEqualTo(port);
    }

    @Test
    public void givenNullHost_whenSetThenHostIsSetToEmptyString() {
        // GIVEN
        String nullHost = null;

        // WHEN
        mongoProperty.setHost(nullHost);

        // THEN
        assertThat(mongoProperty.getHost()).isEqualTo("");
    }

    @Test
    public void givenEmptyHost_whenSetThenHostIsSetToEmptyString() {
        // GIVEN
        String emptyHost = "";

        // WHEN
        mongoProperty.setHost(emptyHost);

        // THEN
        assertThat(mongoProperty.getHost()).isEqualTo("");
    }

    @Test
    public void givenValidAuthDatabase_whenSetThenAuthDatabaseIsSetCorrectly() {
        // GIVEN
        String authDatabase = "admin";

        // WHEN
        mongoProperty.setAuthDatabase(authDatabase);

        // THEN
        assertThat(mongoProperty.getAuthDatabase()).isEqualTo(authDatabase);
    }

    @Test
    public void givenValidPlatformDatabase_whenSetThenPlatformDatabaseIsSetCorrectly() {
        // GIVEN
        String platformDatabase = "platform";

        // WHEN
        mongoProperty.setPlatformDatabase(platformDatabase);

        // THEN
        assertThat(mongoProperty.getPlatformDatabase()).isEqualTo(platformDatabase);
    }

    @Test
    public void givenValidUserAndPassword_whenSetThenUserAndPasswordAreSetCorrectly() {
        // GIVEN
        String user = "admin";
        String password = "secret";

        // WHEN
        mongoProperty.setUser(user);
        mongoProperty.setPassword(password);

        // THEN
        assertThat(mongoProperty.getUser()).isEqualTo(user);
        assertThat(mongoProperty.getPassword()).isEqualTo(password);
    }

    @Test
    public void givenEmptyUser_whenSetThenUserIsSetToEmptyString() {
        // GIVEN
        String emptyUser = "";

        // WHEN
        mongoProperty.setUser(emptyUser);

        // THEN
        assertThat(mongoProperty.getUser()).isEqualTo("");
    }

    @Test
    public void givenNullUser_whenSetThenUserIsSetToEmptyString() {
        // GIVEN
        String nullUser = null;

        // WHEN
        mongoProperty.setUser(nullUser);

        // THEN
        assertThat(mongoProperty.getUser()).isEqualTo("");
    }

    @Test
    public void givenEmptyPassword_whenSetThenPasswordIsSetToEmptyString() {
        // GIVEN
        String emptyPassword = "";

        // WHEN
        mongoProperty.setPassword(emptyPassword);

        // THEN
        assertThat(mongoProperty.getPassword()).isEqualTo("");
    }

    @Test
    public void givenNullPassword_whenSetThenPasswordIsSetToEmptyString() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        mongoProperty.setPassword(nullPassword);

        // THEN
        assertThat(mongoProperty.getPassword()).isEqualTo("");
    }

    @Test
    public void givenAllFieldsSet_whenGetAllFieldsThenAllValuesAreReturned() {
        // GIVEN
        String host = "localhost";
        int port = 27017;
        String authDatabase = "admin";
        String platformDatabase = "platform";
        String user = "admin";
        String password = "secret";

        // WHEN
        mongoProperty.setHost(host);
        mongoProperty.setPort(port);
        mongoProperty.setAuthDatabase(authDatabase);
        mongoProperty.setPlatformDatabase(platformDatabase);
        mongoProperty.setUser(user);
        mongoProperty.setPassword(password);

        // THEN
        assertThat(mongoProperty.getHost()).isEqualTo(host);
        assertThat(mongoProperty.getPort()).isEqualTo(port);
        assertThat(mongoProperty.getAuthDatabase()).isEqualTo(authDatabase);
        assertThat(mongoProperty.getPlatformDatabase()).isEqualTo(platformDatabase);
        assertThat(mongoProperty.getUser()).isEqualTo(user);
        assertThat(mongoProperty.getPassword()).isEqualTo(password);
    }
}
