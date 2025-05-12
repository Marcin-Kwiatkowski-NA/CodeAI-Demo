package com.bestpractice.api.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtensions.class)
class AuthServiceImplGeneratedAiTests {

    private AuthServiceImpl authService;
    private UserPersistentRepository userPersistentRepository;
    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;

    @BeforeEach
    void setUp() {
        // Initialize dependencies for each test
        userPersistentRepository = new UserPersistentRepository() {
            @Override
            public String newId() {
                return "testId";
            }

            @Override
            public User findByEmail(String email) {
                return new User("testId", "testUser", "test@example.com", "testPassword");
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
                return true;
            }
        };
        encryptionComponent = new BCryptPasswordEncryptionComponent();
        authComponent = new AuthComponent();
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @org.junit.jupiter.api.Test
    void login_validCredentials_returnsAuthResponse() {
        // GIVEN: Valid user credentials
        String email = "test@example.com";
        String password = "testPassword";

        // WHEN: User logs in with valid credentials
        AuthResponse response = authService.login(email, password);

        // THEN: The login should succeed and return an AuthResponse
        assertNotNull(response);
        assertEquals("TokenType", response.getTokenType());
        assertEquals("token", response.getToken());
        assertEquals("refreshToken", response.getRefreshToken());
        assertEquals(0, response.getExp());
    }

    @org.junit.jupiter.api.Test
    void login_invalidCredentials_throwsUnAuthorizedException() {
        // GIVEN: Invalid user credentials
        String email = "test@example.com";
        String password = "wrongPassword";

        // WHEN: User attempts to log in with invalid credentials
        UnAuthorized exception = assertThrows(UnAuthorized.class, () -> {
            authService.login(email, password);
        });

        // THEN: An UnAuthorized exception should be thrown
        assertEquals("Email or password is invalid", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void login_refreshToken_validToken_returnsAuthResponse() {
        // GIVEN: Valid user credentials and refresh token
        String refreshToken = "validRefreshToken";

        // WHEN: User logs in with a valid refresh token
        AuthResponse response = authService.login(refreshToken);

        // THEN: The login should succeed and return an AuthResponse
        assertNotNull(response);
        assertEquals("TokenType", response.getTokenType());
        assertEquals("token", response.getToken());
        assertEquals("refreshToken", response.getRefreshToken());
        assertEquals(0, response.getExp());
    }

    @org.junit.jupiter.api.Test
    void login_refreshToken_invalidToken_throwsUnAuthorizedException() {
        // GIVEN: Invalid refresh token
        String refreshToken = "invalidRefreshToken";

        // WHEN: User attempts to log in with an invalid refresh token
        UnAuthorized exception = assertThrows(UnAuthorized.class, () -> {
            authService.login(refreshToken);
        });

        // THEN: An UnAuthorized exception should be thrown
        assertEquals("Token invalid", exception.getMessage());
    }
}
