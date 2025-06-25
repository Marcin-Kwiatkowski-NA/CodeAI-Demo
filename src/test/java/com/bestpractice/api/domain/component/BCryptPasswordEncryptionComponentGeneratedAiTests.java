package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

@org.junit.jupiter.api.ExtensionRegistry.Extensions.registerExtension(BCryptPasswordEncryptionComponentGeneratedAiTests.class)
class BCryptPasswordEncryptionComponentGeneratedAiTests {

  private BCryptPasswordEncryptionComponent component;

  @BeforeEach
  void setUp() {
    component = new BCryptPasswordEncryptionComponent();
  }

  @Test
  void encodePassword() {
    // GIVEN a raw password
    String rawPassword = "password123";
    // WHEN the encodePassword method is called
    String encryptedPassword = component.encodePassword(rawPassword);
    // THEN the encrypted password should be a hashed version of the raw password
    // Assert that the encrypted password is not the same as the raw password
    assert !encryptedPassword.equals(rawPassword);
  }

  @Test
  void matchedPassword() {
    // GIVEN an encrypted password and a raw password
    String encryptedPassword = component.encodePassword("password123");
    String rawPassword = "password123";
    // WHEN the matchedPassword method is called with the encrypted password and the raw password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be true if the passwords match
    assertTrue(result);
  }

  @Test
  void matchedPasswordWithWrongPassword() {
    // GIVEN an encrypted password and a wrong raw password
    String encryptedPassword = component.encodePassword("password123");
    String rawPassword = "wrongPassword";
    // WHEN the matchedPassword method is called with the encrypted password and the wrong raw password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be false if the passwords do not match
    assertFalse(result);
  }
}
