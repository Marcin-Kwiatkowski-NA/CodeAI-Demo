package com.bestpractice.api.infrastrucuture.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({})
public class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    public void setUp() {
        sharedData = new SharedData();
    }

    @Test
    public void testOnPrePersistSetsCreatedAt() {
        // GIVEN
        Date beforeDate = new Date();

        // WHEN
        sharedData.onPrePersist();
        Date afterDate = new Date();

        // THEN
        assertNotNull(sharedData.getCreatedAt(), "createdAt should not be null");
        assertTrue(sharedData.getCreatedAt().after(beforeDate), "createdAt should be set to a date after the pre-persist call");
    }

    @Test
    public void testGetCreatedAtReturnsCorrectValue() {
        // GIVEN
        Date expectedDate = new Date();
        sharedData.setCreatedAt(expectedDate);

        // WHEN
        Date actualDate = sharedData.getCreatedAt();

        // THEN
        assertNotNull(actualDate, "getCreatedAt should not return null");
        assertTrue(actualDate.equals(expectedDate), "getCreatedAt should return the same date that was set");
    }

    @Test
    public void testSetCreatedAtSetsCorrectValue() {
        // GIVEN
        Date expectedDate = new Date();

        // WHEN
        sharedData.setCreatedAt(expectedDate);

        // THEN
        assertNotNull(sharedData.getCreatedAt(), "createdAt should not be null");
        assertTrue(sharedData.getCreatedAt().equals(expectedDate), "setCreatedAt should set the correct date");
    }
}
