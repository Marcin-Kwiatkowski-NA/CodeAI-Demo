package com.bestpractice.api.infrastrucuture.persistent.cassandra;

        // GIVEN: An Info object with ID "id1" is created.

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
        Info info1 = new Info();
        info1.setId("id1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");

        // WHEN: removeById("id1") is called.
        // THEN: The Info object with ID "id1" is removed from the Cassandra database.
        boolean removed = repository.removeById("id1");

        // THEN: The returned boolean should be true, and the Info object with ID "id1" should be removed.
        assertTrue(removed, "Removal should be successful");
    }
}
