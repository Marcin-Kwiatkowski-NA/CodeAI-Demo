package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
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

    @BeforeEach
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    public void testCreateUser_Success() {
        // GIVEN: a valid UserRequest and no binding errors
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        UserResponse expectedResponse = new UserResponse("1", "testuser", "test@example.com");
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN: createUser is called
        UserResponse actualResponse = userController.createUser(request, bindingResult);

        // THEN: the returned UserResponse matches the expected one
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
    }

    @Test
    public void testCreateUser_BadRequestDueToBindingErrors() {
        // GIVEN: a UserRequest with binding errors
        UserRequest request = new UserRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN & THEN: createUser throws BadRequest
        BadRequest thrown = assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
        assertEquals("userRequest", thrown.getMessage());
    }

    @Test
    public void testCreateUser_BadRequestDueToNullObjectName() {
        // GIVEN: a UserRequest with binding errors and null object name
        UserRequest request = new UserRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn(null);

        // WHEN & THEN: createUser throws BadRequest with null message
        BadRequest thrown = assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
        assertEquals(null, thrown.getMessage());
    }

    @Test
    public void testCreateUser_ServiceThrowsRuntimeException() {
        // GIVEN: a valid UserRequest and no binding errors, but service throws exception
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.generateUser(request)).thenThrow(new RuntimeException("Service failure"));

        // WHEN & THEN: createUser throws RuntimeException
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userController.createUser(request, bindingResult));
        assertEquals("Service failure", thrown.getMessage());
    }

    @Test
    public void testCreateUser_BadRequestDueToEmptyObjectName() {
        // GIVEN: a UserRequest with binding errors and empty object name
        UserRequest request = new UserRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("");

        // WHEN & THEN: createUser throws BadRequest with empty message
        BadRequest thrown = assertThrows(BadRequest.class, () -> userController.createUser(request, bindingResult));
        assertEquals("", thrown.getMessage());
    }
}
