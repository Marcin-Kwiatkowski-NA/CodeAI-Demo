package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.extension.ExtendWith.withFactory;

@ExtendWith(withFactory(SharedDataGeneratedAiTestsFactory.class))
class SharedDataGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: A new instance of SharedData
        SharedData sharedData = new SharedData();
    }

    @Test
    void onPrePersist_setsCreatedAtToCurrentDate() {
        // WHEN: The onPrePersist method is called
        sharedData.onPrePersist();

        // THEN: The createdAt field is set to the current date and time
        assertEquals(new Date(), sharedData.getCreatedAt());
    }

    @Test
    void getCreatedAt_returnsCreatedAtDate() {
        // GIVEN: A new instance of SharedData
        SharedData sharedData = new SharedData();

        // WHEN: The getCreatedAt method is called
        Date createdAt = sharedData.getCreatedAt();

        // THEN: The createdAt field is returned
        assertNotNull(createdAt);
    }

    @Test
    void setCreatedAt_updatesCreatedAtDate() {
        // GIVEN: A new instance of SharedData
        SharedData sharedData = new SharedData();

        // WHEN: The setCreatedAt method is called with a new Date
        sharedData.setCreatedAt(new Date());

        // THEN: The createdAt field is updated to the new date
        assertEquals(new Date(), sharedData.getCreatedAt());
    }
}

// Factory class to enable extension
class SharedDataGeneratedAiTestsFactory {
    public static <T extends SharedData> T create(T testObject) {
        return (T) testObject;
    }
}
