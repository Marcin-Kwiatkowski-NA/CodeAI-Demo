package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class MongoInfoPersistentRepositoryGeneratedAiTests {

  private MongoInfoPersistentRepository repository;

  @Mock private MongoClient mockMongoClient;
  @Mock private MongoDatabase mockMongoDatabase;
  @Mock private MongoCollection<MongoInfoEntity> mockCollection;
  @Mock private FindIterable<MongoInfoEntity> mockFindIterable;
  @Mock private MongoCursor<MongoInfoEntity> mockCursor;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    when(mockMongoDatabase.getCollection("infos", MongoInfoEntity.class)).thenReturn(mockCollection);
    repository = new MongoInfoPersistentRepository(mockMongoClient, mockMongoDatabase);
  }

  @Test
  void newId_shouldGenerateNewObjectId() {
    // GIVEN

    // WHEN
    String newId = repository.newId();

    // THEN
    assertThat(newId).isNotNull();
    assertThat(new ObjectId(newId)).isInstanceOf(ObjectId.class);
  }

  @Test
  void findAll_shouldReturnAllInfos() {
    // GIVEN
    List<MongoInfoEntity> mockEntities = new ArrayList<>();
    mockEntities.add(new MongoInfoEntity(new ObjectId(), "Title1", "Description1"));
    mockEntities.add(new MongoInfoEntity(new ObjectId(), "Title2", "Description2"));

    when(mockCollection.find()).thenReturn(mockFindIterable);
    when(mockFindIterable.iterator()).thenReturn(mockCursor);
    when(mockCursor.hasNext()).thenReturn(true, true, false);
    when(mockCursor.next()).thenReturn(mockEntities.get(0), mockEntities.get(1));

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
    when(mockCollection.find()).thenThrow(new RuntimeException("Database error"));

    // WHEN & THEN
    assertThatThrownBy(() -> repository.findAll())
        .isInstanceOf(InternalServerError.class)
        .hasMessageContaining("Failed to get data of range from database");
  }

  @Test
  void findById_shouldReturnInfoById() {
    // GIVEN
    String id = new ObjectId().toString();
    MongoInfoEntity mockEntity = new MongoInfoEntity(new ObjectId(id), "Title", "Description");

    when(mockCollection.find(any(Bson.class))).thenReturn(mockFindIterable);
    when(mockFindIterable.first()).thenReturn(mockEntity);

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
    when(mockCollection.find(any(Bson.class))).thenThrow(new RuntimeException("Database error"));

    // WHEN & THEN
    assertThatThrownBy(() -> repository.findById(id))
        .isInstanceOf(InternalServerError.class)
        .hasMessageContaining("Failed to get detail from database");
  }

  @Test
  void insert_shouldInsertInfoSuccessfully() {
    // GIVEN
    Info info = new Info();
    info.setId(new ObjectId().toString());
    info.setTitle("Title");
    info.setDescription("Description");

    // WHEN
    Info result = repository.insert(info);

    // THEN
    verify(mockCollection).insertOne(any(MongoInfoEntity.class));
    assertThatresult).isEqualTo(info);
  }

  @Test
  void insert_shouldThrowInternalServerErrorOnException() {
    // GIVEN
    Info info = new Info();
    info.setId(new ObjectId().toString());
    info.setTitle("Title");
    info.setDescription("Description");

    doThrow(new RuntimeException("Insert error")).when(mockCollection).insertOne(any(MongoInfoEntity.class));

    // WHEN & THEN
    assertThatThrownBy(() -> repository.insert(info))
        .isInstanceOf(InternalServerError.class)
        .hasMessageContaining("Failed to insert");
  }

  @Test
  void replace_shouldReplaceInfoSuccessfully() {
    // GIVEN
    String id = new ObjectId().toString();
    Info info = new Info();
    info.setId(id);
    info.setTitle("Updated Title");
    info.setDescription("Updated Description");

    MongoInfoEntity mongoInfoEntity = MongoInfoEntity.convertFrom(info);
    ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
    UpdateResult mockUpdateResult = mock(UpdateResult.class);

    when(mockCollection.replaceOne(any(Bson.class), eq(mongoInfoEntity), eq(replaceOptions)))
        .thenReturn(mockUpdateResult);
    when(mockUpdateResult.wasAcknowledged()).thenReturn(true);

    // WHEN
    Info result = repository.replace(id, info);

    // THEN
    verify(mockCollection).replaceOne(any(Bson.class), eq(mongoInfoEntity), eq(replaceOptions));
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

    doThrow(new RuntimeException("Replace error"))
        .when(mockCollection)
        .replaceOne(any(Bson.class), any(MongoInfoEntity.class), any(ReplaceOptions.class));

    // WHEN & THEN
    assertThatThrownBy(() -> repository.replace(id, info))
        .isInstanceOf(InternalServerError.class)
        .hasMessageContaining("Failed to insert");
  }

  @Test
  void removeById_shouldRemoveInfoSuccessfully() {
    // GIVEN
    String id = new ObjectId().toString();
    DeleteResult mockDeleteResult = mock(DeleteResult.class);

    when(mockCollection.deleteOne(any(Bson.class))).thenReturn(mockDeleteResult);
    when(mockDeleteResult.wasAcknowledged()).thenReturn(true);

    // WHEN
    boolean result = repository.removeById(id);

    // THEN
    verify(mockCollection).deleteOne(any(Bson.class));
    assertThat(result).isTrue();
  }

  @Test
  void removeById_shouldThrowInternalServerErrorOnException() {
    // GIVEN
    String id = new ObjectId().toString();
    doThrow(new RuntimeException("Delete error")).when(mockCollection).deleteOne(any(Bson.class));

    // WHEN & THEN
    assertThatThrownBy(() -> repository.removeById(id))
        .isInstanceOf(InternalServerError.class)
        .hasMessageContaining("Failed to delete");
  }
}
