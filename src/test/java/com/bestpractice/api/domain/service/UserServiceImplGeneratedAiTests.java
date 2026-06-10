package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplGeneratedAiTests {

    @Mock
    private UserPersistentRepository userRepository;

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("1", "testUser", "test@example.com", "encodedPw");
    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        // GIVEN
        when(userRepository.findById("1")).thenReturn(user);

        // WHEN
        User result = userService.getUserById("1");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
        verify(userRepository, times(1)).findById("1");
    }

    @Test
    void getAuthenticatedUser_shouldReturnUser_whenCredentialsAreValid() {
        // GIVEN
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "rawPw")).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser("test@example.com", "rawPw");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("test@example.com");
        verify(encryptionComponent, times(1)).matchedPassword("encodedPw", "rawPw");
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenUserNotFound() {
        // GIVEN
        when(userRepository.findByEmail("missing@example.com")).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser("missing@example.com", "pw"))
                .isInstanceOf(UnAuthorized.class);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenPasswordDoesNotMatch() {
        // GIVEN
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "wrongPw")).thenReturn(false);

        // WHEN / THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser("test@example.com", "wrongPw"))
                .isInstanceOf(UnAuthorized.class);
    }

    @Test
    void generateUser_shouldReturnUserResponse_whenUserIsCreatedSuccessfully() {
        // GIVEN
        UserRequest request = mock(UserRequest.class);
        when(request.getPassword()).thenReturn("rawPw");
        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        User newUser = new User("1", "testUser", "test@example.com", "encodedPw");
        when(request.convert("1", "encodedPw")).thenReturn(newUser);
        when(userRepository.insert(newUser)).thenReturn(newUser);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo("1");
        assertThat(response.getEmail()).isEqualTo("test@example.com");
        verify(userRepository, times(1)).insert(newUser);
    }

    @Test
    void generateUser_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        UserRequest request = mock(UserRequest.class);
        when(request.getPassword()).thenReturn("rawPw");
        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        User newUser = new User("1", "testUser", "test@example.com", "encodedPw");
        when(request.convert("1", "encodedPw")).thenReturn(newUser);
        when(userRepository.insert(newUser)).thenThrow(new Conflict());

        // WHEN / THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void generateUser_shouldThrowInternalServerError_whenRepositoryThrowsUnexpectedException() {
        // GIVEN
        UserRequest request = mock(UserRequest.class);
        when(request.getPassword()).thenReturn("rawPw");
        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        User newUser = new User("1", "testUser", "test@example.com", "encodedPw");
        when(request.convert("1", "encodedPw")).thenReturn(newUser);
        when(userRepository.insert(newUser)).thenThrow(new RuntimeException("DB error"));

        // WHEN / THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(InternalServerError.class);
    }
}
