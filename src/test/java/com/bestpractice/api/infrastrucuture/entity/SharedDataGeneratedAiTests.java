package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

class SharedDataGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset createdAt to null before each test
        // This ensures that tests are independent and don't rely on previous test states.
    }

    @Test
    void onPrePersist_setsCreatedAtToCurrentDate() {
        // GIVEN: A new instance of SharedData is created.
        SharedData sharedData = new SharedData();

        // WHEN: The onPrePersist method is called.
        sharedData.onPrePersist();

        // THEN: The createdAt field is set to the current date and time.
        Date createdAt = sharedData.getCreatedAt();
        assert createdAt != null;
    }
}
