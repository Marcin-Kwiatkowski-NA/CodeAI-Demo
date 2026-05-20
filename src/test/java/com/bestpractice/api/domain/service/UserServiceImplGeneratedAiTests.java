package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.clearInvocations;
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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.clearInvocations;

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
        MockitoAnnotations.openMocks(this);
        user = new User("1", "testUser", "test@example.com", "encodedPw");
        clearInvocations(userRepository, encryptionComponent);
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
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser("notfound@example.com", "rawPw"))
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
        UserRequest request = new UserRequest();
        request.setUsername("newUser");
        request.setEmail("new@example.com");
        request.setPassword("plainPw");

        when(encryptionComponent.encodePassword("plainPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("2");
        when(userRepository.insert(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo("2");
        assertThat(response.getUsername()).isEqualTo("newUser");
        verify(userRepository, times(1)).insert(any(User.class));
    }

    @Test
    void generateUser_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("conflictUser");
        request.setEmail("conflict@example.com");
        request.setPassword("plainPw");

        when(encryptionComponent.encodePassword("plainPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("3");
        when(userRepository.insert(any(User.class))).thenThrow(new Conflict());

        // WHEN / THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void generateUser_shouldThrowInternalServerError_whenRepositoryThrowsUnexpectedException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("errorUser");
        request.setEmail("error@example.com");
        request.setPassword("plainPw");

        when(encryptionComponent.encodePassword("plainPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("4");
        when(userRepository.insert(any(User.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN / THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(InternalServerError.class);
    }
}