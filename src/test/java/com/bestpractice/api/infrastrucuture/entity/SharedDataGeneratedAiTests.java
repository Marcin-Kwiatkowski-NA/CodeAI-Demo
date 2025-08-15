package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testOnPrePersistSetsCreatedAtToCurrentDate() {
        // GIVEN: A new instance of SharedData is created.
        // WHEN: The onPrePersist method is called.
        // THEN: The createdAt field is set to the current date and time.
        sharedData.onPrePersist();
        Date createdAt = sharedData.getCreatedAt();
        assert createdAt != null;
        assert createdAt.equals(new Date());
    }
}
