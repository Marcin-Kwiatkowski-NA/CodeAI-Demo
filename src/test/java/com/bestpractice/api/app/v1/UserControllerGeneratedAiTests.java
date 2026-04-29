package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        if (userController == null) {
            userController = new UserController(userService);
        }
        Mockito.reset(userService, bindingResult);
    }

    @Test
    void createUser_ShouldReturnUserResponse_WhenNoValidationErrors() {
        // GIVEN - setup valid request and mock service response
        UserRequest request = new UserRequest();
        request.setUsername("john_doe");
        request.setEmail("john@example.com");
        request.setPassword("securePassword");
        UserResponse expectedResponse = new UserResponse("1", "john_doe", "john@example.com");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.generateUser(Mockito.any(UserRequest.class))).thenReturn(expectedResponse);

        // WHEN - invoke controller method
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN - verify expected outcome
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getId()).isEqualTo("1");
        assertThat(actualResponse.getUsername()).isEqualTo("john_doe");
        assertThat(actualResponse.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void createUser_ShouldThrowBadRequest_WhenValidationErrorsExist() {
        // GIVEN - setup invalid request with validation errors
        UserRequest request = new UserRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN - invoke controller method and expect exception
        // THEN - verify exception is thrown
        assertThatThrownBy(() -> userController.createUser(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessage("userRequest");
    }
}
