package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
    void getUserById_returnsUser_whenFound() {
        // GIVEN
        User expectedUser = new User();
        when(userRepository.findById("123")).thenReturn(expectedUser);

        // WHEN
        User actualUser = userService.getUserById("123");

        // THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getAuthenticatedUser_returnsUser_whenCredentialsValid() {
        // GIVEN
        User user = new User();
        user.setPassword("encrypted");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encrypted", "raw")).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser("test@example.com", "raw");

        // THEN
        assertEquals(user, result);
    }

    @Test
    void getAuthenticatedUser_throwsUnAuthorized_whenUserNotFound() {
        // GIVEN
        when(userRepository.findByEmail("missing@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("missing@example.com", "raw"));
    }

    @Test
    void getAuthenticatedUser_throwsUnAuthorized_whenPasswordMismatch() {
        // GIVEN
        User user = new User();
        user.setPassword("encrypted");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encrypted", "raw")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("test@example.com", "raw"));
    }

    @Test
    void generateUser_returnsUserResponse_whenInsertSuccessful() {
        // GIVEN
        UserRequest request = mock(UserRequest.class);
        when(request.getPassword()).thenReturn("rawPw");
        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encPw");
        when(userRepository.newId()).thenReturn("newId");
        User user = new User();
        user.setId("newId");
        user.setUsername("username");
        user.setEmail("email@example.com");
        when(request.convert("newId", "encPw")).thenReturn(user);
        when(userRepository.insert(user)).thenReturn(user);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertEquals("newId", response.getId());
        assertEquals("username", response.getUsername());
        assertEquals("email@example.com", response.getEmail());
    }

    @Test
    void generateUser_throwsConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        UserRequest request = mock(UserRequest.class);
        when(request.getPassword()).thenReturn("rawPw");
        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encPw");
        when(userRepository.newId()).thenReturn("newId");
        User user = new User();
        when(request.convert("newId", "encPw")).thenReturn(user);
        when(userRepository.insert(user)).thenThrow(new Conflict());

        // WHEN & THEN
        assertThrows(Conflict.class, () -> userService.generateUser(request));
    }

    @Test
    void generateUser_throwsInternalServerError_whenRepositoryThrowsOtherException() {
        // GIVEN
        UserRequest request = mock(UserRequest.class);
        when(request.getPassword()).thenReturn("rawPw");
        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encPw");
        when(userRepository.newId()).thenReturn("newId");
        User user = new User();
        when(request.convert("newId", "encPw")).thenReturn(user);
        when(userRepository.insert(user)).thenThrow(new RuntimeException());

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> userService.generateUser(request));
    }
}

/*
2025-09-11 15:47:51.293 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-09-11 15:47:51.301 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generating code...
2025-09-11 15:47:51.301 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,23] ')' or ',' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[148,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[148,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[151,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[151,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[152,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[152,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[153,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[153,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[154,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[154,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[155,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[155,33] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,8] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,48] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[158,1] illegal start of type
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[158,35] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,37] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,55] ';' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,83] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[276,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[276,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[279,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[279,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[280,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[280,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[281,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[281,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[282,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[282,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[283,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[283,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[284,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[284,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[285,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[285,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[286,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[286,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[287,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[287,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[288,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[288,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[289,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[289,33] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,8] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,48] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[292,1] illegal start of type
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[292,35] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,37] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,55] ';' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,83] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[410,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[410,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[411,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[411,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[412,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[412,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[413,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[413,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[414,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[414,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[415,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[415,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[416,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[416,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[417,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[417,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[418,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[418,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[419,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[419,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[420,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[420,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[421,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[421,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[422,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[422,19] not a statement
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,23] ')' or ',' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[148,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[148,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[151,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[151,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[152,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[152,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[153,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[153,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[154,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[154,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[155,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[155,33] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,8] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,48] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[158,1] illegal start of type
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[158,35] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,37] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,55] ';' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,83] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[276,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[276,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[279,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[279,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[280,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[280,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[281,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[281,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[282,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[282,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[283,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[283,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[284,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[284,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[285,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[285,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[286,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[286,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[287,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[287,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[288,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[288,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[289,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[289,33] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,8] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,48] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[292,1] illegal start of type
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[292,35] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,37] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,55] ';' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,83] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[410,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[410,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[411,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[411,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[412,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[412,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[413,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[413,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[414,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[414,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[415,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[415,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[416,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[416,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[417,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[417,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[418,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[418,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[419,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[419,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[420,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[420,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[421,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[421,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[422,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[422,19] not a statement
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-09-11 15:47:51.303 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 15:47:51.988 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy88.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-09-11 15:47:51.990 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-09-11 15:47:51.991 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-09-11 15:47:51.991 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-09-11 15:48:03.454 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-09-11 15:48:03.455 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generating code...
2025-09-11 15:48:03.455 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,23] ')' or ',' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[148,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[148,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[151,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[151,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[152,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[152,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[153,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[153,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[154,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[154,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[155,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[155,33] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,8] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,48] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[158,1] illegal start of type
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[158,35] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,37] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,55] ';' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,83] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[276,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[276,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[279,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[279,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[280,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[280,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[281,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[281,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[282,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[282,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[283,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[283,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[284,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[284,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[285,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[285,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[286,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[286,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[287,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[287,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[288,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[288,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[289,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[289,33] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,8] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,48] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[292,1] illegal start of type
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[292,35] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,37] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,55] ';' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,83] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[410,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[410,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[411,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[411,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[412,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[412,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[413,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[413,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[414,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[414,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[415,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[415,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[416,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[416,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[417,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[417,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[418,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[418,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[419,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[419,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[420,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[420,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[421,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[421,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[422,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[422,19] not a statement
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,23] ')' or ',' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[148,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[148,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[151,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[151,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[152,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[152,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[153,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[153,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[154,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[154,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[155,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[155,33] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,8] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,48] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[158,1] illegal start of type
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[158,35] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,37] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,55] ';' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,83] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[276,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[276,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[279,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[279,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[280,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[280,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[281,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[281,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[282,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[282,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[283,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[283,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[284,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[284,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[285,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[285,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[286,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[286,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[287,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[287,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[288,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[288,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[289,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[289,33] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,8] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,48] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[292,1] illegal start of type
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[292,35] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,37] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,55] ';' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,83] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[410,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[410,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[411,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[411,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[412,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[412,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[413,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[413,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[414,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[414,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[415,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[415,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[416,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[416,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[417,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[417,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[418,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[418,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[419,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[419,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[420,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[420,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[421,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[421,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[422,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[422,19] not a statement
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-09-11 15:48:03.460 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 15:48:03.745 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy88.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-09-11 15:48:03.750 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-09-11 15:48:03.750 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-09-11 15:48:03.750 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-09-11 15:48:13.243 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-09-11 15:48:13.244 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generating code...
2025-09-11 15:48:13.244 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,23] ')' or ',' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[148,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[148,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[151,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[151,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[152,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[152,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[153,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[153,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[154,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[154,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[155,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[155,33] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,8] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,48] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[158,1] illegal start of type
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[158,35] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,37] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,55] ';' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,83] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[276,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[276,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[279,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[279,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[280,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[280,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[281,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[281,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[282,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[282,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[283,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[283,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[284,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[284,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[285,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[285,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[286,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[286,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[287,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[287,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[288,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[288,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[289,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[289,33] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,8] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,48] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[292,1] illegal start of type
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[292,35] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,37] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,55] ';' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,83] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[410,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[410,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[411,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[411,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[412,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[412,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[413,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[413,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[414,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[414,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[415,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[415,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[416,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[416,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[417,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[417,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[418,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[418,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[419,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[419,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[420,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[420,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[421,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[421,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[422,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[422,19] not a statement
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,23] ')' or ',' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[148,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[148,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[151,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[151,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[152,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[152,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[153,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[153,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[154,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[154,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[155,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[155,33] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,8] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,48] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[158,1] illegal start of type
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[158,35] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,37] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,55] ';' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,83] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[276,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[276,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[279,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[279,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[280,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[280,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[281,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[281,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[282,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[282,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[283,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[283,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[284,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[284,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[285,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[285,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[286,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[286,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[287,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[287,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[288,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[288,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[289,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[289,33] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,8] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,48] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[292,1] illegal start of type
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[292,35] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,37] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,55] ';' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,83] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[410,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[410,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[411,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[411,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[412,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[412,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[413,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[413,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[414,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[414,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[415,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[415,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[416,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[416,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[417,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[417,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[418,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[418,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[419,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[419,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[420,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[420,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[421,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[421,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[422,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[422,19] not a statement
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-09-11 15:48:13.245 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 15:48:14.030 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy88.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-09-11 15:48:14.032 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-09-11 15:48:14.032 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-09-11 15:48:14.032 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-09-11 15:48:22.294 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-09-11 15:48:22.295 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generating code...
2025-09-11 15:48:22.295 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,23] ')' or ',' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[148,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[148,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[151,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[151,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[152,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[152,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[153,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[153,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[154,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[154,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[155,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[155,33] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,8] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,48] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[158,1] illegal start of type
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[158,35] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,37] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,55] ';' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,83] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[276,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[276,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[279,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[279,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[280,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[280,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[281,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[281,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[282,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[282,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[283,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[283,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[284,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[284,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[285,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[285,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[286,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[286,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[287,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[287,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[288,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[288,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[289,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[289,33] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,8] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,48] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[292,1] illegal start of type
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[292,35] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,37] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,55] ';' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,83] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[410,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[410,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[411,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[411,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[412,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[412,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[413,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[413,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[414,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[414,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[415,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[415,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[416,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[416,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[417,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[417,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[418,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[418,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[419,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[419,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[420,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[420,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[421,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[421,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[422,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[422,19] not a statement
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,23] ')' or ',' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[148,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[148,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[151,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[151,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[152,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[152,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[153,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[153,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[154,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[154,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[155,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[155,33] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,8] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[157,48] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[158,1] illegal start of type
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[158,35] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,37] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,55] ';' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,83] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[276,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[276,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[279,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[279,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[280,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[280,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[281,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[281,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[282,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[282,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[283,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[283,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[284,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[284,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[285,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[285,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[286,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[286,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[287,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[287,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[288,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[288,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[289,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[289,33] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,8] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[291,48] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[292,1] illegal start of type
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[292,35] <identifier> expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,37] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,55] ';' expected
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[408,83] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[410,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[410,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[411,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[411,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[412,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[412,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[413,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[413,45] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[414,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[414,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[415,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[415,41] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[416,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[416,51] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[417,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[417,55] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[418,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[418,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[419,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[419,29] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[420,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[420,39] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[421,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[421,19] not a statement
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[422,1] illegal start of expression
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-15094555487994252547/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[422,19] not a statement
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-09-11 15:48:22.297 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-09-11 15:48:23.137 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy88.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-09-11 15:48:23.138 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-09-11 15:48:23.139 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-09-11 15:48:23.139 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
*/
