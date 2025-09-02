package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
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
  void encodePassword() {
    // GIVEN a raw password
    String rawPassword = "testPassword123";
    // WHEN the encodePassword method is called
    String encryptedPassword = component.encodePassword(rawPassword);
    // THEN the encryptedPassword should be a bcrypt hash of the rawPassword
    assertNotNull(encryptedPassword);
  }

  @Test
  void matchedPassword() {
    // GIVEN a raw password and its encrypted counterpart
    String rawPassword = "testPassword123";
    String encryptedPassword = component.encodePassword(rawPassword);
    // WHEN the matchedPassword method is called with the same rawPassword and encryptedPassword
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be true
    assertTrue(result);
  }

  @Test
  void matchedPasswordIncorrectPassword() {
    // GIVEN a raw password and its encrypted counterpart
    String rawPassword = "testPassword123";
    String encryptedPassword = component.encodePassword(rawPassword);
    // WHEN the matchedPassword method is called with an incorrect rawPassword
    boolean result = component.matchedPassword("wrongPassword", encryptedPassword);
    // THEN the result should be false
    assertFalse(result);
  }
}
