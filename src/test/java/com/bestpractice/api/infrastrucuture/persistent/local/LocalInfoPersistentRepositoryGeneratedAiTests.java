package com.bestpractice.api.infrastrucuture.persistent.local;

        Info foundInfo = LocalInfoPersistentRepository.this.findById("testId1");

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
        assert foundInfo != null;
        assert foundInfo.getId().equals("testId1");
        assert foundInfo.getTitle().equals("Test Title");
        assert foundInfo.getDescription().equals("Test Description");
    }

    @Test
    void replace_replacesInfoById() {
        // GIVEN: An instance of the repository with some data
        LocalInfoPersistentRepository repository = new LocalInfoPersistentRepository();
        Info info1 = new Info();
        info1.setId("testId1");
        info1.setTitle("Test Title");
        info1.setDescription("Test Description");
        LocalInfoPersistentRepository.this.insert(info1);

        // WHEN: The replace("testId1", new Info()) method is called
        // THEN: The info with id "testId1" is replaced with the new info object
        Info newInfo = new Info();
        newInfo.setId("newTestId1");
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");
        LocalInfoPersistentRepository.this.replace("testId1", newInfo);
        Info foundInfo = LocalInfoPersistentRepository.this.findById("newTestId1");
        assert foundInfo != null;
        assert foundInfo.getId().equals("newTestId1");
        assert foundInfo.getTitle().equals("New Title");
        assert foundInfo.getDescription().equals("New Description");
    }

    @Test
    void removeById_removesInfoById() {
        // GIVEN: An instance of the repository with some data
        LocalInfoPersistentRepository repository = new LocalInfoPersistentRepository();
        Info info1 = new Info();
        info1.setId("testId1");
        info1.setTitle("Test Title");
        info1.setDescription("Test Description");
        LocalInfoPersistentRepository.this.insert(info1);

        // WHEN: The removeById("testId1") method is called
        // THEN: The info with id "testId1" is removed from the list of infos
        boolean removed = LocalInfoPersistentRepository.this.removeById("testId1");
        assert removed;
        Info foundInfo = LocalInfoPersistentRepository.this.findById("testId1");
        assert foundInfo == null;
    }
}
