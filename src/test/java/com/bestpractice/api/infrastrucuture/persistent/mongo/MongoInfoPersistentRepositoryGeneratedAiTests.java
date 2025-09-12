package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.mongodb.client.MongoClient;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MongoInfoPersistentRepositoryGeneratedAiTests.class)
class MongoInfoPersistentRepositoryGeneratedAiTests {

    private MongoInfoPersistentRepository repository;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoInfoEntity> collection;

    @BeforeEach
    void setUp() {
        mongoClient = MongoClient.create("mongodb://localhost:27017/");
        mongoDatabase = mongoClient.getDatabase("testdb");
        collection = mongoDatabase.getCollection("infos", MongoInfoEntity.class);
    }

    @Test
    void newId() {
        String id = repository.newId();
        assertNotNull(id, "New ID should not be null");
        assert(!id.isEmpty(), "New ID should not be empty");
    }

    @Test
    void findAll() {
        // Arrange
        Info info1 = new Info();
        info1.setId("id1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");

        Info info2 = new Info();
        info2.setId("id2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");

        // Act
        repository.insert(info1);
        repository.insert(info2);
        List<Info> allInfos = repository.findAll();

        // Assert
        assertEquals(2, allInfos.size(), "Should return all inserted infos");
        assertTrue(allInfos.stream().anyMatch(i -> i.getId().equals("id1")), "Should contain info with id1");
        assertTrue(allInfos.stream().anyMatch(i -> i.getId().equals("id2")), "Should contain info with id2");
    }

    @Test
    void findById() {
        // Arrange
        Info info = new Info();
        info.setId("id3");
        info.setTitle("Title3");
        info.setDescription("Description3");

        // Act
        repository.insert(info);
        Info foundInfo = repository.findById("id3");

        // Assert
        assertEquals("id3", foundInfo.getId(), "ID should match");
        assertEquals("Title3", foundInfo.getTitle(), "Title should match");
        assertEquals("Description3", foundInfo.getDescription(), "Description should match");
    }

    @Test
    void insert() {
        // Arrange
        Info info = new Info();
        info.setId("id4");
        info.setTitle("Title4");
        info.setDescription("Description4");

        // Act
        Info insertedInfo = repository.insert(info);

        // Assert
        assertEquals("id4", insertedInfo.getId(), "ID should match");
        assertEquals("Title4", insertedInfo.getTitle(), "Title should match");
        assertEquals("Description4", insertedInfo.getDescription(), "Description should match");
    }

    @Test
    void replace() {
        // Arrange
        Info info = new Info();
        info.setId("id5");
        info.setTitle("Title5");
        info.setDescription("Description5");

        // Act
        Info replacedInfo = repository.replace("id5", info);

        // Assert
        assertEquals("id5", replacedInfo.getId(), "ID should match");
        assertEquals("Title5", replacedInfo.getTitle(), "Title should match");
        assertEquals("Description5", replacedInfo.getDescription(), "Description should match");
    }

    @Test
    void removeById() {
        // Arrange
        Info info = new Info();
        info.setId("id6");
        info.setTitle("Title6");
        info.setDescription("Description6");

        // Act
        repository.insert(info);
        boolean removed = repository.removeById("id6");

        // Assert
        assertFalse(removed, "Should be removed");
        assertNull(repository.findById("id6"), "Should return null after removal");
    }
}
