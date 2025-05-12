package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class SharedDataGeneratedAiTests {

    @Test
    public void testOnPrePersistSetsCreatedAtToCurrentTime() {
        // GIVEN: A new instance of SharedData is created.
        SharedData sharedData = new SharedData();

        // WHEN: The onPrePersist method is called.
        sharedData.onPrePersist();

        // THEN: The createdAt field is set to the current date and time.
        assertEquals(new Date(), sharedData.getCreatedAt());
    }

    @Test
    public void testGetCreatedAtReturnsCreatedAtDate() {
        // GIVEN: A new instance of SharedData is created.
        SharedData sharedData = new SharedData();

        // WHEN: The getCreatedAt method is called.
        Date createdAt = sharedData.getCreatedAt();

        // THEN: The getCreatedAt method returns the createdAt date.
        assertNotNull(createdAt);
        assertEquals(new Date(), createdAt);
    }

    @Test
    public void testSetCreatedAtSetsNewDate() {
        // GIVEN: A new instance of SharedData is created.
        SharedData sharedData = new SharedData();

        // WHEN: The setCreatedAt method is called with a new Date object.
        sharedData.setCreatedAt(new Date());

        // THEN: The createdAt field is updated to the new date.
        assertEquals(new Date(), sharedData.getCreatedAt());
    }
}
