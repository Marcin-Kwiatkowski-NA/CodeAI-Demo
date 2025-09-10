package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

@Test
class SharedDataGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: A new instance of SharedData is created.
    }

    @Test
    void createdAtIsInitializedOnPrePersist() {
        // GIVEN: A new instance of SharedData is created.
        SharedData sharedData = new SharedData();

        // WHEN: The onPrePersist method is called.
        sharedData.onPrePersist();

        // THEN: The createdAt field is initialized with the current date and time.
        assertEquals(new Date(), sharedData.getCreatedAt());
    }

    @Test
    void createdAtIsSetCorrectly() {
        // GIVEN: A new instance of SharedData is created.
        SharedData sharedData = new SharedData();

        // WHEN: The onPrePersist method is called.
        sharedData.onPrePersist();

        // THEN: The createdAt field is set to the current date and time.
        assertEquals(new Date(), sharedData.getCreatedAt());
    }

    @Test
    void createdAtIsNotNull() {
        // GIVEN: A new instance of SharedData is created.
        SharedData sharedData = new SharedData();

        // WHEN: The onPrePersist method is called.
        sharedData.onPrePersist();

        // THEN: The createdAt field is not null.
        assertNotNull(sharedData.getCreatedAt());
    }
}
