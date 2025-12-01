package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void givenSharedData_whenGetCreatedAt_thenReturnNullInitially() {
        // GIVEN
        // No setup required as createdAt is null initially

        // WHEN
        Date createdAt = sharedData.getCreatedAt();

        // THEN
        assertEquals(null, createdAt);
    }

    @Test
    void givenSharedData_whenSetCreatedAt_thenCreatedAtIsUpdated() {
        // GIVEN
        Date now = new Date();

        // WHEN
        sharedData.setCreatedAt(now);

        // THEN
        assertEquals(now, sharedData.getCreatedAt());
    }

    @Test
    void givenSharedData_whenOnPrePersist_thenCreatedAtIsSetToCurrentDate() {
        // GIVEN
        // No additional setup required

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertEquals(true, sharedData.getCreatedAt() != null);
        assertEquals(true, Math.abs(sharedData.getCreatedAt().getTime() - new Date().getTime()) < 1000);
    }
}
