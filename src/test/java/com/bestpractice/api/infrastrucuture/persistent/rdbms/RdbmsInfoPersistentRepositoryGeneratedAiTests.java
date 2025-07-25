package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@Test
class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    private RdbmsInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new RdbmsInfoPersistentRepository(new JdbcTemplate());
    }

    @Test
    void newId() {
        // GIVEN: A new ID should be generated.
        // WHEN: The newId() method is called.
        // THEN: A new UUID string is returned.
        String id = repository.newId();
        assert id != null;
    }

    @Test
    void findAll() {
        // GIVEN: The database contains some info records.
        // WHEN: The findAll() method is called.
        // THEN: A list of Info objects is returned, representing all records in the database.
        var infoList = repository.findAll();
        assert infoList != null;
        assert !infoList.isEmpty();
    }

    @Test
    void findById() {
        // GIVEN: An existing info record with a specific ID.
        String id = repository.newId();
        var info = repository.findById(id);
        assert info != null;
        assert info.getId() != null;
    }

    @Test
    void insert() {
        // GIVEN: A new Info object is created.
        Info info = new Info();
        info.setId(repository.newId());
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN: The insert(info) method is called.
        // THEN: The info record is inserted into the database, and the inserted Info object is returned.
        var insertedInfo = repository.insert(info);
        assert insertedInfo != null;
        assert insertedInfo.getId() != null;
    }

    @Test
    void replace() {
        // GIVEN: An existing info record with a specific ID.
        String id = repository.newId();
        Info info = new Info();
        info.setId(id);
        info.setTitle("New Title");
        info.setDescription("New Description");

        // WHEN: The replace(id, info) method is called with the ID and the updated info.
        // THEN: The existing info record is updated in the database with the new title and description, and the updated info object is returned.
        var replacedInfo = repository.replace(id, info);
        assert replacedInfo != null;
        assert replacedInfo.getId() != null;
    }

    @Test
    void removeById() {
        // GIVEN: An existing info record with a specific ID.
        String id = repository.newId();
        // WHEN: The removeById(id) method is called with the ID.
        // THEN: The info record with that ID is deleted from the database, and true is returned.
        boolean removed = repository.removeById(id);
        assert removed;
    }
}
