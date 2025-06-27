package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;

class UserControllerGeneratedAiTests {

    private UserService userService;
    private UserController controller;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        controller = new UserController(userService);
    }

    @Test
    void createUser_validRequest_returnsUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testUser");
        request.setEmail("test@example.com");
        request.setPassword("password123");

        // WHEN
        UserResponse response = controller.createUser(request, null);

        // THEN
        assertNotNull(response);
        assertEquals("testUser", response.getUsername());
        assertEquals("test@example.com", response.getEmail());
        verify(userService).generateUser(request);
    }

    @Test
    void createUser_invalidRequest_throwsBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername(null);
        request.setEmail(null);
        request.setPassword(null);

        // WHEN
        assertThrows(BadRequest.class, () -> controller.createUser(request, null));

        // THEN
        verify(userService, never()).generateUser(request);
    }

    @Test
    void createUser_requestWithMissingFields_throwsBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername(null);

        // WHEN
        assertThrows(BadRequest.class, () -> controller.createUser(request, null));

        // THEN
        verify(userService, never()).generateUser(request);
    }
}
