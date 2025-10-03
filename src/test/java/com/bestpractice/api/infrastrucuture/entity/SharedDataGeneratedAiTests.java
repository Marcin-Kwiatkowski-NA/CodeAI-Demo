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
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date date = new Date(1000000L);

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

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        long now = System.currentTimeMillis();
        assertTrue(Math.abs(sharedData.getCreatedAt().getTime() - now) < 2000);
    }

    @Test
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: createdAt is set to an old date
        Date oldDate = new Date(0L);
        sharedData.setCreatedAt(oldDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date
        assertNotEquals(oldDate, sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
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
    void testOnPrePersistDoesNotThrowExceptionWhenCreatedAtIsNull() {
        // GIVEN: createdAt is null
        sharedData.setCreatedAt(null);

        // WHEN & THEN: onPrePersist should not throw any exception
        assertDoesNotThrow(() -> sharedData.onPrePersist());
        assertNotNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsFutureDate() {
        // GIVEN: a future date
        Date futureDate = new Date(System.currentTimeMillis() + 1000000L);

        // WHEN: setting createdAt to a future date
        sharedData.setCreatedAt(futureDate);

        // THEN: getter should return the same future date
        assertEquals(futureDate, sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtAcceptsPastDate() {
        // GIVEN: a past date
        Date pastDate = new Date(System.currentTimeMillis() - 1000000L);

        // WHEN: setting createdAt to a past date
        sharedData.setCreatedAt(pastDate);

        // THEN: getter should return the same past date
        assertEquals(pastDate, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistUpdatesCreatedAtEvenIfAlreadySet() {
        // GIVEN: createdAt is set to a specific date
        Date initialDate = new Date(System.currentTimeMillis() - 5000L);
        sharedData.setCreatedAt(initialDate);

        // WHEN: onPrePersist is called
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a new date after the initial date
        assertTrue(sharedData.getCreatedAt().after(initialDate));
    }

    @Test
    void testSetCreatedAtWithInvalidTypeThrowsClassCastException() {
        // GIVEN: an invalid type for createdAt (simulated via casting)
        Object invalidDate = new Object();

        // WHEN & THEN: setting createdAt with invalid type should throw ClassCastException
        assertThrows(ClassCastException.class, () -> {
            sharedData.setCreatedAt((Date) invalidDate);
        });
    }
}
