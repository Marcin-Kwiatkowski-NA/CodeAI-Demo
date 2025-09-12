package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.extension.ExtendWith.withExtension;

@ExtendWith(withExtension(SharedDataExtension.class))
class SharedDataGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset the createdAt field before each test.
        // This is necessary because the createdAt field is not final.
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
    void createdAtIsSetAndFormattedCorrectly() {
        // GIVEN: A new instance of SharedData is created.
        SharedData sharedData = new SharedData();

        // WHEN: The onPrePersist method is called.
        sharedData.onPrePersist();

        // THEN: The createdAt field is set to the current date and time, and formatted as "yyyy-MM-dd HH:mm:ss".
        assertEquals("2024-01-01 12:00:00", sharedData.getCreatedAt().toString());
    }
}

class SharedDataExtension implements org.junit.jupiter.api.extension.ExtensionContextAware {
    @Override
    public void accept(org.junit.jupiter.api.extension.ExtensionContext context) {
    }
}
