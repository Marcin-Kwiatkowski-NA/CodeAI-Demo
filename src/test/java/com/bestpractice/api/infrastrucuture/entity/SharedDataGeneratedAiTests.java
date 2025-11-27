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
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void givenNewSharedData_whenOnPrePersist_thenCreatedAtIsSet() {
        // GIVEN
        Date beforePersist = new Date();

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertNotNull(sharedData.getCreatedAt(), "CreatedAt should not be null after onPrePersist");
        assertEquals(true, sharedData.getCreatedAt().after(beforePersist) || sharedData.getCreatedAt().equals(beforePersist), "CreatedAt should be after or equal to the time before persist");
    }

    @Test
    void givenSharedData_whenSetCreatedAt_thenCreatedAtIsUpdated() {
        // GIVEN
        Date newDate = new Date();

        // WHEN
        sharedData.setCreatedAt(newDate);

        // THEN
        assertEquals(newDate, sharedData.getCreatedAt(), "CreatedAt should be updated to the new date");
    }

    @Test
    void givenSharedData_whenGetCreatedAt_thenReturnsCorrectValue() {
        // GIVEN
        Date expectedDate = new Date();
        sharedData.setCreatedAt(expectedDate);

        // WHEN
        Date actualDate = sharedData.getCreatedAt();

        // THEN
        assertEquals(expectedDate, actualDate, "GetCreatedAt should return the correct date");
    }
}
