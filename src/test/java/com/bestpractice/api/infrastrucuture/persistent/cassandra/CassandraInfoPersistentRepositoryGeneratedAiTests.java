package com.bestpractice.api.infrastrucuture.persistent.cassandra;

    @Test

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
    void replace() {
        // GIVEN: A new Info object is created and persisted.
        Info info = new Info();
        info.setId("replaceTestId");
        info.setTitle("replaceTestTitle");
        info.setDescription("replaceDescription");
        repository.insert(info);

        // WHEN: replace("replaceTestId", info) is called.
        // THEN: The Info object is updated in the database, and the updated object is returned.

        Info updatedInfo = repository.replace("replaceTestId", info);

        // Reset the state to avoid side effects
        repository.removeById("replaceTestId");

        assertNotNull(updatedInfo, "Updated Info object should not be null");
        assertEquals("replaceTestTitle", updatedInfo.getTitle(), "Title should match");
        assertEquals("replaceDescription", updatedInfo.getDescription(), "Description should match");
    }
}
