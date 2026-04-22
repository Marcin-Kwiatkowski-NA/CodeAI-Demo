package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterEach;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

public class UserServiceImplGeneratedAiTests {

    @Mock
    private UserPersistentRepository userRepository;

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    private UserServiceImpl userService;

    @BeforeAll
    static void beforeAll() {
        // setup before all tests
    }

    @AfterAll
    static void afterAll() {
        // cleanup after all tests
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, encryptionComponent);
    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        // GIVEN
        User expectedUser = new User("1", "john", "john@example.com", "encodedPw");
        Mockito.when(userRepository.findById("1")).thenReturn(expectedUser);

        // WHEN
        User result = userService.getUserById("1");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getUsername()).isEqualTo("john");
    }

    @Test
    void getAuthenticatedUser_shouldReturnUser_whenCredentialsAreValid() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "encodedPw");
        Mockito.when(userRepository.findByEmail("john@example.com")).thenReturn(user);
        Mockito.when(encryptionComponent.matchedPassword("encodedPw", "rawPw")).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser("john@example.com", "rawPw");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenUserNotFound() {
        // GIVEN
        Mockito.when(userRepository.findByEmail("missing@example.com")).thenReturn(null);

        // WHEN THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser("missing@example.com", "rawPw"))
                .isInstanceOf(UnAuthorized.class);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenPasswordDoesNotMatch() {
        // GIVEN
        User user = new User("1", "john", "john@example.com", "encodedPw");
        Mockito.when(userRepository.findByEmail("john@example.com")).thenReturn(user);
        Mockito.when(encryptionComponent.matchedPassword("encodedPw", "wrongPw")).thenReturn(false);

        // WHEN THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser("john@example.com", "wrongPw"))
                .isInstanceOf(UnAuthorized.class);
    }

    @Test
    void generateUser_shouldReturnUserResponse_whenInsertSucceeds() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john");
        request.setEmail("john@example.com");
        request.setPassword("rawPw");

        User user = new User("1", "john", "john@example.com", "encodedPw");
        Mockito.when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        Mockito.when(userRepository.newId()).thenReturn("1");
        Mockito.when(userRepository.insert(any(User.class))).thenReturn(user);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo("1");
        assertThat(response.getUsername()).isEqualTo("john");
        assertThat(response.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void generateUser_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john");
        request.setEmail("john@example.com");
        request.setPassword("rawPw");

        Mockito.when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        Mockito.when(userRepository.newId()).thenReturn("1");
        Mockito.when(userRepository.insert(any(User.class))).thenThrow(new Conflict());

        // WHEN THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void generateUser_shouldThrowInternalServerError_whenRepositoryThrowsGenericException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john");
        request.setEmail("john@example.com");
        request.setPassword("rawPw");

        Mockito.when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        Mockito.when(userRepository.newId()).thenReturn("1");
        Mockito.when(userRepository.insert(any(User.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(InternalServerError.class);
    }
}
