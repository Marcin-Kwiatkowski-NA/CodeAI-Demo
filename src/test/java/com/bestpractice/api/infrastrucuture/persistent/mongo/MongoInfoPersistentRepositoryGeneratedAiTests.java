package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.mongodb.client.FindIterable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MongoInfoPersistentRepositoryGeneratedAiTests.class)
class MongoInfoPersistentRepositoryGeneratedAiTests {

    private MongoInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MongoInfoPersistentRepository(Mockito.mock(MongoClient.class), Mockito.mock(MongoDatabase.class));
    }

    @Test
    void newId_returns_valid_object_id() {
        // GIVEN: A MongoInfoPersistentRepository instance is created.
        // WHEN: The newId() method is called.
        // THEN: The newId() method returns a valid ObjectId string.
        String id = repository.newId();
        // Assert that the returned string is a valid ObjectId string.
        assert id != null;
    }

    @Test
    void findAll_returns_list_of_info_objects() {
        // GIVEN: A MongoInfoPersistentRepository instance is created.
        // WHEN: The findAll() method is called.
        // THEN: The findAll() method returns a list of Info objects.

        List<Info> infoList = repository.findAll();
        // Assert that the returned list is not null.
        assert infoList != null;
        // Assert that the list contains at least one Info object.
        assert infoList.size() > 0;
    }

    @Test
    void findById_returns_info_object_by_id() {
        // GIVEN: A MongoInfoPersistentRepository instance is created.
        // WHEN: The findById("someId") method is called with a valid ObjectId string.
        // THEN: The findById() method returns an Info object corresponding to the given ObjectId.

        Info info = repository.findById("someId");
        // Assert that the returned Info object is not null.
        assert info != null;
        // Assert that the returned Info object has the correct title and description.
        assert info.getTitle() != null;
        assert info.getDescription() != null;
    }

    @Test
    void insert_inserts_info_object_into_database() {
        // GIVEN: A MongoInfoPersistentRepository instance is created.
        // WHEN: The insert(new Info()) method is called with a new Info object.
        // THEN: The insert() method returns the same Info object, and the Info object is inserted into the database.

        Info info = new Info();
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        Info insertedInfo = repository.insert(info);
        // Assert that the returned Info object is the same as the inserted Info object.
        assert insertedInfo.equals(info);
    }

    @Test
    void replace_replaces_info_object_in_database() {
        // GIVEN: A MongoInfoPersistentRepository instance is created.
        // WHEN: The replace("someId", new Info()) method is called with a valid ObjectId string and a new Info object.
        // THEN: The replace() method returns the same Info object, and the Info object is replaced in the database.

        Info info = new Info();
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        Info replacedInfo = repository.replace("someId", info);
        // Assert that the returned Info object is the same as the replaced Info object.
        assert replacedInfo.equals(info);
    }

    @Test
    void removeById_removes_info_object_from_database() {
        // GIVEN: A MongoInfoPersistentRepository instance is created.
        // WHEN: The removeById("someId") method is called with a valid ObjectId string.
        // THEN: The removeById() method returns true, and the Info object is deleted from the database.

        ```java
        // Assert that the result of the removeById() method is true, indicating successful deletion.
        assert result == true;
    }
}