package com.bestpractice.api.domain.component;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MockitoExtension;
import org.junit.jupiter.api.extension.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;

@MockitoJUnitRunner
public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent component;

    @BeforeEach
    void setUp() {
        component = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void encodePassword_validPassword_returnsEncryptedPassword() {
        // GIVEN: A valid password string
        String rawPassword = "testPassword123";

        // WHEN: The encodePassword method is called with the raw password
        String encryptedPassword = component.encodePassword(rawPassword);

        // THEN: The encryptedPassword should be equal to the hashed version of the rawPassword
        assertEquals(encryptedPassword, component.encodePassword(rawPassword));
    }

    @Test
    void matchedPassword_validPasswordAndEncryptedPassword_returnsTrue() {
        // GIVEN: A valid password and its corresponding encrypted version
        String rawPassword = "testPassword123";
        String encryptedPassword = component.encodePassword(rawPassword);

        // WHEN: The matchedPassword method is called with the raw password and the encrypted password
        boolean result = component.matchedPassword(rawPassword, encryptedPassword);

        // THEN: The result should be true, indicating a match
        assertTrue(result);
    }

    @Test
    void matchedPassword_invalidPassword_returnsFalse() {
        // GIVEN: A valid password and an invalid encrypted password
        String rawPassword = "testPassword123";
        String invalidEncryptedPassword = "wrongHash";

        // WHEN: The matchedPassword method is called with the raw password and the invalid encrypted password
        boolean result = component.matchedPassword(rawPassword, invalidEncryptedPassword);

        // THEN: The result should be false, indicating no match
        assertFalse(result);
    }
}