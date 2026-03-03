package com.bestpractice.api.domain.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplGeneratedAiTests {

    @Mock
    private UserPersistentRepository userRepository;

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void resetMocks() {
        Mockito.reset(userRepository, encryptionComponent);
    }

    @Test
    void getUserByIdReturnsUserWhenFound() {
        // GIVEN
        String userId = "user-123";
        User expectedUser = new User(userId, "john", "john@example.com", "hashedpw");
        when(userRepository.findById(userId)).thenReturn(expectedUser);

        // WHEN
        User result = userService.getUserById(userId);

        // THEN
        assertThat(result).isSameAs(expectedUser);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getUserByIdReturnsNullWhenNotFound() {
        // GIVEN
        String userId = "missing-id";
        when(userRepository.findById(userId)).thenReturn(null);

        // WHEN
        User result = userService.getUserById(userId);

        // THEN
        assertThat(result).isNull();
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getAuthenticatedUserReturnsUserWhenPasswordMatches() {
        // GIVEN
        String email = "alice@example.com";
        String rawPassword = "plainPassword";
        User storedUser = new User("id-456", "alice", email, "encodedPassword");
        when(userRepository.findByEmail(email)).thenReturn(storedUser);
        when(encryptionComponent.matchedPassword("encodedPassword", rawPassword)).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser(email, rawPassword);

        // THEN
        assertThat(result).isSameAs(storedUser);
        verify(userRepository, times(1)).findByEmail(email);
        verify(encryptionComponent, times(1)).matchedPassword("encodedPassword", rawPassword);
    }

    @Test
    void getAuthenticatedUserThrowsUnAuthorizedWhenUserNotFound() {
        // GIVEN
        String email = "missing@example.com";
        String rawPassword = "anyPassword";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser(email, rawPassword));
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void getAuthenticatedUserThrowsUnAuthorizedWhenPasswordDoesNotMatch() {
        // GIVEN
        String email = "bob@example.com";
        String rawPassword = "wrongPassword";
        User storedUser = new User("id-789", "bob", email, "encodedPassword");
        when(userRepository.findByEmail(email)).thenReturn(storedUser);
        when(encryptionComponent.matchedPassword("encodedPassword", rawPassword)).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser(email, rawPassword));
        verify(userRepository, times(1)).findByEmail(email);
        verify(encryptionComponent, times(1)).matchedPassword("encodedPassword", rawPassword);
    }

    @Test
    void getAuthenticatedUserThrowsInternalServerErrorWhenRepositoryFails() {
        // GIVEN
        String email = "error@example.com";
        String rawPassword = "anyPassword";
        when(userRepository.findByEmail(email)).thenThrow(new RuntimeException("DB failure"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> userService.getAuthenticatedUser(email, rawPassword));
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void generateUserCreatesUserAndReturnsResponse() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("alice");
        request.setEmail("alice@example.com");
        request.setPassword("plainPassword");
        String encodedPassword = "encodedPassword";
        String newId = "new-id-001";
        User insertedUser = new User(newId, "alice", "alice@example.com", encodedPassword);

        when(encryptionComponent.encodePassword("plainPassword")).thenReturn(encodedPassword);
        when(userRepository.newId()).thenReturn(newId);
        when(userRepository.insert(any(User.class))).thenReturn(insertedUser);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertThat(response.getId()).isEqualTo(newId);
        assertThat(response.getUsername()).isEqualTo("alice");
        assertThat(response.getEmail()).isEqualTo("alice@example.com");
        verify(encryptionComponent, times(1)).encodePassword("plainPassword");
        verify(userRepository, times(1)).newId();
        verify(userRepository, times(1)).insert(argThat(user ->
                user.getId().equals(newId) &&
                user.getPassword().equals(encodedPassword) &&
                user.getUsername().equals("alice") &&
                user.getEmail().equals("alice@example.com")
        ));
    }

    @Test
    void generateUserThrowsConflictWhenRepositoryInsertFailsWithConflict() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("bob");
        request.setEmail("bob@example.com");
        request.setPassword("plainPassword");
        when(encryptionComponent.encodePassword("plainPassword")).thenReturn("encodedPassword");
        when(userRepository.newId()).thenReturn("id-999");
        when(userRepository.insert(any(User.class))).thenThrow(new Conflict("Duplicate entry"));

        // WHEN & THEN
        assertThrows(Conflict.class, () -> userService.generateUser(request));
        verify(userRepository, times(1)).newId();
        verify(userRepository, times(1)).insert(any(User.class));
    }

    @Test
    void generateUserThrowsInternalServerErrorWhenRepositoryInsertFailsWithGenericException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("charlie");
        request.setEmail("charlie@example.com");
        request.setPassword("plainPassword");
        when(encryptionComponent.encodePassword("plainPassword")).thenReturn("encodedPassword");
        when(userRepository.newId()).thenReturn("id-555");
        when(userRepository.insert(any(User.class))).thenThrow(new RuntimeException("Unexpected failure"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> userService.generateUser(request));
        verify(userRepository, times(1)).newId();
        verify(userRepository, times(1)).insert(any(User.class));
    }

    @Test
    void generateUserReturnsResponseWithCorrectFields() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("diana");
        request.setEmail("diana@example.com");
        request.setPassword("plainPassword");
        String encodedPassword = "encodedPassword";
        String newId = "id-777";
        User insertedUser = new User(newId, "diana", "diana@example.com", encodedPassword);

        when(encryptionComponent.encodePassword("plainPassword")).thenReturn(encodedPassword);
        when(userRepository.newId()).thenReturn(newId);
        when(userRepository.insert(any(User.class))).thenReturn(insertedUser);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertThat(response.getId()).isEqualTo(newId);
        assertThat(response.getUsername()).isEqualTo("diana");
        assertThat(response.getEmail()).isEqualTo("diana@example.com");
        verify(encryptionComponent, times(1)).encodePassword("plainPassword");
        verify(userRepository, times(1)).newId();
        verify(userRepository, times(1)).insert(any(User.class));
    }

    @Test
    void getAuthenticatedUserThrowsUnAuthorizedWhenUserNotFound() {
        // GIVEN
        String email = "missing@example.com";
        String rawPassword = "anyPassword";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser(email, rawPassword));
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void getAuthenticatedUserThrowsUnAuthorizedWhenPasswordDoesNotMatch() {
        // GIVEN
        String email = "eve@example.com";
        String rawPassword = "wrongPassword";
        User storedUser = new User("id-888", "eve", email, "encodedPassword");
        when(userRepository.findByEmail(email)).thenReturn(storedUser);
        when(encryptionComponent.matchedPassword("encodedPassword", rawPassword)).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser(email, rawPassword));
        verify(userRepository, times(1)).findByEmail(email);
        verify(encryptionComponent, times(1)).matchedPassword("encodedPassword", rawPassword);
    }

    @Test
    void getAuthenticatedUserThrowsInternalServerErrorWhenRepositoryFails() {
        // GIVEN
        String email = "error@example.com";
        String rawPassword = "anyPassword";
        when(userRepository.findByEmail(email)).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> userService.getAuthenticatedUser(email, rawPassword));
        verify(userRepository, times(1)).findByEmail(email);
    }
}
