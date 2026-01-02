package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
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


@ExtendWith(MockitoExtension.class)
class UserControllerGeneratedAiTests {

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
    void createUser_successful() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john_doe");
        request.setEmail("john@example.com");
        request.setPassword("secret");

        UserResponse expectedResponse = new UserResponse("123", "john_doe", "john@example.com");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getId()).isEqualTo("123");
        assertThat(actualResponse.getUsername()).isEqualTo("john_doe");
        assertThat(actualResponse.getEmail()).isEqualTo("john@example.com");
        verify(userService, times(1)).generateUser(request);
    }

    @Test
    void createUser_bindingErrors_throwsBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john_doe");
        request.setEmail("john@example.com");
        request.setPassword("secret");

        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("UserRequest");

        // WHEN & THEN
        assertThatThrownBy(() -> userController.createUser(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessageContaining("UserRequest");
        verify(userService, never()).generateUser(any());
    }

    @Test
    void createUser_bindingErrors_noServiceCall() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john_doe");
        request.setEmail("john@example.com");
        request.setPassword("secret");

        when(bindingResult.hasErrors()).thenReturn(true);

        // WHEN & THEN
        assertThatThrownBy(() -> userController.createUser(request, bindingResult))
                .isInstanceOf(BadRequest.class);
        verify(userService, never()).generateUser(any());
    }

    @Test
    void createUser_successful_serviceCalledOnce() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("alice");
        request.setEmail("alice@example.com");
        request.setPassword("password");

        UserResponse response = new UserResponse("456", "alice", "alice@example.com");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.generateUser(request)).thenReturn(response);

        // WHEN
        userController.createUser(request, bindingResult);

        // THEN
        verify(userService, times(1)).generateUser(request);
    }
}
