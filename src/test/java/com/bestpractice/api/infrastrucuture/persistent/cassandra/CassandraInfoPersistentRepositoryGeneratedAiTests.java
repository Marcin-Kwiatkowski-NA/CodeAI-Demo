package com.bestpractice.api.infrastrucuture.persistent.cassandra;

        info1.setTitle("NewTitle1");

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
        info1.setDescription("NewDescription1");
        repository.insert(info1);

        // WHEN: replace("id1", info1) is called.
        // THEN: The Info object is returned, and the title and description are updated in the database.

        Info replacedInfo = repository.replace("id1", info1);
        assertNotNull(replacedInfo, "Replaced Info should not be null");
        assertEquals("id1", replacedInfo.getId(), "ID should match");
        assertEquals("NewTitle1", replacedInfo.getTitle(), "Title should match");
        assertEquals("NewDescription1", replacedInfo.getDescription(), "Description should match");
    }

    @Test
    void removeById() {
        // GIVEN: An Info object with an ID "id1" is inserted into the database.
        Info info1 = new Info();
        info1.setId("id1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        // WHEN: removeById("id1") is called.
        // THEN: The Info object with the ID "id1" is removed from the database.

        boolean removed = repository.removeById("id1");
        assertTrue(removed, "Removal should be successful");

        // Verify that the object is no longer in the database.
        List<Info> infos = repository.findAll();
        assertEquals(0, infos.size(), "Should return 0 info objects after removal");
    }
}
