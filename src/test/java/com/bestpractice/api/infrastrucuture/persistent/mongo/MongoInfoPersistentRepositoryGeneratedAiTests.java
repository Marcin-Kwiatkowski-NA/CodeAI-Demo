package com.bestpractice.api.infrastrucuture.persistent.mongo;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.*;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MongoInfoPersistentRepositoryGeneratedAiTests {

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
    when(mongoDatabase.getCollection("infos", MongoInfoEntity.class)).thenReturn(collection);
    repository = new MongoInfoPersistentRepository(mongoClient, mongoDatabase);
  }

  @Test
  void newId_shouldGenerateNewObjectIdAsString() {
    // GIVEN

    // WHEN
    String newId = repository.newId();

    // THEN
    assertThat(newId).isNotNull();
    assertThat(newId).isInstanceOf(String.class);
  }

  @Test
  void findAll_shouldReturnListOfInfo() {
    // GIVEN
    FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
    MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);
    MongoInfoEntity entity = mock(MongoInfoEntity.class);
    Info info = new Info();
    when(collection.find()).thenReturn(findIterable);
    when(findIterable.iterator()).thenReturn(cursor);
    when(cursor.hasNext()).thenReturn(true, false);
    when(cursor.next()).thenReturn(entity);
    when(entity.convertTo()).thenReturn(info);

    // WHEN
    List<Info> result = repository.findAll();

    // THEN
    assertThat(result).isNotNull();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isEqualTo(info);
  }

  @Test
  void findById_shouldReturnInfoWhenFound() {
    // GIVEN
    String id = new ObjectId().toString();
    MongoInfoEntity entity = mock(MongoInfoEntity.class);
    Info info = new Info();
    Bson filter = Filters.eq("_id", new ObjectId(id));
    when(collection.find(filter)).thenReturn(mock(FindIterable.class));
    when(collection.find(filter).first()).thenReturn(entity);
    when(entity.convertTo()).thenReturn(info);

    // WHEN
    Info result = repository.findById(id);

    // THEN
    assertThat(result).isNotNull();
    assertThat(result).isEqualTo(info);
  }

  @Test
  void findById_shouldThrowInternalServerErrorWhenNotFound() {
    // GIVEN
    String id = new ObjectId().toString();
    Bson filter = Filters.eq("_id", new ObjectId(id));
    when(collection.find(filter).first()).thenReturn(null);

    // WHEN THEN
    try {
      repository.findById(id);
    } catch (InternalServerError ex) {
      assertThat(ex).isInstanceOf(InternalServerError.class);
      assertThat(ex.getMessage()).isEqualTo("Failed to get detail from database");
    }
  }

  @Test
  void insert_shouldInsertInfoAndReturnIt() {
    // GIVEN
    Info info = new Info();
    MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);
    doNothing().when(collection).insertOne(entity);

    // WHEN
    Info result = repository.insert(info);

    // THEN
    assertThat(result).isNotNull();
    assertThat(result).isEqualTo(info);
  }

  @Test
  void replace_shouldReplaceInfoAndReturnIt() {
    // GIVEN
    String id = new ObjectId().toString();
    Info info = new Info();
    info.setId(id);
    MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);
    Bson filter = Filters.eq("_id", entity.getId());
    ReplaceOptions options = new ReplaceOptions().upsert(true);
    UpdateResult updateResult = mock(UpdateResult.class);
    when(collection.replaceOne(filter,entity, options)).thenReturn(updateResult);
    when(updateResult.wasAcknowledged()).thenReturn(true);

    // WHEN
    Info result = repository.replace(id, info);

    // THEN
    assertThat(result).isNotNull();
    assertThat(result).isEqualTo(info);
  }

  @Test
  void replace_shouldThrowInternalServerErrorWhenReplaceFails() {
    // GIVEN
    String id = new ObjectId().toString();
    Info info = new Info();
    info.setId(id);
    MongoInfoEntity entity = MongoInfoEntity.convertFrom(info);
    Bson filter = Filters.eq("_id", entity.getId());
    ReplaceOptions options = new ReplaceOptions().upsert(true);
    when(collection.replaceOne(filter, entity, options)).thenThrow(new RuntimeException("Replace failed"));

    // WHEN THEN
    try {
      repository.replace(id, info);
    } catch (InternalServerError ex) {
      assertThat(ex).isInstanceOf(InternalServerError.class);
      assertThat(ex.getMessage()).isEqualTo("Failed to insert");
    }
  }

  @Test
  void removeById_shouldRemoveInfoAndReturnTrueWhenAcknowledged() {
    // GIVEN
    String id = new ObjectId().toString();
    ObjectId objectId = new ObjectId(id);
    Bson filter = Filters.eq("_id", objectId);
    DeleteResult deleteResult = mock(DeleteResult.class);
    when(collection.deleteOne(filter)).thenReturn(deleteResult);
    when(deleteResult.wasAcknowledged()).thenReturn(true);

    // WHEN
    boolean result = repository.removeById(id);

    // THEN
    assertThat(result).isTrue();
  }

  @Test
  void removeById_shouldReturnFalseWhenNotAcknowledged() {
    // GIVEN
    String id = new ObjectId().toString();
    ObjectId objectId = new ObjectId(id);
    Bson filter = Filters.eq("_id", objectId);
    DeleteResult deleteResult = mock(DeleteResult.class);
    when(collection.deleteOne(filter)).thenReturn(deleteResult);
    when(deleteResult.wasAcknowledged()).thenReturn(false);

    // WHEN
    boolean result = repository.removeById(id);

    // THEN
    assertThat(result).isFalse();
  }

  @Test
  void removeById_shouldThrowInternalServerErrorWhenDeleteFails() {
    // GIVEN
    String id = new ObjectId().toString();
    ObjectId objectId = new ObjectId(id);
    Bson filter = Filters.eq("_id", objectId);
    when(collection.deleteOne(filter)).thenThrow(new RuntimeException("Delete failed"));

    // WHEN THEN
    try {
      repository.removeById(id);
    } catch (InternalServerError ex) {
      assertThat(ex).isInstanceOf(InternalServerError.class);
      assertThat(ex.getMessage()).isEqualTo("Failed to delete");
    }
  }
}