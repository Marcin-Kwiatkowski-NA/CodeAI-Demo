package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RdbmsInfoPersistentRepositoryGeneratedAiTests.class)
class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    private RdbmsInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        var jdbcTemplate = newJdbcTemplate();
        repository = new RdbmsInfoPersistentRepository(jdbcTemplate);
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new ID should be generated.
        // WHEN: The newId() method is called.
        // THEN: A valid UUID string is returned.
        String id = repository.newId();
        assertNotNull(id, "Generated ID cannot be null");
        assertTrue(id.length() > 30, "Generated ID should be long enough");
    }

    @Test
    void findAll_returnsAllInfos() {
        // GIVEN: Some Info objects are inserted into the database.
        var info1 = new Info();
        info1.setId("id1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");

        var info2 = new Info();
        info2.setId("id2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");

        repository.insert(info1);
        repository.insert(info2);

        // WHEN: The findAll() method is called.
        // THEN: A list of all inserted Info objects is returned.
        List<Info> allInfos = repository.findAll();
        assertEquals(2, allInfos.size(), "Should return 2 infos");
        assertNotNull(allInfos, "Returned list cannot be null");
    }

    @Test
    void findById_returnsInfoById() {
        // GIVEN: An Info object is inserted into the database.
        var info = new Info();
        info.setId("id1");
        info.setTitle("Title1");
        info.setDescription("Description1");
        repository.insert(info);

        // WHEN: The findById("id1") method is called.
        // THEN: The Info object with id "id1" is returned.
        Info foundInfo = repository.findById("id1");
        assertNotNull(foundInfo, "Found info cannot be null");
        assertEquals("id1", foundInfo.getId(), "ID should match");
        assertEquals("Title1", foundInfo.getTitle(), "Title should match");
        assertEquals("Description1", foundInfo.getDescription(), "Description should match");
    }

    @Test
    void insert_insertsInfoAndReturnsIt() {
        // GIVEN: A new Info object is created.
        var info = new Info();
        info.setId("id1");
        info.setTitle("Title1");
        info.setDescription("Description1");
        // WHEN: The insert(info) method is called.
        // THEN: The Info object is inserted into the database, and the inserted object is returned.
        Info insertedInfo = repository.insert(info);
        assertNotNull(insertedInfo, "Inserted info cannot be null");
        assertEquals("id1", insertedInfo.getId(), "ID should match");
        assertEquals("Title1", insertedInfo.getTitle(), "Title should match");
        assertEquals("Description1", insertedInfo.getDescription(), "Description should match");
    }

    @Test
    void replace_updatesInfoByIdAndReturnsIt() {
        // GIVEN: An Info object is inserted into the database.
        var info = new Info();
        info.setId("id1");
        info.setTitle("Title1");
        info.setDescription("Description1");
        repository.insert(info);

        // WHEN: The replace("id1", info) method is called.
        // THEN:```java
        var updatedInfo = new Info();
        updatedInfo.setId("id1");
        updatedInfo.setTitle("Title2");
        updatedInfo.setDescription("Description2");
        Info replacedInfo = repository.replace("id1", updatedInfo);
        assertNotNull(replacedInfo, "Replaced info cannot be null");
        assertEquals("id1", replacedInfo.getId(), "ID should match");
        assertEquals("Title2", replacedInfo.getTitle(), "Title should match");
        assertEquals("Description2", replacedInfo.getDescription(), "Description should match");
    }

    @Test
    void removeById_removesInfoByIdAndReturnsTrue() {
        // GIVEN: An Info object is inserted into the database.
        var info = new Info();
        info.setId("id1");
        info.setTitle("Title1");
        info.setDescription("Description1");
        repository.insert(info);

        // WHEN: The removeById("id1") method is called.
        // THEN: The Info object with id "id1" is removed from the database, and true is returned.
        boolean removed = repository.removeById("id1");
        assertTrue(removed, "Removal should be successful");
        assertFalse(repository.findById("id1").getId().equals("id1"), "Info should not exist after removal");
    }

    private JdbcTemplate newJdbcTemplate() {
        return new JdbcTemplate(jdbcTemplate());
    }

    private InfoJdbcTemplate jdbcTemplate() {
        return new InfoJdbcTemplate();
    }
}