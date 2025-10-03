package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthComponentGeneratedAiTests {

    private CredentialProperty credentialProperty;
    private AuthComponent authComponent;

    @BeforeEach
    public void setUp() {
        credentialProperty = new CredentialProperty();
        credentialProperty.setProvider("testProvider");
        credentialProperty.setHmacSecret("testSecret");
        credentialProperty.setExpiresHourStr("1");
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    public void givenValidToken_whenDecodeJwt_thenReturnsDecodedJWT() {
        // GIVEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken();

        // WHEN
        DecodedJWT decodedJWT = authComponent.decodeJwt(token);

        // THEN
        assertNotNull(decodedJWT);
        assertEquals("user123", decodedJWT.getClaim(AuthComponent.ClaimUserIdKey).asString());
        assertEquals("user@example.com", decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString());
    }

    @Test
    public void givenExpiredToken_whenDecodeJwt_thenThrowsUnAuthorized() throws InterruptedException {
        // GIVEN
        credentialProperty.setExpiresHourStr("0");
        authComponent = new AuthComponent(credentialProperty);
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken();
        Thread.sleep(1000);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void givenInvalidSignatureToken_whenDecodeJwt_thenThrowsInternalServerError() {
        // GIVEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken() + "tampered";

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void givenValidData_whenGenerateJwt_thenReturnsCredentialWithExpectedClaims() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);

        // THEN
        assertNotNull(credential);
        assertEquals("Bearer", credential.getTokenType());
        assertEquals(userId, authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimUserIdKey).asString());
        assertEquals(email, authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimUserEmailKey).asString());
        assertFalse(authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimRefreshKey).asBoolean());
    }

    @Test
    public void givenRefreshTokenFlag_whenGenerateJwt_thenReturnsCredentialWithRefreshClaimTrue() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, true);

        // THEN
        assertNotNull(credential);
        assertTrue(authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimRefreshKey).asBoolean());
    }
}

/*
2025-10-02 15:10:01.928 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-02 15:10:01.939 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Generating code...
2025-10-02 15:10:01.939 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[38,34] cannot find symbol
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[55,34] cannot find symbol
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[66,34] cannot find symbol
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[84,64] cannot find symbol
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[85,63] cannot find symbol
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[86,55] cannot find symbol
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[87,33] cannot find symbol
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[101,54] cannot find symbol
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[102,30] cannot find symbol
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[38,34] cannot find symbol
[ERROR]   symbol:   method getAccessToken()
[ERROR]   location: variable credential of type com.bestpractice.api.domain.model.Credential
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[55,34] cannot find symbol
[ERROR]   symbol:   method getAccessToken()
[ERROR]   location: variable credential of type com.bestpractice.api.domain.model.Credential
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[66,34] cannot find symbol
[ERROR]   symbol:   method getAccessToken()
[ERROR]   location: variable credential of type com.bestpractice.api.domain.model.Credential
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[84,64] cannot find symbol
[ERROR]   symbol:   method getAccessToken()
[ERROR]   location: variable credential of type com.bestpractice.api.domain.model.Credential
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[85,63] cannot find symbol
[ERROR]   symbol:   method getAccessToken()
[ERROR]   location: variable credential of type com.bestpractice.api.domain.model.Credential
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[86,55] cannot find symbol
[ERROR]   symbol:   method getAccessToken()
[ERROR]   location: variable credential of type com.bestpractice.api.domain.model.Credential
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[87,33] cannot find symbol
[ERROR]   symbol:   method getExpiresAt()
[ERROR]   location: variable credential of type com.bestpractice.api.domain.model.Credential
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[101,54] cannot find symbol
[ERROR]   symbol:   method getAccessToken()
[ERROR]   location: variable credential of type com.bestpractice.api.domain.model.Credential
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/domain/component/AuthComponentGeneratedAiTests.java:[102,30] cannot find symbol
[ERROR]   symbol:   method getExpiresAt()
[ERROR]   location: variable credential of type com.bestpractice.api.domain.model.Credential
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

2025-10-02 15:10:01.939 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-02 15:10:06.819 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4088, outputTokenCount = 766, totalTokenCount = 4854 }
2025-10-02 15:10:06.820 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Done
2025-10-02 15:10:06.820 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.component;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthComponentGeneratedAiTests {

    private CredentialProperty credentialProperty;
    private AuthComponent authComponent;

    @BeforeEach
    public void setUp() {
        credentialProperty = new CredentialProperty();
        credentialProperty.setProvider("testProvider");
        credentialProperty.setHmacSecret("testSecret");
        credentialProperty.setExpiresHourStr("1");
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    public void givenValidToken_whenDecodeJwt_thenReturnsDecodedJWT() {
        // GIVEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken();

        // WHEN
        DecodedJWT decodedJWT = authComponent.decodeJwt(token);

        // THEN
        assertNotNull(decodedJWT);
        assertEquals("user123", decodedJWT.getClaim(AuthComponent.ClaimUserIdKey).asString());
        assertEquals("user@example.com", decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString());
    }

    @Test
    public void givenExpiredToken_whenDecodeJwt_thenThrowsUnAuthorized() throws InterruptedException {
        // GIVEN
        credentialProperty.setExpiresHourStr("0");
        authComponent = new AuthComponent(credentialProperty);
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken();
        Thread.sleep(1000);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void givenInvalidSignatureToken_whenDecodeJwt_thenThrowsInternalServerError() {
        // GIVEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken() + "tampered";

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void givenValidData_whenGenerateJwt_thenReturnsCredentialWithExpectedClaims() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);

        // THEN
        assertNotNull(credential);
        assertEquals("Bearer", credential.getTokenType());
        assertEquals(userId, authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimUserIdKey).asString());
        assertEquals(email, authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimUserEmailKey).asString());
        assertFalse(authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimRefreshKey).asBoolean());
        assertNotNull(credential.getExpiration());
    }

    @Test
    public void givenRefreshTokenFlag_whenGenerateJwt_thenReturnsCredentialWithRefreshClaimTrue() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, true);

        // THEN
        assertNotNull(credential);
        assertTrue(authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimRefreshKey).asBoolean());
        assertNull(credential.getExpiration());
    }
}
2025-10-02 15:10:06.820 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Refining code...
2025-10-02 15:10:06.821 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Done
2025-10-02 15:10:06.821 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthComponentGeneratedAiTests {

    private CredentialProperty credentialProperty;
    private AuthComponent authComponent;

    @BeforeEach
    public void setUp() {
        credentialProperty = new CredentialProperty();
        credentialProperty.setProvider("testProvider");
        credentialProperty.setHmacSecret("testSecret");
        credentialProperty.setExpiresHourStr("1");
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    public void givenValidToken_whenDecodeJwt_thenReturnsDecodedJWT() {
        // GIVEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken();

        // WHEN
        DecodedJWT decodedJWT = authComponent.decodeJwt(token);

        // THEN
        assertNotNull(decodedJWT);
        assertEquals("user123", decodedJWT.getClaim(AuthComponent.ClaimUserIdKey).asString());
        assertEquals("user@example.com", decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString());
    }

    @Test
    public void givenExpiredToken_whenDecodeJwt_thenThrowsUnAuthorized() throws InterruptedException {
        // GIVEN
        credentialProperty.setExpiresHourStr("0");
        authComponent = new AuthComponent(credentialProperty);
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken();
        Thread.sleep(1000);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void givenInvalidSignatureToken_whenDecodeJwt_thenThrowsInternalServerError() {
        // GIVEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken() + "tampered";

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void givenValidData_whenGenerateJwt_thenReturnsCredentialWithExpectedClaims() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);

        // THEN
        assertNotNull(credential);
        assertEquals("Bearer", credential.getTokenType());
        assertEquals(userId, authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimUserIdKey).asString());
        assertEquals(email, authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimUserEmailKey).asString());
        assertFalse(authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimRefreshKey).asBoolean());
        assertNotNull(credential.getExpiration());
    }

    @Test
    public void givenRefreshTokenFlag_whenGenerateJwt_thenReturnsCredentialWithRefreshClaimTrue() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, true);

        // THEN
        assertNotNull(credential);
        assertTrue(authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimRefreshKey).asBoolean());
        assertNull(credential.getExpiration());
    }
}

2025-10-02 15:10:17.103 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-10-02 15:10:17.103 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Generating code...
2025-10-02 15:10:17.104 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Remove all usages of credential.getExpiration() in this class.

In this code:

package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthComponentGeneratedAiTests {

    private CredentialProperty credentialProperty;
    private AuthComponent authComponent;

    @BeforeEach
    public void setUp() {
        credentialProperty = new CredentialProperty();
        credentialProperty.setProvider("testProvider");
        credentialProperty.setHmacSecret("testSecret");
        credentialProperty.setExpiresHourStr("1");
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    public void givenValidToken_whenDecodeJwt_thenReturnsDecodedJWT() {
        // GIVEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken();

        // WHEN
        DecodedJWT decodedJWT = authComponent.decodeJwt(token);

        // THEN
        assertNotNull(decodedJWT);
        assertEquals("user123", decodedJWT.getClaim(AuthComponent.ClaimUserIdKey).asString());
        assertEquals("user@example.com", decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString());
    }

    @Test
    public void givenExpiredToken_whenDecodeJwt_thenThrowsUnAuthorized() throws InterruptedException {
        // GIVEN
        credentialProperty.setExpiresHourStr("0");
        authComponent = new AuthComponent(credentialProperty);
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken();
        Thread.sleep(1000);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void givenInvalidSignatureToken_whenDecodeJwt_thenThrowsInternalServerError() {
        // GIVEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken() + "tampered";

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void givenValidData_whenGenerateJwt_thenReturnsCredentialWithExpectedClaims() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);

        // THEN
        assertNotNull(credential);
        assertEquals("Bearer", credential.getTokenType());
        assertEquals(userId, authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimUserIdKey).asString());
        assertEquals(email, authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimUserEmailKey).asString());
        assertFalse(authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimRefreshKey).asBoolean());
        assertNotNull(credential.getExpiration());
    }

    @Test
    public void givenRefreshTokenFlag_whenGenerateJwt_thenReturnsCredentialWithRefreshClaimTrue() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, true);

        // THEN
        assertNotNull(credential);
        assertTrue(authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimRefreshKey).asBoolean());
        assertNull(credential.getExpiration());
    }
}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text
2025-10-02 15:10:17.104 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-02 15:10:22.442 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 5763, outputTokenCount = 769, totalTokenCount = 6532 }
2025-10-02 15:10:22.442 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Done
2025-10-02 15:10:22.443 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthComponentGeneratedAiTests {

    private CredentialProperty credentialProperty;
    private AuthComponent authComponent;

    @BeforeEach
    public void setUp() {
        credentialProperty = new CredentialProperty();
        credentialProperty.setProvider("testProvider");
        credentialProperty.setHmacSecret("testSecret");
        credentialProperty.setExpiresHourStr("1");
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    public void givenValidToken_whenDecodeJwt_thenReturnsDecodedJWT() {
        // GIVEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken();

        // WHEN
        DecodedJWT decodedJWT = authComponent.decodeJwt(token);

        // THEN
        assertNotNull(decodedJWT);
        assertEquals("user123", decodedJWT.getClaim(AuthComponent.ClaimUserIdKey).asString());
        assertEquals("user@example.com", decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString());
    }

    @Test
    public void givenExpiredToken_whenDecodeJwt_thenThrowsUnAuthorized() throws InterruptedException {
        // GIVEN
        credentialProperty.setExpiresHourStr("0");
        authComponent = new AuthComponent(credentialProperty);
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken();
        Thread.sleep(1000);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void givenInvalidSignatureToken_whenDecodeJwt_thenThrowsInternalServerError() {
        // GIVEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken() + "tampered";

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void givenValidData_whenGenerateJwt_thenReturnsCredentialWithExpectedClaims() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);

        // THEN
        assertNotNull(credential);
        assertEquals("Bearer", credential.getTokenType());
        assertEquals(userId, authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimUserIdKey).asString());
        assertEquals(email, authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimUserEmailKey).asString());
        assertFalse(authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimRefreshKey).asBoolean());
    }

    @Test
    public void givenRefreshTokenFlag_whenGenerateJwt_thenReturnsCredentialWithRefreshClaimTrue() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, true);

        // THEN
        assertNotNull(credential);
        assertTrue(authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimRefreshKey).asBoolean());
    }
}
2025-10-02 15:10:22.443 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Refining code...
2025-10-02 15:10:22.443 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Done
2025-10-02 15:10:22.443 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.component.AuthComponentGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthComponentGeneratedAiTests {

    private CredentialProperty credentialProperty;
    private AuthComponent authComponent;

    @BeforeEach
    public void setUp() {
        credentialProperty = new CredentialProperty();
        credentialProperty.setProvider("testProvider");
        credentialProperty.setHmacSecret("testSecret");
        credentialProperty.setExpiresHourStr("1");
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    public void givenValidToken_whenDecodeJwt_thenReturnsDecodedJWT() {
        // GIVEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken();

        // WHEN
        DecodedJWT decodedJWT = authComponent.decodeJwt(token);

        // THEN
        assertNotNull(decodedJWT);
        assertEquals("user123", decodedJWT.getClaim(AuthComponent.ClaimUserIdKey).asString());
        assertEquals("user@example.com", decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString());
    }

    @Test
    public void givenExpiredToken_whenDecodeJwt_thenThrowsUnAuthorized() throws InterruptedException {
        // GIVEN
        credentialProperty.setExpiresHourStr("0");
        authComponent = new AuthComponent(credentialProperty);
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken();
        Thread.sleep(1000);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void givenInvalidSignatureToken_whenDecodeJwt_thenThrowsInternalServerError() {
        // GIVEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken() + "tampered";

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void givenValidData_whenGenerateJwt_thenReturnsCredentialWithExpectedClaims() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);

        // THEN
        assertNotNull(credential);
        assertEquals("Bearer", credential.getTokenType());
        assertEquals(userId, authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimUserIdKey).asString());
        assertEquals(email, authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimUserEmailKey).asString());
        assertFalse(authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimRefreshKey).asBoolean());
    }

    @Test
    public void givenRefreshTokenFlag_whenGenerateJwt_thenReturnsCredentialWithRefreshClaimTrue() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, true);

        // THEN
        assertNotNull(credential);
        assertTrue(authComponent.decodeJwt(credential.getToken()).getClaim(AuthComponent.ClaimRefreshKey).asBoolean());
    }
}
*/
