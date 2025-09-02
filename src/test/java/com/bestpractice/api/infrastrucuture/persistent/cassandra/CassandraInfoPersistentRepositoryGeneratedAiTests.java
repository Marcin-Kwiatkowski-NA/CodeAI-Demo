package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
  public Info insert(Info info) {
    PreparedStatement preparedSelectInfo = session.prepare(insertInfo.build());
    ResultSet resultSet = session.execute(
        preparedSelectInfo.bind(info.getId(), info.getTitle(), info.getDescription()));

    if (!resultSet.wasApplied()) {
      return null;
    }
    return info;
  }

  @Override
  public Info replace(String id, Info info) {
    PreparedStatement preparedSelectInfo = session.prepare(updateInfo.build());
    ResultSet resultSet = session.execute(
        preparedSelectInfo.java
        preparedSelectInfo.bind(info.getTitle(), info.getDescription(), info.getId()));

    if (!resultSet.wasApplied()) {
      return null;
    }
    return info;
  }

  @Override
  public boolean removeById(String id) {
    PreparedStatement preparedSelectInfo = session.prepare(deleteInfo.build());
    ResultSet resultSet = session.execute(
        preparedSelectInfo.bind(id));

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
    session = CqlSession.builder().withCassSpec(CassSpec.builder().addKeyspace("infos").build()).build();
    repository = new CassandraInfoPersistentRepository(session);
    info = new Info();
  }

  @Test
  void findAll_shouldReturnAllInfos() {
    // GIVEN
    info.setId(repository.newId());
    info.setTitle("Test Title");
    info.setDescription("Test Description");
    repository.insert(info);

    // WHEN
    List<Info> allInfos = repository.findAll();

    // THEN
    assertEquals(1, allInfos.size());
    assertEquals("Test Title", allInfos.get(0).getTitle());
    assertEquals("Test Description", allInfos.get(0).getDescription());
  }

  @Test
  void findById_shouldReturnInfoById() {
    // GIVEN
    String id = repository.newId();
    info.setId(id);
    info.setTitle("Test Title");
    info.setDescription("Test Description");
    repository.insert(info);

    // WHEN
    Info foundInfo = repository.findById(id);

    // THEN
    assertNotNull(foundInfo);
    assertEquals("Test Title", foundInfo.getTitle());
    assertEquals("Test Description", foundInfo.getDescription());
    assertEquals(id, foundInfo.getId());
  }

  @Test
  void insert_shouldInsertInfo() {
    // GIVEN
    info.setId(repository.newId());
    info.setTitle("Test Title");
    info.setDescription("Test Description");

    // WHEN
    Info insertedInfo = repository.insert(info);

    // THEN
    assertNotNull(insertedInfo);
    assertEquals("Test Title", insertedInfo.getTitle());
    assertEquals("Test Description", insertedInfo.getDescription());
    assertEquals(info.getId(), insertedInfo.getId());
  }

  @Test
  void replace_shouldReplaceInfo() {
    // GIVEN
    String id = repository.newId();
    info.setId(id);
    info.setTitle("Test Title");
    info.setDescription("Test Description");
    repository.insert(info);

    // WHEN
    Info replacedInfo = repository.replace(id, info);

    // THEN
    assertNotNull(replacedInfo);
    assertEquals("Test Title", replacedInfo.getTitle());
    assertEquals("Test Description", replacedInfo.getDescription());
    assertEquals(id, replacedInfo.getId());
  }

  @Test
  void removeById_shouldRemoveInfo() {
    // GIVEN
    String id = repository.newId();
    info.setId(id);
    info.setTitle("Test Title");
    info.setDescription("Test Description");
    repository.insert(info);

    // WHEN
    boolean removed = repository.removeById(id);

    // THEN
    assertTrue(removed);
  }
}
