package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.DeleteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.result.UpdateResult;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class MongoInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoInfoEntity> collection;

    @InjectMocks
    private MongoInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        Mockito.reset(mongoClient, mongoDatabase, collection);
    }

    @Test
    void newId_ShouldReturnValidObjectIdString() {
        // GIVEN
        // WHEN
        String id = repository.newId();
        // THEN
        assertThat(id).isNotNull().hasSize(24);
    }

    @Test
    void findAll_ShouldReturnAllInfos() {
        // GIVEN
        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();
        MongoInfoEntity entity1 = new MongoInfoEntity();
        setEntityFields(entity1, id1, "Title1", "Desc1");
        MongoInfoEntity entity2 = new MongoInfoEntity();
        setEntityFields(entity2, id2, "Title2", "Desc2");

        FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);

        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, true, false);
        when(cursor.next()).thenReturn(entity1, entity2);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(id1.toString());
        assertThat(result.get(0).getTitle()).isEqualTo("Title1");
        assertThat(result.get(0).getDescription()).isEqualTo("Desc1");
        assertThat(result.get(1).getId()).isEqualTo(id2.toString());
        assertThat(result.get(1).getTitle()).isEqualTo("Title2");
        assertThat(result.get(1).getDescription()).isEqualTo("Desc2");
    }

    @Test
    void findById_ShouldReturnInfo() {
        // GIVEN
        String id = new ObjectId().toHexString();
        MongoInfoEntity entity = new MongoInfoEntity();
        setEntityFields(entity, new ObjectId(id), "SampleTitle", "SampleDesc");

        when(collection.find(any())).thenReturn(mock(FindIterable.class));
        when(collection.find(any()).first()).thenReturn(entity);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo("SampleTitle");
        assertThat(result.getDescription()).isEqualTo("SampleDesc");
    }

    @Test
    void findById_WhenExceptionThrown_ShouldThrowInternalServerError() {
        // GIVEN
        String id = new ObjectId().toHexString();
        when(collection.find(any())).thenThrow(new RuntimeException("db error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findById(id));
    }

    @Test
    void insert_ShouldInsertEntityAndReturnInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("507f1f77bcf86cd799439011");
        info.setTitle("InsertTitle");
        info.setDescription("InsertDesc");

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isSameAs(info);
        verify(collection).insertOne(MongoInfoEntity.convertFrom(info));
    }

    @Test
    void replace_ShouldReplaceEntityAndReturnInfo() {
        // GIVEN
        String id = new ObjectId().toHexString();
        Info info = new Info();
        info.setId(id);
        info.setTitle("ReplaceTitle");
        info.setDescription("ReplaceDesc");

        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), any(), any())).thenReturn(updateResult);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertThat(result).isSameAs(info);
        verify(collection).replaceOne(any(), any(), any());
    }

    @Test
    void replace_WhenNotAcknowledged_ShouldThrowInternalServerError() {
        // GIVEN
        String id = new ObjectId().toHexString();
        Info info = new Info();
        info.setId(id);
        info.setTitle("ReplaceTitle");
        info.setDescription("ReplaceDesc");

        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(false);
        when(collection.replaceOne(any(), any(), any())).thenReturn(updateResult);

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.replace(id, info));
    }

    @Test
    void removeById_ShouldReturnTrueWhenAcknowledged() {
        // GIVEN
        String id = new ObjectId().toHexString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void removeById_WhenNotAcknowledged_ShouldReturnFalse() {
        // GIVEN
        String id = new ObjectId().toHexString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(false);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
    }

    @Test
    void removeById_WhenExceptionThrown_ShouldThrowInternalServerError() {
        // GIVEN
        String id = new ObjectId().toHexString();
        when(collection.deleteOne(any())).thenThrow(new RuntimeException("delete error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.removeById(id));
    }

    @Test
    void findAll_WhenExceptionThrown_ShouldThrowInternalServerError() {
        // GIVEN
        when(collection.find()).thenThrow(new RuntimeException("find error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findAll());
    }

    private void setEntityFields(MongoInfoEntity entity, ObjectId id, String title, String desc) {
        try {
            java.lang.reflect.Field idField = MongoInfoEntity.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(entity, id);
            java.lang.reflect.Field titleField = MongoInfoEntity.class.getDeclaredField("title");
            titleField.setAccessible(true);
            titleField.set(entity, title);
            java.lang.reflect.Field descField = MongoInfoEntity.class.getDeclaredField("description");
            descField.setAccessible(true);
            descField.set(entity, desc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
