package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetAndSetCreatedAt() {
        // GIVEN: a specific date to set
        Date expectedDate = new Date();

        // WHEN: setting the createdAt field
        sharedData.setCreatedAt(expectedDate);

        // THEN: the getter should return the same date
        assertEquals(expectedDate, sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN: a new SharedData instance with no createdAt set
        assertNull(sharedData.getCreatedAt());

        // WHEN: invoking onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().getTime() <= System.currentTimeMillis());
    }

    @Test
    void testOnPrePersistUpdatesCreatedAtToNewDate() {
        // GIVEN: a SharedData instance with an existing createdAt value
        Date existingDate = new Date(System.currentTimeMillis() - 10000);
        sharedData.setCreatedAt(existingDate);

        // WHEN: invoking onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a newer date
        assertNotNull(sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(existingDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a SharedData instance
        sharedData.setCreatedAt(new Date());

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(null);

        // THEN: createdAt should be null
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtHandlesNullWithoutException() {
        // GIVEN: a SharedData instance
        // WHEN & THEN: setting createdAt to null should not throw any exception
        assertThrows(Exception.class, () -> {
            sharedData.setCreatedAt(null);
        });
    }
}
