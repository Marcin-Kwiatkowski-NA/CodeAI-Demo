package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({})
public class AuthByEmailRequestGeneratedAiTests {

  private AuthByEmailRequest authByEmailRequest;

  @BeforeEach
  public void setUp() {
    authByEmailRequest = new AuthByEmailRequest();
  }

  @Test
  public void testSetAndGetEmailValid() {
    // GIVEN
    String email = "test@example.com";

    // WHEN
    authByEmailRequest.setEmail(email);

    // THEN
    assertEquals(email, authByEmailRequest.getEmail());
  }

  @Test
  public void testSetAndGetPassword() {
    // GIVEN
    String password = "password123";

    // WHEN
    authByEmailRequest.setPassword(password);

    // THEN
    assertEquals(password, authByEmailRequest.getPassword());
  }

  @Test
  public void testInvalidEmail() {
    // GIVEN
    String invalidEmail = "invalid-email@";

    // WHEN / THEN
    Exception exception = assertThrows(ConstraintViolationException.class, () -> {
      authByEmailRequest.setEmail(invalidEmail);
    });

    // THEN
    assertNotNull(exception.getMessage());
  }

  @Test
  public void testNullPassword() {
    // GIVEN
    String password = null;

    // WHEN / THEN
    Exception exception = assertThrows(ConstraintViolationException.class, () -> {
      authByEmailRequest.setPassword(password);
    });

    // THEN
    assertNotNull(exception.getMessage());
  }

  @Test
  public void testResetEmail() {
    // GIVEN
    String email = "test@example.com";
    authByEmailRequest.setEmail(email);

    // WHEN
    authByEmailRequest.setEmail(null);

    // THEN
    assertNull(authByEmailRequest.getEmail());
  }

  @Test
  public void testResetPassword() {
    // GIVEN
    String password = "password123";
    authByEmailRequest.setPassword(password);

    // WHEN
    authByEmailRequest.setPassword(null);

    // THEN
    assertNull(authByEmailRequest.getPassword());
  }
}