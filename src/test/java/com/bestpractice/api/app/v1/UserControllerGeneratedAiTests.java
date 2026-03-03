package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
class UserControllerGeneratedAiTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        reset(userService);
    }

    @Test
    void testCreateUserSuccess() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john_doe");
        request.setEmail("john@example.com");
        request.setPassword("securePassword");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        UserResponse expectedResponse = new UserResponse("123", "john_doe", "john@example.com");
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
    void testCreateUserBadRequestWhenBindingResultHasErrors() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john_doe");
        request.setEmail("invalid-email");
        request.setPassword("securePassword");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        // WHEN / THEN
        assertThatThrownBy(() -> userController.createUser(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessageContaining(bindingResult.getObjectName());
        verify(userService, never()).generateUser(any());
    }

    @Test
    void testCreateUserServiceThrowsRuntimeException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john_doe");
        request.setEmail("john@example.com");
        request.setPassword("securePassword");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        RuntimeException runtimeException = new RuntimeException("Service failure");
        when(userService.generateUser(request)).thenThrow(runtimeException);

        // WHEN / THEN
        assertThatThrownBy(() -> userController.createUser(request, bindingResult))
                .isSameAs(runtimeException);
        verify(userService, times(1)).generateUser(request);
    }
}
