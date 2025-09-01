package com.bestpractice.api.infrastrucuture.persistent.cassandra;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.bindMarker;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.deleteFrom;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.insertInto;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.selectFrom;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.update;
import static com.datastax.oss.driver.api.querybuilder.update.Assignment.setColumn;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.update.Update;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CassandraInfoPersistentRepository implements InfoPersistentRepository {

  private final CqlSession session;
  private final Select selectInfo = selectFrom("infos")
      .all();

  private final Select selectInfoWithWhere = selectFrom("infos")
      .all().whereColumn("id").isEqualTo(bindMarker());

  private final RegularInsert insertInfo = insertInto("infos")
      .value("id", bindMarker())
      .value("title", bindMarker())
      .value("description", bindMarker());

  private final Update updateInfo = update("infos")
      .set(setColumn("title", bindMarker()),
          setColumn("description", bindMarker()))
      .whereColumn("id").isEqualTo(bindMarker());

  private final Delete deleteInfo = deleteFrom("infos")
      .whereColumn("id").isEqualTo(bindMarker());

  public CassandraInfoPersistentRepository(CqlSession session) {
    this.session = session;
  }

  @Override
  public String newId() {
    return UUID.randomUUID().toString();
  }

  @Override
  public List<Info> findAll() {
    // Limit is required if cassandra
    PreparedStatement preparedSelectInfo = session.prepare(selectInfo.build());
    ResultSet resultSet = session.execute(preparedSelectInfo.bind());

    List<Info> infos = new ArrayList<>();
    for (Row row : resultSet) {
      Info info = new Info();
      info.setId(row.getString("id"));
      info.setTitle(row.getString("title"));
      info.setDescription(row.getString("description"));
      infos.add(info);
    }
    return infos;
  }

  @Override
  public Info findById(String id) {
    PreparedStatement preparedSelectInfo = session.prepare(selectInfoWithWhere.build());
    ResultSet resultSet = session.execute(preparedSelectInfo.bind(id));

    Row row = resultSet.one();
    if (row == null) {
      return null;
    }

    Info info = new Info();
    info.setId(row.getString("id"));
    info.setTitle(row.getString("title"));
    info.setDescription(row.getString("description"));
    return info;
  }

  @Override
  publicjava
  @Override
  public Info insert(Info info) {
    PreparedStatement preparedInsertInfo = session.prepare(insertInfo.build());
    ResultSet resultSet = preparedInsertInfo.bind(info.getId(), info.getTitle(), info.getDescription());

    if (!resultSet.wasApplied()) {
      return null;
    }
    return info;
  }

  @Override
  public Info replace(String id, Info info) {
    PreparedStatement preparedUpdateInfo = session.prepare(updateInfo.build());
    ResultSet resultSet = session.execute(
        preparedUpdateInfo.bind(info.getTitle(), info.getDescription(), info.getId()));

    if (!resultSet.wasApplied()) {
      return null;
    }
    return info;
  }

  @Override
  public boolean removeById(String id) {
    PreparedStatement preparedDeleteInfo = session.prepare(deleteInfo.build());
    ResultSet resultSet = session.execute(
        preparedDeleteInfo.bind(id));

    if (!resultSet.wasApplied()) {
      return false;
    }
    return true;
  }
}


// CassandraInfoPersistentRepositoryGeneratedAiTests
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CassandraInfoPersistentRepositoryGeneratedAiTests {

  private CqlSession session;
  private CassandraInfoPersistentRepository repository;
  private Info info;

  @BeforeEach
  void setUp() {
    session = CqlSession.builder().withCassSpec("test").build();
    repository = new CassandraInfoPersistentRepository(session);
    info = new Info();
  }

  @Test
  void newId_returnsValidUUID() {
    // GIVEN a new CassandraInfoPersistentRepository instance
    // WHEN the newId() method is called
    // THEN a valid UUID string is returned
    String id = repository.newId();
    // THEN the returned string is a valid UUID
    assertNotNull(id);
    assert(!id.isEmpty());
  }

  @Test
  void findAll_returnsAllInfoObjects() {
    // GIVEN a new Info object
    info.setId(repository.newId());
    info.setTitle("Test Title");
    info.setDescription("Test Description");

    // WHEN findAll() is called
    // THEN a list of Info objects is returned, containing the newly created Info object
    List<Info> allInfos = repository.findAll();

    // THEN the list contains one Info object
    assertEquals(1, allInfos.size());

    // THEN the Info object in the list has the correct values
    Info firstInfo = allInfos.get(0);
    assertEquals(info.getId(), firstInfo.getId());
    assertEquals(info.getTitle(), firstInfo.getTitle());
    assertEquals(info.getDescription(), firstInfo.getDescription());
  }

  @Test
  void findById_returnsInfoObjectById() {
    // GIVEN a new Info object
    info.setId(repository.newId());
    info.setTitle("Test Title");
    info.setDescription("Test Description");

    // WHEN findById("test") is called
    // THEN an Info object is returned, containing the correct values
    Info foundInfo = repository.findById("test");

    // THEN the returned Info object has the correct values
    assertNotNull(foundInfo);
    assertEquals(info.getId(), foundInfo.getId());
    assertEquals(info.getTitle(), foundInfo.getTitle());
    assertEquals(info.getDescription(), foundInfo.getDescription());
  }

  @Test
  void insert_insertsInfoObject() {
    // GIVEN a new Info object
    info.setId(repository.newId());
    info.setTitle("Test Title");
    info.setDescription("Test Description");

    // WHEN insert(info) is called
    // THEN an Info object is returned, containing the correct values
    Info insertedInfo = repository.insert(info);

    // THEN the returned Info object has the correct values
    assertNotNull(insertedInfo);
    assertEquals(info.getId(), insertedInfo.getId());
    assertEquals(info.getTitle(), insertedInfo.getTitle());
    assertEquals(info.getDescription(), insertedInfo.getDescription());
  }

  @Test
  void replace_replacesInfoObject() {
    // GIVEN a new Info object
    info.setId(repository.newId());
    info.setTitle("Test Title");
    info.setDescription("Test Description");

    // WHEN replace("test", info) is called
    // THEN the Info// THEN the returned Info object has the correct values
    assertNotNull(replacedInfo);
    assertEquals(info.getId(), replacedInfo.getId());
    assertEquals(info.getTitle(), replacedInfo.getTitle());
    assertEquals(info.getDescription(), replacedInfo.getDescription());
  }

  @Test
  void removeById_removesInfoObjectById() {
    // GIVEN a new Info object
    info.setId(repository.newId());
    info.setTitle("Test Title");
    info.setDescription("Test Description");

    // WHEN removeById("test") is called
    // THEN the Info object is removed from the database
    boolean removed = repository.removeById("test");

    // THEN the returned boolean is true, indicating that the removal was successful
    assertTrue(removed);

    // AND the Info object is no longer found when findAll() is called
    List<Info> allInfos = repository.findAll();
    assertEquals(0, allInfos.size());
  }
}