package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplGeneratedAiTests {

    private UserPersistentRepository userRepository;
    private BCryptPasswordEncryptionComponent encryptionComponent;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserPersistentRepository.class);
        encryptionComponent = Mockito.mock(BCryptPasswordEncryptionComponent.class);
        userService = new UserServiceImpl(userRepository, encryptionComponent);
    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        // GIVEN
        User expectedUser = new User("1", "username", "email@example.com", "password");
        Mockito.when(userRepository.findById("1")).thenReturn(expectedUser);

        // WHEN
        User actualUser = userService.getUserById("1");

        // THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getAuthenticatedUser_shouldReturnUser_whenCredentialsAreValid() {
        // GIVEN
        User user = new User("1", "username", "email@example.com", "encodedPw");
        Mockito.when(userRepository.findByEmail("email@example.com")).thenReturn(user);
        Mockito.when(encryptionComponent.matchedPassword("encodedPw", "rawPw")).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser("email@example.com", "rawPw");

        // THEN
        assertEquals(user, result);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenUserNotFound() {
        // GIVEN
        Mockito.when(userRepository.findByEmail("email@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("email@example.com", "rawPw"));
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenPasswordDoesNotMatch() {
        // GIVEN
        User user = new User("1", "username", "email@example.com", "encodedPw");
        Mockito.when(userRepository.findByEmail("email@example.com")).thenReturn(user);
        Mockito.when(encryptionComponent.matchedPassword("encodedPw", "rawPw")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("email@example.com", "rawPw"));
    }

    @Test
    void generateUser_shouldReturnUserResponse_whenInsertSucceeds() {
        // GIVEN
        UserRequest request = Mockito.mock(UserRequest.class);
        Mockito.when(request.getPassword()).thenReturn("rawPw");
        Mockito.when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        Mockito.when(userRepository.newId()).thenReturn("newId");
        User user = new User("newId", "username", "email@example.com", "encodedPw");
        Mockito.when(request.convert("newId", "encodedPw")).thenReturn(user);
        Mockito.when(userRepository.insert(user)).thenReturn(user);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertEquals("newId", response.getId());
        assertEquals("username", response.getUsername());
        assertEquals("email@example.com", response.getEmail());
    }

    @Test
    void generateUser_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        UserRequest request = Mockito.mock(UserRequest.class);
        Mockito.when(request.getPassword()).thenReturn("rawPw");
        Mockito.when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        Mockito.when(userRepository.newId()).thenReturn("newId");
        User user = new User("newId", "username", "email@example.com", "encodedPw");
        Mockito.when(request.convert("newId", "encodedPw")).thenReturn(user);
        Mockito.when(userRepository.insert(user)).thenThrow(new Conflict());

        // WHEN & THEN
        assertThrows(Conflict.class, () -> userService.generateUser(request));
    }

    @Test
    void generateUser_shouldThrowInternalServerError_whenRepositoryThrowsOtherException() {
        // GIVEN
        UserRequest request = Mockito.mock(UserRequest.class);
        Mockito.when(request.getPassword()).thenReturn("rawPw");
        Mockito.when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        Mockito.when(userRepository.newId()).thenReturn("newId");
        User user = new User("newId", "username", "email@example.com", "encodedPw");
        Mockito.when(request.convert("newId", "encodedPw")).thenReturn(user);
        Mockito.when(userRepository.insert(user)).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> userService.generateUser(request));
    }
}

/*
2025-10-06 12:23:45.761 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 12:23:45.763 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 12:23:45.763 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,42] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,70] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[262,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[262,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[264,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[264,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[265,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[265,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[266,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[266,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[267,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[267,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[268,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[268,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[269,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[269,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[270,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[270,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[271,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[271,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[272,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[272,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[273,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[273,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[275,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[275,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[390,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[390,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[392,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[392,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[393,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[393,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[394,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[394,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[395,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[395,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[396,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[396,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[397,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[397,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[398,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[398,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[399,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[399,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[400,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[400,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[401,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[401,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[402,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[402,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[403,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[403,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[406,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[406,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[518,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[518,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[520,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[520,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[521,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[521,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[522,1] illegal start of expression
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,42] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,70] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[262,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[262,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[264,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[264,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[265,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[265,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[266,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[266,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[267,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[267,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[268,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[268,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[269,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[269,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[270,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[270,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[271,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[271,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[272,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[272,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[273,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[273,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[275,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[275,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[390,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[390,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[392,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[392,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[393,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[393,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[394,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[394,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[395,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[395,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[396,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[396,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[397,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[397,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[398,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[398,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[399,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[399,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[400,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[400,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[401,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[401,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[402,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[402,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[403,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[403,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[406,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[406,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[518,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[518,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[520,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[520,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[521,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[521,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[522,1] illegal start of expression
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

2025-10-06 12:23:45.764 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 12:23:46.251 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
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
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
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
2025-10-06 12:23:46.254 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 12:23:46.255 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-10-06 12:23:46.255 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 12:23:49.853 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 12:23:49.854 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 12:23:49.854 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,42] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,70] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[262,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[262,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[264,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[264,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[265,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[265,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[266,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[266,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[267,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[267,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[268,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[268,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[269,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[269,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[270,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[270,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[271,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[271,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[272,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[272,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[273,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[273,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[275,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[275,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[390,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[390,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[392,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[392,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[393,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[393,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[394,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[394,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[395,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[395,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[396,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[396,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[397,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[397,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[398,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[398,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[399,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[399,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[400,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[400,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[401,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[401,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[402,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[402,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[403,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[403,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[406,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[406,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[518,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[518,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[520,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[520,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[521,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[521,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[522,1] illegal start of expression
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,42] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,70] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[262,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[262,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[264,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[264,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[265,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[265,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[266,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[266,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[267,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[267,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[268,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[268,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[269,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[269,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[270,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[270,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[271,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[271,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[272,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[272,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[273,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[273,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[275,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[275,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[390,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[390,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[392,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[392,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[393,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[393,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[394,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[394,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[395,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[395,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[396,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[396,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[397,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[397,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[398,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[398,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[399,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[399,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[400,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[400,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[401,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[401,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[402,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[402,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[403,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[403,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[406,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[406,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[518,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[518,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[520,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[520,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[521,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[521,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[522,1] illegal start of expression
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

2025-10-06 12:23:49.857 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 12:23:50.459 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
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
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
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
2025-10-06 12:23:50.462 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 12:23:50.462 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-10-06 12:23:50.462 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 12:23:53.548 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 12:23:53.548 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 12:23:53.548 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,42] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,70] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[262,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[262,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[264,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[264,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[265,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[265,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[266,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[266,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[267,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[267,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[268,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[268,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[269,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[269,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[270,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[270,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[271,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[271,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[272,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[272,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[273,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[273,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[275,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[275,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[390,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[390,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[392,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[392,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[393,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[393,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[394,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[394,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[395,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[395,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[396,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[396,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[397,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[397,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[398,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[398,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[399,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[399,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[400,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[400,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[401,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[401,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[402,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[402,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[403,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[403,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[406,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[406,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[518,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[518,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[520,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[520,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[521,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[521,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[522,1] illegal start of expression
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,42] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,70] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[142,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[143,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[144,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[145,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[146,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[147,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[149,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[150,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[262,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[262,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[264,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[264,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[265,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[265,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[266,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[266,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[267,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[267,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[268,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[268,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[269,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[269,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[270,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[270,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[271,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[271,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[272,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[272,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[273,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[273,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[274,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[275,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[275,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[277,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[278,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[390,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[390,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[392,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[392,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[393,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[393,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[394,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[394,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[395,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[395,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[396,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[396,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[397,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[397,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[398,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[398,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[399,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[399,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[400,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[400,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[401,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[401,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[402,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[402,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[403,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[403,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[405,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[406,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[406,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[518,63] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[518,91] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[520,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[520,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[521,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[521,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[522,1] illegal start of expression
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

2025-10-06 12:23:53.549 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 12:24:41.283 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 42057, outputTokenCount = 1024, totalTokenCount = 43081 }
2025-10-06 12:24:41.284 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 2
2025-10-06 12:24:42.012 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
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
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
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
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:135)
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
2025-10-06 12:24:42.014 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 12:24:42.014 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-10-06 12:24:42.014 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.service;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplGeneratedAiTests {

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
        User expectedUser = new User();
        when(userRepository.findById("123")).thenReturn(expectedUser);

        // WHEN
        User actualUser = userService.getUserById("123");

        // THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getAuthenticatedUser_shouldReturnUser_whenCredentialsAreValid() {
        // GIVEN
        User user = new User();
        user.setPassword("encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "rawPw")).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser("test@example.com", "rawPw");

        // THEN
        assertEquals(user, result);
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
        User user = new User();
        user.setPassword("encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "wrongPw")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("test@example.com", "wrongPw"));
    }

    @Test
    void generateUser_shouldReturnUserResponse_whenInsertSucceeds() {
        // GIVEN
        UserRequest request = mock(UserRequest.class);
        when(request.getPassword()).thenReturn("rawPw");
        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("newId");
        User user = new User();
        user.setId("newId");
        user.setUsername("username");
        user.setEmail("email@example.com");
        when(request.convert("newId", "encodedPw")).thenReturn(user);
        when(userRepository.insert(user)).thenReturn(user);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertEquals("newId", response.getId());
        assertEquals("username", response.getUsername());
        assertEquals("email@example.com", response.getEmail());
    }

    @Test
    void generateUser_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        UserRequest request = mock(UserRequest.class);
        when(request.getPassword()).thenReturn("rawPw");
        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("newId");
        User user = new User();
        when(request.convert("newId", "encodedPw")).thenReturn(user);
        when(userRepository.insert(user)).thenThrow(new Conflict());

        // WHEN & THEN
        assertThrows(Conflict.class, () -> userService.generateUser(request));
    }

    @Test
    void generateUser_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        UserRequest request = mock(UserRequest.class);
        when(request.getPassword()).thenReturn("rawPw");
        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("newId");
        User user = new User();
        when(request.convert("newId", "encodedPw")).thenReturn(user);
        when(userRepository.insert(user)).thenThrow(new
2025-10-06 12:24:42.015 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Refining code...
2025-10-06 12:24:42.016 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-10-06 12:24:42.016 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
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
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplGeneratedAiTests {

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
        User expectedUser = new User();
        when(userRepository.findById("123")).thenReturn(expectedUser);

        // WHEN
        User actualUser = userService.getUserById("123");

        // THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getAuthenticatedUser_shouldReturnUser_whenCredentialsAreValid() {
        // GIVEN
        User user = new User();
        user.setPassword("encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "rawPw")).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser("test@example.com", "rawPw");

        // THEN
        assertEquals(user, result);
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
        User user = new User();
        user.setPassword("encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "wrongPw")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("test@example.com", "wrongPw"));
    }

    @Test
    void generateUser_shouldReturnUserResponse_whenInsertSucceeds() {
        // GIVEN
        UserRequest request = mock(UserRequest.class);
        when(request.getPassword()).thenReturn("rawPw");
        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("newId");
        User user = new User();
        user.setId("newId");
        user.setUsername("username");
        user.setEmail("email@example.com");
        when(request.convert("newId", "encodedPw")).thenReturn(user);
        when(userRepository.insert(user)).thenReturn(user);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertEquals("newId", response.getId());
        assertEquals("username", response.getUsername());
        assertEquals("email@example.com", response.getEmail());
    }

    @Test
    void generateUser_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        UserRequest request = mock(UserRequest.class);
        when(request.getPassword()).thenReturn("rawPw");
        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("newId");
        User user = new User();
        when(request.convert("newId", "encodedPw")).thenReturn(user);
        when(userRepository.insert(user)).thenThrow(new Conflict());

        // WHEN & THEN
        assertThrows(Conflict.class, () -> userService.generateUser(request));
    }

2025-10-06 12:24:45.140 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 12:24:45.140 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 12:24:45.140 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[122,6] reached end of file while parsing
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[122,6] reached end of file while parsing
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

2025-10-06 12:24:45.140 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 12:24:45.868 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
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
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
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
2025-10-06 12:24:45.870 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 12:24:45.870 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-10-06 12:24:45.870 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 13:52:56.106 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 13:52:56.109 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 13:52:56.109 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,43] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,42] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,78] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[261,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[261,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[378,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[378,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[381,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[381,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[487,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[487,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[489,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[489,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[490,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[490,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[491,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[491,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[492,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[492,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[493,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[493,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[494,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[494,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[495,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[495,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[496,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[496,55] not a statement
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,43] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,42] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,78] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[261,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[261,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[378,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[378,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[381,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[381,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[487,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[487,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[489,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[489,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[490,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[490,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[491,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[491,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[492,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[492,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[493,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[493,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[494,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[494,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[495,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[495,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[496,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[496,55] not a statement
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

2025-10-06 13:52:56.110 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 13:52:56.602 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
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
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
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
2025-10-06 13:52:56.605 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 13:52:56.606 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:52:56.606 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 13:53:00.093 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 13:53:00.093 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 13:53:00.093 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,43] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,42] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,78] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[261,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[261,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[378,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[378,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[381,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[381,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[487,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[487,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[489,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[489,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[490,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[490,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[491,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[491,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[492,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[492,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[493,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[493,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[494,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[494,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[495,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[495,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[496,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[496,55] not a statement
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,43] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,42] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,78] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[261,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[261,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[378,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[378,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[381,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[381,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[487,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[487,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[489,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[489,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[490,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[490,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[491,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[491,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[492,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[492,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[493,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[493,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[494,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[494,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[495,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[495,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[496,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[496,55] not a statement
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

2025-10-06 13:53:00.099 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 13:53:00.593 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
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
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
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
2025-10-06 13:53:00.598 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 13:53:00.598 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:53:00.598 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 13:53:03.693 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 13:53:03.693 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 13:53:03.693 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,43] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,42] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,78] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[261,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[261,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[378,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[378,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[381,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[381,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[487,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[487,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[489,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[489,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[490,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[490,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[491,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[491,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[492,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[492,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[493,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[493,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[494,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[494,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[495,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[495,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[496,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[496,55] not a statement
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,43] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,42] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,78] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[261,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[261,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[378,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[378,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[381,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[381,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[487,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[487,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[489,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[489,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[490,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[490,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[491,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[491,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[492,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[492,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[493,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[493,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[494,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[494,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[495,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[495,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[496,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[496,55] not a statement
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

2025-10-06 13:53:03.694 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 13:53:04.321 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
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
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
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
2025-10-06 13:53:04.325 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 13:53:04.325 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:53:04.325 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 13:53:07.474 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 13:53:07.474 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 13:53:07.474 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,43] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,42] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,78] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[261,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[261,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[378,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[378,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[381,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[381,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[487,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[487,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[489,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[489,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[490,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[490,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[491,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[491,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[492,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[492,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[493,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[493,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[494,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[494,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[495,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[495,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[496,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[496,55] not a statement
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,43] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,42] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,78] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[140,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[141,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[260,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[261,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[261,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[378,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[378,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[380,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[381,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[381,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[487,81] '(' or '[' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[487,108] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[489,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[489,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[490,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[490,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[491,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[491,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[492,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[492,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[493,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[493,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[494,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[494,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[495,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[495,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[496,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[496,55] not a statement
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

2025-10-06 13:53:07.476 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 13:53:08.179 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
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
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
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
2025-10-06 13:53:08.183 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 13:53:08.183 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:53:08.183 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 14:49:09.882 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 14:49:09.884 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 14:49:09.884 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[126,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[128,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[245,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[245,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[246,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[246,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[248,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[248,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[364,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[364,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[365,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[365,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[366,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[366,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[368,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[368,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[483,1] illegal start of expression
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[126,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[128,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[245,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[245,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[246,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[246,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[248,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[248,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[364,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[364,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[365,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[365,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[366,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[366,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[368,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[368,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[483,1] illegal start of expression
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

2025-10-06 14:49:09.885 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:49:10.371 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
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
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
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
2025-10-06 14:49:10.374 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 14:49:10.374 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-10-06 14:49:10.374 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 14:49:13.846 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 14:49:13.847 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 14:49:13.847 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[126,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[128,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[245,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[245,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[246,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[246,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[248,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[248,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[364,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[364,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[365,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[365,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[366,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[366,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[368,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[368,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[483,1] illegal start of expression
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[126,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[128,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[245,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[245,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[246,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[246,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[248,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[248,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[364,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[364,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[365,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[365,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[366,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[366,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[368,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[368,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[483,1] illegal start of expression
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

2025-10-06 14:49:13.850 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:49:14.436 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
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
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
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
2025-10-06 14:49:14.439 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 14:49:14.439 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-10-06 14:49:14.439 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 14:49:17.576 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 14:49:17.576 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 14:49:17.576 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[126,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[128,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[245,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[245,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[246,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[246,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[248,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[248,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[364,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[364,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[365,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[365,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[366,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[366,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[368,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[368,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[483,1] illegal start of expression
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[126,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[128,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[245,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[245,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[246,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[246,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[248,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[248,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[364,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[364,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[365,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[365,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[366,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[366,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[368,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[368,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[483,1] illegal start of expression
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

2025-10-06 14:49:17.577 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:49:18.193 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
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
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
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
2025-10-06 14:49:18.197 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 14:49:18.197 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-10-06 14:49:18.197 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 14:49:21.314 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 14:49:21.314 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 14:49:21.314 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[126,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[128,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[245,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[245,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[246,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[246,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[248,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[248,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[364,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[364,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[365,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[365,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[366,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[366,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[368,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[368,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[483,1] illegal start of expression
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[124,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[126,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[127,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[128,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[129,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[130,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[131,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[132,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[133,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[134,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[135,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[136,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[137,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[138,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[139,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[243,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[245,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[245,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[246,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[246,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[247,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[248,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[248,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[249,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[250,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[251,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[252,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[253,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[254,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[255,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[256,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[257,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[258,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[362,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[364,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[364,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[365,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[365,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[366,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[366,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[367,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[368,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[368,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[369,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[370,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[371,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[372,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[373,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[374,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[375,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[376,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[377,50] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,46] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[481,81] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/UserServiceImplGeneratedAiTests.java:[483,1] illegal start of expression
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

2025-10-06 14:49:21.315 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:49:22.030 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
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
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
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
2025-10-06 14:49:22.033 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 14:49:22.033 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - Done
2025-10-06 14:49:22.033 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.UserServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
*/
