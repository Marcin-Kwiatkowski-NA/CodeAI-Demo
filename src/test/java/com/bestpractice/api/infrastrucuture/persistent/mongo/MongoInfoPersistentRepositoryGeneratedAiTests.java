package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
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
    public void testNewIdGeneratesObjectIdString() {
        String id = repository.newId();
        assertNotNull(id);
        assertDoesNotThrow(() -> new ObjectId(id));
    }

    @Test
    public void testFindAllReturnsConvertedList() {
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "title", "desc");
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);

        List<Info> result = repository.findAll();

        assertEquals(1, result.size());
        assertEquals(entity.getTitle(), result.get(0).getTitle());
    }

    @Test
    public void testFindAllThrowsInternalServerErrorOnException() {
        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenThrow(new RuntimeException("fail"));

        assertThrows(InternalServerError.class, () -> repository.findAll());
    }

    @Test
    public void testFindByIdReturnsConvertedEntity() {
        String id = new ObjectId().toString();
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(id), "title", "desc");
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(collection.find(any())).thenReturn(iterable);
        when(iterable.first()).thenReturn(entity);

        Info result = repository.findById(id);

        assertEquals(entity.getTitle(), result.getTitle());
    }

    @Test
    public void testFindByIdThrowsInternalServerErrorOnException() {
        String id = new ObjectId().toString();
        when(collection.find(any())).thenThrow(new RuntimeException("fail"));

        assertThrows(InternalServerError.class, () -> repository.findById(id));
    }

    @Test
    public void testInsertReturnsInfo() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");

        Info result = repository.insert(info);

        assertEquals(info, result);
        verify(collection, times(1)).insertOne(any(MongoInfoEntity.class));
    }

    @Test
    public void testInsertThrowsInternalServerErrorOnException() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");
        doThrow(new RuntimeException("fail")).when(collection).insertOne(any());

        assertThrows(InternalServerError.class, () -> repository.insert(info));
    }

    @Test
    public void testReplaceReturnsInfoOnAcknowledged() {
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), any(MongoInfoEntity.class), any())).thenReturn(updateResult);

        Info result = repository.replace(info.getId(), info);

        assertEquals(info, result);
    }
