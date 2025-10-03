package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class MongoInfoPersistentRepositoryGeneratedAiTests {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoInfoEntity> collection;
    private MongoInfoPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        mongoClient = mock(MongoClient.class);
        mongoDatabase = mock(MongoDatabase.class);
        collection = mock(MongoCollection.class);
        when(mongoDatabase.getCollection(anyString(), eq(MongoInfoEntity.class))).thenReturn(collection);
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    public void testNewIdGeneratesValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertDoesNotThrow(() -> new ObjectId(id));
    }

    @Test
    public void testFindAllReturnsConvertedList() {
        // GIVEN
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "title", "desc");
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertEquals(1, result.size());
        assertEquals(entity.getTitle(), result.get(0).getTitle());
    }

    @Test
    public void testFindByIdReturnsConvertedEntity() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(id), "title", "desc");
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(iterable);
        when(iterable.first()).thenReturn(entity);

        // WHEN
        Info info = repository.findById(id);

        // THEN
        assertNotNull(info);
        assertEquals(entity.getTitle(), info.getTitle());
    }

    @Test
    public void testInsertReturnsInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertEquals(info, result);
        verify(collection, times(1)).insertOne(any(MongoInfoEntity.class));
    }

    @Test
    public void testReplaceAcknowledgedReturnsInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        info.setId(id);
        info.setTitle("title");
        info.setDescription("desc");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class))).thenReturn(updateResult);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertEquals(info, result);
    }

    @Test
    public void testRemoveByIdAcknowledgedReturnsTrue() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertTrue(result);
    }
}

/*
2025-10-02 15:26:31.511 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-02 15:26:31.522 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Generating code...
2025-10-02 15:26:31.522 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/infrastrucuture/persistent/mongo/MongoInfoPersistentRepositoryGeneratedAiTests.java:[81,24] reference to find is ambiguous
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/infrastrucuture/persistent/mongo/MongoInfoPersistentRepositoryGeneratedAiTests.java:[82,60] reference to find is ambiguous
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/infrastrucuture/persistent/mongo/MongoInfoPersistentRepositoryGeneratedAiTests.java:[119,24] reference to replaceOne is ambiguous
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/infrastrucuture/persistent/mongo/MongoInfoPersistentRepositoryGeneratedAiTests.java:[81,24] reference to find is ambiguous
[ERROR]   both method find(org.bson.conversions.Bson) in com.mongodb.client.MongoCollection and method find(com.mongodb.client.ClientSession) in com.mongodb.client.MongoCollection match
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/infrastrucuture/persistent/mongo/MongoInfoPersistentRepositoryGeneratedAiTests.java:[82,60] reference to find is ambiguous
[ERROR]   both method find(org.bson.conversions.Bson) in com.mongodb.client.MongoCollection and method find(com.mongodb.client.ClientSession) in com.mongodb.client.MongoCollection match
[ERROR] /C:/Users/marckwia/AppData/Local/Temp/1/codeai-test-9043317115010529968/src/test/java/com/bestpractice/api/infrastrucuture/persistent/mongo/MongoInfoPersistentRepositoryGeneratedAiTests.java:[119,24] reference to replaceOne is ambiguous
[ERROR]   both method replaceOne(org.bson.conversions.Bson,TDocument,com.mongodb.client.model.ReplaceOptions) in com.mongodb.client.MongoCollection and method replaceOne(com.mongodb.client.ClientSession,org.bson.conversions.Bson,TDocument) in com.mongodb.client.MongoCollection match
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

2025-10-02 15:26:31.522 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-02 15:26:40.512 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4114, outputTokenCount = 946, totalTokenCount = 5060 }
2025-10-02 15:26:40.512 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Done
2025-10-02 15:26:40.512 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class MongoInfoPersistentRepositoryGeneratedAiTests {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoInfoEntity> collection;
    private MongoInfoPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        mongoClient = mock(MongoClient.class);
        mongoDatabase = mock(MongoDatabase.class);
        collection = mock(MongoCollection.class);
        when(mongoDatabase.getCollection(anyString(), eq(MongoInfoEntity.class))).thenReturn(collection);
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    public void testNewIdGeneratesValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertDoesNotThrow(() -> new ObjectId(id));
    }

    @Test
    public void testFindAllReturnsConvertedList() {
        // GIVEN
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "title", "desc");
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertEquals(1, result.size());
        assertEquals(entity.getTitle(), result.get(0).getTitle());
    }

    @Test
    public void testFindByIdReturnsConvertedEntity() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(id), "title", "desc");
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(iterable);
        when(iterable.first()).thenReturn(entity);

        // WHEN
        Info info = repository.findById(id);

        // THEN
        assertNotNull(info);
        assertEquals(entity.getTitle(), info.getTitle());
    }

    @Test
    public void testInsertReturnsInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertEquals(info, result);
        verify(collection, times(1)).insertOne(any(MongoInfoEntity.class));
    }

    @Test
    public void testReplaceAcknowledgedReturnsInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        info.setId(id);
        info.setTitle("title");
        info.setDescription("desc");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any())).thenReturn(updateResult);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertEquals(info, result);
    }

    @Test
    public void testRemoveByIdAcknowledgedReturnsTrue() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertTrue(result);
    }
}
2025-10-02 15:26:40.513 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Refining code...
2025-10-02 15:26:40.513 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Done
2025-10-02 15:26:40.513 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class MongoInfoPersistentRepositoryGeneratedAiTests {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoInfoEntity> collection;
    private MongoInfoPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        mongoClient = mock(MongoClient.class);
        mongoDatabase = mock(MongoDatabase.class);
        collection = mock(MongoCollection.class);
        when(mongoDatabase.getCollection(anyString(), eq(MongoInfoEntity.class))).thenReturn(collection);
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    public void testNewIdGeneratesValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertDoesNotThrow(() -> new ObjectId(id));
    }

    @Test
    public void testFindAllReturnsConvertedList() {
        // GIVEN
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "title", "desc");
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertEquals(1, result.size());
        assertEquals(entity.getTitle(), result.get(0).getTitle());
    }

    @Test
    public void testFindByIdReturnsConvertedEntity() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(id), "title", "desc");
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(iterable);
        when(iterable.first()).thenReturn(entity);

        // WHEN
        Info info = repository.findById(id);

        // THEN
        assertNotNull(info);
        assertEquals(entity.getTitle(), info.getTitle());
    }

    @Test
    public void testInsertReturnsInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertEquals(info, result);
        verify(collection, times(1)).insertOne(any(MongoInfoEntity.class));
    }

    @Test
    public void testReplaceAcknowledgedReturnsInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        info.setId(id);
        info.setTitle("title");
        info.setDescription("desc");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any())).thenReturn(updateResult);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertEquals(info, result);
    }

    @Test
    public void testRemoveByIdAcknowledgedReturnsTrue() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertTrue(result);
    }
}

2025-10-02 15:26:51.708 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-10-02 15:26:51.708 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Generating code...
2025-10-02 15:26:51.708 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

In the `testReplaceAcknowledgedReturnsInfo` method, change the third argument in `when(collection.replaceOne(...))` to explicitly use either `any(UpdateOptions.class)` or `any(ReplaceOptions.class)` to match the intended method overload.

In this code:

package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class MongoInfoPersistentRepositoryGeneratedAiTests {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoInfoEntity> collection;
    private MongoInfoPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        mongoClient = mock(MongoClient.class);
        mongoDatabase = mock(MongoDatabase.class);
        collection = mock(MongoCollection.class);
        when(mongoDatabase.getCollection(anyString(), eq(MongoInfoEntity.class))).thenReturn(collection);
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    public void testNewIdGeneratesValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertDoesNotThrow(() -> new ObjectId(id));
    }

    @Test
    public void testFindAllReturnsConvertedList() {
        // GIVEN
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "title", "desc");
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertEquals(1, result.size());
        assertEquals(entity.getTitle(), result.get(0).getTitle());
    }

    @Test
    public void testFindByIdReturnsConvertedEntity() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(id), "title", "desc");
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(iterable);
        when(iterable.first()).thenReturn(entity);

        // WHEN
        Info info = repository.findById(id);

        // THEN
        assertNotNull(info);
        assertEquals(entity.getTitle(), info.getTitle());
    }

    @Test
    public void testInsertReturnsInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertEquals(info, result);
        verify(collection, times(1)).insertOne(any(MongoInfoEntity.class));
    }

    @Test
    public void testReplaceAcknowledgedReturnsInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        info.setId(id);
        info.setTitle("title");
        info.setDescription("desc");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any())).thenReturn(updateResult);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertEquals(info, result);
    }

    @Test
    public void testRemoveByIdAcknowledgedReturnsTrue() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertTrue(result);
    }
}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text
2025-10-02 15:26:51.708 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-02 15:26:57.547 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 6187, outputTokenCount = 958, totalTokenCount = 7145 }
2025-10-02 15:26:57.547 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Done
2025-10-02 15:26:57.547 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class MongoInfoPersistentRepositoryGeneratedAiTests {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoInfoEntity> collection;
    private MongoInfoPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        mongoClient = mock(MongoClient.class);
        mongoDatabase = mock(MongoDatabase.class);
        collection = mock(MongoCollection.class);
        when(mongoDatabase.getCollection(anyString(), eq(MongoInfoEntity.class))).thenReturn(collection);
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    public void testNewIdGeneratesValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertDoesNotThrow(() -> new ObjectId(id));
    }

    @Test
    public void testFindAllReturnsConvertedList() {
        // GIVEN
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "title", "desc");
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertEquals(1, result.size());
        assertEquals(entity.getTitle(), result.get(0).getTitle());
    }

    @Test
    public void testFindByIdReturnsConvertedEntity() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(id), "title", "desc");
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(iterable);
        when(iterable.first()).thenReturn(entity);

        // WHEN
        Info info = repository.findById(id);

        // THEN
        assertNotNull(info);
        assertEquals(entity.getTitle(), info.getTitle());
    }

    @Test
    public void testInsertReturnsInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertEquals(info, result);
        verify(collection, times(1)).insertOne(any(MongoInfoEntity.class));
    }

    @Test
    public void testReplaceAcknowledgedReturnsInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        info.setId(id);
        info.setTitle("title");
        info.setDescription("desc");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class))).thenReturn(updateResult);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertEquals(info, result);
    }

    @Test
    public void testRemoveByIdAcknowledgedReturnsTrue() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertTrue(result);
    }
}
2025-10-02 15:26:57.548 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Refining code...
2025-10-02 15:26:57.548 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Done
2025-10-02 15:26:57.548 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepositoryGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class MongoInfoPersistentRepositoryGeneratedAiTests {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoInfoEntity> collection;
    private MongoInfoPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        mongoClient = mock(MongoClient.class);
        mongoDatabase = mock(MongoDatabase.class);
        collection = mock(MongoCollection.class);
        when(mongoDatabase.getCollection(anyString(), eq(MongoInfoEntity.class))).thenReturn(collection);
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    public void testNewIdGeneratesValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertDoesNotThrow(() -> new ObjectId(id));
    }

    @Test
    public void testFindAllReturnsConvertedList() {
        // GIVEN
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "title", "desc");
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertEquals(1, result.size());
        assertEquals(entity.getTitle(), result.get(0).getTitle());
    }

    @Test
    public void testFindByIdReturnsConvertedEntity() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(id), "title", "desc");
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(iterable);
        when(iterable.first()).thenReturn(entity);

        // WHEN
        Info info = repository.findById(id);

        // THEN
        assertNotNull(info);
        assertEquals(entity.getTitle(), info.getTitle());
    }

    @Test
    public void testInsertReturnsInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertEquals(info, result);
        verify(collection, times(1)).insertOne(any(MongoInfoEntity.class));
    }

    @Test
    public void testReplaceAcknowledgedReturnsInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        info.setId(id);
        info.setTitle("title");
        info.setDescription("desc");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class))).thenReturn(updateResult);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertEquals(info, result);
    }

    @Test
    public void testRemoveByIdAcknowledgedReturnsTrue() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertTrue(result);
    }
}
*/
