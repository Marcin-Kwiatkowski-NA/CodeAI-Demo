package com.bestpractice.api.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

@Test
  void generateUser_internalServerError_throwsInternalServerError() {
    // GIVEN
    UserRequest request = new UserRequest();
    request.setUsername("testUser");
    request.setEmail("test@example.com");
    request.setPassword("password");

    String id = "456";
    String encPw = "encodedPassword";
    when(userRepository.newId()).thenReturn(id);
    when(encryptionComponent.encodePassword(request.getPassword())).thenReturn(encPw);
    when(userRepository.insert(user)).thenThrow(new InternalServerError(new Exception()));

    // WHEN
    // THEN
    assertThrows(InternalServerError.class, () -> userServiceImpl.generateUser(request));
  }
}
