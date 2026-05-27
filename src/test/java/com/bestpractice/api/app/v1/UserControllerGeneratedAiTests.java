package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerGeneratedAiTests {

    @Mock
    private UserService userService;

    @Mock
    private BindingResult bindingResult;

    private UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController(userService);
    }

    @Test
    void createUser_ShouldReturnUserResponse_WhenNoValidationErrors() {
        // GIVEN - setup valid request and mock service behavior
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("securePassword");
        UserResponse expectedResponse = new UserResponse("1", "testuser", "test@example.com");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN - invoke controller method
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN - verify expected outcome
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getId()).isEqualTo("1");
        assertThat(actualResponse.getUsername()).isEqualTo("testuser");
        assertThat(actualResponse.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void createUser_ShouldThrowBadRequest_WhenValidationErrorsExist() {
        // GIVEN - setup invalid request with validation errors
        UserRequest request = new UserRequest();
        request.setUsername(null);
        request.setEmail("invalid-email");
        request.setPassword("pw");
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN - invoke controller method and expect exception
        // THEN - verify exception is thrown
        assertThatThrownBy(() -> userController.createUser(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessageContaining("userRequest");
    }
}
