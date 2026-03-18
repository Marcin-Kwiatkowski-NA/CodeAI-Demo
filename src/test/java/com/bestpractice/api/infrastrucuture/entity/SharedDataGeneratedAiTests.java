package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN - a specific date to set
        Date expectedDate = new Date();

        // WHEN - setting the createdAt field
        sharedData.setCreatedAt(expectedDate);

        // THEN - verify that the getter returns the same date
        assertEquals(expectedDate, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN - a new SharedData instance with no createdAt set
        assertNull(sharedData.getCreatedAt());

        // WHEN - invoking onPrePersist
        sharedData.onPrePersist();

        // THEN - verify that createdAt is now set to a non-null value
        assertNotNull(sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt() instanceof Date);
    }

    @Test
    void testOnPrePersistOverwritesExistingCreatedAt() {
        // GIVEN - an existing createdAt value
        Date oldDate = new Date(System.currentTimeMillis() - 10000);
        sharedData.setCreatedAt(oldDate);

        // WHEN - invoking onPrePersist
        sharedData.onPrePersist();

        // THEN - verify that createdAt has been updated to a newer date
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN - a null value
        Date nullDate = null;

        // WHEN - setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN - verify that createdAt is null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testMultipleOnPrePersistCallsProduceNewTimestamps() throws InterruptedException {
        // GIVEN - first persist call
        sharedData.onPrePersist();
        Date firstTimestamp = sharedData.getCreatedAt();

        // WHEN - wait a bit and call onPrePersist again
        Thread.sleep(5);
        sharedData.onPrePersist();
        Date secondTimestamp = sharedData.getCreatedAt();

        // THEN - verify that the timestamp was updated
        assertTrue(secondTimestamp.after(firstTimestamp));
    }
}
