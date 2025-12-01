package com.bestpractice.api.infrastrucuture.persistent.mongo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
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
import java.util.ArrayList;
import java.util.List;
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
  void newId_shouldGenerateNewObjectId() {
    // GIVEN

    // WHEN
    String newId = repository.newId();

    // THEN
    assertThat(newId).isNotNull();
    assertThat(newId).hasSize(24); // ObjectId length
  }

  @Test
  void findAll_shouldReturnListOfInfo() {
    // GIVEN
    MongoInfoEntity entity1 = mock(MongoInfoEntity.class);
    MongoInfoEntity entity2 = mock(MongoInfoEntity.class);
    Info info1 = mock(Info.class);
    Info info2 = mock(Info.class);

    when(entity1.convertTo()).thenReturn(info1);
    when(entity2.convertTo()).thenReturn(info2);

    FindIterable<MongoInfoEntity> findIterable = mock(FindIterable.class);
    MongoCursor<MongoInfoEntity> cursor = mock(MongoCursor.class);

    when(collection.find()).thenReturn(findIterable);
    when(findIterable.iterator()).thenReturn(cursor);
    when(cursor.hasNext()).thenReturn(true, true, false);
    when(cursor.next()).thenReturn(entity1, entity2);

    // WHEN
    List<Info> result = repository.findAll();

    // THEN
    assertThat(result).containsExactly(info1, info2);
  }

  @Test
  void findById_shouldReturnInfoWhenFound() {
    // GIVEN
    String id = new ObjectId().toString();
    MongoInfoEntity entity = mock(MongoInfoEntity.class);
    Info info = mock(Info.class);

    when(entity.convertTo()).thenReturn(info);
    when(collection.find(Filters.eq("_id", new ObjectId(id)))).thenReturn(mock(FindIterable.class));
    when(collection.find(Filters.eq("_id", new ObjectId(id))).first()).thenReturn(entity);

    // WHEN
    Info result = repository.findById(id);

    // THEN
    assertThat(result).isEqualTo(info);
  }

  @Test
  void findById_shouldThrowInternalServerErrorWhenNotFound() {
    // GIVEN
    String id = new ObjectId().toString();
    when(collection.find(Filters.eq("_id", new ObjectId(id))).first()).thenReturn(null);

    // WHEN THEN
    assertThatThrownBy(() -> repository.findById(id))
        .isInstanceOf(InternalServerError.class)
        .hasMessageContaining("Failed to get detail from database");
  }

  @Test
  void insert_shouldInsertInfoSuccessfully() {
    // GIVEN
    Info info = mock(Info.class);
    MongoInfoEntity entity = mock(MongoInfoEntity.class);

    when(MongoInfoEntity.convertFrom(info)).thenReturn(entity);

    // WHEN
    Info result = repository.insert(info);

    // THEN
    verify(collection).insertOne(entity);
    assertThat(result).isEqualTo(info);
  }

  @Test
  void replace_shouldReplaceInfoSuccessfully() {
    // GIVEN
    String id = new ObjectId().toString();
    Info info = mock(Info.class);
    MongoInfoEntity entity = mock(MongoInfoEntity.class);
    ReplaceOptions options = new ReplaceOptions().upsert(true);
    UpdateResult updateResult = mock(UpdateResult.class);

    when(entity.getId()).thenReturn(new ObjectId(id));
    when(MongoInfoEntity.convertFrom(info)).thenReturn(entity);
    when(collection.replaceOne(Filters.eq("_id", entity.getId()), entity, options)).thenReturn(updateResult);
    when(updateResult.wasAcknowledged()).thenReturn(true);

    // WHEN
    Info result = repository.replace(id, info);

    // THEN
    assertThat(result).isEqualTo(info);
  }

  @Test
  void replace_shouldThrowInternalServerErrorWhenNotAcknowledged() {
    // GIVEN
    String id = new ObjectId().toString();
    Info info = mock(Info.class);
    MongoInfoEntity entity = mock(MongoInfoEntity.class);
    ReplaceOptions options = new ReplaceOptions().upsert(true);
    UpdateResult updateResult = mock(UpdateResult.class);

    when(entity.getId()).thenReturn(new ObjectId(id));
    when(MongoInfoEntity.convertFrom(info)).thenReturn(entity);
    when(collection.replaceOne(Filters.eq("_id", entity.getId()), entity, options)).thenReturn(updateResult);
    when(updateResult.wasAcknowledged()).thenReturn(false);

    // WHEN THEN
    assertThatThrownBy(() -> repository.replace(id, info))
        .isInstanceOf(InternalServerError.class)
        .hasMessageContaining("Failed to insert");
  }

  @Test
  void removeById_shouldRemoveInfoSuccessfully() {
    // GIVEN
    String id = new ObjectId().toString();
    ObjectId objectId = new ObjectId(id);
    DeleteResult deleteResult = mock(DeleteResult.class);

    when(collection.deleteOne(Filters.eq("_id", objectId))).thenReturn(deleteResult);
    when(deleteResult.wasAcknowledged()).thenReturn(true);

    // WHEN
    boolean result = repository.removeById(id);

    // THEN
    assertThat(result).isTrue();
  }

  @Test
  void removeById_shouldThrowInternalServerErrorWhenNotAcknowledged() {
    // GIVEN
    String id = new ObjectId().toString();
    ObjectId objectId = new ObjectId(id);
    DeleteResult deleteResult = mock(DeleteResult.class);

    when(collection.deleteOne(Filters.eq("_id", objectId))).thenReturn(deleteResult);
    when(deleteResult.wasAcknowledged()).thenReturn(false);

    // WHEN THEN
    assertThatThrownBy(() -> repository.removeById(id))
        .isInstanceOf(InternalServerError.class)
        .hasMessageContaining("Failed to delete");
  }
}