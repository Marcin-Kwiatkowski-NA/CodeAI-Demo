package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit5.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
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
    assertNotEquals(rawPassword, encryptedPassword, "Password encoding should be different");
  }

  @Test
  void matchedPassword_correctPassword() {
    // GIVEN an encrypted password and a correct raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String rawPassword = "testPassword123";
    // WHEN the matchedPassword method is called with the correct raw password and encrypted password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be true
    assertTrue(result, "Correct password should match");
  }

  @Test
  void matchedPassword_incorrectPassword() {
    // GIVEN an encrypted password and an incorrect raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String rawPassword = "wrongPassword";
    // WHEN the matchedPassword method is called with the incorrect raw password and encrypted password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be false
    assertFalse(result, "Incorrect password should not match");
  }
}
