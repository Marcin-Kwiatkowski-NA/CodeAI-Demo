package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.bestpractice.api.infrastrucuture.entity.Info;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Test
class LocalInfoPersistentRepositoryGeneratedAiTests {

  private final LocalInfoPersistentRepository repository = new LocalInfoPersistentRepository();

  @BeforeEach
  void beforeEach() {
    repository.infos.clear();
  }

  @Test
  void newId_returnsValidUUID() {
    String id = repository.newId();
    assert id != null;
    assert id.length() > 20; // UUIDs are typically longer than 20 characters
  }

  @Test
  void findAll_returnsAllInfo() {
    Info info1 = new Info();
    info1.setId("testId1");
    info1.setTitle("Title1");
    info1.setDescription("Description1");
    repository.insert(info1);

    List<Info> allInfos = repository.findAll();
    assert allInfos != null;
    assert allInfos.size() == 1;
    assert allInfos.get(0).getId().equals("testId1");
    assert allInfos.get(0).getTitle().equals("Title1");
    assert allInfos.get(0).getDescription().equals("Description1");
  }

  @Test
  void findById_returnsInfoById() {
    Info info1 = new Info();
    info1.setId("testId1");
    info1.setTitle("Title1");
    info1.setDescription("Description1");
    repository.insert(info1);

    Info foundInfo = repository.findById("testId1");
    assert foundInfo != null;
    assert foundInfo.getId().equals("testId1");
    assert foundInfo.getTitle().equals("Title1");
    assert foundInfo.getDescription().equals("Description1");
  }

  @Test
  void insert_insertsInfoAndReturnsIt() {
    Info info1 = new Info();
    info1.setId("testId1");
    info1.setTitle("Title1");
    info1.setDescription("Description1");

    Info insertedInfo = repository.insert(info1);

    assert insertedInfo != null;
    assert insertedInfo.getId().equals("testId1");
    assert insertedInfo.getTitle().equals("Title1");
    assert insertedInfo.getDescription().equals("Description1");
  }

  @Test
  void replace_replacesInfoById() {
    Info info1 = new Info();
    info1.setId("testId1");
    info1.setTitle("Title1");
    info1.setDescription("Description1");
    repository.insert(info1);

    Info updatedInfo = new Info();
    updatedInfo.setId("testId1");
    updatedInfo.setTitle("NewTitle");
    updatedInfo.setDescription("NewDescription");
    repository.replace("testId1", updatedInfo);

    Info retrievedInfo = repository.findById("testId1");
    assert retrievedInfo != null;
    assert retrievedInfo.getId().equals("testId1");
    assert retrievedInfo.getTitle().equals("NewTitle");
    assert retrievedInfo.getDescription().equals("NewDescription");
  }

  @Test
  void removeById_removesInfoById() {
    Info info1 = new Info();
    info1.setId("testId1");
    info1.setTitle("Title1");
    info1.setDescription("Description1");
    repository.insert(info1);

    boolean removed = repository.removeById("testId1");
    assert removed;
    assert repository.findAll().isEmpty();
  }
}
