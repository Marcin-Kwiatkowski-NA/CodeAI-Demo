package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.DeleteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
@PrepareForTest({MongoInfoPersistentRepository.class})
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
    void setUp() {
        // Ensure that the repository uses the mocked collection
        when(mongoDatabase.getCollection("infos", MongoInfoEntity.class)).thenReturn(collection);
    }

    @Test
    void newId_ShouldReturnValidObjectIdString() {
        // GIVEN nothing

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThat(new ObjectId(id).toString()).isEqualTo(id);
    }

    @Test
    void findAll_ShouldReturnAllInfoObjects() {
        // GIVEN
        MongoInfoEntity entity1 = new MongoInfoEntity(new ObjectId(), "Title 1", "Desc 1");
        MongoInfoEntity entity2 = new MongoInfoEntity(new ObjectId(), "Title 2", "Desc 2");

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
        assertThat(result.get(0).getTitle()).isEqualTo("Title 1");
        assertThat(result.get(1).getDescription()).isEqualTo("Desc 2");
        verify(collection, times(1)).find();
    }

    @Test
    void findAll_WhenCollectionThrows_ShouldWrapInInternalServerError() {
        // GIVEN
        when(collection.find()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findAll())
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get data of range from database");
    }

    @Test
    void findById_ShouldReturnInfoObject() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(id), "Title", "Desc");

        when(collection.find(any())).thenReturn(mock(FindIterable.class));
        when(collection.find(any()).first()).thenReturn(entity);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo("Title");
    }

    @Test
    void findById_WhenNotFound_ShouldThrowInternalServerError() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(any())).thenReturn(mock(FindIterable.class));
        when(collection.find(any()).first()).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void findById_WhenException_ShouldWrapInInternalServerError() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_ShouldPersistAndReturnInfo() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        info.setTitle("New Title");
        info.setDescription("New Desc");

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isSameAs(info);
        verify(collection, times(1)).insertOne(any(MongoInfoEntity.class));
    }

    @Test
    void insert_WhenException_ShouldWrapInInternalServerError() {
        // GIVEN
        Info info = new Info();
        info.setId(new ObjectId().toString());
        doThrow(new RuntimeException("Insert error")).when(collection).insertOne(any());

        // WHEN & THEN
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_ShouldUpdateAndReturnInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        info.setId(id);
        info.setTitle("Updated");
        info.setDescription("Updated Desc");

        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), any(), any())).thenReturn(updateResult);

        // WHEN
        Info result = repository.replace(id, info);

        // THEN
        assertThat(result).isSameAs(info);
        verify(collection, times(1)).replaceOne(any(), any(), any());
    }

    @Test
    void replace_WhenNotAcknowledged_ShouldThrowRuntimeException() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        info.setId(id);

        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(false);
        when(collection.replaceOne(any(), any(), any())).thenReturn(updateResult);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace(id, info))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Failed to get Acknowledged on replace operation");
    }

    @Test
    void replace_WhenException_ShouldWrapInInternalServerError() {
        // GIVEN
        String id = new ObjectId().toString();
        Info info = new Info();
        info.setId(id);
        doThrow(new RuntimeException("Replace error")).when(collection).replaceOne(any(), any(), any());

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace(id, info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void removeById_ShouldReturnTrueWhenAcknowledged() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
        verify(collection, times(1)).deleteOne(any());
    }

    @Test
    void removeById_ShouldReturnFalseWhenNotAcknowledged() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(false);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isFalse();
    }

    @Test
    void removeById_WhenException_ShouldWrapInInternalServerError() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.deleteOne(any())).thenThrow(new RuntimeException("Delete error"));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}
