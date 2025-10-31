package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        UserResponse expectedResponse = new UserResponse("1", "testuser", "test@example.com");
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
    }

    @Test
    public void givenInvalidUserRequest_whenCreateUser_thenThrowsBadRequestWithMessage() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername(null);
        request.setEmail("invalid-email");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
        assertEquals("userRequest", thrown.getMessage());
    }

    @Test
    public void givenBindingResultThrowsException_whenCreateUser_thenPropagatesException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("user");
        request.setEmail("user@example.com");
        request.setPassword("pass");
        when(bindingResult.hasErrors()).thenThrow(new RuntimeException("Unexpected error"));

        // WHEN & THEN
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userController.createUser(request, bindingResult));
        assertEquals("Unexpected error", thrown.getMessage());
    }

    @Test
    public void givenUserServiceThrowsException_whenCreateUser_thenPropagatesException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("user");
        request.setEmail("user@example.com");
        request.setPassword("pass");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.generateUser(request)).thenThrow(new RuntimeException("Service failure"));

        // WHEN & THEN
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userController.createUser(request, bindingResult));
        assertEquals("Service failure", thrown.getMessage());
    }

    @Test
    public void givenNullUserServiceResponse_whenCreateUser_thenReturnsNull() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("user");
        request.setEmail("user@example.com");
        request.setPassword("pass");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.generateUser(request)).thenReturn(null);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertEquals(null, actualResponse);
    }

    @Test
    public void givenBindingResultHasErrorsWithoutObjectName_whenCreateUser_thenThrowsBadRequestWithNullMessage() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("user");
        request.setEmail("user@example.com");
        request.setPassword("pass");
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn(null);

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
        assertEquals(null, thrown.getMessage());
    }
}
