package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserResponseGeneratedAiTests {

  @BeforeEach
  void setUp() {
    // Reset state before each test.  Not applicable in this simple case, but included for completeness.
  }

  @Test
  void getId_returnsId() {
    // GIVEN a UserResponse object with id "123"
    UserResponse userResponse = new UserResponse("123", "testuser", "test@example.com");
    // WHEN we call getID()
    String id = userResponse.getId();
    // THEN the returned id should be "123"
    assertEquals("123", id);
  }

  @Test
  void getUsername_returnsUsername() {
    // GIVEN a UserResponse object with username "testuser"
    UserResponse userResponse = new UserResponse("123", "testuser", "test@example.com");
    // WHEN we call getUsername()
    String username = userResponse.getUsername();
    // THEN the returned username should be "testuser"
    assertEquals("testuser", username);
  }

  @Test
  void getEmail_returnsEmail() {
    // GIVEN a UserResponse object with email "test@example.com"
    UserResponse userResponse = new UserResponse("123", "testuser", "test@example.com");
    // WHEN we call getEmail()
    String email = userResponse.getEmail();
    // THEN the returned email should be "test@example.com"
    assertEquals("test@example.com", email);
  }
}
