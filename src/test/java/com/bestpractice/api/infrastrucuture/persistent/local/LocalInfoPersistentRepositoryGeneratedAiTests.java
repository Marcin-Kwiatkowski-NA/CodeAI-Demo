package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(LocalInfoPersistentRepositoryGeneratedAiTests.class)
class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void newId_returnsValidUUID() {
        // GIVEN: A new ID should be generated.
        // WHEN: The newId() method is called.
        // THEN: A valid UUID string is returned.
        String id = repository.newId();
        assertNotNull(id, "Generated ID cannot be null");
        assertTrue(id.length() > 32, "Generated ID should be longer than 32 characters");
    }

    @Test
    void findAll_returnsAllInfo() {
        // GIVEN: The repository should contain some data.
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

        // WHEN: The findAll() method is called.
        // THEN: A list containing all inserted Info objects is returned.
        List<Info> allInfos = repository.findAll();
        assertEquals(2, allInfos.size(), "Should return all inserted Info objects");
        assertNotNull(allInfos, "Returned list cannot be null");
    }

    @Test
    void findById_returnsInfoById() {
        // GIVEN: An Info object with a specific ID exists in the repository.
        Info info1 = new Info();
        info1.setId("id1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");

        repository.insert(info1);

        // WHEN: The findById("id1") method is called.
        // THEN: The Info object with ID "id1" is returned.
        Info foundInfo = repository.findById("id1");
        assertNotNull(foundInfo, "Info object cannot be null");
        assertEquals("id1", foundInfo.getId(), "ID should match");
        assertEquals("Title1", foundInfo.getTitle(), "Title should match");
        assertEquals("Description1", foundInfo.getDescription(), "Description should match");
    }

    @Test
    void insert_insertsInfoAndReturnsIt() {
        // GIVEN: A new Info object.
        Info info1 = new Info();
        info1.setId("id1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");

        // WHEN: The insert(info1) method is called.
        // THEN: The info1 object is added to the repository, and the same info1 object is returned.
        Info insertedInfo = repository.insert(info1);
        assertEquals(info1, insertedInfo, "Inserted Info object should be the same as the input");
    }

    @Test
    void replace_replacesInfoById() {
        // GIVEN: An Info object with a specific ID exists in the repository.
        Info info1 = new Info();
        info1.setId("id1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");

        repository.insert(info1);

        // GIVEN: A new Info object with the same ID.
        Info newInfo = new Info();
        newInfo.setId("id1");
        newInfo.setTitle("NewTitle1");
        newInfo.setDescription("NewDescription1");

        // WHEN: The replace("id1", newInfo) method is called.
        // THEN: The Info object with ID "id1" is replaced with the newInfo object, and the same newInfo object is returned.
        Info replacedInfo = repository.replace("id1", newInfo);
        assertNotNull(replacedInfo, "Replaced Info object cannot be null");
        assertEquals("id1", replacedInfo.getId(), "ID should match");
        assertEquals("New```java
        assertEquals("NewTitle1", replacedInfo.getTitle(), "Title should match");
        assertEquals("NewDescription1", replacedInfo.getDescription(), "Description should match");
    }

    @Test
    void removeById_removesInfoById() {
        // GIVEN: An Info object with a specific ID exists in the repository.
        Info info1 = new Info();
        info1.setId("id1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");

        repository.insert(info1);

        // WHEN: The removeById("id1") method is called.
        // THEN: The Info object with ID "id1" is removed from the repository, and true is returned.
        boolean removed = repository.removeById("id1");
        assertTrue(removed, "Removal should be successful");
        assertNull(repository.findById("id1"), "Info with ID 'id1' should not exist after removal");

        // GIVEN: An Info object with a specific ID exists in the repository.
        Info info2 = new Info();
        info2.setId("id2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");

        repository.insert(info2);

        // WHEN: The removeById("id2") method is called.
        // THEN: The Info object with ID "id2" is removed from the repository, and true is returned.
        boolean removed2 = repository.removeById("id2");
        assertTrue(removed2, "Removal should be successful");
        assertNull(repository.findById("id2"), "Info with ID 'id2' should not exist after removal");
    }
}
