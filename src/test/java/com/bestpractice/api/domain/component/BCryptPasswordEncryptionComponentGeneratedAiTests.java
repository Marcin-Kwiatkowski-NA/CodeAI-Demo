package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
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
  void encodePassword() {
    // GIVEN a raw password
    String rawPassword = "testPassword123";
    // WHEN the encodePassword method is called
    String encryptedPassword = component.encodePassword(rawPassword);
    // THEN the encrypted password should be different from the raw password
    assertNotEquals(rawPassword, encryptedPassword, "Password encoding should produce a different string");
  }

  @Test
  void matchedPassword() {
    // GIVEN an encrypted password and a raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String rawPassword = "testPassword123";
    // WHEN the matchedPassword method is called with the encrypted password and the raw password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be true
    assertTrue(result, "Password matching should return true");
  }

  @Test
  void matchedPasswordIncorrectPassword() {
    // GIVEN an encrypted password and an incorrect raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String rawPassword = "wrongPassword";
    // WHEN the matchedPassword method is called with the encrypted password and the incorrect raw password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be false
    assertFalse(result, "Password matching should return false for incorrect passwords");
  }
}
