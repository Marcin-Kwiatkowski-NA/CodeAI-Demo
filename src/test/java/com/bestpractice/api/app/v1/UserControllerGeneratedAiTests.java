package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserControllerGeneratedAiTests {

    private UserService userService;
    private UserController userController;
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        userController = new UserController(userService);
        bindingResult = Mockito.mock(BindingResult.class);
    }

    @Test
    public void givenValidUserRequest_whenCreateUser_thenReturnsUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("securepassword");
        when(bindingResult.hasErrors()).thenReturn(false);
        UserResponse expectedResponse = new UserResponse("1", "testuser", "test@example.com");
        when(userService.generateUser(any(UserRequest.class))).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
        verify(userService, times(1)).generateUser(any(UserRequest.class));
    }

    @Test
    public void givenInvalidUserRequest_whenCreateUser_thenThrowsBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername(null);
        request.setEmail("invalid-email");
        request.setPassword("pw");
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            userController.createUser(request, bindingResult);
        });
        assertEquals("userRequest", thrown.getMessage());
        verify(userService, times(0)).generateUser(any(UserRequest.class));
    }
}
