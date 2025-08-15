package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

assertEquals("newUser", userResponse.getUsername());
    assertEquals("new@example.com", userResponse.getEmail());
  }

  @Test
  @ExtendWith(MockitoExtension.class)
  void generateUser_conflict_throwsConflict() {
    // GIVEN
    UserRequest request = new UserRequest();
    request.setUsername("newUser");
    request.setEmail("new@example.com");
    request.setPassword("newPassword");
    String id = "456";
    String encodePw = "newPassword";
    User user = new User(id, "testUser", id, "password");
    Mockito.when(this.userRepository.newId()).thenReturn(id);
    Mockito.when(this.userRepository.insert(user)).thenThrow(new Conflict(new Exception()));

    // WHEN
    // THEN
    assertThrows(Conflict.class, () -> this.generateUser(request));
  }

  @Test
  @ExtendWith(MockitoExtension.class)
  void getUserByEmail_validEmail_returnsUser() {
    // GIVEN
    String email = "test@example.com";
    User user = new User(email, "testUser", email, "password");
    Mockito.when(this.userRepository.findByEmail(email)).thenReturn(user);

    // WHEN
    User returnedUser = this.userRepository.findByEmail(email);

    // THEN
    assert returnedUser != null;
    assertEquals(email, returnedUser.getEmail());
    assertEquals("testUser", returnedUser.getUsername());
    assertEquals("password", returnedUser.getPassword());
  }

  @Test
  @ExtendWith(MockitoExtension.class)
  void getUserByEmail_invalidEmail_throwsInternalServerError() {
    // GIVEN
    String email = "invalidEmail";

    // WHEN
    // THEN
    assertThrows(InternalServerError.class, () -> this.userRepository.findByEmail(email));
  }
}
