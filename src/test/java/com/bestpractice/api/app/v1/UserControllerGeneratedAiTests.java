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
import org.mockito.Mock;
import org.mockito.Mockito;
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
    void testCreateUser_WhenBindingResultHasErrors_ThrowsBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john");
        request.setEmail("john@example.com");
        request.setPassword("secret");

        when(bindingResult.hasErrors()).thenReturn(true);

        // WHEN & THEN
        assertThatThrownBy(() -> userController.createUser(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessageContaining(bindingResult.getObjectName());

        verifyNoInteractions(userService);
    }

    @Test
    void testCreateUser_WhenNoErrors_ReturnsUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("alice");
        request.setEmail("alice@example.com");
        request.setPassword("password");

        UserResponse expectedResponse = new UserResponse("123", "alice", "alice@example.com");

        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getId()).isEqualTo("123");
        assertThat(actualResponse.getUsername()).isEqualTo("alice");
        assertThat(actualResponse.getEmail()).isEqualTo("alice@example.com");

        verify(userService, times(1)).generateUser(request);
    }

    @Test
    void testCreateUser_ServiceCalledWithCorrectRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("bob");
        request.setEmail("bob@example.com");
        request.setPassword("pass");

        UserResponse dummyResponse = new UserResponse("456", "bob", "bob@example.com");

        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.generateUser(any(UserRequest.class))).thenReturn(dummyResponse);

        // WHEN
        userController.createUser(request, bindingResult);

        // THEN
        verify(userService, times(1)).generateUser(request);
    }
}
