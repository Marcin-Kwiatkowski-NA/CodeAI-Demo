package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

@ExtendWith(RdbmsInfoPersistentRepositoryGeneratedAiTests.class)
class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset state for each test
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new ID is expected
        // WHEN: newId() is called
        // THEN: A valid UUID string is returned
        String id = RdbmsInfoPersistentRepository.this.newId();
        assert id != null;
    }

    @Test
    void findAll_returnsAllInfos() {
        // GIVEN: An InfoPersistentRepository is created
        // WHEN: findAll() is called
        // THEN: A list of Info objects is returned
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title 1");
        info1.setDescription("Description 1");

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title 2");
        info2.setDescription("Description 2");

        RdbmsInfoPersistentRepository repository = new RdbmsInfoPersistentRepository(null);
        repository.insert(info1);
        repository.insert(info2);

        var result = repository.findAll();
        assert result != null;
        assert result.size() == 2;
    }

    @Test
    void findById_returnsInfoById() {
        // GIVEN: An InfoPersistentRepository is created
        // WHEN: findById("1") is called
        // THEN: The Info object with id "1" is returned
        InfoPersistentRepository repository = new RdbmsInfoPersistentRepository(null);
        Info info = repository.findById("1");
        assert info != null;
        assert info.getId().equals("1");
        assert info.getTitle().equals("Title 1");
        assert info.getDescription().equals("Description 1");
    }

    @Test
    void insert_insertsInfoWithCreatedAt() {
        // GIVEN: An InfoPersistentRepository is created
        // WHEN: insert(info) is called
        // THEN: The Info object is inserted into the database, and createdAt is set to the current date
        InfoPersistentRepository repository = new RdbmsInfoPersistentRepository(null);
        Info info = new Info();
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        var insertedInfo = repository.insert(info);

        assert insertedInfo != null;
        assert insertedInfo.getId() != null;
        assert insertedInfo.getCreatedAt() != null;
    }

    @Test
    void replace_updatesInfo() {
        // GIVEN: An InfoPersistentRepository is created
        // WHEN: replace("1", info) is called
        // THEN: The Info object with id "1" is updated in the database
        InfoPersistentRepository repository = new RdbmsInfoPersistentRepository(null);
        Info info = new Info();
        info.setId("1");
        info.setTitle("New Title");
        info.setDescription("New Description");

        repository.replace("1", info);

        Info retrievedInfo = repository.findById("1");

        assert retrievedInfo != null;
        assert retrievedInfo.getId().equals("1");
        assert retrievedInfo.getTitle().equals("New Title");
        assert retrievedInfo.getDescription().equals("New Description");
    }
}
