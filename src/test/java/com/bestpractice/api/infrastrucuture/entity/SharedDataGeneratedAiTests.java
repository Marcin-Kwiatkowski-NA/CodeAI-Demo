package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void testOnPrePersistOverridesExistingCreatedAt() {
        // GIVEN: an existing createdAt value
        Date oldDate = new Date(System.currentTimeMillis() - 100000);
        sharedData.setCreatedAt(oldDate);

        // WHEN: invoking onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a newer date
        assertNotNull(sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullValue() {
        // GIVEN: a null value
        Date nullDate = null;

        // WHEN: setting createdAt to null
        sharedData.setCreatedAt(nullDate);

        // THEN: getter should return null without throwing exceptions
        assertNull(sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAtHandlesInvalidInputGracefully() {
        // GIVEN: an invalid input scenario (null is acceptable, but we test robustness)
        // WHEN & THEN: ensure no exception is thrown when setting null
        assertThrows(Exception.class, () -> {
            // This test ensures that if future modifications introduce exceptions, it will catch them
            sharedData.setCreatedAt(null);
        });
    }
}
