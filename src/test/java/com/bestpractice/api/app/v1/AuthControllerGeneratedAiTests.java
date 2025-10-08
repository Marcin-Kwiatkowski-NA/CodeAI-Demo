package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerGeneratedAiTests {

    private AuthService authService;
    private AuthController authController;

    @BeforeEach
    void setUp() {
        authService = mock(AuthService.class);
        authController = new AuthController(authService);
    }

    @Test
    void givenOptionsRequest_whenOptionsAuth_thenReturnsAllowedHeaders() {
        // GIVEN
        // No special preconditions

        // WHEN
        ResponseEntity<Object> response = authController.optionsAuth();

        // THEN
        assertNotNull(response);
        assertTrue(response.getHeaders().containsKey("Allow"));
        assertEquals("POST,OPTIONS", response.getHeaders().getFirst("Allow"));
        assertEquals("*", response.getHeaders().getFirst("Access-Control-Allow-Origin"));
    }

    @Test
    void givenValidEmailRequest_whenLogin_thenReturnsAuthResponse() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "authByEmailRequest");
        AuthResponse expectedResponse = new AuthResponse("accessToken123", "refreshToken123", "userId123", new Date());
        when(authService.login(request.getEmail(), request.getPassword())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(authService, times(1)).login(request.getEmail(), request.getPassword());
    }

    @Test
    void givenInvalidEmailRequest_whenLogin_thenThrowsBadRequest() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "authByEmailRequest");
        bindingResult.reject("email", "Email is invalid");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> authController.login(request, bindingResult));
    }

    @Test
    void givenValidRefreshTokenRequest_whenLogin_thenReturnsAuthResponse() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refresh-token-123");
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "authByRefreshTokenRequest");
        AuthResponse expectedResponse = new AuthResponse("accessToken456", "refreshToken456", "userId456", new Date());
        when(authService.login(request.getRefreshToken())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(authService, times(1)).login(request.getRefreshToken());
    }

    @Test
    void givenInvalidRefreshTokenRequest_whenLogin_thenThrowsBadRequest() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "authByRefreshTokenRequest");
        bindingResult.reject("refreshToken", "Refresh token is invalid");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> authController.login(request, bindingResult));
    }
}

/*
2025-10-08 12:26:14.516 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.app.v1.AuthControllerGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-08 12:26:14.519 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:66)] [{conversationName=com.bestpractice.api.app.v1.AuthControllerGeneratedAiTests.java}] - Generating code...
2025-10-08 12:26:14.519 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.app.v1.AuthControllerGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /tmp/codeai-test-2770620041540791543/src/test/java/com/bestpractice/api/app/v1/AuthControllerGeneratedAiTests.java:[55,41] constructor AuthResponse in class com.bestpractice.api.domain.model.AuthResponse cannot be applied to given types;
[ERROR] /tmp/codeai-test-2770620041540791543/src/test/java/com/bestpractice/api/app/v1/AuthControllerGeneratedAiTests.java:[84,41] constructor AuthResponse in class com.bestpractice.api.domain.model.AuthResponse cannot be applied to given types;
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /tmp/codeai-test-2770620041540791543/src/test/java/com/bestpractice/api/app/v1/AuthControllerGeneratedAiTests.java:[55,41] constructor AuthResponse in class com.bestpractice.api.domain.model.AuthResponse cannot be applied to given types;
[ERROR]   required: java.lang.String,java.lang.String,java.lang.String,java.util.Date
[ERROR]   found:    no arguments
[ERROR]   reason: actual and formal argument lists differ in length
[ERROR] /tmp/codeai-test-2770620041540791543/src/test/java/com/bestpractice/api/app/v1/AuthControllerGeneratedAiTests.java:[84,41] constructor AuthResponse in class com.bestpractice.api.domain.model.AuthResponse cannot be applied to given types;
[ERROR]   required: java.lang.String,java.lang.String,java.lang.String,java.util.Date
[ERROR]   found:    no arguments
[ERROR]   reason: actual and formal argument lists differ in length
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

2025-10-08 12:26:14.519 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.app.v1.AuthControllerGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-08 12:26:19.350 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.app.v1.AuthControllerGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 2716, outputTokenCount = 809, totalTokenCount = 3525 }
2025-10-08 12:26:19.351 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.app.v1.AuthControllerGeneratedAiTests.java}] - Done
2025-10-08 12:26:19.351 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.app.v1.AuthControllerGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.app.v1;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerGeneratedAiTests {

    private AuthService authService;
    private AuthController authController;

    @BeforeEach
    void setUp() {
        authService = mock(AuthService.class);
        authController = new AuthController(authService);
    }

    @Test
    void givenOptionsRequest_whenOptionsAuth_thenReturnsAllowedHeaders() {
        // GIVEN
        // No special preconditions

        // WHEN
        ResponseEntity<Object> response = authController.optionsAuth();

        // THEN
        assertNotNull(response);
        assertTrue(response.getHeaders().containsKey("Allow"));
        assertEquals("POST,OPTIONS", response.getHeaders().getFirst("Allow"));
        assertEquals("*", response.getHeaders().getFirst("Access-Control-Allow-Origin"));
    }

    @Test
    void givenValidEmailRequest_whenLogin_thenReturnsAuthResponse() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "authByEmailRequest");
        AuthResponse expectedResponse = new AuthResponse("accessToken123", "refreshToken123", "userId123", new Date());
        when(authService.login(request.getEmail(), request.getPassword())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(authService, times(1)).login(request.getEmail(), request.getPassword());
    }

    @Test
    void givenInvalidEmailRequest_whenLogin_thenThrowsBadRequest() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "authByEmailRequest");
        bindingResult.reject("email", "Email is invalid");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> authController.login(request, bindingResult));
    }

    @Test
    void givenValidRefreshTokenRequest_whenLogin_thenReturnsAuthResponse() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refresh-token-123");
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "authByRefreshTokenRequest");
        AuthResponse expectedResponse = new AuthResponse("accessToken456", "refreshToken456", "userId456", new Date());
        when(authService.login(request.getRefreshToken())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(authService, times(1)).login(request.getRefreshToken());
    }

    @Test
    void givenInvalidRefreshTokenRequest_whenLogin_thenThrowsBadRequest() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "authByRefreshTokenRequest");
        bindingResult.reject("refreshToken", "Refresh token is invalid");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> authController.login(request, bindingResult));
    }
}
2025-10-08 12:26:19.351 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:91)] [{conversationName=com.bestpractice.api.app.v1.AuthControllerGeneratedAiTests.java}] - Refining code...
2025-10-08 12:26:19.351 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.app.v1.AuthControllerGeneratedAiTests.java}] - Done
2025-10-08 12:26:19.351 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:94)] [{conversationName=com.bestpractice.api.app.v1.AuthControllerGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerGeneratedAiTests {

    private AuthService authService;
    private AuthController authController;

    @BeforeEach
    void setUp() {
        authService = mock(AuthService.class);
        authController = new AuthController(authService);
    }

    @Test
    void givenOptionsRequest_whenOptionsAuth_thenReturnsAllowedHeaders() {
        // GIVEN
        // No special preconditions

        // WHEN
        ResponseEntity<Object> response = authController.optionsAuth();

        // THEN
        assertNotNull(response);
        assertTrue(response.getHeaders().containsKey("Allow"));
        assertEquals("POST,OPTIONS", response.getHeaders().getFirst("Allow"));
        assertEquals("*", response.getHeaders().getFirst("Access-Control-Allow-Origin"));
    }

    @Test
    void givenValidEmailRequest_whenLogin_thenReturnsAuthResponse() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "authByEmailRequest");
        AuthResponse expectedResponse = new AuthResponse("accessToken123", "refreshToken123", "userId123", new Date());
        when(authService.login(request.getEmail(), request.getPassword())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(authService, times(1)).login(request.getEmail(), request.getPassword());
    }

    @Test
    void givenInvalidEmailRequest_whenLogin_thenThrowsBadRequest() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "authByEmailRequest");
        bindingResult.reject("email", "Email is invalid");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> authController.login(request, bindingResult));
    }

    @Test
    void givenValidRefreshTokenRequest_whenLogin_thenReturnsAuthResponse() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refresh-token-123");
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "authByRefreshTokenRequest");
        AuthResponse expectedResponse = new AuthResponse("accessToken456", "refreshToken456", "userId456", new Date());
        when(authService.login(request.getRefreshToken())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(authService, times(1)).login(request.getRefreshToken());
    }

    @Test
    void givenInvalidRefreshTokenRequest_whenLogin_thenThrowsBadRequest() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "authByRefreshTokenRequest");
        bindingResult.reject("refreshToken", "Refresh token is invalid");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> authController.login(request, bindingResult));
    }
}
*/
