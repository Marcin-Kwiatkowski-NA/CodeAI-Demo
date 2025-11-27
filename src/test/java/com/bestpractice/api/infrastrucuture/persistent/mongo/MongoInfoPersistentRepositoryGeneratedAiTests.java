package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.Arrays;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class MongoInfoPersistentRepositoryGeneratedAiTests {

  private MongoInfoPersistentRepository repository;

  @Mock private MongoClient mongoClient;
  @Mock private MongoDatabase mongoDatabase;
  @Mock private MongoCollection<MongoInfoEntity> collection;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    when(mongoDatabase.getCollection("infos", MongoInfoEntity.class)).thenReturn(collection);
    repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
  }

  @Test
  void newId_shouldGenerateNewId() {
    // GIVEN

    // WHEN
    String newId = repository.newId();

    // THEN
    assertThat(newId).isNotNull();
    assertThat(newId).hasSize(24); // ObjectId length
  }

  @Test
  void findAll_shouldReturnAllInfos() {
    // GIVEN
    MongoInfoEntity entity1 = new MongoInfoEntity(new ObjectId(), "Title1", "Description1");
    MongoInfoEntity entity2 = new MongoInfoEntity(new ObjectId(), "Title2", "Description2");
    when(collection.find()).thenReturn(mockFindIterable(entity1, entity2));

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
    when(collection.find()).thenThrow(new RuntimeException("Database error"));

    // WHEN THEN
    assertThatThrownBy(() -> repository.findAll())
        .isInstanceOf(InternalServerError.class)
        .hasMessageContaining("Failed to get data of range from database");
  }

  @Test
  void findById_shouldReturnInfo() {
    // GIVEN
    String id = new ObjectId().toString();
    MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(id), "Title", "Description");
    when(collection.find(Filters.eq("_id", new ObjectId(id)))).thenReturn(mockFindIterable(entity));

    // WHEN
    Info result = repository.findById(id);

    // THEN
    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(id);
    assertThat(result.getTitle()).isEqualTo("Title");
  }

  @Test
  void findById_shouldThrowInternalServerErrorOnException() {
    // GIVEN
    String id = new ObjectId().toString();
    when(collection.find(Filters.eq("_id", new ObjectId(id)))).thenThrow(new RuntimeException("Database error"));

    // WHEN THEN
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

    // WHEN
    Info result = repository.insert(info);

    // THEN
    verify(collection).insertOne(any(MongoInfoEntity.class));
    assertThat(result).isEqualTo(info);
  }

  @Test
  void insert_shouldThrowInternalServerErrorOnException() {
    // GIVEN
    Info info = new Info();
    info.setId(new ObjectId().toString());
    info.setTitle("Title");
    info.setDescription("Description");
    doThrow(new RuntimeException("Database error")).when(collection).insertOne(any(MongoInfoEntity.class));

    // WHEN THEN
    assertThatThrownBy(() -> repository.insert(info))
        .isInstanceOf(InternalServerError.class)
        .hasMessageContaining("Failed to insert");
  }

  @Test
  void replace_shouldReplaceInfo()@Test
  void replace_shouldReplaceInfo() {
    // GIVEN
    String id = new ObjectId().toString();
    Info info = new Info();
    info.setId(id);
    info.setTitle("Updated Title");
    info.setDescription("Updated Description");

    MongoInfoEntity mongoInfoEntity = MongoInfoEntity.convertFrom(info);
    ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
    UpdateResult updateResult = mock(UpdateResult.class);
    when(updateResult.wasAcknowledged()).thenReturn(true);
    when(collection.replaceOne(Filters.eq("_id", mongoInfoEntity.getId()), mongoInfoEntity, replaceOptions))
        .thenReturn(updateResult);

    // WHEN
    Info result = repository.replace(id, info);

    // THEN
    verify(collection).replaceOne(Filters.eq("_id", mongoInfoEntity.getId()), mongoInfoEntity, replaceOptions);
    assertThat(result).isEqualTo(info);
  }

  @Test
  void replace_shouldThrowInternalServerErrorOnException() {
    // GIVEN
    String id = new ObjectId().toString();
    Info info = new Info();
    info.setId(id);
    info.setTitle("Updated Title");
    info.setDescription("Updated Description");

    MongoInfoEntity mongoInfoEntity = MongoInfoEntity.convertFrom(info);
    ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
    doThrow(new RuntimeException("Database error"))
        .when(collection)
        .replaceOne(Filters.eq("_id", mongoInfoEntity.getId()), mongoInfoEntity, replaceOptions);

    // WHEN THEN
    assertThatThrownBy(() -> repository.replace(id, info))
        .isInstanceOf(InternalServerError.class)
        .hasMessageContaining("Failed to insert");
  }

  @Test
  void removeById_shouldRemoveInfo() {
    // GIVEN
    String id = new ObjectId().toString();
    DeleteResult deleteResult = mock(DeleteResult.class);
    when(deleteResult.wasAcknowledged()).thenReturn(true);
    when(collection.deleteOne(Filters.eq("_id", new ObjectId(id)))).thenReturn(deleteResult);

    // WHEN
    boolean result = repository.removeById(id);

    // THEN
    verify(collection).deleteOne(Filters.eq("_id", new ObjectId(id)));
    assertThat(result).isTrue();
  }

  @Test
  void removeById_shouldThrowInternalServerErrorOnException() {
    // GIVEN
    String id = new ObjectId().toString();
    doThrow(new RuntimeException("Database error")).when(collection).deleteOne(Filters.eq("_id", new ObjectId(id)));

    // WHEN THEN
    assertThatThrownBy(() -> repository.removeById(id))
        .isInstanceOf(InternalServerError.class)
        .hasMessageContaining("Failed to delete");
  }

  private <T> FindIterable<T> mockFindIterable(T... items) {
    FindIterable<T> findIterable = mock(FindIterable.class);
    when(findIterable.iterator()).thenReturn(mockMongoCursor(items));
    return findIterable;
  }

  private <T> MongoCursor<T> mockMongoCursor(T... items) {
    MongoCursor<T> cursor = mock(MongoCursor.class);
    when(cursor.hasNext()).thenReturn(items.length > 0, Arrays.copyOf(new boolean[]{true}, items.length));
    when(cursor.next()).thenReturn(items[0], items.length > 1 ? items[1] : null);
    return cursor;
  }
}
