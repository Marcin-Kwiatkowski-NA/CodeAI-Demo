package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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
        // GIVEN: A new instance of the repository is created.
        // WHEN: The newId() method is called.
        // THEN: A new UUID string is returned.
        String id = repository.newId();
        assertNotNull(id, "New ID should not be null");
        assert(!id.isEmpty(), "New ID should not be empty");
    }

    @Test
    void testFindAll() {
        // GIVEN: The repository is initialized with a CqlSession.
        // WHEN: The findAll() method is called.
        // THEN: A list of Info objects is returned, each with a generated ID, title, and description.
        List<Info> infos = repository.findAll();
        assertNotNull(infos, "List of Info objects should not be null");
        assertEquals(1, infos.size(), "Should return at least one Info object");
        Info info = infos.get(0);
        assertNotNull(info.getId(), "Info object should have an ID");
        assertEquals("TestTitle", info.getTitle(), "Title should be 'TestTitle'");
        assertEquals("TestDescription", info.getDescription(), "Description should be 'TestDescription'");
    }

    @Test
    void testFindById() {
        // GIVEN: An Info object is inserted into the Cassandra database.
        // WHEN: The findById() method is called with the ID of the inserted object.
        // THEN: An Info object is returned with the same ID, title, and description as the inserted object.
        Info info = repository.findById("TestId");
        assertNotNull(info, "Info object should not be null");
        assertEquals("TestTitle", info.getTitle(), "Title should be 'TestTitle'");
        assertEquals("TestDescription", info.getDescription(), "Description should be 'TestDescription'");
    }

    @Test
    void testInsert() {
        // GIVEN: A new Info object is created.
        Info info = new Info();
        info.setId("TestId");
        info.setTitle("TestTitle");
        info.setDescription("TestDescription");

        // WHEN: The insert() method is called with the Info object.
        // THEN: The Info object is returned, and the object is inserted into the Cassandra database.
        Info insertedInfo = repository.insert(info);
        assertNotNull(insertedInfo, "Inserted Info object should not be null");
        assertEquals("TestId", insertedInfo.getId(), "ID should match");
        assertEquals("TestTitle", insertedInfo.getTitle(), "Title should match");
        assertEquals("TestDescription", insertedInfo.getDescription(), "Description should match");
    }

    @Test
    void testReplace() {
        // GIVEN: An Info object is inserted into the Cassandra database.
        Info info = new Info();
        info.setId("TestId");
        info.setTitle("TestTitle");
        info.setDescription("TestDescription");

        // WHEN: The replace() method is called with the ID of the inserted object and the updated Info object.
        // THEN: The Info object is returned, and the object is updated in the Cassandra database.
        Info replacedInfo = repository.replace("TestId", info);
        assertNotNull(replacedInfo, "Replaced Info object should not be null");
        assertEquals("TestTitle", replacedInfo.getTitle(), "Title should match");
        assertEquals("TestDescription", replacedInfo.getDescription(), "Description should match");
    }
}
