package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {
  @JsonProperty("id")
  private final String id;
  @JsonProperty("username")
  private final String username;
  @JsonProperty("email")
  private final String email;

  public UserResponse(String id, String username, String email) {
    this.id = id;
    this.username = username;
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }
}


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
public class UserResponseGeneratedAiTests {

  private UserResponse userResponse;

  @BeforeEach
  void setUp() {
    userResponse = new UserResponse("123", "john.doe", "john.doe@example.com");
  }

  @Test
  void getId() {
    // GIVEN a UserResponse object with id "123"
    // WHEN the getId() method is called
    // THEN the id "123" should be returned
    assertEquals("123", userResponse.getId());
  }

  @Test
  void getUsername() {
    // GIVEN a UserResponse object with username "john.doe"
    // WHEN the getUsername() method is called
    // THEN the username "john.doe" should be returned
    assertEquals("john.doe", userResponse.getUsername());
  }

  @Test
  void getEmail() {
    // GIVEN a UserResponse object with email "john.doe@example.com"
    // WHEN the getEmail() method is called
    // THEN the email "john.doe@example.com" should be returned
    assertEquals("john.doe@example.com", userResponse.getEmail());
  }
}
