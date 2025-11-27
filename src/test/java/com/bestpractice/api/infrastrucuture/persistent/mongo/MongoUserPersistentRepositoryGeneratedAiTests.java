package com.bestpractice.api.infrastrucuture.persistent.mongo;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class MongoUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoCollection<MongoUserEntity> collection;

    @InjectMocks
    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
    void findByEmail_shouldReturnUser_whenEmailExists() {
        // GIVEN
        String email = "test@example.com";
        MongoUserEntity mockEntity = mock(MongoUserEntity.class);
        when(collection.find(any())).thenReturn(mock(Iterable.class));
        when(mockEntity.convertTo()).thenReturn(new User("1", "username", email, "password"));
        when(collection.find(any()).first()).thenReturn(mockEntity);

        // WHEN
        User user = repository.findByEmail(email);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
        verify(collection).find(any());
    }

    @Test
    void findByEmail_shouldThrowInternalServerError_whenEmailNotFound() {
        // GIVEN
        String email = "nonexistent@example.com";
        when(collection.find(any()).first()).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void findById_shouldReturnUser_whenIdExists() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoUserEntity mockEntity = mock(MongoUserEntity.class);
        when(collection.find(any())).thenReturn(mock(Iterable.class));
        when(mockEntity.convertTo()).thenReturn(new User(id, "username", "email@example.com", "password"));
        when(collection.find(any()).first()).thenReturn(mockEntity);

        // WHEN
        User user = repository.findById(id);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(id);
        verify(collection).find(any());
    }

    @Test
    void findById_shouldThrowInternalServerError_whenIdNotFound() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(any()).first()).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_shouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User("1", "username", "email@example.com", "password");
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // WHEN
        repository.insert(user);

        // THEN
        verify(collection).insertOne(entity);
    }

    @Test
    void insert_shouldThrowInternalServerError_whenInsertFails() {
        // GIVEN
        User user = new User("1", "username", "email@example.com", "password");
        doThrow(new RuntimeException("Insert failed")).when(collection).insertOne(any());

        // WHEN & THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_shouldReplaceUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "username", "email@example.com", "password");
        MongoUserEntity entity = MongoUserEntityconvertFrom(user);
        UpdateResult mockResult = mock(UpdateResult.class);
        when(mockResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), eq(entity), any())).thenReturn(mockResult);

        // WHEN
        repository.replace(id, user);

        // THEN
        verify(collection).replaceOne(any(), eq(entity), any());
    }

    @Test
    void replace_shouldThrowInternalServerError_whenReplaceFails() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "username", "email@example.com", "password");
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);
        when(collection.replaceOne(any(), eq(entity), any())).thenThrow(new RuntimeException("Replace failed"));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace(id, user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_shouldThrowRuntimeException_whenAcknowledgmentFails() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "username", "email@example.com", "password");
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);
        UpdateResult mockResult = mock(UpdateResult.class);
        when(mockResult.wasAcknowledged()).thenReturn(false);
        when(collection.replaceOne(any(), eq(entity), any())).thenReturn(mockResult);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace(id, user))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Failed to get Acknowledged on replace operation");
    }

    @Test
    void removeById_shouldRemoveUserSuccessfully() {
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
    void removeById_shouldThrowInternalServerError_whenRemoveFails() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.deleteOne(any())).thenThrow(new RuntimeException("Delete failed"));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}