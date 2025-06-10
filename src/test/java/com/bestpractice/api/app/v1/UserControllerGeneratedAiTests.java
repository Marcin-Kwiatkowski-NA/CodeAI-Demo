package com.bestpractice.api.app.v1;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

@ExtendWith(MockitoExtension.class)
public class UserControllerGeneratedAiTests {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        // Reset mocks before each test
    }

    @Test
    public void createUser_Success() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password");

        UserResponse expectedResponse = new UserResponse("1", "testuser", "test@example.com");

        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.generateUser(request)).thenReturn(expectedResponse);

        // WHEN
        UserResponse response = userController.createUser(request, bindingResult);

        // THEN
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getUsername(), response.getUsername());
        assertEquals(expectedResponse.getEmail(), response.getEmail());
    }

    @Test
    public void createUser_BadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("userRequest");

        // WHEN & THEN
        BadRequest exception = org.junit.jupiter.api.Assertions.assertThrows(BadRequest.class, () -> {
            userController.createUser(request, bindingResult);
        });

        assertEquals("userRequest", exception.getMessage());
    }
}
