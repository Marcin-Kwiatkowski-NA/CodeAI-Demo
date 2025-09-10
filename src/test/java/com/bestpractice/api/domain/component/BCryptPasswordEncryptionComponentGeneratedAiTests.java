package com.bestpractice.api.domain.component;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    String rawPassword = "password123";
    // WHEN the encodePassword method is called
    String encryptedPassword = component.encodePassword(rawPassword);
    // THEN the encrypted password should be a bcrypt hash
    assertNotNull(encryptedPassword);
    // Assert that the length of the encrypted password is greater than the raw password
    assertNotEquals(rawPassword.length(), encryptedPassword.length());
  }

  @Test
  void matchedPassword() {
    // GIVEN a raw password and its encrypted counterpart
    String rawPassword = "password123";
    String encryptedPassword = component.encodePassword(rawPassword);
    // WHEN the matchedPassword method is called with the same raw password and encrypted password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be true
    assertTrue(result);

    // WHEN the matchedPassword method is called with a different raw password
    String differentRawPassword = "differentPassword";
    boolean differentResult = component.matchedPassword(differentRawPassword, encryptedPassword);
    // THEN the result should be false
    assertFalse(differentResult);
  }
}