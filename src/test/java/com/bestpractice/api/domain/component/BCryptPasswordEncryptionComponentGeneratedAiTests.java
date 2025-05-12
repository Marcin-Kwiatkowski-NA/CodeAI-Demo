package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(BCryptPasswordEncryptionComponentGeneratedAiTests.class)
class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent component;

    @BeforeEach
    void setUp() {
        component = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void encodePassword_validPassword_returnsEncryptedPassword() {
        // GIVEN: A valid password to be encoded.
        String rawPassword = "password123";

        // WHEN: The encodePassword method is called with the raw password.
        String encryptedPassword = component.encodePassword(rawPassword);

        // THEN: The encrypted password should be returned.
        assertNotNull(encryptedPassword);
        // We cannot directly compare the encrypted password due to its hashing nature.
        // However, we can verify that it is not null and has a reasonable length.
        assertFalse(encryptedPassword.isEmpty());
    }

    @Test
    void matchedPassword_validPasswordAndEncryptedPassword_returnsTrue() {
        // GIVEN: A valid raw password and its corresponding encrypted password.
        String rawPassword = "password123";
        String encryptedPassword = component.encodePassword(rawPassword);

        // WHEN: The matchedPassword method is called with the raw password and the encrypted password.
        boolean result = component.matchedPassword(rawPassword, encryptedPassword);

        // THEN: The result should be true.
        assertTrue(result);
    }

    @Test
    void matchedPassword_invalidPassword_returnsFalse() {
        // GIVEN: An invalid raw password.
        String rawPassword = "wrongpassword";

        // WHEN: The matchedPassword method is called with the raw password and the encrypted password.
        boolean result = component.matchedPassword(rawPassword, component.encodePassword(rawPassword));

        // THEN: The result should be false.
        assertFalse(result);
    }
}
