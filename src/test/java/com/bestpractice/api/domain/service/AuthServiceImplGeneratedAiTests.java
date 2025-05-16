package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplGeneratedAiTests {

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    @Mock
    private AuthComponent authComponent;

    @Mock
    private UserPersistentRepository userPersistentRepository;

    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void loginSuccess() {
        Mockito.when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(null);
        Mockito.when(encryptionComponent.matchedPassword("password", null)).thenReturn(true);
        Mockito.when(authComponent.generateJwt(null, null, false)).thenReturn(null);
        Mockito.when(authComponent.generateJwt(null, null, true)).thenReturn(null);

        AuthResponse response = authService.login("test@example.com", "password");

        Mockito.verify(userPersistentRepository, ArgumentMatchers.eq(userPersistentRepository)).findByEmail("test@example.com");
        Mockito.verify(encryptionComponent, ArgumentMatchers.eq(encryptionComponent)).matchedPassword("password", null);
        Mockito.verify(authComponent, ArgumentMatchers.eq(authComponent)).generateJwt(null, null, false);
        Mockito.verify(authComponent, ArgumentMatchers.eq(authComponent)).generateJwt(null, null, true);

        assert response != null;
        assert response.getTokenType().equals("Bearer");
        assert response.getToken().length() > 0;
        assert response.getExp().getTime() > 0;
    }
}
