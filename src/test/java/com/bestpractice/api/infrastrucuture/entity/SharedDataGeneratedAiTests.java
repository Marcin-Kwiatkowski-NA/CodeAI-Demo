package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        // GIVEN: createdAt is initially null
        assertNull(sharedData.getCreatedAt());

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistOverwritesExistingCreatedAt() {
        // GIVEN: createdAt is set to a past date
        Date pastDate = new Date(System.currentTimeMillis() - 100000);
        sharedData.setCreatedAt(pastDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a more recent date
        assertTrue(sharedData.getCreatedAt().after(pastDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsNullWithoutException() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN & THEN: setting createdAt to null should not throw any exception
        assertDoesNotThrow(() -> sharedData.setCreatedAt(nullDate));
    }

    @Test
    void testOnPrePersistDoesNotThrowException() {
        // GIVEN: a valid SharedData instance
        assertNotNull(sharedData);

        // WHEN & THEN: calling onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
    }

    @Test
    void testMultipleOnPrePersistCallsUpdateCreatedAt() throws InterruptedException {
        // GIVEN: call onPrePersist once
        sharedData.onPrePersist();
        Date firstDate = sharedData.getCreatedAt();

        // WHEN: wait and call onPrePersist again
        Thread.sleep(10);
        sharedData.onPrePersist();
        Date secondDate = sharedData.getCreatedAt();

        // THEN: second date should be after first date
        assertTrue(secondDate.after(firstDate));
    }
}
