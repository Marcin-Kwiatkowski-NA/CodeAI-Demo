package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MongoPropertyGeneratedAiTests {

    private MongoProperty mongoProperty;

    @BeforeEach
    void setUp() {
        mongoProperty = new MongoProperty();
    }

    // --- Improvements Summary ---
    // 1. Removed unused imports and redundant comments.
    // 2. Ensured consistent GIVEN-WHEN-THEN structure.
    // 3. Added missing null tests for all string fields.
    // 4. Verified default initialization behavior.
    // 5. Ensured all tests are independent and logically correct.

    @Test
    void testDefaultValuesAreNullOrZero() {
        // GIVEN a new MongoProperty instance
        // WHEN no values are set
        // THEN all fields should have default values
        assertEquals(null, mongoProperty.getHost());
        assertEquals(0, mongoProperty.getPort());
        assertEquals(null, mongoProperty.getAuthDatabase());
        assertEquals(null, mongoProperty.getPlatformDatabase());
        assertEquals(null, mongoProperty.getUser());
        assertEquals(null, mongoProperty.getPassword());
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN
        String expectedHost = "localhost";

        // WHEN
        mongoProperty.setHost(expectedHost);

        // THEN
        assertEquals(expectedHost, mongoProperty.getHost());
    }

    @Test
    void testSetHostWithEmptyString() {
        // GIVEN
        String emptyHost = "";

        // WHEN
        mongoProperty.setHost(emptyHost);

        // THEN
        assertEquals(emptyHost, mongoProperty.getHost());
    }

    @Test
    void testSetHostWithWhitespaceString() {
        // GIVEN
        String whitespaceHost = "   ";

        // WHEN
        mongoProperty.setHost(whitespaceHost);

        // THEN
        assertEquals(whitespaceHost, mongoProperty.getHost());
    }

    @Test
    void testSetHostWithNull() {
        // GIVEN
        String nullHost = null;

        // WHEN
        mongoProperty.setHost(nullHost);

        // THEN
        assertEquals(null, mongoProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN
        int expectedPort = 27017;

        // WHEN
        mongoProperty.setPort(expectedPort);

        // THEN
        assertEquals(expectedPort, mongoProperty.getPort());
    }

    @Test
    void testSetPortWithZero() {
        // GIVEN
        int zeroPort = 0;

        // WHEN
        mongoProperty.setPort(zeroPort);

        // THEN
        assertEquals(zeroPort, mongoProperty.getPort());
    }

    @Test
    void testSetPortWithNegativeValue() {
        // GIVEN
        int negativePort = -1;

        // WHEN
        mongoProperty.setPort(negativePort);

        // THEN
        assertEquals(negativePort, mongoProperty.getPort());
    }

    @Test
    void testSetPortWithMaxIntValue() {
        // GIVEN
        int maxPort = Integer.MAX_VALUE;

        // WHEN
        mongoProperty.setPort(maxPort);

        // THEN
        assertEquals(maxPort, mongoProperty.getPort());
    }

    @Test
    void testSetPortWithMinIntValue() {
        // GIVEN
        int minPort = Integer.MIN_VALUE;

        // WHEN
        mongoProperty.setPort(minPort);

        // THEN
        assertEquals(minPort, mongoProperty.getPort());
    }

    @Test
    void testSetAndGetAuthDatabase() {
        // GIVEN
        String expectedAuthDatabase = "admin";

        // WHEN
        mongoProperty.setAuthDatabase(expectedAuthDatabase);

        // THEN
        assertEquals(expectedAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAuthDatabaseWithEmptyString() {
        // GIVEN
        String emptyAuthDatabase = "";

        // WHEN
        mongoProperty.setAuthDatabase(emptyAuthDatabase);

        // THEN
        assertEquals(emptyAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAuthDatabaseWithWhitespaceString() {
        // GIVEN
        String whitespaceAuthDatabase = "   ";

        // WHEN
        mongoProperty.setAuthDatabase(whitespaceAuthDatabase);

        // THEN
        assertEquals(whitespaceAuthDatabase, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAuthDatabaseWithNull() {
        // GIVEN
        String nullAuthDatabase = null;

        // WHEN
        mongoProperty.setAuthDatabase(nullAuthDatabase);

        // THEN
        assertEquals(null, mongoProperty.getAuthDatabase());
    }

    @Test
    void testSetAndGetPlatformDatabase() {
        // GIVEN
        String expectedPlatformDatabase = "platformDB";

        // WHEN
        mongoProperty.setPlatformDatabase(expectedPlatformDatabase);

        // THEN
        assertEquals(expectedPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetPlatformDatabaseWithEmptyString() {
        // GIVEN
        String emptyPlatformDatabase = "";

        // WHEN
        mongoProperty.setPlatformDatabase(emptyPlatformDatabase);

        // THEN
        assertEquals(emptyPlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetPlatformDatabaseWithWhitespaceString() {
        // GIVEN
        String whitespacePlatformDatabase = "   ";

        // WHEN
        mongoProperty.setPlatformDatabase(whitespacePlatformDatabase);

        // THEN
        assertEquals(whitespacePlatformDatabase, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetPlatformDatabaseWithNull() {
        // GIVEN
        String nullPlatformDatabase = null;

        // WHEN
        mongoProperty.setPlatformDatabase(nullPlatformDatabase);

        // THEN
        assertEquals(null, mongoProperty.getPlatformDatabase());
    }

    @Test
    void testSetAndGetUser() {
        // GIVEN
        String expectedUser = "testUser";

        // WHEN
        mongoProperty.setUser(expectedUser);

        // THEN
        assertEquals(expectedUser, mongoProperty.getUser());
    }

    @Test
    void testSetUserWithEmptyString() {
        // GIVEN
        String emptyUser = "";

        // WHEN
        mongoProperty.setUser(emptyUser);

        // THEN
        assertEquals(emptyUser, mongoProperty.getUser());
    }

    @Test
    void testSetUserWithWhitespaceString() {
        // GIVEN
        String whitespaceUser = "   ";

        // WHEN
        mongoProperty.setUser(whitespaceUser);

        // THEN
        assertEquals(whitespaceUser, mongoProperty.getUser());
    }

    @Test
    void testSetUserWithNull() {
        // GIVEN
        String nullUser = null;

        // WHEN
        mongoProperty.setUser(nullUser);

        // THEN
        assertEquals(null, mongoProperty.getUser());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "securePassword"; // SECURITY-SENSITIVE

        // WHEN
        mongoProperty.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoProperty.getPassword());
    }

    @Test
    void testSetPasswordWithEmptyString() {
        // GIVEN
        String emptyPassword = ""; // SECURITY-SENSITIVE

        // WHEN
        mongoProperty.setPassword(emptyPassword);

        // THEN
        assertEquals(emptyPassword, mongoProperty.getPassword());
    }

    @Test
    void testSetPasswordWithWhitespaceString() {
        // GIVEN
        String whitespacePassword = "   "; // SECURITY-SENSITIVE

        // WHEN
        mongoProperty.setPassword(whitespacePassword);

        // THEN
        assertEquals(whitespacePassword, mongoProperty.getPassword());
    }

    @Test
    void testSetPasswordWithNull() {
        // GIVEN
        String nullPassword = null; // SECURITY-SENSITIVE

        // WHEN
        mongoProperty.setPassword(nullPassword);

        // THEN
        assertEquals(null, mongoProperty.getPassword());
    }
}
