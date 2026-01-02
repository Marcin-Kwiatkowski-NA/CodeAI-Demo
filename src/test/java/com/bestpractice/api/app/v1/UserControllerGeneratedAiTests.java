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
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


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
    void createUser_whenValidRequest_shouldReturnUserResponseAndCallService() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("alice");
        request.setEmail("alice@example.com");
        request.setPassword("secret");
        UserResponse expectedResponse = new UserResponse("123", "alice", "alice@example.com");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertThat(actualResponse).isEqualToComparingFieldByField(expectedResponse);
        verify(userService, times(1)).generateUser(request);
    }

    @Test
    void createUser_whenBindingResultHasErrors_shouldThrowBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("UserRequest");

        // WHEN & THEN
        assertThatThrownBy(() -> userController.createUser(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessageContaining("UserRequest");
        verify(userService, never()).generateUser(any());
    }

    @Test
    void createUser_whenServiceThrowsException_shouldPropagateRuntimeException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("bob");
        request.setEmail("bob@example.com");
        request.setPassword("pass");
        when(bindingResult.hasErrors()).thenReturn(false);
        RuntimeException serviceException = new RuntimeException("service failure");
        when(userService.generateUser(request)).thenThrow(serviceException);

        // WHEN & THEN
        assertThatThrownBy(() -> userController.createUser(request, bindingResult))
                .isSameAs(serviceException);
        verify(userService, times(1)).generateUser(request);
    }

    @Test
    void createUser_whenBindingResultHasNoErrors_andServiceReturnsNull_shouldReturnNull() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("charlie");
        request.setEmail("charlie@example.com");
        request.setPassword("pass");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.generateUser(request)).thenReturn(null);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertThat(actualResponse).isNull();
        verify(userService, times(1)).generateUser(request);
    }
}
