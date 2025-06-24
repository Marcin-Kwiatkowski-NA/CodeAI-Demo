package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(BCryptPasswordEncryptionComponentGeneratedAiTests.class)
class BCryptPasswordEncryptionComponent {

  private PasswordEncoder passwordEncoder;

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
  void encodePassword_validPassword_returnsEncryptedPassword() {
    // GIVEN: A valid password string
    String rawPassword = "password123";

    // WHEN: The encodePassword method is called with the raw password
    String encryptedPassword = component.encodePassword(rawPassword);

    // THEN: The encrypted password should be the bcrypt-encoded version of the raw password
    assertEquals(component.passwordEncoder.encode(rawPassword), encryptedPassword);
  }

  @Test
  void matchedPassword_validPasswordAndEncryptedPassword_returnsTrue() {
    // GIVEN: A valid password string and its corresponding encrypted version
    String rawPassword = "password123";
    String encryptedPassword = component.encodePassword(rawPassword);

    // WHEN: The matchedPassword method is called with the raw password and encrypted password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);

    // THEN: The method should return true, indicating a match
    assertTrue(result);
  }

  @Test
  void matchedPassword_invalidPassword_returnsFalse() {
    // GIVEN: An invalid password string and its corresponding encrypted version
    String rawPassword = "wrongPassword";
    String encryptedPassword = component.encodePassword(rawPassword);

    // WHEN: The matchedPassword method is called with the raw password and encrypted password
    boolean result = component.matchedPassword(rawPassword, encryptedPassword);

    // THEN: The method should return false, indicating no match
    assertFalse(result);
  }
}
