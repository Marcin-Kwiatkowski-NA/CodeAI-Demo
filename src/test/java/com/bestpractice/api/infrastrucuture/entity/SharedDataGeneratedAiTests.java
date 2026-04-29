package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals(null, sharedData.getCreatedAt());

        // WHEN: invoking onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be set to a non-null value close to current time
        assertNotNull(sharedData.getCreatedAt());
        assertTrue(sharedData.getCreatedAt().getTime() <= System.currentTimeMillis());
    }

    @Test
    void testOnPrePersistOverwritesExistingCreatedAt() {
        // GIVEN: an existing createdAt date
        Date oldDate = new Date(System.currentTimeMillis() - 100000);
        sharedData.setCreatedAt(oldDate);

        // WHEN: invoking onPrePersist
        sharedData.onPrePersist();

        // THEN: createdAt should be updated to a newer date
        assertTrue(sharedData.getCreatedAt().after(oldDate));
    }

    @Test
    void testSetCreatedAtWithNullDoesNotThrowException() {
        // GIVEN: a null value for createdAt
        Date nullDate = null;

        // WHEN: setting null value
        sharedData.setCreatedAt(nullDate);

        // THEN: createdAt should be null and no exception thrown
        assertEquals(null, sharedData.getCreatedAt());
    }
}
