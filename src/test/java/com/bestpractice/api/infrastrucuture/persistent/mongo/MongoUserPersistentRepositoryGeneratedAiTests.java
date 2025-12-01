package com.bestpractice.api.infrastrucuture.persistent.mongo;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

class MongoUserPersistentRepositoryGeneratedAiTests {

    private MongoClient mockMongoClient;
    private MongoDatabase mockMongoDatabase;
    private MongoCollection<MongoUserEntity> mockCollection;
    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        mockMongoClient = mock(MongoClient.class);
        mockMongoDatabase = mock(MongoDatabase.class);
        mockCollection = mock(MongoCollection.class);

        when(mockMongoDatabase.getCollection(anyString(), eq(MongoUserEntity.class))).thenReturn(mockCollection);

        repository = new MongoUserPersistentRepository(mockMongoClient, mockMongoDatabase);
    }

    @Test
    void newId_ShouldGenerateNewId() {
        // GIVEN

        // WHEN
        String newId = repository.newId();

        // THEN
        assertThat(newId).isNotNull();
        assertThat(newId).isNotEmpty();
    }

    @Test
    void findByEmail_ShouldReturnUser_WhenEmailExists() {
        // GIVEN
        String email = "test@example.com";
        MongoUserEntity mockEntity = new MongoUserEntity(new ObjectId(), "testUser", email, "password123");
        when(mockCollection.find(any())).thenReturn(Mockito.mock(Iterable.class));
        when(mockCollection.find(any()).first()).thenReturn(mockEntity);

        // WHEN
        User result = repository.findByEmail(email);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);
    }

    @Test
    void findByEmail_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        String email = "test@example.com";
        when(mockCollection.find(any())).thenThrow(new RuntimeException("Database error"));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void findById_ShouldReturnUser_WhenIdExists() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoUserEntity mockEntity = new MongoUserEntity(new ObjectId(id), "testUser", "test@example.com", "password123");
        when(mockCollection.find(any())).thenReturn(Mockito.mock(Iterable.class));
        when(mockCollection.find(any()).first()).thenReturn(mockEntity);

        // WHEN
        User result = repository.findById(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    void findById_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        String id = new ObjectId().toString();
        when(mockCollection.find(any())).thenThrow(new RuntimeException("Database error"));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_ShouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "testUser", "test@example.com", "password123");
        doNothing().when(mockCollection).insertOne(any(MongoUserEntity.class));

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
        verify(mockCollection, times(1)).insertOne(any(MongoUserEntity.class));
    }

    @Test
    void insert_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "testUser", "test@example.com", "password123");
        do        doThrow(new RuntimeException("Database error")).when(mockCollection).insertOne(any(MongoUserEntity.class));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_ShouldReplaceUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "testUser", "test@example.com", "password123");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        UpdateResult mockUpdateResult = mock(UpdateResult.class);
        when(mockUpdateResult.wasAcknowledged()).thenReturn(true);
        when(mockCollection.replaceOne(any(), eq(mongoUserEntity), eq(replaceOptions))).thenReturn(mockUpdateResult);

        // WHEN
        User result = repository.replace(id, user);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        verify(mockCollection, times(1)).replaceOne(any(), eq(mongoUserEntity), eq(replaceOptions));
    }

    @Test
    void replace_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "testUser", "test@example.com", "password123");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        when(mockCollection.replaceOne(any(), eq(mongoUserEntity), eq(replaceOptions)))
                .thenThrow(new RuntimeException("Database error"));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace(id, user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void removeById_ShouldRemoveUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult mockDeleteResult = mock(DeleteResult.class);
        when(mockDeleteResult.wasAcknowledged()).thenReturn(true);
        when(mockCollection.deleteOne(any())).thenReturn(mockDeleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        verify(mockCollection, times(1)).deleteOne(any());
    }

    @Test
    void removeById_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        String id = new ObjectId().toString();
        when(mockCollection.deleteOne(any())).thenThrow(new RuntimeException("Database error"));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}