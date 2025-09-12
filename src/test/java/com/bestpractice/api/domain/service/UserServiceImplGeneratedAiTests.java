package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;

class UserServiceImplGeneratedAiTests {
    private UserService userService;
    private UserPersistentRepository userRepository;
    private BCryptPasswordEncryptionComponent encryptionComponent;

    @BeforeEach
    void setUp() {
        userRepository = new UserPersistentRepository() {
            @Override
            public String newId() {
                return "testId";
            }

            @Override
            public User findByEmail(String email) {
                return null;
            }

            @Override
            public User findById(String id) {
                return new User("testId", "testUser", "test@example.com", "testPassword");
            }

            @Override
            public User insert(User user) {
                return user;
            }

            @Override
            public User replace(String id, User user) {
                return user;
            }

            @Override
            public boolean removeById(String id) {
                return false;
            }
        };
        encryptionComponent = new BCryptPasswordEncryptionComponent() {
            @Override
            public String encodePassword(String password) {
                return "encodedPassword";
            }

            @Override
            public boolean matchedPassword(String password, String rawPw) {
                return rawPw.equals("testPassword");
            }
        };
        userService = new UserServiceImpl(userRepository, encryptionComponent);
    }

    @Test
    void getUserById_returnsUser() {
        User user = userService.getUserById("testId");
        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void getAuthenticatedUser_returnsUser_userExists() {
        User user = userService.getAuthenticatedUser("test@example.com", "testPassword");
        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void getAuthenticatedUser_throwsUnAuthorized_userDoesNotExist() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.getAuthenticatedUser("wrong@example.com", "testPassword");
        });
        assertEquals(UnAuthorized.class, exception.getTarget().getClass());
    }

    @Test
    void generateUser_returnsUser_userCreatedSuccessfully() {
        UserRequest request = new UserRequest() {
            @Override
            public User convert(String id, String password) {
                return new User(id, "newUser", "new@example.com", password);
            }
        };
        UserResponse response = userService.generateUser(request);
        assertNotNull(response);
        assertEquals("newUser", response.getUsername());
        assertEquals("new@example.com", response.getEmail());
    }

    @Test
    void generateUser_throwsConflict_duplicateEmail() {
        UserRequest request = new UserRequest() {
            @Override
            public User convert(String id, String password) {
                throw new Conflict("Email already exists");
            }
        };
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.generateUser(request);
        });
        assertEquals(Conflict.class, exception.getTarget().getClass());
    }
}
