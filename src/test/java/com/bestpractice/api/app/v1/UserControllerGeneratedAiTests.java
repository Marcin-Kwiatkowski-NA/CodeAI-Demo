package com.bestpractice.api.app.v1;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.springframework.validation.BindingResult;

@ExtendWith(com.bestpractice.api.app.v1.UserControllerGeneratedAiTests.class)
class UserControllerGeneratedAiTests {

    private UserController userController;
    private UserService userService;
    private UserRequest userRequest;
    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        userRequest = new UserRequest();
        userResponse = new UserResponse("", "", "");
        userController = new UserController(userService);
    }

    @Test
    void createUser_validRequest_returnsUserResponse() {
        // GIVEN: A valid UserRequest is provided.
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password123");

        // WHEN: The createUser method is called.
        UserResponse result = userController.createUser(userRequest, new BindingResult());

        // THEN: A UserResponse object is returned.
        assert result != null;
        assert result.getId().equals("testUser");
        assert result.getUsername().equals("testUser");
        assert result.getEmail().equals("test@example.com");
    }

    @Test
    void createUser_invalidRequest_throwsBadRequest() {
        // GIVEN: An invalid UserRequest is provided.
        userRequest.setUsername("");
        userRequest.setEmail("");
        userRequest.setPassword("");

        // WHEN: The createUser method is called.
        assertThrows(BadRequest.class, () -> userController.createUser(userRequest, new BindingResult()));
    }
}