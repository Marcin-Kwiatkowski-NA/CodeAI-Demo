package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

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

@ExtendWith(MyAnnotations.class)
class UserServiceImplGeneratedAiTests {

    private UserServiceImpl userService;
    private UserPersistentRepository userRepository;
    private BCryptPasswordEncryptionComponent encryptionComponent;
    private User user;

    @BeforeEach
    void setUp() {
        userRepository = new UserPersistentRepository() {
            @Override
            public void insert(User user) {
                // Do nothing for testing purposes
            }

            @Override
            public User findByEmail(String email) {
                return new User("1", "test", "test@example.com", "password");
            }

            @Override
            public User newId() {
                return new User("1");
            }
        };
        encryptionComponent = new BCryptPasswordEncryptionComponent() {
            @Override
            public boolean matchedPassword(String password, String rawPw) {
                return rawPw.equals("password");
            }

            @Override
            public String encodePassword(String password) {
                return "encoded_password";
            }
        };
        userService = new UserServiceImpl(userRepository, encryptionComponent);
    }

    @Test
    void getUserById_validId_returnsUser() {
        // GIVEN: A valid user ID
        String id = "1";

        // WHEN: The getUserById method is called
        User returnedUser = userService.getUserById(id);

        // THEN: The returned user is the expected user
        assertNotNull(returnedUser);
        assertEquals("1", returnedUser.getId());
        assertEquals("test", returnedUser.getUsername());
        assertEquals("test@example.com", returnedUser.getEmail());
        assertEquals("password", returnedUser.getPassword());
    }

    @Test
    void getAuthenticatedUser_validCredentials_returnsUser() {
        // GIVEN: Valid email and password
        String email = "test@example.com";
        String rawPw = "password";

        // WHEN: The getAuthenticatedUser method is called
        User returnedUser = userService.getAuthenticatedUser(email, rawPw);

        // THEN: The returned user is the expected user
        assertNotNull(returnedUser);
        assertEquals("1", returnedUser.getId());
        assertEquals("test", returnedUser.getUsername());
        assertEquals("test@example.com", returnedUser.getEmail());
        assertEquals("password", returnedUser.getPassword());
    }

    @Test
    void generateUser_validRequest_insertsUserAndReturnsResponse() {
        // GIVEN: A valid UserRequest
        UserRequest request = new UserRequest();
        request.setUsername("newuser");
        request.setEmail("newuser@example.com");
        request.setPassword("newpassword");

        // WHEN: The generateUser method is called
        UserResponse response = userService.generateUser(request);

        // THEN: The user is inserted and the response contains the correct data
        assertNotNull(response);
        assertEquals("1", response.getId());
        assertEquals("newuser", response.getUsername());
        assertEquals("newuser@example.com", response.getEmail());
        assertEquals("encoded_password", response.getPassword());
    }
}
