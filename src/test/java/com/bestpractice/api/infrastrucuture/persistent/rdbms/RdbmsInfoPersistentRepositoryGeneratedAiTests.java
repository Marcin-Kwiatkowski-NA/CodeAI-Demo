package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RdbmsInfoPersistentRepositoryGeneratedAiTests.class)
class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    private RdbmsInfoPersistentRepository repository;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        // Initialize the database with some sample data
        var sql = "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)";
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.update(sql, "id1", "Title 1", "Description 1");
        jdbcTemplate = new JdbcTemplate();
        repository = new RdbmsInfoPersistentRepository(jdbcTemplate);
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new ID should be generated.
        // WHEN: The newId() method is called.
        // THEN: A valid UUID string is returned.
        String id = repository.newId();
        assertNotNull(id, "ID should not be null");
        assertTrue(id.length() > 30, "UUID should be at least 30 characters long");
    }

    @Test
    void findAll_returnsAllInfos() {
        // GIVEN: The database contains some sample info records.
        // WHEN: The findAll() method is called.
        // THEN: A list of Info objects is returned, containing all the records from the database.
        var allInfos = repository.findAll();
        assertNotNull(allInfos, "List of infos should not be null");
        assertEquals(1, allInfos.size(), "Should return 1 infos");
    }

    @Test
    void findById_returnsInfoById() {
        // GIVEN: An info record exists with the specified ID.
        String id = repository.newId();
        Info info = repository.findById(id);
        assertNotNull(info, "Info object should not be null");
        assertEquals(id, info.getId(), "ID should match");
        assertNotNull(info.getCreatedAt(), "Created at should not be null");
    }

    @Test
    void insert_insertsInfoAndReturnsNewInfo() {
        // GIVEN: An Info object is created.
        Info info = new Info();
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN: The insert(info) method is called.
        Info insertedInfo = repository.insert(info);

        // THEN: The inserted Info object is returned, and its ID is not null.
        assertNotNull(insertedInfo, "Inserted info should not be null");
        assertEquals(info.getId(), insertedInfo.getId(), "ID should match");
        assertNotNull(insertedInfo.getCreatedAt(), "Created at should not be null");
    }

    @Test
    void replace_updatesInfoAndReturnsUpdatedInfo() {
        // GIVEN: An info record exists with the specified ID.
        String id = repository.newId();
        Info info = new Info();
        info.setTitle("Updated Title");
        info.setDescription("Updated Description");

        // WHEN: The replace(id, info) method is called with the ID and the updated Info object.
        Info updatedInfo = repository.replace(id, info);

        // THEN: The Info object with the specified ID is updated with the new title and description.
        assertEquals("Updated Title", updatedInfo.getTitle(), "Title should be updated");
        assertEquals("Updated Description", updatedInfo.getDescription(), "Description should be updated");
    }

    @Test
    void removeById_removesInfoByIdAndReturnsTrue() {
        // GIVEN: An info record exists with the specified ID.
        String id = repository.newId();
        // WHEN: The removeById(id) method is called with the ID.
        // THEN: The info record with the specified ID is removed from the database, and the method returns true.
        boolean removed = repository.removeById(id);
        assertTrue(removed, "Removal should be successful");
    }
}
