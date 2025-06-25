package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;

@ExtendWith(UserServiceImplGeneratedAiTests.class)
class UserServiceImplGeneratedAiTests {

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        UserPersistentRepository userRepo = new UserPersistentRepository() {
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
                return null;
            }

            @Override
            public User insert(User user) {
                return null;
            }

            @Override
            public User replace(String id, User user) {
                return null;
            }

            @Override
            public boolean removeById(String id) {
                return false;
            }
        };
        BCryptPasswordEncryptionComponent encryptionComponent = new BCryptPasswordEncryptionComponent();
        userService = new UserServiceImpl(userRepo, encryptionComponent);
    }

    @Test
    void getUserById_validId_returnsUser() {
        User user = userService.getUserById("testId");
        assert user != null;
    }

    @Test
    void getAuthenticatedUser_validCredentials_returnsUser() {
        String email = "test@example.com";
        String password = "testPassword";
        User user = userService.getAuthenticatedUser(email, password);
        assert user != null;
    }

    @Test
    void getAuthenticatedUser_invalidCredentials_throwsUnAuthorized() {
        String email = "test@example.com";
        String password = "wrongPassword";
        UnAuthorized exception = assertThrows(
                () -> userService.getAuthenticatedUser(email, password)
        );
        assert exception.getClass().equals(UnAuthorized.class);
    }

    @Test
    void generateUser_validRequest_returnsUserResponse() {
        UserRequest request = new UserRequest();
        request.setUsername("testUser");
        request.setEmail("test@example.com");
        request.setPassword("testPassword");
        UserResponse response = userService.generateUser(request);
        assert response != null;
        assert response.getId().equals("testId");
        assert response.getUsername().equals("testUser");
        assert response.getEmail().equals("test@example.com");
    }

    @Test
    void generateUser_conflict_throwsConflict() {
        UserRequest request = new UserRequest();
        request.setUsername("testUser");
        request.setEmail("test@example.com");
        request.setPassword("testPassword");
        Conflict exception = assertThrows(
                () -> userService.generateUser(request)
        );
        assert exception.getClass().equals(Conflict.class);
    }

    @Test
    void generateUser_internalServerError_throwsInternalServerError() {
        UserRequest request = new UserRequest();
        request.setUsername("testUser");
        request.setEmail("test@example.com");
        request.setPassword("testPassword");
        InternalServerError exception = assertThrows(
                () -> userService.generateUser(request)
        );
        assert exception.getClass().equals(InternalServerError.class);
    }
}
