package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(iterable.iterator()).thenReturn(cursor);
        when(collection.find()).thenReturn(iterable);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(info, result.get(0));
    }

    @Test
    public void testFindByIdReturnsConvertedInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(iterable.first()).thenReturn(entity);
        when(collection.find(any())).thenReturn(iterable);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertNotNull(result);
        assertSame(info, result);
    }

    @Test
    public void testInsertReturnsInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        mockStatic(MongoInfoEntity.class).when(() -> MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertSame(info, result);
        verify(collection).insertOne(mongoEntity);
    }

    @Test
    public void testReplaceReturnsInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        ObjectId objectId = new ObjectId();
        when(mongoEntity.getId()).thenReturn(objectId);
        mockStatic(MongoInfoEntity.class).when(() -> MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), eq(mongoEntity), any())).thenReturn(updateResult);

        // WHEN
        Info result = repository.replace(objectId.toString(), info);

        // THEN
        assertSame(info, result);
    }

    @Test
    public void testRemoveByIdReturnsTrueWhenAcknowledged() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertTrue(result);
    }

    @Test
    public void testFindByIdThrowsInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(any())).thenThrow(new RuntimeExceptionpackage com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(iterable.iterator()).thenReturn(cursor);
        when(collection.find()).thenReturn(iterable);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(info, result.get(0));
    }

    @Test
    public void testFindByIdReturnsConvertedInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(iterable.first()).thenReturn(entity);
        when(collection.find(any())).thenReturn(iterable);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertNotNull(result);
        assertSame(info, result);
    }

    @Test
    public void testInsertReturnsInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        try (MockedStatic<MongoInfoEntity> mockedStatic = mockStatic(MongoInfoEntity.class)) {
            mockedStatic.when(() -> MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);

            // WHEN
            Info result = repository.insert(info);

            // THEN
            assertSame(info, result);
            verify(collection).insertOne(mongoEntity);
        }
    }

    @Test
    public void testReplaceReturnsInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        ObjectId objectId = new ObjectId();
        when(mongoEntity.getId()).thenReturn(objectId);
        try (MockedStatic<MongoInfoEntity> mockedStatic = mockStatic(MongoInfoEntity.class)) {
            mockedStatic.when(() -> MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);
            UpdateResult updateResult = mock(UpdateResult.class);
            when(updateResult.wasAcknowledged()).thenReturn(true);
            when(collection.replaceOne(any(), eq(mongoEntity), any())).thenReturn(updateResult);

            // WHEN
            Info result = repository.replace(objectId.toString(), info);

            // THEN
            assertSame(info, result);
        }
    }

    @Test
    public void testRemoveByIdReturnsTrueWhenAcknowledged() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertTrue(result);
    }

    @Test
   package com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(iterable.iterator()).thenReturn(cursor);
        when(collection.find()).thenReturn(iterable);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(info, result.get(0));
    }

    @Test
    public void testFindByIdReturnsConvertedInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(iterable.first()).thenReturn(entity);
        when(collection.find(any())).thenReturn(iterable);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertNotNull(result);
        assertSame(info, result);
    }

    @Test
    public void testInsertReturnsInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        try (MockedStatic<MongoInfoEntity> mockedStatic = mockStatic(MongoInfoEntity.class)) {
            mockedStatic.when(() -> MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);

            // WHEN
            Info result = repository.insert(info);

            // THEN
            assertSame(info, result);
            verify(collection).insertOne(mongoEntity);
        }
    }

    @Test
    public void testReplaceReturnsInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        ObjectId objectId = new ObjectId();
        when(mongoEntity.getId()).thenReturn(objectId);
        try (MockedStatic<MongoInfoEntity> mockedStatic = mockStatic(MongoInfoEntity.class)) {
            mockedStatic.when(() -> MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);
            UpdateResult updateResult = mock(UpdateResult.class);
            when(updateResult.wasAcknowledged()).thenReturn(true);
            when(collection.replaceOne(any(), eq(mongoEntity), any())).thenReturn(updateResult);

            // WHEN
            Info result = repository.replace(objectId.toString(), info);

            // THEN
            assertSame(info, result);
        }
    }

    @Test
    public void testRemoveByIdReturnsTrueWhenAcknowledged() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertTrue(result);
    }

    @Test
   package com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(iterable.iterator()).thenReturn(cursor);
        when(collection.find()).thenReturn(iterable);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(info, result.get(0));
    }

    @Test
    public void testFindByIdReturnsConvertedInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(iterable.first()).thenReturn(entity);
        when(collection.find(any())).thenReturn(iterable);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertNotNull(result);
        assertSame(info, result);
    }

    @Test
    public void testInsertReturnsInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        try (MockedStatic<MongoInfoEntity> mockedStatic = mockStatic(MongoInfoEntity.class)) {
            mockedStatic.when(() -> MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);

            // WHEN
            Info result = repository.insert(info);

            // THEN
            assertSame(info, result);
            verify(collection).insertOne(mongoEntity);
        }
    }

    @Test
    public void testReplaceReturnsInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        ObjectId objectId = new ObjectId();
        when(mongoEntity.getId()).thenReturn(objectId);
        try (MockedStatic<MongoInfoEntity> mockedStatic = mockStatic(MongoInfoEntity.class)) {
            mockedStatic.when(() -> MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);
            UpdateResult updateResult = mock(UpdateResult.class);
            when(updateResult.wasAcknowledged()).thenReturn(true);
            when(collection.replaceOne(any(), eq(mongoEntity), any())).thenReturn(updateResult);

            // WHEN
            Info result = repository.replace(objectId.toString(), info);

            // THEN
            assertSame(info, result);
        }
    }

    @Test
    public void testRemoveByIdReturnsTrueWhenAcknowledged() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertTrue(result);
    }

    @Test
   package com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(iterable.iterator()).thenReturn(cursor);
        when(collection.find()).thenReturn(iterable);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(info, result.get(0));
    }

    @Test
    public void testFindByIdReturnsConvertedInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(iterable.first()).thenReturn(entity);
        when(collection.find(any())).thenReturn(iterable);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertNotNull(result);
        assertSame(info, result);
    }

    @Test
    public void testInsertReturnsInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        try (MockedStatic<MongoInfoEntity> mockedStatic = mockStatic(MongoInfoEntity.class)) {
            mockedStatic.when(() -> MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);

            // WHEN
            Info result = repository.insert(info);

            // THEN
            assertSame(info, result);
            verify(collection).insertOne(mongoEntity);
        }
    }

    @Test
    public void testReplaceReturnsInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        ObjectId objectId = new ObjectId();
        when(mongoEntity.getId()).thenReturn(objectId);
        try (MockedStatic<MongoInfoEntity> mockedStatic = mockStatic(MongoInfoEntity.class)) {
            mockedStatic.when(() -> MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);
            UpdateResult updateResult = mock(UpdateResult.class);
            when(updateResult.wasAcknowledged()).thenReturn(true);
            when(collection.replaceOne(any(), eq(mongoEntity), any())).thenReturn(updateResult);

            // WHEN
            Info result = repository.replace(objectId.toString(), info);

            // THEN
            assertSame(info, result);
        }
    }

    @Test
    public void testRemoveByIdReturnsTrueWhenAcknowledged() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertTrue(result);
    }

    @Test
   package com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(iterable.iterator()).thenReturn(cursor);
        when(collection.find()).thenReturn(iterable);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(info, result.get(0));
    }

    @Test
    public void testFindByIdReturnsConvertedInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(iterable.first()).thenReturn(entity);
        when(collection.find(any())).thenReturn(iterable);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertNotNull(result);
        assertSame(info, result);
    }

    @Test
    public void testInsertReturnsInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        try (MockedStatic<MongoInfoEntity> mockedStatic = mockStatic(MongoInfoEntity.class)) {
            mockedStatic.when(() -> MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);

            // WHEN
            Info result = repository.insert(info);

            // THEN
            assertSame(info, result);
            verify(collection).insertOne(mongoEntity);
        }
    }

    @Test
    public void testReplaceReturnsInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        ObjectId objectId = new ObjectId();
        when(mongoEntity.getId()).thenReturn(objectId);
        try (MockedStatic<MongoInfoEntity> mockedStatic = mockStatic(MongoInfoEntity.class)) {
            mockedStatic.when(() -> MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);
            UpdateResult updateResult = mock(UpdateResult.class);
            when(updateResult.wasAcknowledged()).thenReturn(true);
            when(collection.replaceOne(any(), eq(mongoEntity), any())).thenReturn(updateResult);

            // WHEN
            Info result = repository.replace(objectId.toString(), info);

            // THEN
            assertSame(info, result);
        }
    }

    @Test
    public void testRemoveByIdReturnsTrueWhenAcknowledged() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertTrue(result);
    }

    @Test
   package com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(iterable.iterator()).thenReturn(cursor);
        when(collection.find()).thenReturn(iterable);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(info, result.get(0));
    }

    @Test
    public void testFindByIdReturnsConvertedInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(iterable.first()).thenReturn(entity);
        when(collection.find(any())).thenReturn(iterable);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertNotNull(result);
        assertSame(info, result);
    }

    @Test
    public void testInsertReturnsInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        try (MockedStatic<MongoInfoEntity> mockedStatic = mockStatic(MongoInfoEntity.class)) {
            mockedStatic.when(() -> MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);

            // WHEN
            Info result = repository.insert(info);

            // THEN
            assertSame(info, result);
            verify(collection).insertOne(mongoEntity);
        }
    }

    @Test
    public void testReplaceReturnsInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity mongoEntity = mock(MongoInfoEntity.class);
        ObjectId objectId = new ObjectId();
        when(mongoEntity.getId()).thenReturn(objectId);
        try (MockedStatic<MongoInfoEntity> mockedStatic = mockStatic(MongoInfoEntity.class)) {
            mockedStatic.when(() -> MongoInfoEntity.convertFrom(info)).thenReturn(mongoEntity);
            UpdateResult updateResult = mock(UpdateResult.class);
            when(updateResult.wasAcknowledged()).thenReturn(true);
            when(collection.replaceOne(any(), eq(mongoEntity), any())).thenReturn(updateResult);

            // WHEN
            Info result = repository.replace(objectId.toString(), info);

            // THEN
            assertSame(info, result);
        }
    }

    @Test
    public void testRemoveByIdReturnsTrueWhenAcknowledged() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertTrue(result);
    }
