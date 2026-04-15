package com.bestpractice.api.infrastrucuture.persistent.mongo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.eq;
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
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class MongoInfoPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoInfoEntity> collection;

    @Mock
    private FindIterable<MongoInfoEntity> findIterable;

    @Mock
    private MongoCursor<MongoInfoEntity> cursor;

    @Mock
    private DeleteResult deleteResult;

    @Mock
    private UpdateResult updateResult;

    @InjectMocks
    private MongoInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        when(mongoDatabase.getCollection(any(), any())).thenReturn(collection);
        repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void testNewId_ShouldReturnValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThatCode(() -> new ObjectId(id)).doesNotThrowAnyException();
    }

    @Test
    void testFindAll_ShouldReturnListOfInfo() {
        // GIVEN
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(entity);

        // WHEN
        List<Info> result = repository.findAll();

        // THEN
        assertThat(result).isNotEmpty();
        assertThat(result.get(0)).isEqualTo(info);
    }

    @Test
    void testFindAll_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        when(collection.find()).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findAll())
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get data of range from database");
    }

    @Test
    void testFindById_ShouldReturnInfo() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        Info info = new Info();
        when(entity.convertTo()).thenReturn(info);
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(entity);

        // WHEN
        Info result = repository.findById(id);

        // THEN
        assertThat(result).isEqualTo(info);
    }

    @Test
    void testFindById_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void testInsert_ShouldInsertAndReturnInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        mockStaticConversion(info, entity);

        // WHEN
        Info result = repository.insert(info);

        // THEN
        assertThat(result).isEqualTo(info);
        verify(collection, times(1)).insertOne(entity);
    }

    @Test
    void testInsert_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        mockStaticConversion(info, entity);
        doThrow(new RuntimeException("DB error")).when(collection).insertOne(any());

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testReplace_ShouldReplaceAndReturnInfo() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        when(entity.getId()).thenReturn(new ObjectId());
        mockStaticConversion(info, entity);
        when(collection.replaceOne(any(Bson.class), eq(entity), any())).thenReturn(updateResult);
        when(updateResult.wasAcknowledged()).thenReturn(true);

        // WHEN
        Info result = repository.replace("someId", info);

        // THEN
        assertThat(result).isEqualTo(info);
    }

    @Test
    void testReplace_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        Info info = new Info();
        MongoInfoEntity entity = mock(MongoInfoEntity.class);
        mockStaticConversion(info, entity);
        doThrow(new RuntimeException("DB error")).when(collection).replaceOne(any(), any(), any());

        // WHEN THEN
        assertThatThrownBy(() -> repository.replace("someId", info))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testRemoveById_ShouldReturnTrueWhenAcknowledged() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);
        when(deleteResult.wasAcknowledged()).thenReturn(true);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void testRemoveById_ShouldThrowInternalServerErrorOnException() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }

    private void mockStaticConversion(Info info, MongoInfoEntity entity) {
        // GIVEN
        try {
            MongoInfoEntity spyEntity = spy(entity);
            doReturn(spyEntity).when(spyEntity).convertFrom(info);
        } catch (Exception ignored) {
        }
    }
}