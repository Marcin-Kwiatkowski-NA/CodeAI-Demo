package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class MongoInfoPersistentRepositoryGeneratedAiTests {

    private MongoClient mockMongoClient;
    private MongoDatabase mockMongoDatabase;
    private MongoCollection<MongoInfoEntity> mockCollection;
    private MongoInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        mockMongoClient = mock(MongoClient.class);
        mockMongoDatabase = mock(MongoDatabase.class);
        mockCollection = mock(MongoCollection.class);

        Mockito.when(mockMongoDatabase.getCollection(eq("infos"), eq(MongoInfoEntity.class))).thenReturn(mockCollection);

        repository = new MongoInfoPersistentRepository(mockMongoClient, mockMongoDatabase);
    }

    @Test
    void newId_shouldGenerateNewObjectId() {
        // GIVEN

        // WHEN
        String newId = repository.newId();

        // THEN
        assertThat(newId).isNotNull();
        assertThat(newId).hasSize(24); // ObjectId length
    }

    @Test
    void findAll_shouldReturnListOfInfo() {
        // GIVEN
        FindIterable<MongoInfoEntity> mockFindIterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> mockCursor = mock(MongoCursor.class);

        Mockito.when(mockCollection.find()).thenReturn(mockFindIterable);
        Mockito.when(mockFindIterable.iterator()).thenReturn(mockCursor);

        MongoInfoEntity entity1 = new MongoInfoEntity(new ObjectId(), "Title1", "Description1");
        MongoInfoEntity entity2 = new MongoInfoEntity(new ObjectId(), "Title2", "Description2");

        Mockito.when(mockCursor.hasNext()).thenReturn(true, true, false);
        Mockito.when(mockCursor.next()).thenReturn(entity1, entity2);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getTitle()).isEqualTo("Title1");
        assertThat(result.get(1).getTitle()).isEqualTo("Title2");
    }

    @Test
    void findAll_shouldThrowInternalServerErrorOnException() {
        // GIVEN
        Mockito.when(mockCollection.find()).thenThrow(new RuntimeException("Database error"));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findAll())
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get data of range from database");
    }

    @Test
    void findById_shouldReturnInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity mockEntity = new MongoInfoEntity(new ObjectId(id), "Title", "Description");

        FindIterable<MongoInfoEntity> mockFindIterable = mock(FindIterable.class);

        Mockito.when(mockCollection.find(any(Bson.class))).thenReturn(mockFindIterable);
        Mockito.when(mockFindIterable.first()).thenReturn(mockEntity);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo("Title");
        assertThat(result.getDescription()).isEqualTo("Description");
    }

    @Test
    void findById_shouldThrowInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        Mockito.when(mockCollection.find(any(Bson.class))).thenThrow(new RuntimeException("Database error"));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_shouldInsertInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Title");
        info.setDescription("Description");

        Mockito.doNothing().when(mockCollection).insertOne(any(MongoInfoEntity.class));

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Title");
        assertThat(result.getDescription()).isEqualTo("Description");
    }

    @Test
    void insert_shouldThrowInternalServerErrorOnException() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("Title");
        info.setDescription("Description");

        Mockito.doThrow(new RuntimeException("Database error")).when(mockCollection).insertOne(any(MongoInfoEntity.class));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_shouldReplaceInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        info.setId(id);
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");

        UpdateResult mockUpdateResult = mock(UpdateResult.class);
        Mockito.when(mockUpdateResult.wasAcknowledged()).thenReturn(true);
        Mockito.when(mockCollection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class))).thenReturn(mockUpdateResult);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Updated Title");
        assertThat(result.getDescription()).isEqualTo("Updated Description");
    }

    @Test
    void replace_shouldThrowInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        info.setId(id);
        info.setTitle("Title");
        info.setDescription("Description");

        Mockito.when(mockCollection.replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class))).thenThrow(new RuntimeException("Database error"));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace(id, info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void removeById_shouldRemoveInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult mockDeleteResult = mock(DeleteResult.class);
        Mockito.when(mockDeleteResult.wasAcknowledged()).thenReturn(true);
        Mockito.when(mockCollection.deleteOne(any(Bson.class))).thenReturn(mockDeleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void removeById_shouldThrowInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        Mockito.when(mockCollection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("Database error"));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}
