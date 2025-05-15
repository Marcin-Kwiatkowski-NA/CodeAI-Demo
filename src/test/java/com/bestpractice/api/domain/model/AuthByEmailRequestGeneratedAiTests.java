package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthByEmailRequestGeneratedAiTests {

  private AuthByEmailRequest authByEmailRequest;

  @BeforeEach
  void setUp() {
    authByEmailRequest = new AuthByEmailRequest();
  }

  @Test
  void setEmailAndGetEmail() {
    // GIVEN: A new AuthByEmailRequest object is created.
    // WHEN: The email field is set to "test@example.com".
    authByEmailRequest.setEmail("test@example.com");
    // THEN: The email field is set to "test@example.com".
    assertEquals("test@example.com", authByEmailRequest.getEmail());
  }

  @Test
  void setPasswordAndGetPassword() {
    // GIVEN: A new AuthByEmailRequest object is created.
    // WHEN: The password field is set to "secretPassword".
    authByEmailRequest.setPassword("secretPassword");
    // THEN: The password field is set to "secretPassword".
    assertEquals("secretPassword", authByEmailRequest.getPassword());
  }

  @Test
  void setEmailAndSetPassword() {
    // GIVEN: A new AuthByEmailRequest object is created.
    // WHEN: The email field is set to "test@example.com" and the password field is set to "secretPassword".
    authByEmailRequest.setEmail("test@example.com");
    authByEmailRequest.setPassword("secretPassword");
    // THEN: Both the email and password fields are set correctly.
    assertEquals("test@example.com", authByEmailRequest.getEmail());
    assertEquals("secretPassword", authByEmailRequest.getPassword());
  }
}
