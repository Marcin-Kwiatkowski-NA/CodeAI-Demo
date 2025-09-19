package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.extension.ExtendWith.withExtension;

@withExtension(SharedDataExtension.class)
class SharedDataGeneratedAiTests {

    @BeforeEach
    void beforeEach() {
        // This method is called before each test.
        // It is used to reset the state of the SharedData object.
    }

    @Test
    void createdAtIsSetOnPrePersist() {
        // GIVEN: A new instance of SharedData is created.
        SharedData sharedData = new SharedData();

        // WHEN: The onPrePersist method is called.
        sharedData.onPrePersist();

        // THEN: The createdAt field is set to the current date and time.
        assertEquals(new Date(), sharedData.getCreatedAt());
    }

    @Test
    void createdAtIsNotSetIfNoPrePersistCall() {
        // GIVEN: A new instance of SharedData is created.
        SharedData sharedData = new SharedData();

        // WHEN: The onPrePersist method is not called.
        // THEN: The createdAt field is not set.
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void createdAtIsFormattedAsExpected() {
        // GIVEN: A new instance of SharedData is created.
        SharedData sharedData = new SharedData();

        // WHEN: The onPrePersist method is called.
        sharedData.onPrePersist();

        // THEN: The createdAt field is set to the current date and time, formatted as "yyyy-MM-dd HH:mm:ss".
        assertEquals("2024-07-26 10:30:00", sharedData.getCreatedAt().toString());
    }
}
