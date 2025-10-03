package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserResponseGeneratedAiTests {

  private UserResponse userResponse;

  @BeforeEach
  void setUp() {
    // Initialize the UserResponse object before each test
    this.userResponse = new UserResponse("123", "john.doe", "john.doe@example.com");
  }

  @Test
  void getId_returnsId() {
    // GIVEN: A UserResponse object is created with an ID of "123".
    // WHEN: The getId() method is called.
    // THEN: The method returns the ID "123".
    assertEquals("123", userResponse.getId());
  }

  @Test
  void getUsername_returnsUsername() {
    // GIVEN: A UserResponse object is created with a username of "john.doe".
    // WHEN: The getUsername() method is called.
    // THEN: The method returns the username "john.doe".
    assertEquals("john.doe", userResponse.getUsername());
  }

  @Test
  void getEmail_returnsEmail() {
    // GIVEN: A UserResponse object is created with an email of "john.doe@example.com".
    // WHEN: The getEmail() method is called.
    // THEN: The method returns the email "john.doe@example.com".
    assertEquals("john.doe@example.com", userResponse.getEmail());
  }
}
