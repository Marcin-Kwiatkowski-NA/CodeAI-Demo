package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
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
import static org.mockito.ArgumentMatchers.anyString;
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
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        var cursor = mock(com.mongodb.client.MongoCursor.class);
        when(collection.find()).thenReturn(iterable);
        when(iterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertEquals(1, result.size());
        assertEquals(entity.getTitle(), result.get(0).getTitle());
    }

    @Test
    public void testFindAllThrowsInternalServerErrorOnException() {
        // GIVEN
        FindIterable<MongoInfoEntity> iterable = mock(FindIterable.class);
        when(collection.find()).thenReturn(iterable);
        when(iterable.iterator()).thenThrow(new RuntimeException("fail"));

        // WHEN / THEN
        assertThrows(InternalServerError.class, () -> repository.findAll());
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
        Info result = repository.findById(id);

        // THEN
        assertEquals(entity.getTitle(), result.getTitle());
    }

    @Test
    public void testFindByIdThrowsInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("fail"));

        // WHEN / THEN
        assertThrows(InternalServerError.class, () -> repository.findById(id));
    }

    @Test
    public void testInsertInsertsEntity() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");

        // WHEN
        Info result = repository.insert(info);

        // THEN
        verify(collection, times(1)).insertOne(any(MongoInfoEntity.class));
        assertEquals(info, result);
    }

    @Test
    public void testInsertThrowsInternalServerErrorOnException() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("title");
        info.setDescription("desc");
        doThrow(new RuntimeException("fail")).when(collection).insertOne(any());

        // WHEN / THEN
        assertThrows(InternalServerError.class, () -> repository.insert(info));
    }

    @Test
    public void testReplaceUpdatesEntity() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info =Info();
        info.setId(id);
        info.setTitle("title");
        info.setDescription("desc");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class))).thenReturn(updateResult);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        verify(collection, times(1)).replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class));
        assertEquals(info, result);
    }

    @Test
    public void testReplaceThrowsInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        info.setId(id);
        info.setTitle("title");
        info.setDescription("desc");
        when(collection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class))).thenThrow(new RuntimeException("fail"));

        // WHEN / THEN
        assertThrows(InternalServerError.class, () -> repository.replace(id, info));
    }

    @Test
    public void testRemoveByIdReturnsTrueWhenAcknowledged() {
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

    @Test
    public void testRemoveByIdThrowsInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("fail"));

        // WHEN / THEN
        assertThrows(InternalServerError.class, () -> repository.removeById(id));
    }
}
