package com.bestpractice.api.infrastrucuture.persistent.mongo;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
        when(collection.find(any())).thenReturn(iterable);
        when(iterable.first()).thenReturn(entity);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertEquals(entity.getTitle(), result.getTitle());
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
    public void testReplaceUpdatesEntity() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        info.setId(id);
        info.setTitle("title");
        info.setDescription("desc");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), any(), any())).thenReturn(updateResult);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertEquals(info, result);
        verify(collection, times(1)).replaceOne(any(), any(), any());
    }

    @Test
    public void testRemoveByIdDeletesEntity() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertTrue(result);
        verify(collection, times(1)).deleteOne(any());
    }