package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.datastax.oss.driver.api.core.CqlSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CassandraInfoPersistentRepositoryGeneratedAiTests {

    private CassandraInfoPersistentRepository repository;
    private CqlSession session;

    @BeforeEach
    void setUp() {
        session = CqlSession.builder().build();
        repository = new CassandraInfoPersistentRepository(session);
    }

    @Test
    void newId() {
        // GIVEN: A new repository instance is created.
        // WHEN: The newId() method is called.
        // THEN: A new UUID is returned.
        String newId = repository.newId();
        assertNotNull(newId, "New ID should not be null");
        assert(!newId.isEmpty(), "New ID should not be empty");
    }

    @Test
    void findAll() {
        // GIVEN: A new Info object is created and inserted into the Cassandra database.
        Info info = new Info();
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);

        // WHEN: The findAll() method is called.
        // THEN: A list of Info objects is returned, containing the inserted Info object.
        List<Info> infos = repository.findAll();
        assertEquals(1, infos.size(), "Should return one Info object");
        assertNotNull(infos.get(0), "Info object should not be null");
        assertEquals("testId", infos.get(0).getId(), "ID should match");
        assertEquals("Test Title", infos.get(0).getTitle(), "Title should match");
        assertEquals("Test Description", infos.get(0).getDescription(), "Description should match");
    }

    @Test
    void findById() {
        // GIVEN: A new Info object is created and inserted into the Cassandra database.
        Info info = new Info();
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);

        // WHEN: The findById("testId") method is called.
        // THEN: An Info object is returned, containing the data for the specified ID.
        Info foundInfo = repository.findById("testId");
        assertNotNull(foundInfo, "Info object should not be null");
        assertEquals("testId", foundInfo.getId(), "ID should match");
        assertEquals("Test Title", foundInfo.getTitle(), "Title should match");
        assertEquals("Test Description", foundInfo.getDescription(), "Description should match");
    }

    @Test
    void insert() {
        // GIVEN: A new Info object is created.
        Info info = new Info();
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN: The insert() method is called.
        // THEN: The Info object is returned, and the data is inserted into the Cassandra database.
        Info insertedInfo = repository.insert(info);
        assertNotNull(insertedInfo, "Inserted Info object should not be null");
        assertEquals("testId", insertedInfo.getId(), "ID should match");
        assertEquals("Test Title", insertedInfo.getTitle(), "Title should match");
        assertEquals("Test Description", insertedInfo.getDescription(), "Description should match");
    }

    @Test
    void replace() {
        // GIVEN: A new Info object is created and inserted into the Cassandra database.
        Info info = new Info();
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);

        // WHEN: The replace("testId", info) method is called.
        // THEN: The Info object is returned, and the data is updated in the Cassandra database.
        Info replacedInfo = repository.replace("testId", info);
        assertNotNull(replacedInfo, "Replaced Info object should not be null");
        assertEquals("testId", replacedInfo.getId(), "ID should match");
        assertEquals("Test Title", replacedInfo.getTitle(), "Title should match");
        assertEquals("Test Description", replacedInfo.getDescription```java
        assertEquals("Test Description", replacedInfo.getDescription(), "Description should match");
    }

    @Test
    void removeById() {
        // GIVEN: A new Info object is created and inserted into the Cassandra database.
        Info info = new Info();
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);

        // WHEN: The removeById("testId") method is called.
        // THEN: The Info object with the specified ID is removed from the Cassandra database.
        boolean removed = repository.removeById("testId");
        assertTrue(removed, "Removal should be successful");
        assertFalse(repository.findById("testId") != null, "Info should be removed");
    }
}
