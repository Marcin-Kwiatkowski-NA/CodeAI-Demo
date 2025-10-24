package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testSetAndGetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date();

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(date);

        // THEN: the getter should return the same date
        assertEquals(date, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt()).isEqualTo(date);
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: a SharedData instance with no createdAt set
        assertNull(sharedData.getCreatedAt());

        // WHEN: calling onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value
        assertNotNull(sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt()).isNotNull();
    }

    @Test
    void testOnPrePersistOverwritesExistingCreatedAt() {
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
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null
        assertNull(sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt()).isNull();
    }

    @Test
    void testSetCreatedAtAcceptsNullWithoutException() {
        // GIVEN: a null date
        Date nullDate = null;

        // WHEN & THEN: setting createdAt to null should not throw any exception
        assertDoesNotThrow(() -> sharedData.setCreatedAt(nullDate));
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowException() {
        // GIVEN: a SharedData instance
        // WHEN & THEN: calling onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
    }

    @Test
    void testMultipleOnPrePersistCallsUpdateCreatedAt() throws InterruptedException {
        // GIVEN: a SharedData instance and first call to onPrePersist
        sharedData.onPrePersist();
        Date firstDate = sharedData.getCreatedAt();

        // WHEN: waiting a bit and calling onPrePersist again
        Thread.sleep(5);
        sharedData.onPrePersist();
        Date secondDate = sharedData.getCreatedAt();

        // THEN: the second date should be after the first date
        assertThat(secondDate).isAfter(firstDate);
    }

    @Test
    void testSetCreatedAtWithPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 100000);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the past date
        assertEquals(pastDate, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt()).isBefore(new Date());
    }

    @Test
    void testSetCreatedAtWithFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 100000);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the future date
        assertEquals(futureDate, sharedData.getCreatedAt());
        assertThat(sharedData.getCreatedAt()).isAfter(new Date(System.currentTimeMillis() - 5000));
    }
}
