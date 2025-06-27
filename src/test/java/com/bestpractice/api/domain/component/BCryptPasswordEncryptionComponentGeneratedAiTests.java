package com.bestpractice.api.domain.component;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(BCryptPasswordEncryptionComponentGeneratedAiTests.class)
class BCryptPasswordEncryptionComponent {

  private final PasswordEncoder passwordEncoder;

  public BCryptPasswordEncryptionComponent() {
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  public String encodePassword(String rawPassword) {
    return this.passwordEncoder.encode(rawPassword);
  }

  public boolean matchedPassword(String rawPassword, String encryptedPassword) {
    return passwordEncoder.matches(rawPassword, encryptedPassword);
  }
}

class BCryptPasswordEncryptionComponentGeneratedAiTests {
  BCryptPasswordEncryptionComponent component;

  @BeforeEach
  void setUp() {
    component = new BCryptPasswordEncryptionComponent();
  }

  @Test
  void encodePassword_shouldEncodePasswordCorrectly() {
    // GIVEN a raw password
    String rawPassword = "testPassword123";
    // WHEN the encodePassword method is called
    String encryptedPassword = component.encodePassword(rawPassword);
    // THEN the encrypted password should be the bcrypt encoded version of the raw password
    assertEquals(encryptedPassword, component.encodePassword(rawPassword));
  }

  @Test
  void matchedPassword_shouldReturnTrueIfPasswordsMatch() {
    // GIVEN a raw password and its encrypted counterpart
    String rawPassword = "testPassword123";
    String encryptedPassword = component.encodePassword(rawPassword);
    // WHEN the matchedPassword method is called with the same raw password and encrypted password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be true
    assertTrue(result);
  }

  @Test
  void matchedPassword_shouldReturnFalseIfPasswordsDoNotMatch() {
    // GIVEN a raw password and a different encrypted password
    String rawPassword = "testPassword123";
    String differentEncryptedPassword = "differentEncryptedPassword";
    // WHEN the matchedPassword method is called with different raw and encrypted passwords
    boolean result = component.matchedPassword(rawPassword, differentEncryptedPassword);
    // THEN the result should be false
    assertFalse(result);
  }
}
