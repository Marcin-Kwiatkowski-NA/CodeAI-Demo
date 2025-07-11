package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.UUID;

@ExtendWith(LocalInfoPersistentRepository.class)
class LocalInfoPersistentRepositoryGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset the list of infos before each test
        LocalInfoPersistentRepository repository = new LocalInfoPersistentRepository();
        repository.infos = Collections.emptyList();
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new instance of the repository
        // WHEN: The newId() method is called
        // THEN: A new UUID string is returned
        String id = LocalInfoPersistentRepository.this.newId();
        assert id != null;
    }

    @Test
    void findAll_returnsAllInfos() {
        // GIVEN: A list of Info objects is added to the repository
        LocalInfoPersistentRepository repository = new LocalInfoPersistentRepository();
        Info info1 = new Info();
        info1.setId("id1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);
        Info info2 = new Info();
        info2.setId("id2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");
        repository.insert(info2);

        // WHEN: The findAll() method is called
        // THEN: The list of all Info objects is returned
        List<Info> allInfos = LocalInfoPersistentRepository.this.findAll();
        assert allInfos != null;
        assert allInfos.size() == 2;
    }

    @Test
    void findById_returnsInfoById() {
        // GIVEN: An Info object with an ID is added to the repository
        LocalInfoPersistentRepository repository = new LocalInfoPersistentRepository();
        Info info1 = new Info();
        info1.setId("id1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        // WHEN: The findById("id1") method is called
        // THEN: The Info object with ID "id1" is returned
        Info foundInfo = LocalInfoPersistentRepository.this.findById("id1");
        assert foundInfo != null;
        assert foundInfo.getId().equals("id1");
        assert foundInfo.getTitle().equals("Title1");
        assert foundInfo.getDescription().equals("Description1");

        // Reset the list of infos before each test
        LocalInfoPersistentRepository repository2 = new LocalInfoPersistentRepository();
        repository2.infos = Collections.emptyList();
    }

    @Test
    void insert_insertsInfo() {
        // GIVEN: A new Info object is created
        LocalInfoPersistentRepository repository = new LocalInfoPersistentRepository();
        Info info1 = new Info();
        info1.setId("id1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        // WHEN: The insert(info1) method is called
        // THEN: The info1 object is added to the list of infos and is returned
        Info insertedInfo = LocalInfoPersistentRepository.this.insert(info1);
        assert insertedInfo != null;
        assert insertedInfo.getId().equals("id1");
        assert insertedInfo.getTitle().equals("Title1");
        assert insertedInfo.getDescription().equals("Description1");

        // Reset the list of infos before each test
        LocalInfoPersistentRepository repository2 = new LocalInfoPersistentRepository();
        repository2.infos = Collections.emptyList();
    }

    @Test
    void replace_replacesInfoById() {
        // GIVEN: An Info object with an ID is added to the repository
        LocalInfoPersistentRepository repository = new LocalInfoPersistentRepository();
        Info info1 = new Info();
        info1.setId("id1```java
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        // WHEN: The replace("id1", new Info()) method is called
        // THEN: The Info object with ID "id1" is replaced with a new Info object
        Info replacedInfo = LocalInfoPersistentRepository.this.replace("id1", new Info());
        assert replacedInfo != null;
        assert replacedInfo.getId().equals("id1");
        assert replacedInfo.getTitle().equals("Title1");
        assert replacedInfo.getDescription().equals("Description1");

        // Reset the list of infos before each test
        LocalInfoPersistentRepository repository2 = new LocalInfoPersistentRepository();
        repository2.infos = Collections.emptyList();
    }

    @Test
    void removeById_removesInfoById() {
        // GIVEN: An Info object with an ID is added to the repository
        LocalInfoPersistentRepository repository = new LocalInfoPersistentRepository();
        Info info1 = new Info();
        info1.setId("id1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        // WHEN: The removeById("id1") method is called
        // THEN: The Info object with ID "id1" is removed from the list of infos
        boolean removed = LocalInfoPersistentRepository.this.removeById("id1");
        assert removed;
        List<Info> allInfos = LocalInfoPersistentRepository.this.findAll();
        assert allInfos.size() == 1;

        // Reset the list of infos before each test
        LocalInfoPersistentRepository repository2 = new LocalInfoPersistentRepository();
        repository2.infos = Collections.emptyList();
    }
}