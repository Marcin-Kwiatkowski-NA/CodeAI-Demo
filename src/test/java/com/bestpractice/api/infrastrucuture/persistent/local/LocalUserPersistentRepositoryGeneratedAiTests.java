package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

// GIVEN: There is no user with the specified ID in the repository.
        // WHEN: The removeById("nonexistentId") method is called.
        // THEN: True is returned, and the user object is not removed.
        assertTrue(repository.removeById("nonexistentId"));
        assertEquals(1, repository.users.size());
    }
}
