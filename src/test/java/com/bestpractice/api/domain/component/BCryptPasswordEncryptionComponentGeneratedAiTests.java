package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import static com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent.BCryptPasswordEncryptionComponent.GIVEN_WHEN_THEN_BLOCK;
import static com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent.test_method_setup;
import static com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent.test_method_assert_result;

class BCryptPasswordEncryptionComponentTest {

    @Test
    public void testEncodePassword() {
        String rawPassword = "MySecretPassword";
        String encryptedPassword = BCryptPasswordEncryptionComponent.encodePassword(rawPassword);
        assertResult(BCryptPasswordEncryptionComponent.GIVEN_WHEN_THEN_BLOCK("Input Password: " + rawPassword + ", Encrypted Password: " + encryptedPassword));
    }

    @Test
    public void testMatchedPassword() {
        String rawPassword = "MySecretPassword";
        String encryptedPassword = BCryptPasswordEncryptionComponent.encodePassword(rawPassword);
        assertResult(BCryptPasswordEncryptionComponent.GIVEN_WHEN_THEN_BLOCK("Input Password: " + rawPassword + ", Encrypted Password: " + encryptedPassword));
    }

    @Test
    public void testEncodePasswordWithInvalidPassword() {
        String rawPassword = "MySecretPassword";
        String encryptedPassword = BCryptPasswordEncryptionComponent.encodePassword(rawPassword);
        assertResult(BCryptPasswordEncryptionComponent.GIVEN_WHEN_THEN_BLOCK("Input Password: " + rawPassword + ", Encrypted Password: " + encryptedPassword));
    }

    @Test
    public void testMatchedPasswordWithInvalidPassword() {
        String rawPassword = "MySecretPassword";
        String encryptedPassword = BCryptPasswordEncryptionComponent.encodePassword(rawPassword);
        assertResult(BCryptPasswordEncryptionComponent.GIVEN_WHEN_THEN_BLOCK("Input Password: " + rawPassword + ", Encrypted Password: " + encryptedPassword));
    }
}
