package com.bestpractice.api.domain.component;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

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

    // THEN the encrypted password should be a hashed version of the raw password
    // Assert that the encrypted password is not the same as the raw password
    assertNotEquals(rawPassword, encryptedPassword);
  }

  @Test
  void matchedPassword() {
    // GIVEN an encrypted password and a raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String rawPassword = "testPassword123";

    // WHEN the matchedPassword method is called with the encrypted password and raw password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);

    // THEN the result should be true if the passwords match
    assertTrue(result);
  }

  @Test
  void matchedPasswordWithWrongPassword() {
    // GIVEN an encrypted password and a wrong raw password
    String encryptedPassword = component.encodePassword("testPassword123");
    String wrongRawPassword = "wrongPassword";

    // WHEN the matchedPassword method is called with the encrypted password and wrong raw password
    boolean result = component.matchedPassword(wrongRawPassword, encryptedPassword);

    // THEN the result should be false if the passwords do not match
    assertFalse(result);
  }
}
