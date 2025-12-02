package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


@ExtendWith(MockitoExtension.class)
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
    void newId_shouldGenerateNewObjectId() {
        // GIVEN

        // WHEN
        String newId = repository.newId();

        // THEN
        assertThat(newId).isNotNull();
        assertThat(newId).matches("^[a-f\\d]{24}$");
    }

    @Test
    void findByEmail_shouldReturnUser_whenEmailExists() {
        // GIVEN
        String email = "test@example.com";
        MongoUserEntity mongoUserEntity = mock(MongoUserEntity.class);
        User expectedUser = new User("1", "testUser", email, "password");
        when(mongoUserEntity.convertTo()).thenReturn(expectedUser);
        when(collection.find(any())).thenReturn(mock(Iterable.class));
        when(collection.find(any()).first()).thenReturn(mongoUserEntity);

        // WHEN
        User result = repository.findByEmail(email);

        // THEN
        assertThat(result).isEqualTo(expectedUser);
        verify(collection).find(Filters.eq("email", email));
    }

    @Test
    void findByEmail_shouldThrowInternalServerError_whenEmailNotFound() {
        // GIVEN
        String email = "nonexistent@example.com";
        when(collection.find(any())).thenReturn(mock(Iterable.class));
        when(collection.find(any()).first()).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void findById_shouldReturnUser_whenIdExists() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoUserEntity mongoUserEntity = mock(MongoUserEntity.class);
        User expectedUser = new User(id, "testUser", "test@example.com", "password");
        when(mongoUserEntity.convertTo()).thenReturn(expectedUser);
        when(collection.find(any())).thenReturn(mock(Iterable.class));
        when(collection.find(any()).first()).thenReturn(mongoUserEntity);

        // WHEN
        User result = repository.findById(id);

        // THEN
        assertThat(result).isEqualTo(expectedUser);
        verify(collection).find(Filters.eq("_id", new ObjectId(id)));
    }

    @Test
    void findById_shouldThrowInternalServerError_whenIdNotFound() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(any())).thenReturn(mock(Iterable.class));
        when(collection.find(any()).first()).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_shouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertThat(result).isEqualTo(user);
        verify(collection).insertOne(mongoUserEntity);
    }

    @Test
    void insert_shouldThrowInternalServerError_whenInsertFails() {
        // GIVEN
        User user = new User("1", "testUser", "test@Test
    void insert_shouldThrowInternalServerError_whenInsertFails() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        doThrow(new RuntimeException("Insert failed")).when(collection).insertOne(any(MongoUserEntity.class));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_shouldReplaceUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "testUser", "test@example.com", "password");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), eq(mongoUserEntity), eq(replaceOptions))).thenReturn(updateResult);

        // WHEN
        User result = repository.replace(id, user);

        // THEN
        assertThat(result).isEqualTo(user);
        verify(collection).replaceOne(Filters.eq("_id", mongoUserEntity.getId()), mongoUserEntity, replaceOptions);
    }

    @Test
    void replace_shouldThrowInternalServerError_whenReplaceFails() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "testUser", "test@example.com", "password");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        doThrow(new RuntimeException("Replace failed")).when(collection).replaceOne(any(), eq(mongoUserEntity), eq(replaceOptions));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace(id, user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_shouldThrowRuntimeException_whenAcknowledgmentFails() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "testUser", "test@example.com", "password");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(false);
        when(collection.replaceOne(any(), eq(mongoUserEntity), eq(replaceOptions))).thenReturn(updateResult);

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace(id, user))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Failed to get Acknowledged on replace operation");
    }

    @Test
    void removeById_shouldRemoveUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        verify(collection).deleteOne(Filters.eq("_id", new ObjectId(id)));
    }

    @Test
    void removeById_shouldThrowInternalServerError_whenRemoveFails() {
        // GIVEN
        String id = new ObjectId().toString();
        doThrow(new RuntimeException("Delete failed")).when(collection).deleteOne(any());

        // WHEN / THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}
