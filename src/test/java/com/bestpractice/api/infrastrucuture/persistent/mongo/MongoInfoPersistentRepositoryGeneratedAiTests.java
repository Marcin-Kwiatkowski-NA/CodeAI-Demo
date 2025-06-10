package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MongoInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoInfoEntity> collection;

    @InjectMocks
    private MongoInfoPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNewId() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
    }

    @Test
    public void testFindAll() {
        // GIVEN
        MongoInfoEntity entity1 = new MongoInfoEntity();
        Info info1 = new Info();
        entity1.setId("id1");
        entity1.setTitle("title1");
        entity1.setDescription("description1");

        when(collection.find()).thenReturn(FindIterable.of(entity1));

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertEquals(1, result.size());
        assertEquals("id1", result.get(0).getId());
    }

    @Test
    public void testFindById() {
        // GIVEN
        MongoInfoEntity entity = new MongoInfoEntity();
        Info info = new Info();
        entity.setId("id1");
        entity.setTitle("title1");
        entity.setDescription("description1");

        when(collection.find(any(Bson.class))).thenReturn(FindIterable.of(entity));

        // WHEN
        Info result = repository.findById("id1");

        // THEN
        assertNotNull(result);
        assertEquals("id1", result.getId());
    }

    @Test
    public void testInsert() {
        // GIVEN
        Info info = new Info();
        info.setTitle("title1");
        info.setDescription("description1");

        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        doNothing().when(collection).insertOne(entity);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertNotNull(result);
    }

    @Test
    public void testReplace() {
        // GIVEN
        Info info = new Info();
        info.setId("id1");
        info.setTitle("title1");
        info.setDescription("description1");

        MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);

        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);

        doNothing().when(collection).replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class));

        // WHEN
        Info result = repository.replace("id1", info);

        // THEN
        assertNotNull(result);
    }

    @Test
    public void testRemoveById() {
        // GIVEN
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);

        doNothing().when(collection).deleteOne(any(Bson.class));

        // WHEN
        boolean result = repository.removeById("id1");

        // THEN
        assertTrue(result);
    }
}