package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
class AuthResponseGeneratedAiTests {

  private AuthResponse authResponse;

  @BeforeEach
  void setUp() {
    // Set up the AuthResponse object with default values for testing.
    this.authResponse = new AuthResponse("Bearer", "someToken", "refreshToken", new Date());
  }

  @Test
  void testGetTokenType() {
    // GIVEN: A new AuthResponse object has been created.
    // WHEN: The getTokenType() method is called.
    // THEN: The tokenType (Bearer) is returned.
    assertEquals("Bearer", authResponse.getTokenType());
  }

  @Test
  void test getToken() {
    // GIVEN: A new AuthResponse object has been created.
    // WHEN: The getToken() method is called.
    // THEN: The token ("someToken") is returned.
    assertEquals("someToken", authResponse.getToken());
  }

  @Test
  void testGetRefreshToken() {
    // GIVEN: A new AuthResponse object has been created.
    // WHEN: The getRefreshToken() method is called.
    // THEN: The refreshToken ("refreshToken") is returned.
    assertEquals("refreshToken", authResponse.getRefreshToken());
  }

  @Test
  void testGetExpiresAt() {
    // GIVEN: A new AuthResponse object has been created.
    // WHEN: The getExpiresAt() method is called.
    // THEN: The expiresAt date is returned.
    assertEquals(new Date(), authResponse.getExpiresAt());
  }
}

class MyExtension {}
