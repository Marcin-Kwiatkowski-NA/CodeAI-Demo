package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    public void setUp() {
        sharedData = new SharedData();
    }

    @Test
    public void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date();

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt()).isEqualTo(date);
    }

    @Test
    public void testOnPrePersistSetsCreatedAt() {
        // GIVEN: a SharedData instance with no createdAt set
        assertNull(sharedData.getCreatedAt());

        // WHEN: calling onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value
        assertNotNull(sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt()).isNotNull();
    }

    @Test
    public void testOnPrePersistOverwritesExistingCreatedAt() {
        // GIVEN: a SharedData instance with an existing createdAt
        Date oldDate = new Date(System.currentTimeMillis() - 100000);
        sharedData.setCreatedAt(oldDate);

        // WHEN: calling onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt()).isAfter(oldDate);
    }

    @Test
    public void testSetCreatedAtWithNullValue() {
        // GIVEN: a SharedData instance

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(null);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt()).isNull();
    }

    @Test
    public void testSetCreatedAtAcceptsNullWithoutException() {
        // GIVEN: a SharedData instance

        // WHEN & THEN: setting createdAt to null should not throw any exception
        assertDoesNotThrow(() -> sharedData.setCreatedAt(null));
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    public void testOnPrePersistDoesNotThrowException() {
        // GIVEN: a SharedData instance

        // WHEN & THEN: calling onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    public void testSetCreatedAtWithFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 100000);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the same future date
        assertEquals(futureDate, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt()).isEqualTo(futureDate);
    }

    @Test
    public void testMultipleOnPrePersistCallsUpdateDate() throws InterruptedException {
        // GIVEN: a SharedData instance and first persist call
        sharedData.onPrePersist();
        Date firstPersistDate = sharedData.getCreatedAt();

        // WHEN: waiting and calling onPrePersist again
        Thread.sleep(5);
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a later date
        assertThat(sharedData.getCreatedAt()).isAfter(firstPersistDate);
    }

    @Test
    public void testSetCreatedAtWithPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the same past date
        assertEquals(pastDate, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt()).isEqualTo(pastDate);
    }
}
