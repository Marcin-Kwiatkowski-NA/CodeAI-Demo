package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

assertEquals("id1", replacedInfo.getId(), "ID should match");
        assertEquals("NewTitle", replacedInfo.getTitle(), "Title should match");
        assertEquals("NewDescription", replacedInfo.getDescription(), "Description should match");
    }

    @Test
    void removeById_removes_info_by_id() {
        // GIVEN: An info object exists with a specific ID.
        Info info1 = new Info();
        info1.setId("id1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        // WHEN: The removeById("id1") method is called.
        // THEN: The info object with ID "id1" is removed from the repository.
        boolean removed = repository.removeById("id1");
        assertTrue(removed, "Removal should be successful");

        Info removedInfo = repository.findById("id1");
        assertNull(removedInfo, "Info should be null after removal");
    }
}
