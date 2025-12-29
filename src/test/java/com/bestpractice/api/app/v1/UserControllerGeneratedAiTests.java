package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserControllerGeneratedAiTests {

    private UserService userService;
    private UserController userController;

    @Test
    void shouldCreateUserWithValidRequestAndReturnUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john_doe");
        request.setEmail("john@example.com");
        request.setPassword("securePassword123");

        UserResponse expectedResponse = new UserResponse("uuid-123", "john_doe", "john@example.com");

        userService = Mockito.mock(UserService.class);
        when(userService.generateUser(any(UserRequest.class))).thenReturn(expectedResponse);

        userController = new UserController(userService);

        // WHEN
        UserResponse response = userController.createUser(request, Mockito.mock(org.springframework.validation.BindingResult.class));

        // THEN
        assertThat(response.getId()).isEqualTo("uuid-123");
        assertThat(response.getUsername()).isEqualTo("john_doe");
        assertThat(response.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void shouldThrowBadRequestWhenValidationFails() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("invalid");
        request.setEmail("invalid-email");
        request.setPassword("weak");

        org.springframework.validation.BindingResult bindingResult = Mockito.mock(org.springframework.validation.BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN & THEN
        assertThatThrownBy(() -> userController.createUser(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessage("userRequest");
    }

    @Test
    void shouldReturnCreatedStatusWhenUserIsCreated() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("test_user");
        request.setEmail("test@example.com");
        request.setPassword("password123");

        // WHEN
        UserResponse response = userController.createUser(request, Mockito.mock(org.springframework.validation.BindingResult.class));

        // THEN
        assertThat(response.getId()).isNotNull();
        assertThat(response.getUsername()).isEqualTo("test_user");
        assertThat(response.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void shouldThrowBadRequestWhenRequestIsNull() {
        // GIVEN
        UserRequest nullRequest = null;

        org.springframework.validation.BindingResult bindingResult = Mockito.mock(org.springframework.validation.BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN & THEN
        assertThatThrownBy(() -> userController.createUser(nullRequest, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessage("userRequest");
    }
}
