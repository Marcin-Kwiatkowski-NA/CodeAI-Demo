package com.bestpractice.api.domain.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplGeneratedAiTests {

    @Mock
    private UserPersistentRepository userRepository;

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    @Mock
    private UserRequest userRequest;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        reset(userRepository, encryptionComponent, userRequest);
    }

    @Test
    void getUserById_returnsUserWhenFound() {
        // GIVEN
        User mockUser = mock(User.class);
        when(userRepository.findById("123")).thenReturn(mockUser);

        // WHEN
        User result = userService.getUserById("123");

        // THEN
        assertThat(result).isSameAs(mockUser);
        verify(userRepository).findById("123");
    }

    @Test
    void getUserById_returnsNullWhenNotFound() {
        // GIVEN
        when(userRepository.findById("999")).thenReturn(null);

        // WHEN
        User result = userService.getUserById("999");

        // THEN
        assertThat(result).isNull();
        verify(userRepository).findById("999");
    }

    @Test
    void getAuthenticatedUser_returnsUserWhenCredentialsValid() {
        // GIVEN
        User mockUser = mock(User.class);
        when(userRepository.findByEmail("user@example.com")).thenReturn(mockUser);
        when(encryptionComponent.matchedPassword(mockUser.getPassword(), "rawPassword")).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser("user@example.com", "rawPassword");

        // THEN
        assertThat(result).isSameAs(mockUser);
        verify(userRepository).findByEmail("user@example.com");
        verify(encryptionComponent).matchedPassword(mockUser.getPassword(), "rawPassword");
    }

    @Test
    void getAuthenticatedUser_throwsUnAuthorizedWhenUserNotFound() {
        // GIVEN
        when(userRepository.findByEmail("missing@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser("missing@example.com", "anyPassword"))
                .isInstanceOf(UnAuthorized.class);
        verify(userRepository).findByEmail("missing@example.com");
    }

    @Test
    void getAuthenticatedUser_throwsUnAuthorizedWhenPasswordDoesNotMatch() {
        // GIVEN
        User mockUser = mock(User.class);
        when(userRepository.findByEmail("user@example.com")).thenReturn(mockUser);
        when(encryptionComponent.matchedPassword(mockUser.getPassword(), "wrongPassword")).thenReturn(false);

        // WHEN & THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser("user@example.com", "wrongPassword"))
                .isInstanceOf(UnAuthorized.class);
        verify(userRepository).findByEmail("user@example.com");
        verify(encryptionComponent).matchedPassword(mockUser.getPassword(), "wrongPassword");
    }

    @Test
    void generateUser_successfullyCreatesUserAndReturnsResponse() {
        // GIVEN
        String rawPassword = "plainPass";
        String encodedPassword = "encodedPass";
        String newId = "new-uid";

        User mockUser = mock(User.class);
        when(mockUser.getId()).thenReturn(newId);
        when(mockUser.getUsername()).thenReturn("testUser");
        when(mockUser.getEmail()).thenReturn("test@example.com");
        when(mockUser.getPassword()).thenReturn(encodedPassword);

        when(userRequest.getPassword()).thenReturn(rawPassword);
        when(encryptionComponent.encodePassword(rawPassword)).thenReturn(encodedPassword);
        when(userRepository.newId()).thenReturn(newId);
        when(userRequest.convert(newId, encodedPassword)).thenReturn(mockUser);
        when(userRepository.insert(mockUser)).thenReturn(mockUser);

        // WHEN
        UserResponse response = userService.generateUser(userRequest);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(newId);
        assertThat(response.getUsername()).isEqualTo("testUser");
        assertThat(response.getEmail()).isEqualTo("test@example.com");

        verify(encryptionComponent).encodePassword(rawPassword);
        verify(userRepository).newId();
        verify(userRequest).convert(newId, encodedPassword);
        verify(userRepository).insert(mockUser);
    }

    @Test
    void generateUser_throwsConflictWhenInsertFailsDueToConflict() {
        // GIVEN
        when(userRequest.getPassword()).thenReturn("anyPass");
        when(encryptionComponent.encodePassword(anyString())).thenReturn("encPass");
        when(userRepository.newId()).thenReturn("uid");
        when(userRequest.convert(anyString(), anyString())).thenReturn(mock(User.class));
        when(userRepository.insert(any(User.class))).thenThrow(new Conflict("duplicate key"));

        // WHEN & THEN
        assertThatThrownBy(() -> userService.generateUser(userRequest))
                .isInstanceOf(Conflict.class);
        verify(userRepository).insert(any(User.class));
    }

    @Test
    void generateUser_throwsInternalServerErrorWhenInsertFailsUnexpectedly() {
        // GIVEN
        when(userRequest.getPassword()).thenReturn("anyPass");
        when(encryptionComponent.encodePassword(anyString())).thenReturn("encPass");
        when(userRepository.newId()).thenReturn("uid");
        when(userRequest.convert(anyString(), anyString())).thenReturn(mock(User.class));
        when(userRepository.insert(any(User.class))).thenThrow(new RuntimeException("db error"));

        // WHEN & THEN
        assertThatThrownBy(() -> userService.generateUser(userRequest))
                .isInstanceOf(InternalServerError.class);
        verify(userRepository).insert(any(User.class));
    }
}
