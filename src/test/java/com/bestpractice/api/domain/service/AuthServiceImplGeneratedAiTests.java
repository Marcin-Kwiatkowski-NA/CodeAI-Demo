package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthServiceImplTests {

    @Mock
    private UserPersistentRepository userPersistentRepository;

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    @InjectMocks
    private AuthServiceImpl authService;

    @Captor
    private ArgumentCaptor<String> emailCaptor;

    @Captor
    private ArgumentCaptor<String> passwordCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAuthenticateUserWithValidCredentials() {
        // GIVEN
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("hashed_password");

        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(encryptionComponent.verifyPassword(any(String.class), any(String.class))).thenReturn(true);

        // WHEN
        AuthResponse response = authService.login("test@example.com", "password123");

        // THEN
        assertThat(response.getTokenType()).isEqualTo("Bearer");
        assertThat(response.getToken()).isNotNull();
        assertThat(response.getExp()).isNotNull();
        assertThat(response.getRefreshToken()).isNotNull();
    }

    @Test
    void shouldThrowUnAuthorizedWhenUserNotFound() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login("test@example.com", "password123"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("User not found");
    }

    @Test
    void shouldThrowUnAuthorizedWhenPasswordInvalid() {
        // GIVEN
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("hashed_password");

        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(encryptionComponent.verifyPassword(any(String.class), any(String.class))).thenReturn(false);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login("test@example.com", "wrongpassword"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Invalid credentials");
    }

    @Test
    void shouldGenerateValidCredentialResponse() {
        // GIVEN
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("hashed_password");

        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(encryptionComponent.verifyPassword(any(String.class), any(String.class))).thenReturn(true);

        // WHEN
        Credential credential = authService.authenticate("test@example.com", "password123");

        // THEN
        assertThat(credential.getTokenType()).isEqualTo("Bearer");
        assertThat(credential.getToken()).isNotNull();
        assertThat(credential.getExp()).isNotNull();
        assertThat(credential.getRefreshToken()).isNotNull();
    }

    @Test
    void shouldThrowUnAuthorizedWhenEmailIsInvalid() {
        // GIVEN
        when(userPersistentRepository.findByEmail("invalid@example.com")).thenReturn(Optional.empty());

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login("invalid@example.com", "password123"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("User not found");
    }
}
