package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class MongoUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoDatabase mockDatabase;

    @Mock
    private MongoCollection<MongoUserEntity> mockCollection;

    @Mock
    private FindIterable<MongoUserEntity> mockFindIterable;

    @Mock
    private DeleteResult mockDeleteResult;

    @Mock
    private UpdateResult mockUpdateResult;

    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        when(mockDatabase.getCollection(any(String.class), eq(MongoUserEntity.class))).thenReturn(mockCollection);
        repository = new MongoUserPersistentRepository(null, mockDatabase);
    }

    @Test
    void newId_ShouldReturnValidObjectIdString() {
        // GIVEN
        // (no setup needed)

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).matches("^[a-fA-F0-9]{24}$");
    }

    @Test
    void findByEmail_ShouldReturnUser_WhenEntityExists() {
        // GIVEN
        String email = "test@example.com";
        MongoUserEntity entity = new MongoUserEntity();
        entity.setId(new org.bson.types.ObjectId());
        entity.setUsername("user1");
        entity.setEmail(email);
        entity.setPassword("pass");
        when(mockCollection.find(any(Bson.class))).thenReturn(mockFindIterable);
        when(mockFindIterable.first()).thenReturn(entity);

        // WHEN
        User result = repository.findByEmail(email);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(entity.getId().toString());
        assertThat(result.getUsername()).isEqualTo("user1");
        assertThat(result.getEmail()).isEqualTo(email);
    }

    @Test
    void findByEmail_ShouldThrowInternalServerError_WhenEntityIsNull() {
        // GIVEN
        String email = "missing@example.com";
        when(mockCollection.find(any(Bson.class))).thenReturn(mockFindIterable);
        when(mockFindIterable.first()).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void findById_ShouldReturnUser_WhenEntityExists() {
        // GIVEN
        String id = new org.bson.types.ObjectId().toString();
        MongoUserEntity entity = new MongoUserEntity();
        entity.setId(new org.bson.types.ObjectId(id));
        entity.setUsername("user2");
        entity.setEmail("user2@example.com");
        entity.setPassword("pass2");
        when(mockCollection.find(any(Bson.class))).thenReturn(mockFindIterable);
        when(mockFindIterable.first()).thenReturn(entity);

        // WHEN
        User result = repository.findById(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getUsername()).isEqualTo("user2");
    }

    @Test
    void findById_ShouldThrowInternalServerError_WhenEntityIsNull() {
        // GIVEN
        String id = new org.bson.types.ObjectId().toString();
        when(mockCollection.find(any(Bson.class))).thenReturn(mockFindIterable);
        when(mockFindIterable.first()).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_ShouldCallInsertOneAndReturnUser() {
        // GIVEN
        User user = new User();
        user.setId("507f1f77bcf86cd799439011");
        user.setUsername("newuser");
        user.setEmail("new@example.com");
        user.setPassword("newpass");

        // WHEN
        User result = repository.insert(user);

        // THEN
        ArgumentCaptor<MongoUserEntity> captor = ArgumentCaptor.forClass(MongoUserEntity.class);
        verify(mockCollection).insertOne(captor.capture());
        MongoUserEntity inserted = captor.getValue();
        assertThat(inserted.getUsername()).isEqualTo("newuser");
        assertThat(result).isSameAs(user);
    }

    @Test
    void replace_ShouldCallReplaceOneWithUpsertAndReturnUser_WhenAcknowledged() {
        // GIVEN
        String id = "507f1f77bcf86cd799439011";
        User user = new User();
        user.setId(id);
        user.setUsername("updated");
        user.setEmail("updated@example.com");
        user.setPassword("updatedpass");

        MongoUserEntity entity = MongoUserEntity.convertFrom(user);
        when(mockCollection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any()))
                .thenReturn(mockUpdateResult);
        when(mockUpdateResult.wasAcknowledged()).thenReturn(true);

        // WHEN
        User result = repository.replace(id, user);

        // THEN
        ArgumentCaptor<Bson> filterCaptor = ArgumentCaptor.forClass(Bson.class);
        ArgumentCaptor<MongoUserEntity> entityCaptor = ArgumentCaptor.forClass(MongoUserEntity.class);
        ArgumentCaptor<ReplaceOptions> optionsCaptor = ArgumentCaptor.forClass(ReplaceOptions.class);
        verify(mockCollection).replaceOne(filterCaptor.capture(), entityCaptor.capture(), optionsCaptor.capture());
        assertThat(optionsCaptor.getValue().isUpsert()).isTrue();
        assertThat(entityCaptor.getValue()).isEqualToComparingFieldByField(entity);
        assertThat(result).isSameAs(user);
    }

    @Test
    void replace_ShouldThrowInternalServerError_WhenNotAcknowledged() {
        // GIVEN
        String id = "507f1f77bcf86cd799439011";
        User user = new User();
        user.setId(id);
        user.setUsername("updated");
        user.setEmail("updated@example.com");
        user.setPassword("updatedpass");

        when(mockCollection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any()))
                .thenReturn(mockUpdateResult);
        when(mockUpdateResult.wasAcknowledged()).thenReturn(false);

        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace(id, user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void removeById_ShouldReturnTrue_WhenAcknowledged() {
        // GIVEN
        String id = "507f1f77bcf86cd799439011";
        when(mockCollection.deleteOne(any(Bson.class))).thenReturn(mockDeleteResult);
        when(mockDeleteResult.wasAcknowledged()).thenReturn(true);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void removeById_ShouldReturnFalse_WhenNotAcknowledged() {
        // GIVEN
        String id = "507f1f77bcf86cd799439011";
        when(mockCollection.deleteOne(any(Bson.class))).thenReturn(mockDeleteResult);
        when(mockDeleteResult.wasAcknowledged()).thenReturn(false);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
    }

    @Test
    void removeById_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        String id = "507f1f77bcf86cd799439011";
        when(mockCollection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("db error"));

        // WHEN / THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}
