package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.entity.SharedData;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.mongodb.client.FindIterable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MongoInfoPersistentRepositoryGeneratedAiTests.class)
class MongoInfoPersistentRepositoryGeneratedAiTests {

    private MongoInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MongoInfoPersistentRepository(Mockito.mock(MongoClient.class), Mockito.mock(MongoDatabase.class));
    }

    @Test
    void newId() {
        String id = repository.newId();
        assert id != null;
    }

    @Test
    void findAll() {
        // GIVEN a new Info object
        Info info = new Info();
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN the findAll method is called
        List<Info> data = repository.findAll();

        // THEN the data list should not be null and contain the info object
        assert data != null;
        assert data.size() == 1;
        assert data.get(0).getId().equals("testId");
        assert data.get(0).getTitle().equals("Test Title");
        assert data.get(0).getDescription().equals("Test Description");
    }

    @Test
    void findById() {
        // GIVEN a test ID
        String id = "testId";

        // WHEN the findById method is called with the test ID
        Info info = repository.findById(id);

        // THEN the info object should not be null and have the correct ID
        assert info != null;
        assert info.getId().equals(id);
        assert info.getTitle().equals("Test Title");
        assert info.getDescription().equals("Test Description");
    }

    @Test
    void insert() {
        // GIVEN a new Info object
        Info info = new Info();
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN the insert method is called
        Info insertedInfo = repository.insert(info);

        // THEN the insertedInfo object should not be null and have the correct ID
        assert insertedInfo != null;
        assert insertedInfo.getId().equals("testId");
        assert insertedInfo.getTitle().equals("Test Title");
        assert insertedInfo.getDescription().equals("Test Description");
    }

    @Test
    void replace() {
        // GIVEN a new Info object
        Info info = new Info();
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // WHEN the replace method is called with the ID and the info object
        Info replacedInfo = repository.replace("testId", info);

        // THEN the replacedInfo object should not be null and have the correct ID
        assert replacedInfo != null;
        assert replacedInfo.getId().equals("testId");
        assert replacedInfo.getTitle().equals("Test Title");
        assert replacedInfo.getDescription().equals("Test Description");
    }

    @Test
    void removeById() {
        // GIVEN a test ID
        String id = "testId";

        // WHEN the removeById method is called with the test ID
        boolean removed = repository.removeById(id);

        // THEN the removal should be acknowledged
        assert removed == true;
    }
}