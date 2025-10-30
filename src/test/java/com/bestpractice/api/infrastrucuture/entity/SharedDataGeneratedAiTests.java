package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date();

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: a SharedData instance with no createdAt set
        assertNull(sharedData.getCreatedAt());

        // WHEN: calling onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistOverwritesExistingCreatedAt() {
        // GIVEN: a SharedData instance with an existing createdAt
        Date oldDate = new Date(System.currentTimeMillis() - 100000);
        sharedData.setCreatedAt(oldDate);

        // WHEN: calling onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new value
        assertNotEquals(oldDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtWithNull() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistAfterNullCreatedAt() {
        // GIVEN: createdAt is explicitly set to null
        sharedData.setCreatedAt(null);
        assertNull(sharedData.getCreatedAt());

        // WHEN: calling onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the same past date
        assertEquals(pastDate, sharedData.getCreatedAt());
    }

    @Test
    void testGetCreatedAtInitiallyNull() {
        // GIVEN: a new SharedData instance

        // WHEN: retrieving createdAt without setting it
        Date result = sharedData.getCreatedAt();

        // THEN: it should be null
        assertNull(result);
    }

    @Test
    void testOnPrePersistSetsCurrentTime() {
        // GIVEN: a SharedData instance with no createdAt set
        assertNull(sharedData.getCreatedAt());

        // WHEN: calling onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be close to current time
        long now = System.currentTimeMillis();
        long createdTime = sharedData.getCreatedAt().getTime();
        assertNotNull(sharedData.getCreatedAt());
        // Allowing a small delta for execution time
        long delta = Math.abs(now - createdTime);
        boolean withinRange = delta < 2000;
        assertEquals(true, withinRange);
    }
}
