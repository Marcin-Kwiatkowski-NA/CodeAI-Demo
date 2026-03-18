package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    private UserController userController;

    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        userController = new UserController(userService);
        userRequest = new UserRequest();
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("securePassword");
    }

    @Test
    void createUser_ShouldReturnUserResponse_WhenNoValidationErrors() {
        // GIVEN
        when(bindingResult.hasErrors()).thenReturn(false);
        UserResponse expectedResponse = new UserResponse("1", "testUser", "test@example.com");
        when(userService.generateUser(Mockito.any(UserRequest.class))).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(userRequest, bindingResult);

        // THEN
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getId()).isEqualTo("1");
        assertThat(actualResponse.getUsername()).isEqualTo("testUser");
        assertThat(actualResponse.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void createUser_ShouldThrowBadRequest_WhenValidationErrorsExist() {
        // GIVEN
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN / THEN
        assertThatThrownBy(() -> userController.createUser(userRequest, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessage("userRequest");
    }
}
