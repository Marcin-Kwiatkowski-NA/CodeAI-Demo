package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;

class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testSetAndGetCreatedAt() {
        // GIVEN a specific date
        Date specificDate = new Date(1633036800000L); // 2021-10-01 00:00:00 UTC

        // WHEN setting the createdAt field
        sharedData.setCreatedAt(specificDate);

        // THEN the getter should return the same date
        assertThat(sharedData.getCreatedAt()).isEqualTo(specificDate);
    }

    @Test
    void testSetCreatedAtNull() {
        // GIVEN a null value
        // WHEN setting the createdAt field to null
        sharedData.setCreatedAt(null);

        // THEN the getter should return null
        assertThat(sharedData.getCreatedAt()).isNull();
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN an instance with no createdAt value
        // WHEN invoking onPrePersist
        sharedData.onPrePersist();

        // THEN the createdAt field should be set to a non-null timestamp
        Date createdAt = sharedData.getCreatedAt();
        assertThat(createdAt).isNotNull();
        assertThat(createdAt).isBeforeOrEqualTo(new Date());
    }

    @Test
    void testOnPrePersistOverridesExistingDate() {
        // GIVEN an instance with a pre-set createdAt date
        Date oldDate = new Date(1609459200000L); // 2021-01-01 00:00:00 UTC
        sharedData.setCreatedAt(oldDate);

        // WHEN invoking onPrePersist
        sharedData.onPrePersist();

        // THEN the createdAt field should be updated to a new timestamp
        Date newCreatedAt = sharedData.getCreatedAt();
        assertThat(newCreatedAt).isNotEqualTo(oldDate);
        assertThat(newCreatedAt).isAfter(oldDate);
    }

    @Test
    void testOnPrePersistMultipleTimesUpdatesTimestamp() throws InterruptedException {
        // GIVEN an instance
        // WHEN invoking onPrePersist the first time
        sharedData.onPrePersist();
        Date firstTimestamp = sharedData.getCreatedAt();

        // Simulate a small delay
        Thread.sleep(10);

        // WHEN invoking onPrePersist the second time
        sharedData.onPrePersist();
        Date secondTimestamp = sharedData.getCreatedAt();

        // THEN the second timestamp should be after the first one
        assertThat(secondTimestamp).isAfter(firstTimestamp);
    }
}
