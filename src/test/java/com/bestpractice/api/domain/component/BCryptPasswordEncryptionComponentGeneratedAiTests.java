package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MockitoExtension;
import org.junit.jupiter.api.extension.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;

@MockitoExtension
@MockitoJUnitRunner
public class BCryptPasswordEncryptionComponentGeneratedAiTests {

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
    assertNotEquals(rawPassword, encryptedPassword, "Password encoding should not return the same password");
  }

  @Test
  void matchedPassword_correctPassword() {
    // GIVEN an encrypted password and the correct raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String rawPassword = "testPassword123";
    // WHEN the matchedPassword method is called with the correct raw password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be true
    assertTrue(result, "Correct password should match");
  }

  @Test
  void matchedPassword_incorrectPassword() {
    // GIVEN an encrypted password and an incorrect raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String rawPassword = "wrongPassword";
    // WHEN the matchedPassword method is called with the incorrect raw password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be false
    assertFalse(result, "Incorrect password should not match");
  }
}
