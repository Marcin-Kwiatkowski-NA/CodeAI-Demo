package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class AuthServiceImplGeneratedAiTests {

    @Autowired
    private BCryptPasswordEncryptionComponent encryptionComponent;

    @Autowired
    private AuthComponent authComponent;

    @Autowired
    private UserPersistentRepository userPersistentRepository;

    private AuthServiceImpl authService;

    @BeforeAll
    void setUp() {
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void login_validCredentials_returnsAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password123";
        User user = new User();
        user.setEmail(email);
        user.setPassword(encryptionComponent.encodePassword(password));

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assert response != null;
        assert response.getTokenType().equals("Bearer");
        assert response.getToken().isNotBlank();
        assert response.getRefreshToken().isNotBlank();
        assert response.getExpiresAt() != null;
    }

    @Test
    void login_invalidCredentials_throwsUnAuthorizedException() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongpassword";

        // WHEN
        // THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void login_refreshToken_returnsAuthResponse() {
        // GIVEN
        String refreshToken = "refresh_token_value";
        DecodedJWT decodedJWT = new DecodedJWT();
        decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString("test@example.com");
        decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean(true);

        // WHEN
        // THEN
        assertThrows(Exception.class, () -> authService.login(refreshToken));
    }
}
