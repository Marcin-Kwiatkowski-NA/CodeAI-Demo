package com.bestpractice.api.domain.component;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({})
public class BCryptPasswordEncryptionComponentGeneratedAiTests {

    private BCryptPasswordEncryptionComponent component;

    @BeforeEach
    public void setUp() {
        this.component = new BCryptPasswordEncryptionComponent();
    }

    @Test
    public void testEncodePassword() {
        // GIVEN
        String rawPassword = "password123";

        // WHEN
        String encodedPassword = component.encodePassword(rawPassword);

        // THEN
        assertNotNull(encodedPassword);
        assertTrue(component.matchedPassword(rawPassword, encodedPassword));
    }

    @Test
    public void testMatchedPassword() {
        // GIVEN
        String rawPassword = "password123";
        String encodedPassword = component.encodePassword(rawPassword);

        // WHEN
        boolean isMatch = component.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertTrue(isMatch);
    }

    @Test
    public void testMatchedPasswordWithIncorrectPassword() {
        // GIVEN
        String rawPassword = "password123";
        String incorrectRawPassword = "wrongpassword";
        String encodedPassword = component.encodePassword(rawPassword);

        // WHEN
        boolean isMatch = component.matchedPassword(incorrectRawPassword, encodedPassword);

        // THEN
        assertFalse(isMatch);
    }

    @Test
    public void testMatchedPasswordWithEmptyStrings() {
        // GIVEN
        String rawPassword = "";
        String encodedPassword = component.encodePassword(rawPassword);

        // WHEN
        boolean isMatch = component.matchedPassword(rawPassword, encodedPassword);

        // THEN
        assertTrue(isMatch);
    }

}
