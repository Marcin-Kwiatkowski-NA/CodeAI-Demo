package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@BeforeAll
static class LocalInfoPersistentRepositoryGeneratedAiTests {
}

class LocalInfoPersistentRepositoryGeneratedAiTests {
  private LocalInfoPersistentRepository repository;
  private Info info;

  @BeforeEach
  void setUp() {
    repository = new LocalInfoPersistentRepository();
    info = new Info();
  }

  @Test
  void newId() {
    // GIVEN: A new ID should be generated.
    // WHEN: newId() is called.
    // THEN: A new UUID string is returned.
    String generatedId = repository.newId();
    assert generatedId != null;
  }

  @Test
  void findAll() {
    // GIVEN: The repository has some data.
    repository.insert(new Info());
    repository.insert(new Info());
    // WHEN: findAll() is called.
    // THEN: A list of all inserted Info objects is returned.
    List<Info> allInfos = repository.findAll();
    assert allInfos != null;
    assert allInfos.size() > 0;
  }

  @Test
  void findById() {
    // GIVEN: An Info object exists with a specific ID.
    info.setId("testId");
    repository.insert(info);
    // WHEN: findById("testId") is called.
    // THEN: The Info object with ID "testId" is returned.
    Info foundInfo = repository.findById("testId");
    assert foundInfo != null;
    assert foundInfo.getId().equals("testId");
  }

  @Test
  void insert() {
    // GIVEN: No data exists in the repository.
    // WHEN: insert(new Info()) is called.
    // THEN: The new Info object is added to the repository, and the same object is returned.
    Info insertedInfo = repository.insert(new Info());
    assert insertedInfo != null;
    assert repository.findAll().size() == 1;
  }

  @Test
  void replace() {
    // GIVEN: An Info object exists with a specific ID.
    info.setId("testId");
    repository.insert(info);
    // WHEN: replace("testId", new Info()) is called.
    // THEN: The existing Info object with ID "testId" is replaced with the new Info object, and null is returned.
    Info replacedInfo = repository.replace("testId", new Info());
    assert replacedInfo != null;
    assert repository.findAll().size() == 1;
  }

  @Test
  void removeById() {
    // GIVEN: An Info object exists with a specific ID.
    info.setId("testId");
    repository.insert(info);
    // WHEN: removeById("testId") is called.
    // THEN: The Info object with ID "testId" is removed from the repository, and true is returned.
    boolean removed = repository.removeById("testId");
    assert removed;
    assert repository.findAll().size() == 0;
  }
}
