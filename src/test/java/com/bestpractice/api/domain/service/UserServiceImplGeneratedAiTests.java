package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplGeneratedAiTests {

    @Mock
    private UserPersistentRepository userRepository;

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        Mockito.reset(userRepository, encryptionComponent);
        userService = new UserServiceImpl(userRepository, encryptionComponent);
    }

    @Test
    void testGetUserById_ShouldReturnUser_WhenUserExists() {
        // GIVEN
        User expectedUser = new User("1", "testUser", "test@example.com", "encodedPw");
        when(userRepository.findById("1")).thenReturn(expectedUser);

        // WHEN
        User result = userService.getUserById("1");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getUsername()).isEqualTo("testUser");
    }

    @Test
    void testGetAuthenticatedUser_ShouldReturnUser_WhenCredentialsAreValid() {
        // GIVEN (Security-sensitive: password validation)
        String email = "test@example.com";
        String rawPw = "plainPw";
        User user = new User("1", "testUser", email, "encodedPw");
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", rawPw)).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser(email, rawPw);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);
    }

    @Test
    void testGetAuthenticatedUser_ShouldThrowUnAuthorized_WhenUserNotFound() {
        // GIVEN (Security-sensitive: authentication failure)
        String email = "notfound@example.com";
        String rawPw = "plainPw";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser(email, rawPw))
                .isInstanceOf(UnAuthorized.class);
    }

    @Test
    void testGetAuthenticatedUser_ShouldThrowUnAuthorized_WhenPasswordDoesNotMatch() {
        // GIVEN (Security-sensitive: password mismatch)
        String email = "test@example.com";
        String rawPw = "wrongPw";
        User user = new User("1", "testUser", email, "encodedPw");
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", rawPw)).thenReturn(false);

        // WHEN / THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser(email, rawPw))
                .isInstanceOf(UnAuthorized.class);
    }

    @Test
    void testGenerateUser_ShouldReturnUserResponse_WhenInsertSucceeds() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("newUser");
        request.setEmail("new@example.com");
        request.setPassword("plainPw");
        String encodedPw = "encodedPw";
        String newId = "123";
        User user = new User(newId, "newUser", "new@example.com", encodedPw);

        when(encryptionComponent.encodePassword("plainPw")).thenReturn(encodedPw);
        when(userRepository.newId()).thenReturn(newId);
        when(userRepository.insert(Mockito.any(User.class))).thenReturn(user);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(newId);
        assertThat(response.getUsername()).isEqualTo("newUser");
        assertThat(response.getEmail()).isEqualTo("new@example.com");
    }

    @Test
    void testGenerateUser_ShouldThrowConflict_WhenRepositoryThrowsConflict() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("conflictUser");
        request.setEmail("conflict@example.com");
        request.setPassword("plainPw");
        when(encryptionComponent.encodePassword("plainPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("id123");
        when(userRepository.insert(Mockito.any(User.class))).thenThrow(new Conflict());

        // WHEN / THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void testGenerateUser_ShouldThrowInternalServerError_WhenRepositoryThrowsGenericException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("errorUser");
        request.setEmail("error@example.com");
        request.setPassword("plainPw");
        when(encryptionComponent.encodePassword("plainPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("id456");
        when(userRepository.insert(Mockito.any(User.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN / THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(InternalServerError.class);
    }
}
