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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class UserControllerGeneratedAiTests {

    @Mock
    private UserService userService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenValidUserRequest_whenCreateUser_thenReturnUserResponse() {
        // GIVEN
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testuser");
        userRequest.setEmail("testuser@example.com");
        userRequest.setPassword("password123");

        UserResponse expectedResponse = new UserResponse("1", "testuser", "testuser@example.com");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.generateUser(userRequest)).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(userRequest, bindingResult);

        // THEN
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getId()).isEqualTo(expectedResponse.getId());
        assertThat(actualResponse.getUsername()).isEqualTo(expectedResponse.getUsername());
        assertThat(actualResponse.getEmail()).isEqualTo(expectedResponse.getEmail());
        verify(userService, times(1)).generateUser(userRequest);
    }

    @Test
    void givenInvalidUserRequest_whenCreateUser_thenThrowBadRequestException() {
        // GIVEN
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testuser");
        userRequest.setEmail("invalid-email");
        userRequest.setPassword("password123");

        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("UserRequest");

        // WHEN & THEN
        assertThatThrownBy(() -> userController.createUser(userRequest, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessage("UserRequest");
        verify(bindingResult, times(1)).hasErrors();
        verify(bindingResult, times(1)).getObjectName();
        verifyNoInteractions(userService);
    }
}