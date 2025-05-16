package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyAiGeneratedAiTests.class)
class UserServiceImplGeneratedAiTests {

    private UserServiceImpl userService;
    private UserPersistentRepository userRepository;
    private BCryptPasswordEncryptionComponent encryptionComponent;
    private User user;

    @BeforeEach
    void setUp() {
        userRepository = new UserPersistentRepository();
        encryptionComponent = new BCryptPasswordEncryptionComponent();
        user = new User();
    }

    @Test
    void getUserById_validId_returnsUser() {
        // GIVEN
        String id = "123";
        userRepository.findById(id);

        // WHEN
        User returnedUser = userService.getUserById(id);

        // THEN
        assertNotNull(returnedUser);
        assertEquals(id, returnedUser.getId());
    }

    @Test
    void getAuthenticatedUser_validCredentials_returnsUser() {
        // GIVEN
        String email = "test@example.com";
        String password = "testPassword";
        user.setEmail(email);
        user.setPassword(password);

        // WHEN
        User authenticatedUser = userService.getAuthenticatedUser(email, password);

        // THEN
        assertNotNull(authenticatedUser);
        assertEquals(email, authenticatedUser.getEmail());
        assertEquals(password, authenticatedUser.getPassword());
    }

    @Test
    void generateUser_validRequest_returnsUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        String id = "456";
        String encPw = "encodedPassword";
        request.setUsername("newUser");
        request.setPassword(encPw);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals("newUser", response.getUsername());
        assertEquals("test@example.com", response.getEmail());
    }

    @Test
    void generateUser_invalidRequest_throwsConflict() {
        // GIVEN
        UserRequest request = new UserRequest();
        String id = "789";
        String encPw = "encodedPassword";
        request.setUsername("newUser");
        request.setPassword(encPw);

        // WHEN
        // THEN
        assertThrows(Conflict.class, () -> userService.generateUser(request));
    }

    @Test
    void getUserByEmail_validEmail_returnsUser() {
        // GIVEN
        String email = "test@example.com";
        user.setEmail(email);

        // WHEN
        User userFromRepo = userService.getUserByEmail(email);

        // THEN
        assertNotNull(userFromRepo);
        assertEquals(email, userFromRepo.getEmail());
    }
}
