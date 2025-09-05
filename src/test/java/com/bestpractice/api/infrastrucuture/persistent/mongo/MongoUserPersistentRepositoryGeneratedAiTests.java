package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

@Test
    void removeById_doesNotThrowExceptionIfIdDoesNotExist() {
        // GIVEN: No MongoUserEntity exists with the specified id.
        // WHEN: The removeById() method is called with the id.
        // THEN: The method returns true (indicating successful removal).
        boolean removed = removeById("nonexistentId");
        assertTrue(removed);
    }
