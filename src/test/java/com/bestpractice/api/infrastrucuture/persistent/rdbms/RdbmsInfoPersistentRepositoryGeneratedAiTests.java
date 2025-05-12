package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RdbmsInfoPersistentRepositoryGeneratedAiTests.class)
class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    private RdbmsInfoPersistentRepository rdbmsInfoPersistentRepository;

    @BeforeEach
    void setUp() {
        rdbmsInfoPersistentRepository = new RdbmsInfoPersistentRepository(null);
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new ID is expected
        // WHEN: newId() is called
        // THEN: A valid UUID string is returned
        String id = rdbmsInfoPersistentRepository.newId();
        assertNotNull(id, "New ID should not be null");
        assertTrue(id.length() > 30, "UUID should be at least 30 characters long");
    }

    @Test
    void findAll_returnsAllInfos() {
        // GIVEN: An Info object exists
        Info info1 = new Info();
        info1.setId("id1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");

        Info info2 = new Info();
        info2.setId("id2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");

        // WHEN: findAll() is called
        // THEN: A list of all Info objects is returned
        List<Info> allInfos = rdbmsInfoPersistentRepository.findAll();
        assertEquals(2, allInfos.size(), "Should return 2 infos");
        assertNotNull(allInfos, "List of infos should not be null");
    }

    @Test
    void findById_returnsInfoById() {
        // GIVEN: An Info object exists with an ID
        Info info = new Info();
        info.setId("id1");
        info.setTitle("Title1");
        info.setDescription("Description1");

        // WHEN: findById("id1") is called
        // THEN: The Info object with ID "id1" is returned
        Info foundInfo = rdbmsInfoPersistentRepository.findById("id1");
        assertNotNull(foundInfo, "Info should not be null");
        assertEquals("id1", foundInfo.getId(), "ID should match");
        assertEquals("Title1", foundInfo.getTitle(), "Title should match");
        assertEquals("Description1", foundInfo.getDescription(), "Description should match");
    }

    @Test
    void insert_insertsInfoAndReturnsIt() {
        // GIVEN: An Info object is provided
        Info info = new Info();
        info.setTitle("Title1");
        info.setDescription("Description1");

        // WHEN: insert(info) is called
        // THEN: The Info object is returned, and the info is inserted into the database
        Info insertedInfo = rdbmsInfoPersistentRepository.insert(info);
        assertNotNull(insertedInfo, "Inserted info should not be null");
        assertEquals("id1", insertedInfo.getId(), "ID should match");
        assertEquals("Title1", insertedInfo.getTitle(), "Title should match");
        assertEquals("Description1", insertedInfo.getDescription(), "Description should match");
    }
}
