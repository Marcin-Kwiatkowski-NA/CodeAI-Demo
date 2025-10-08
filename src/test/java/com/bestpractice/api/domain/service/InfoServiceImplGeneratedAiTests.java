package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InfoServiceImplGeneratedAiTests {

    private InfoPersistentRepository infoRepository;
    private InfoServiceImpl infoService;

    @BeforeEach
    public void setUp() {
        infoRepository = Mockito.mock(InfoPersistentRepository.class);
        infoService = new InfoServiceImpl(infoRepository);
    }

    @Test
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("1", responses.get(0).getId());
        assertEquals("Title2", responses.get(1).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("id1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("id1")).thenReturn(existingInfo);

        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("id1");
        updatedInfo.setTitle("NewTitle");
        updatedInfo.setDescription("NewDesc");
        when(request.convert("id1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("id1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("id1")).thenThrow(new RuntimeException("Not found"));
        InfoRequest request = mock(InfoRequest.class);

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("id1", request));
    }
}
/*
2025-10-08 12:31:23.672 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-08 12:31:23.674 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:66)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-08 12:31:23.675 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /tmp/codeai-test-2770620041540791543/src/test/java/com/bestpractice/api/domain/service/InfoServiceImplGeneratedAiTests.java:[143,6] reached end of file while parsing
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure
[ERROR] /tmp/codeai-test-2770620041540791543/src/test/java/com/bestpractice/api/domain/service/InfoServiceImplGeneratedAiTests.java:[143,6] reached end of file while parsing
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

2025-10-08 12:31:23.675 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-08 12:31:29.717 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 3141, outputTokenCount = 1024, totalTokenCount = 4165 }
2025-10-08 12:31:29.718 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Generate code iteration # 2
2025-10-08 12:31:35.750 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4177, outputTokenCount = 1024, totalTokenCount = 5201 }
2025-10-08 12:31:35.750 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:137)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Terminating generation due to duplicate class response from AI
2025-10-08 12:31:35.750 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Done
2025-10-08 12:31:35.750 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.service;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InfoServiceImplGeneratedAiTests {

    private InfoPersistentRepository infoRepository;
    private InfoServiceImpl infoService;

    @BeforeEach
    public void setUp() {
        infoRepository = Mockito.mock(InfoPersistentRepository.class);
        infoService = new InfoServiceImpl(infoRepository);
    }

    @Test
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("1", responses.get(0).getId());
        assertEquals("Title2", responses.get(1).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("id1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("id1")).thenReturn(existingInfo);

        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("id1");
        updatedInfo.setTitle("NewTitle");
        updatedInfo.setDescription("NewDesc");
        when(request.convert("id1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("id1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("id1")).thenThrow(new RuntimeException("Not found"));
        InfoRequest request = mock(InfoRequest.class);

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("id1", request));
    }

    @Test
    public void testUpdateInfoThrowsInternalServerErrorOnInsert() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("id1");
        when(infoRepository.findById("id1")).thenReturn(existingInfo);

        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("id1");
        when(request.convert("id1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.updateInfo("id1", request));
    }

    @Test
    public void testGenerateInfoSuccess() {
        // GIVEN
        InfoRequest request = mock(InfoRequest.class);
        Info newInfo
2025-10-08 12:31:35.750 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:91)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Refining code...
2025-10-08 12:31:35.750 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Done
2025-10-08 12:31:35.751 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:94)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InfoServiceImplGeneratedAiTests {

    private InfoPersistentRepository infoRepository;
    private InfoServiceImpl infoService;

    @BeforeEach
    public void setUp() {
        infoRepository = Mockito.mock(InfoPersistentRepository.class);
        infoService = new InfoServiceImpl(infoRepository);
    }

    @Test
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("1", responses.get(0).getId());
        assertEquals("Title2", responses.get(1).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("id1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("id1")).thenReturn(existingInfo);

        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("id1");
        updatedInfo.setTitle("NewTitle");
        updatedInfo.setDescription("NewDesc");
        when(request.convert("id1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("id1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("id1")).thenThrow(new RuntimeException("Not found"));
        InfoRequest request = mock(InfoRequest.class);

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("id1", request));
    }

    @Test
    public void testUpdateInfoThrowsInternalServerErrorOnInsert() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("id1");
        when(infoRepository.findById("id1")).thenReturn(existingInfo);

        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("id1");
        when(request.convert("id1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.updateInfo("id1", request));
    }

2025-10-08 12:31:40.708 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-10-08 12:31:40.708 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:66)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-08 12:31:40.708 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Add the missing closing curly brace at the end of the class.

In this code:

package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InfoServiceImplGeneratedAiTests {

    private InfoPersistentRepository infoRepository;
    private InfoServiceImpl infoService;

    @BeforeEach
    public void setUp() {
        infoRepository = Mockito.mock(InfoPersistentRepository.class);
        infoService = new InfoServiceImpl(infoRepository);
    }

    @Test
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("1", responses.get(0).getId());
        assertEquals("Title2", responses.get(1).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("id1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("id1")).thenReturn(existingInfo);

        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("id1");
        updatedInfo.setTitle("NewTitle");
        updatedInfo.setDescription("NewDesc");
        when(request.convert("id1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("id1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("id1")).thenThrow(new RuntimeException("Not found"));
        InfoRequest request = mock(InfoRequest.class);

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("id1", request));
    }

    @Test
    public void testUpdateInfoThrowsInternalServerErrorOnInsert() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("id1");
        when(infoRepository.findById("id1")).thenReturn(existingInfo);

        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("id1");
        when(request.convert("id1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.updateInfo("id1", request));
    }


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text
2025-10-08 12:31:40.708 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-08 12:31:47.172 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 5309, outputTokenCount = 1024, totalTokenCount = 6333 }
2025-10-08 12:31:47.172 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Generate code iteration # 2
2025-10-08 12:31:54.766 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 6345, outputTokenCount = 1024, totalTokenCount = 7369 }
2025-10-08 12:31:54.767 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:137)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Terminating generation due to duplicate class response from AI
2025-10-08 12:31:54.767 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Done
2025-10-08 12:31:54.767 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InfoServiceImplGeneratedAiTests {

    private InfoPersistentRepository infoRepository;
    private InfoServiceImpl infoService;

    @BeforeEach
    public void setUp() {
        infoRepository = Mockito.mock(InfoPersistentRepository.class);
        infoService = new InfoServiceImpl(infoRepository);
    }

    @Test
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("1", responses.get(0).getId());
        assertEquals("Title2", responses.get(1).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("id1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("id1")).thenReturn(existingInfo);

        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("id1");
        updatedInfo.setTitle("NewTitle");
        updatedInfo.setDescription("NewDesc");
        when(request.convert("id1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("id1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("id1")).thenThrow(new RuntimeException("Not found"));
        InfoRequest request = mock(InfoRequest.class);

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("id1", request));
    }

    @Test
    public void testUpdateInfoThrowsInternalServerErrorOnInsert() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("id1");
        when(infoRepository.findById("id1")).thenReturn(existingInfo);

        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("id1");
        when(request.convert("id1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () ->
2025-10-08 12:31:54.767 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:91)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Refining code...
2025-10-08 12:31:54.767 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Done
2025-10-08 12:31:54.767 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:94)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InfoServiceImplGeneratedAiTests {

    private InfoPersistentRepository infoRepository;
    private InfoServiceImpl infoService;

    @BeforeEach
    public void setUp() {
        infoRepository = Mockito.mock(InfoPersistentRepository.class);
        infoService = new InfoServiceImpl(infoRepository);
    }

    @Test
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("1", responses.get(0).getId());
        assertEquals("Title2", responses.get(1).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("id1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("id1")).thenReturn(existingInfo);

        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("id1");
        updatedInfo.setTitle("NewTitle");
        updatedInfo.setDescription("NewDesc");
        when(request.convert("id1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("id1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("id1")).thenThrow(new RuntimeException("Not found"));
        InfoRequest request = mock(InfoRequest.class);

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("id1", request));
    }
2025-10-08 12:31:59.088 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-10-08 12:31:59.088 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:66)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-08 12:31:59.088 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Add the missing closing curly brace at the end of the class.

In this code:

package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InfoServiceImplGeneratedAiTests {

    private InfoPersistentRepository infoRepository;
    private InfoServiceImpl infoService;

    @BeforeEach
    public void setUp() {
        infoRepository = Mockito.mock(InfoPersistentRepository.class);
        infoService = new InfoServiceImpl(infoRepository);
    }

    @Test
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("1", responses.get(0).getId());
        assertEquals("Title2", responses.get(1).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("id1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("id1")).thenReturn(existingInfo);

        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("id1");
        updatedInfo.setTitle("NewTitle");
        updatedInfo.setDescription("NewDesc");
        when(request.convert("id1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("id1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("id1")).thenThrow(new RuntimeException("Not found"));
        InfoRequest request = mock(InfoRequest.class);

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("id1", request));
    }

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text
2025-10-08 12:31:59.088 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-08 12:32:04.703 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 7335, outputTokenCount = 896, totalTokenCount = 8231 }
2025-10-08 12:32:04.703 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Done
2025-10-08 12:32:04.703 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InfoServiceImplGeneratedAiTests {

    private InfoPersistentRepository infoRepository;
    private InfoServiceImpl infoService;

    @BeforeEach
    public void setUp() {
        infoRepository = Mockito.mock(InfoPersistentRepository.class);
        infoService = new InfoServiceImpl(infoRepository);
    }

    @Test
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("1", responses.get(0).getId());
        assertEquals("Title2", responses.get(1).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("id1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("id1")).thenReturn(existingInfo);

        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("id1");
        updatedInfo.setTitle("NewTitle");
        updatedInfo.setDescription("NewDesc");
        when(request.convert("id1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("id1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("id1")).thenThrow(new RuntimeException("Not found"));
        InfoRequest request = mock(InfoRequest.class);

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("id1", request));
    }
}
2025-10-08 12:32:04.703 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:91)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Refining code...
2025-10-08 12:32:04.703 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Done
2025-10-08 12:32:04.703 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:94)] [{conversationName=com.bestpractice.api.domain.service.InfoServiceImplGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InfoServiceImplGeneratedAiTests {

    private InfoPersistentRepository infoRepository;
    private InfoServiceImpl infoService;

    @BeforeEach
    public void setUp() {
        infoRepository = Mockito.mock(InfoPersistentRepository.class);
        infoService = new InfoServiceImpl(infoRepository);
    }

    @Test
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("1", responses.get(0).getId());
        assertEquals("Title2", responses.get(1).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("id1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("id1")).thenReturn(existingInfo);

        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("id1");
        updatedInfo.setTitle("NewTitle");
        updatedInfo.setDescription("NewDesc");
        when(request.convert("id1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("id1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("id1")).thenThrow(new RuntimeException("Not found"));
        InfoRequest request = mock(InfoRequest.class);

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("id1", request));
    }
}
*/
