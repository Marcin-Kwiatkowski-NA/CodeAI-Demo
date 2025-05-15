package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplGeneratedAiTests {

    private AuthServiceImpl authService;
    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;

    @BeforeEach
    void setUp() {
        encryptionComponent = Mockito.createMock(BCryptPasswordEncryptionComponent.class);
        authComponent = Mockito.createMock(AuthComponent.class);
        userPersistentRepository = Mockito.createMock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @org.junit.jupiter.api.Test
    void login_validCredentials_returnsAuthResponse() {
        // GIVEN
        User user = Mockito.mock(User.class);
        Credential token = Mockito.mock(Credential.class);
        Credential refreshToken = Mockito.mock(Credential.class);

        Mockito.when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        Mockito.when(encryptionComponent.matchedPassword("password", user.getPassword())).thenReturn(true);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login("test@example.com", "password");

        // THEN
        assert response != null;
        assert response.getTokenType().equals("Bearer");
        assert response.getToken().isPresent();
        assert response.getRefreshToken().isPresent();
        assert response.getExp().isPresent();
    }

    @org.junit.jupiter.api.Test
    void login_invalidCredentials_throwsUnAuthorized() {
        // GIVEN
        Mockito.when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(null);
        Mockito.when(encryptionComponent.matchedPassword("password", user.getPassword())).thenReturn(true);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        // THEN
        assertThrows(new UnAuthorized(), () -> {
            authService.login("test@example.com", "password");
        });
    }

    @org.junit.jupiter.api.Test
    void login_invalidCredentials_throwsUnAuthorized2() {
        // GIVEN
        Mockito.when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(null);
        Mockito.when(encryptionComponent.matchedPassword("password", user.getPassword())).thenReturn(false);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        // THEN
        assertThrows(new UnAuthorized(), () -> {
            authService.login("test@example.com", "password");
        });
    }
}
