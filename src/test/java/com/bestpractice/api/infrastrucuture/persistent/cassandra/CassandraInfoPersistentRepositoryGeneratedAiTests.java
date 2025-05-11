package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.datastax.oss.driver.api.core.CqlSession;

@ExtendWith(CassandraInfoPersistentRepositoryGeneratedAiTests.class)
class CassandraInfoPersistentRepositoryGeneratedAiTests {

    private CassandraInfoPersistentRepository repository;
    private CqlSession session;

    @BeforeEach
    void setUp() {
        session = CqlSession.builder().build();
        repository = new CassandraInfoPersistentRepository(session);
    }

    @Test
    void testNewId() {
        // GIVEN: A new repository instance is created.
        // WHEN: The newId() method is called.
        // THEN: A new UUID string is returned.
        String id = repository.newId();
        assertNotNull(id, "New ID should not be null");
        assert(!id.isEmpty(), "New ID should not be empty");
    }

    @Test
    void testFindAll() {
        // GIVEN: A new Info object is created and inserted into the Cassandra database.
        Info info = new Info();
        info.setId("test_id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);

        // WHEN: The findAll() method is called.
        // THEN: A list of Info objects is returned, containing the inserted Info object.
        List<Info> infos = repository.findAll();
        assertEquals(1, infos.size(), "Should return one Info object");
        assertNotNull(infos.get(0), "Returned Info object should not be null");
        assertEquals("test_id", infos.get(0).getId(), "ID should match");
        assertEquals("Test Title", infos.get(0).getTitle(), "Title should match");
        assertEquals("Test Description", infos.get(0).getDescription(), "Description should match");
    }

    @Test
    void testFindById() {
        // GIVEN: A new Info object is created and inserted into the Cassandra database.
        Info info = new Info();
        info.setId("test_id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);

        // WHEN: The findById("test_id") method is called.
        // THEN: An Info object is returned, containing the data for the specified ID.
        Info foundInfo = repository.findById("test_id");
        assertNotNull(foundInfo, "Found Info object should not be null");
        assertEquals("test_id", foundInfo.getId(), "ID should match");
        assertEquals("Test Title", foundInfo.getTitle(), "Title should match");
        assertEquals("Test Description", foundInfo.getDescription(), "Description should match");
    }

    @Test
    void testInsert() {
        // GIVEN: A new Info object is created.
        Info info = new Info();
        info.setId("test_id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN: The insert() method is called.
        // THEN: The Info object is returned, and the data is inserted into the Cassandra database.
        Info insertedInfo = repository.insert(info);
        assertNotNull(insertedInfo, "Inserted Info object should not be null");
        assertEquals("test_id", insertedInfo.getId(), "ID should match");
        assertEquals("Test Title", insertedInfo.getTitle(), "Title should match");
        assertEquals("Test Description", insertedInfo.getDescription(), "Description should match");
    }
}
