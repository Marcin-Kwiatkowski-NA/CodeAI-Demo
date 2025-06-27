package com.bestpractice.api.infrastrucuture.persistent.local;

        LocalInfoPersistentRepository.this.replace("testId", replacementInfo);

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
        // THEN: The original Info object is replaced with the new Info object, and null is returned
        List<Info> allInfos = LocalInfoPersistentRepository.this.findAll();
        assert allInfos.size() == 1;
        assert allInfos.get(0).getId().equals("testId");
        assert allInfos.get(0).getTitle().equals("New Title");
        assert allInfos.get(0).getDescription().equals("New Description");
    }

    @Test
    void removeById_removesInfoById() {
        // GIVEN: An Info object is added to the repository
        LocalInfoPersistentRepository repository = new LocalInfoPersistentRepository();
        Info info = new Info();
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        LocalInfoPersistentRepository.this.insert(info);

        // WHEN: The removeById("testId") method is called
        // THEN: The Info object with id "testId" is removed from the repository, and true is returned
        boolean removed = LocalInfoPersistentRepository.this.removeById("testId");
        assert removed;

        // Verify that the Info object is no longer present in the repository
        List<Info> allInfos = LocalInfoPersistentRepository.this.findAll();
        assert allInfos.size() == 0;
    }
}
