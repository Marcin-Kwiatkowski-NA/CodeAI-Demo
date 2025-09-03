package com.bestpractice.api.domain.component;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyAnnotations.class)
class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent component;

    @BeforeEach
    void setUp() {
        component = new BCryptPasswordEncryptionComponent();
    }

    @Test
    void encodePassword_validPassword_returnsEncryptedPassword() {
        // GIVEN: A valid password
        String rawPassword = "password123";

        // WHEN: The encodePassword method is called
        String encryptedPassword = component.encodePassword(rawPassword);

        // THEN: The encrypted password should be the bcrypt hashed version of the raw password
        assertNotNull(encryptedPassword);
        // We cannot directly compare the raw and encrypted passwords due to the hashing process.
        // However, we can verify that the returned value is not null and has a reasonable length.
        assertEquals(encryptedPassword.length(), 60); // Approximate length of bcrypt hash
    }

    @Test
    void matchedPassword_validPasswordAndEncryptedPassword_returnsTrue() {
        // GIVEN: A valid password and its corresponding encrypted password
        String rawPassword = "password123";
        String encryptedPassword = component.encodePassword(rawPassword);

        // WHEN: The matchedPassword method is called with the same raw password and encrypted password
        boolean result = component.matchedPassword(rawPassword, encryptedPassword);

        // THEN: The method should return true, indicating a match
        assertTrue(result);
    }

    @Test
    void matchedPassword_invalidPassword_returnsFalse() {
        // GIVEN: An invalid password and its corresponding encrypted password
        String rawPassword = "wrongPassword";
        String encryptedPassword = component.encodePassword(rawPassword);

        // WHEN: The matchedPassword method is called with the invalid password and encrypted password
        boolean result = component.matchedPassword(rawPassword, encryptedPassword);

        // THEN: The method should return false, indicating no match
        assertFalse(result);
    }
}