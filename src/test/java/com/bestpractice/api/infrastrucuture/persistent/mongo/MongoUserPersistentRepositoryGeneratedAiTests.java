package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.types.ObjectId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


class MongoUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoUserEntity> collection;

    @InjectMocks
    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mongoDatabase.getCollection(eq("users"), eq(MongoUserEntity.class))).thenReturn(collection);
    }

    @Test
    void newId_ShouldGenerateNewObjectId() {
        // GIVEN

        // WHEN
        String newId = repository.newId();

        // THEN
        assertThat(newId).isNotNull();
        assertThat(newId).hasSize(24); // ObjectId length
    }

    @Test
    void findByEmail_ShouldReturnUser_WhenEmailExists() {
        // GIVEN
        String email = "test@example.com";
        MongoUserEntity mockEntity = mock(MongoUserEntity.class);
        User expectedUser = new User("1", "testUser", email, "password");
        when(mockEntity.convertTo()).thenReturn(expectedUser);
        when(collection.find(any())).thenReturn(Mockito.mock(Iterable.class));
        when(collection.find(any()).first()).thenReturn(mockEntity);

        // WHEN
        User result = repository.findByEmail(email);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);
        verify(collection).find(any());
    }

    @Test
    void findByEmail_ShouldThrowInternalServerError_WhenEmailNotFound() {
        // GIVEN
        String email = "nonexistent@example.com";
        when(collection.find(any()).first()).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void findById_ShouldReturnUser_WhenIdExists() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoUserEntity mockEntity = mock(MongoUserEntity.class);
        User expectedUser = new User(id, "testUser", "test@example.com", "password");
        when(mockEntity.convertTo()).thenReturn(expectedUser);
        when(collection.find(any())).thenReturn(Mockito.mock(Iterable.class));
        when(collection.find(any()).first()).thenReturn(mockEntity);

        // WHEN
        User result = repository.findById(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        verify(collection).find(any());
    }

    @Test
    void findById_ShouldThrowInternalServerError_WhenIdNotFound() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(any()).first()).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_ShouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        MongoUserEntity mockEntity = MongoUserEntity.convertFrom(user);

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
        verify(collection).insertOne(eq(mockEntity));
    }

    @Test
    void insert_ShouldThrowInternalServerError_WhenInsertFails() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        doThrow(new RuntimeException("Insert failed")).when(collection).insertOne(any());

        // WHEN & THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_ShouldReplaceUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "updatedUser", "updated@example.com", "newPassword");
        MongoUserEntity mockEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions options = new ReplaceOptions().upsert(true);
        UpdateResult mockResult = mock(UpdateResult.class);
        when(mockResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), eq(mockEntity), eq(options))).thenReturn(mockResult);

        // WHEN
        User result = repository.replace(id, user);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        verify(collection).replaceOne(any(), eq(mockEntity), eq(options));
    }

    @Test
    void replace_ShouldThrowInternalServerError_WhenReplaceFails() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "updatedUser", "updated@example.com", "newPassword");
        MongoUserEntity mockEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions options = new ReplaceOptions().upsert(true);
        doThrow(new RuntimeException("Replace failed")).when(collection).replaceOne(any(), eq(mockEntity), eq(options));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace(id, user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_ShouldThrowRuntimeException_WhenAcknowledgmentFails() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "updatedUser", "updated@example.com", "newPassword");
        MongoUserEntity mockEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions options = new ReplaceOptions().upsert(true);
        UpdateResult mockResult = mock(UpdateResult.class);
        when(mockResult.wasAcknowledged()).thenReturn(false);
        when(collection.replaceOne(any(), eq(mockEntity), eq(options))).thenReturn(mockResult);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace(id, user))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Failed to get Acknowledged on replace operation");
    }

    @Test
    void removeById_ShouldRemoveUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult mockResult = mock(DeleteResult.class);
        when(mockResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any())).thenReturn(mockResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        verify(collection).deleteOne(any());
    }

    @Test
    void removeById_ShouldThrowInternalServerError_WhenRemoveFails() {
        // GIVEN
        String id = new ObjectId().toString();
        doThrow(new RuntimeException("Delete failed")).when(collection).deleteOne(any());

        // WHEN & THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}
