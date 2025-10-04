package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplGeneratedAiTests {

    private UserPersistentRepository userRepository;
    private BCryptPasswordEncryptionComponent encryptionComponent;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserPersistentRepository.class);
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        userService = new UserServiceImpl(userRepository, encryptionComponent);
    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        // GIVEN
        User expectedUser = new User("1", "testuser", "test@example.com", "password");
        when(userRepository.findById("1")).thenReturn(expectedUser);

        // WHEN
        User actualUser = userService.getUserById("1");

        // THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getAuthenticatedUser_shouldReturnUser_whenCredentialsAreValid() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "rawPw")).thenReturn(true);

        // WHEN
        User authenticatedUser = userService.getAuthenticatedUser("test@example.com", "rawPw");

        // THEN
        assertEquals(user, authenticatedUser);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenUserNotFound() {
        // GIVEN
        when(userRepository.findByEmail("missing@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("missing@example.com", "rawPw"));
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenPasswordDoesNotMatch() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "wrongPw")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("test@example.com", "wrongPw"));
    }

    @Test
    void generateUser_shouldReturnUserResponse_whenInsertSucceeds() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        User user = request.convert("1", "encodedPw");
        when(userRepository.insert(any(User.class))).thenReturn(user);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertEquals("1", response.getId());
        assertEquals("testuser", response.getUsername());
        assertEquals("test@example.com", response.getEmail());
    }

    @Test
    void generateUser_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        doThrow(new Conflict()).when(userRepository).insert(any(User.class));

        // WHEN & THEN
        assertThrows(Conflict.class, () -> userService.generateUser(request));
    }

    @Test
    void generateUser_shouldThrowInternalServerError_whenRepositoryThrowsGenericException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        doThrow(new RuntimeException()).when(userRepository).insert(any(User.class));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> userService.generateUser(request));
    }
}package com.bestpractice.api.domain.service;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplGeneratedAiTests {

    private UserPersistentRepository userRepository;
    private BCryptPasswordEncryptionComponent encryptionComponent;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserPersistentRepository.class);
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        userService = new UserServiceImpl(userRepository, encryptionComponent);
    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        // GIVEN
        User expectedUser = new User("1", "testuser", "test@example.com", "password");
        when(userRepository.findById("1")).thenReturn(expectedUser);

        // WHEN
        User actualUser = userService.getUserById("1");

        // THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getAuthenticatedUser_shouldReturnUser_whenCredentialsAreValid() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "rawPw")).thenReturn(true);

        // WHEN
        User authenticatedUser = userService.getAuthenticatedUser("test@example.com", "rawPw");

        // THEN
        assertEquals(user, authenticatedUser);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenUserNotFound() {
        // GIVEN
        when(userRepository.findByEmail("missing@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("missing@example.com", "rawPw"));
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenPasswordDoesNotMatch() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "wrongPw")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("test@example.com", "wrongPw"));
    }

    @Test
    void generateUser_shouldReturnUserResponse_whenInsertSucceeds() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        User user = request.convert("1", "encodedPw");
        when(userRepository.insert(any(User.class))).thenReturn(user);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertEquals("1", response.getId());
        assertEquals("testuser", response.getUsername());
        assertEquals("test@example.com", response.getEmail());
    }

    @Test
    void generateUser_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        doThrow(new Conflict()).when(userRepository).insert(any(User.class));

        // WHEN & THEN
        assertThrows(Conflict.class, () -> userService.generateUser(request));
    }

    @Test
    void generateUser_shouldThrowInternalServerError_whenRepositoryThrowsGenericException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        doThrow(new RuntimeException()).when(userRepository).insert(any(User.class));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> userService.generateUser(request));
    }

    @Test
    voidpackage com.bestpractice.api.domain.service;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplGeneratedAiTests {

    private UserPersistentRepository userRepository;
    private BCryptPasswordEncryptionComponent encryptionComponent;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserPersistentRepository.class);
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        userService = new UserServiceImpl(userRepository, encryptionComponent);
    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        // GIVEN
        User expectedUser = new User("1", "testuser", "test@example.com", "password");
        when(userRepository.findById("1")).thenReturn(expectedUser);

        // WHEN
        User actualUser = userService.getUserById("1");

        // THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getAuthenticatedUser_shouldReturnUser_whenCredentialsAreValid() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "rawPw")).thenReturn(true);

        // WHEN
        User authenticatedUser = userService.getAuthenticatedUser("test@example.com", "rawPw");

        // THEN
        assertEquals(user, authenticatedUser);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenUserNotFound() {
        // GIVEN
        when(userRepository.findByEmail("missing@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("missing@example.com", "rawPw"));
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenPasswordDoesNotMatch() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "wrongPw")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("test@example.com", "wrongPw"));
    }

    @Test
    void generateUser_shouldReturnUserResponse_whenInsertSucceeds() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        User user = request.convert("1", "encodedPw");
        when(userRepository.insert(any(User.class))).thenReturn(user);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertEquals("1", response.getId());
        assertEquals("testuser", response.getUsername());
        assertEquals("test@example.com", response.getEmail());
    }

    @Test
    void generateUser_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        doThrow(new Conflict()).when(userRepository).insert(any(User.class));

        // WHEN & THEN
        assertThrows(Conflict.class, () -> userService.generateUser(request));
    }

    @Test
    void generateUser_shouldThrowInternalServerError_whenRepositoryThrowsGenericException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        doThrow(new RuntimeException()).when(userRepository).insert(any(User.class));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> userService.generateUser(request));
    }

    @Test
    voidpackage com.bestpractice.api.domain.service;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplGeneratedAiTests {

    private UserPersistentRepository userRepository;
    private BCryptPasswordEncryptionComponent encryptionComponent;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserPersistentRepository.class);
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        userService = new UserServiceImpl(userRepository, encryptionComponent);
    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        // GIVEN
        User expectedUser = new User("1", "testuser", "test@example.com", "password");
        when(userRepository.findById("1")).thenReturn(expectedUser);

        // WHEN
        User actualUser = userService.getUserById("1");

        // THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getAuthenticatedUser_shouldReturnUser_whenCredentialsAreValid() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "rawPw")).thenReturn(true);

        // WHEN
        User authenticatedUser = userService.getAuthenticatedUser("test@example.com", "rawPw");

        // THEN
        assertEquals(user, authenticatedUser);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenUserNotFound() {
        // GIVEN
        when(userRepository.findByEmail("missing@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("missing@example.com", "rawPw"));
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenPasswordDoesNotMatch() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "wrongPw")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("test@example.com", "wrongPw"));
    }

    @Test
    void generateUser_shouldReturnUserResponse_whenInsertSucceeds() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        User user = request.convert("1", "encodedPw");
        when(userRepository.insert(any(User.class))).thenReturn(user);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertEquals("1", response.getId());
        assertEquals("testuser", response.getUsername());
        assertEquals("test@example.com", response.getEmail());
    }

    @Test
    void generateUser_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        doThrow(new Conflict()).when(userRepository).insert(any(User.class));

        // WHEN & THEN
        assertThrows(Conflict.class, () -> userService.generateUser(request));
    }

    @Test
    void generateUser_shouldThrowInternalServerError_whenRepositoryThrowsGenericException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        doThrow(new RuntimeException()).when(userRepository).insert(any(User.class));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> userService.generateUser(request));
    }

    @Test
    voidpackage com.bestpractice.api.domain.service;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplGeneratedAiTests {

    private UserPersistentRepository userRepository;
    private BCryptPasswordEncryptionComponent encryptionComponent;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserPersistentRepository.class);
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        userService = new UserServiceImpl(userRepository, encryptionComponent);
    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        // GIVEN
        User expectedUser = new User("1", "testuser", "test@example.com", "password");
        when(userRepository.findById("1")).thenReturn(expectedUser);

        // WHEN
        User actualUser = userService.getUserById("1");

        // THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getAuthenticatedUser_shouldReturnUser_whenCredentialsAreValid() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "rawPw")).thenReturn(true);

        // WHEN
        User authenticatedUser = userService.getAuthenticatedUser("test@example.com", "rawPw");

        // THEN
        assertEquals(user, authenticatedUser);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenUserNotFound() {
        // GIVEN
        when(userRepository.findByEmail("missing@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("missing@example.com", "rawPw"));
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenPasswordDoesNotMatch() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "wrongPw")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("test@example.com", "wrongPw"));
    }

    @Test
    void generateUser_shouldReturnUserResponse_whenInsertSucceeds() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        User user = request.convert("1", "encodedPw");
        when(userRepository.insert(any(User.class))).thenReturn(user);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertEquals("1", response.getId());
        assertEquals("testuser", response.getUsername());
        assertEquals("test@example.com", response.getEmail());
    }

    @Test
    void generateUser_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        doThrow(new Conflict()).when(userRepository).insert(any(User.class));

        // WHEN & THEN
        assertThrows(Conflict.class, () -> userService.generateUser(request));
    }

    @Test
    void generateUser_shouldThrowInternalServerError_whenRepositoryThrowsGenericException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        doThrow(new RuntimeException()).when(userRepository).insert(any(User.class));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> userService.generateUser(request));
    }

    @Test
    voidpackage com.bestpractice.api.domain.service;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplGeneratedAiTests {

    private UserPersistentRepository userRepository;
    private BCryptPasswordEncryptionComponent encryptionComponent;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserPersistentRepository.class);
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        userService = new UserServiceImpl(userRepository, encryptionComponent);
    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        // GIVEN
        User expectedUser = new User("1", "testuser", "test@example.com", "password");
        when(userRepository.findById("1")).thenReturn(expectedUser);

        // WHEN
        User actualUser = userService.getUserById("1");

        // THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getAuthenticatedUser_shouldReturnUser_whenCredentialsAreValid() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "rawPw")).thenReturn(true);

        // WHEN
        User authenticatedUser = userService.getAuthenticatedUser("test@example.com", "rawPw");

        // THEN
        assertEquals(user, authenticatedUser);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenUserNotFound() {
        // GIVEN
        when(userRepository.findByEmail("missing@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("missing@example.com", "rawPw"));
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenPasswordDoesNotMatch() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "wrongPw")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("test@example.com", "wrongPw"));
    }

    @Test
    void generateUser_shouldReturnUserResponse_whenInsertSucceeds() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        User user = request.convert("1", "encodedPw");
        when(userRepository.insert(any(User.class))).thenReturn(user);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertEquals("1", response.getId());
        assertEquals("testuser", response.getUsername());
        assertEquals("test@example.com", response.getEmail());
    }

    @Test
    void generateUser_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        doThrow(new Conflict()).when(userRepository).insert(any(User.class));

        // WHEN & THEN
        assertThrows(Conflict.class, () -> userService.generateUser(request));
    }

    @Test
    void generateUser_shouldThrowInternalServerError_whenRepositoryThrowsGenericException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("rawPw");

        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("1");
        doThrow(new RuntimeException()).when(userRepository).insert(any(User.class));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> userService.generateUser(request));
    }
