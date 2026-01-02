package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

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
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
class UserServiceImplGeneratedAiTests {

    @Mock
    private UserPersistentRepository userRepository;

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        reset(userRepository, encryptionComponent);
    }

    @Test
    void getUserById_returnsUserWhenFound() {
        // GIVEN
        String userId = "user123";
        User expectedUser = new User(userId, "john_doe", "john@example.com", "hashed_pw");
        when(userRepository.findById(userId)).thenReturn(expectedUser);

        // WHEN
        User actualUser = userService.getUserById(userId);

        // THEN
        assertThat(actualUser).isSameAs(expectedUser);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getUserById_returnsNullWhenNotFound() {
        // GIVEN
        String userId = "nonexistent";
        when(userRepository.findById(userId)).thenReturn(null);

        // WHEN
        User actualUser = userService.getUserById(userId);

        // THEN
        assertThat(actualUser).isNull();
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getAuthenticatedUser_returnsUserWhenCredentialsMatch() {
        // GIVEN
        String email = "alice@example.com";
        String rawPassword = "plainPassword";
        String hashedPassword = "hashedPassword";
        User user = new User("id1", "alice", email, hashedPassword);
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(hashedPassword, rawPassword)).thenReturn(true);

        // WHEN
        User authenticatedUser = userService.getAuthenticatedUser(email, rawPassword);

        // THEN
        assertThat(authenticatedUser).isSameAs(user);
        verify(userRepository, times(1)).findByEmail(email);
        verify(encryptionComponent, times(1)).matchedPassword(hashedPassword, rawPassword);
    }

    @Test
    void getAuthenticatedUser_throwsUnAuthorizedWhenUserNotFound() {
        // GIVEN
        String email = "missing@example.com";
        String rawPassword = "anyPassword";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser(email, rawPassword))
                .isInstanceOf(UnAuthorized.class);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void getAuthenticatedUser_throwsUnAuthorizedWhenPasswordDoesNotMatch() {
        // GIVEN
        String email = "bob@example.com";
        String rawPassword = "wrongPassword";
        String hashedPassword = "hashedCorrect";
        User user = new User("id2", "bob", email, hashedPassword);
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(hashedPassword, rawPassword)).thenReturn(false);

        // WHEN & THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser(email, rawPassword))
                .isInstanceOf(UnAuthorized.class);
        verify(userRepository, times(1)).findByEmail(email);
        verify(encryptionComponent, times(1)).matchedPassword(hashedPassword, rawPassword);
    }

    @Test
    void generateUser_successfullyCreatesUserAndReturnsResponse() {
        // GIVEN
        String rawPassword = "plainPw";
        String encodedPassword = "encodedPw";
        String newId = "newUserId";
        UserRequest request = new UserRequest();
        request.setUsername("newUser");
        request.setEmail("new@example.com");
        request.setPassword(rawPassword);

        User userToInsert = new User(newId, "newUser", "new@example.com", encodedPassword);
        User insertedUser = new User(newId, "newUser", "new@example.com", encodedPassword);

        when(encryptionComponent.encodePassword(rawPassword)).thenReturn(encodedPassword);
        when(userRepository.newId()).thenReturn(newId);
        when(userRepository.insert(userToInsert)).thenReturn(insertedUser);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(newId);
        assertThat(response.getUsername()).isEqualTo("newUser");
        assertThat(response.getEmail()).isEqualTo("new@example.com");
        verify(encryptionComponent, times(1)).encodePassword(rawPassword);
        verify(userRepository, times(1)).newId();
        verify(userRepository, times(1)).insert(userToInsert);
    }

    @Test
    void generateUser_throwsConflictWhenRepositoryThrowsConflict() {
        // GIVEN
        String rawPassword = "pw";
        String encodedPassword = "encPw";
        UserRequest request = new UserRequest();
        request.setUsername("dupUser");
        request.setEmail("dup@example.com");
        request.setPassword(rawPassword);

        User userToInsert = new User(null, "dupUser", "dup@example.com", encodedPassword);

        when(encryptionComponent.encodePassword(rawPassword)).thenReturn(encodedPassword);
        when(userRepository.newId()).thenReturn("dupId");
        when(userRepository.insert(userToInsert)).thenThrow(new Conflict("duplicate"));

        // WHEN & THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(Conflict.class);
        verify(userRepository, times(1)).insert(userToInsert);
    }

    @Test
    void generateUser_throwsInternalServerErrorWhenRepositoryThrowsException() {
        // GIVEN
        String rawPassword = "pw";
        String encodedPassword = "encPw";
        UserRequest request = new UserRequest();
        request.setUsername("errorUser");
        request.setEmail("error@example.com");
        request.setPassword(rawPassword);

        User userToInsert = new User(null, "errorUser", "error@example.com", encodedPassword);

        when(encryptionComponent.encodePassword(rawPassword)).thenReturn(encodedPassword);
        when(userRepository.newId()).thenReturn("errorId");
        when(userRepository.insert(userToInsert)).thenThrow(new RuntimeException("db error"));

        // WHEN & THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(InternalServerError.class);
        verify(userRepository, times(1)).insert(userToInsert);
    }
}
