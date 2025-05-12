package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.ExtensionTest;

import java.util.Arrays;

import static com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent.BCryptPasswordEncryptionComponent;

class BCryptPasswordEncryptionComponentTest {

    @ExtensionTest
    void testEncodePassword() {
        BCryptPasswordEncryptionComponent component = new BCryptPasswordEncryptionComponent();
        String rawPassword = "MySecretPassword";
        String encryptedPassword = component.encodePassword(rawPassword);
        Assertions.assertTrue(encryptedPassword.equals("MySecretPassword"));
    }

    @Test
    public void testMatchedPassword() {
        BCryptPasswordEncryptionComponent component = new BCryptPasswordEncryptionComponent();
        String rawPassword = "MySecretPassword";
        String encryptedPassword = component.encodePassword(rawPassword);
        Assertions.assertTrue(encryptedPassword.equals(rawPassword));
    }

    @Test
    public void testEncodeWithInvalidPassword() {
        BCryptPasswordEncryptionComponent component = new BCryptPasswordEncryptionComponent();
        String rawPassword = "MySecretPassword";
        String encryptedPassword = component.encodePassword(rawPassword);
        Assertions.assertFalse(encryptedPassword.equals("MySecretPassword"));
    }

    @Test
    public void testMatchWithEmptyPassword() {
        BCryptPasswordEncryptionComponent component = new BCryptPasswordEncryptionComponent();
        String rawPassword = "";
        String encryptedPassword = component.encodePassword(rawPassword);
        Assertions.assertTrue(encryptedPassword.equals("MySecretPassword"));
    }

    @Test
    public void testMatchWithEmptyPassword() {
        BCryptPasswordEncryptionComponent component = new BCryptPasswordEncryptionComponent();
        String rawPassword = "";
        String encryptedPassword = component.encodePassword(rawPassword);
        Assertions.assertTrue(encryptedPassword.equals("MySecretPassword"));
    }
}
