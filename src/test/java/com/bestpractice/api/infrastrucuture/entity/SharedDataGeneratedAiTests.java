package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void givenSharedDataInstance_whenGetCreatedAt_thenReturnNullInitially() {
        // GIVEN: A new instance of SharedData

        // WHEN: Retrieving the createdAt value
        Date createdAt = sharedData.getCreatedAt();

        // THEN: The createdAt value should be null initially
        assertThat(createdAt).isNull();
    }

    @Test
    void givenSharedDataInstance_whenSetCreatedAt_thenValueIsUpdated() {
        // GIVEN: A new instance of SharedData and a specific date
        Date testDate = new Date();

        // WHEN: Setting the createdAt value
        sharedData.setCreatedAt(testDate);

        // THEN: The createdAt value should be updated to the test date
        assertThat(sharedData.getCreatedAt()).isEqualTo(testDate);
    }

    @Test
    void givenSharedDataInstance_whenOnPrePersist_thenCreatedAtIsSetToCurrentDate() {
        // GIVEN: A new instance of SharedData

        // WHEN: Calling the onPrePersist method
        sharedData.onPrePersist();

        // THEN: The createdAt value should be set to the current date
        assertThat(sharedData.getCreatedAt()).isNotNull();
        assertThat(sharedData.getCreatedAt()).isCloseTo(new Date(), 1000);
    }
}
