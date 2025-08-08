package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerGeneratedAiTests {

    private UserController userController;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        this.userController = new UserController(userService);
    }

    @Test
    void createUser_validRequest_returnsUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");

        // WHEN
        UserResponse response = userController.createUser(request, null);

        // THEN
        assertNotNull(response);
        assertEquals("testuser", response.getUsername());
        assertEquals("test@example.com", response.getEmail());
    }

    @Test
    void createUser_invalidRequest_throwsBadRequest() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername(null);
        request.setEmail(null);
        request.setPassword(null);

        // WHEN
        assertThrows(BadRequest.class, () -> userController.createUser(request, null));
    }
}
