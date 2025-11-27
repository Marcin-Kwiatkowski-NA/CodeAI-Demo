package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
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
    void givenSharedData_whenOnPrePersist_thenCreatedAtIsSet() {
        // GIVEN
        Date beforePersist = new Date();

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertNotNull(sharedData.getCreatedAt());
        assertEquals(true, sharedData.getCreatedAt().after(beforePersist) || sharedData.getCreatedAt().equals(beforePersist));
    }

    @Test
    void givenSharedData_whenSetCreatedAt_thenCreatedAtIsUpdated() {
        // GIVEN
        Date newDate = new Date();

        // WHEN
        sharedData.setCreatedAt(newDate);

        // THEN
        assertEquals(newDate, sharedData.getCreatedAt());
    }

    @Test
    void givenSharedData_whenGetCreatedAt_thenReturnsCorrectValue() {
        // GIVEN
        Date expectedDate = new Date();
        sharedData.setCreatedAt(expectedDate);

        // WHEN
        Date actualDate = sharedData.getCreatedAt();

        // THEN
        assertEquals(expectedDate, actualDate);
    }
}
