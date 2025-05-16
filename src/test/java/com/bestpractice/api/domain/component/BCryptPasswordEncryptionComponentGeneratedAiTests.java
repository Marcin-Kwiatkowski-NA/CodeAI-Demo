package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent component;

    @BeforeEach
    void setUp() {
        component = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void encodePassword_validPassword_returnsEncryptedPassword() {
        // GIVEN: A valid password to be encoded.
        String rawPassword = "testpassword";

        // WHEN: The encodePassword method is called with the raw password.
        String encryptedPassword = component.encodePassword(rawPassword);

        // THEN: The encrypted password should be different from the raw password.
        assertNotEquals(rawPassword, encryptedPassword, "Encrypted password should be different from raw password");
    }

    @Test
    void matchedPassword_validPasswordAndEncryptedPassword_returnsTrue() {
        // GIVEN: A valid raw password and its corresponding encrypted password.
        String rawPassword = "testpassword";
        String encryptedPassword = component.encodePassword(rawPassword);

        // WHEN: The matchedPassword method is called with the raw password and encrypted password.
        boolean result = component.matchedPassword(rawPassword, encryptedPassword);

        // THEN: The result should be true.
        assertTrue(result, "Password matching should return true");
    }

    @Test
    void matchedPassword_invalidPassword_returnsFalse() {
        // GIVEN: An invalid raw password and its corresponding encrypted password.
        String rawPassword = "wrongpassword";
        String encryptedPassword = component.encodePassword(rawPassword);

        // WHEN: The matchedPassword method is called with the raw password and encrypted password.
        boolean result = component.matchedPassword(rawPassword, encryptedPassword);

        // THEN: The result should be false.
        assertFalse(result, "Password matching should return false for invalid password");
    }
}
