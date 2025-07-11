package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MyComponentGeneratedAiTests.class)
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

class MyComponentGeneratedAiTests {

  BCryptPasswordEncryptionComponent component;

  @BeforeEach
  void setUp() {
    component = new BCryptPasswordEncryptionComponent();
  }

  @Test
  void encodePassword_shouldEncodePasswordCorrectly() {
    // GIVEN a raw password
    String rawPassword = "testpassword";
    // WHEN the encodePassword method is called
    String encryptedPassword = component.encodePassword(rawPassword);
    // THEN the encrypted password should be the encoded version of the raw password
    assertEquals(encryptedPassword, component.encodePassword(rawPassword));
  }

  @Test
  void matchedPassword_shouldReturnTrueIfPasswordsMatch() {
    // GIVEN a raw password and its encrypted counterpart
    String rawPassword = "testpassword";
    String encryptedPassword = component.encodePassword(rawPassword);
    // WHEN the matchedPassword method is called with the same raw and encrypted passwords
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be true
    assertTrue(result);
  }

  @Test
  void matchedPassword_shouldReturnFalseIfPasswordsDoNotMatch() {
    // GIVEN a raw password and a different encrypted password
    String rawPassword = "testpassword";
    String encryptedPassword = "wrongencryptedpassword";
    // WHEN the matchedPassword method is called with different passwords
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);
    // THEN the result should be false
    assertFalse(result);
  }
}