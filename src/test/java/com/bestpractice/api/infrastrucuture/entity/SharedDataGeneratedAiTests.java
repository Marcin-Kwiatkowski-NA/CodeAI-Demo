package com.bestpractice.api.infrastrucuture.entity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ReflectiveOperationsMethodInvocation;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.extension.ReflectiveOperationsMethodInvocation.ReflectiveOperationsMethodInvocation;

@ExtendWith(ReflectiveOperationsMethodInvocation.class)
class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void testGetCreatedAt() {
        // GIVEN: A new SharedData instance is created.
        // WHEN: The getCreatedAt() method is called.
        // THEN: The createdAt attribute's value (a Date object representing the current time) is returned.
        Date createdAt = sharedData.getCreatedAt();
        assertNotNull(createdAt, "CreatedAt should not be null");
    }

    @Test
    void testSetCreatedAt() {
        // GIVEN: A new SharedData instance is created.
        // WHEN: The setCreatedAt() method is called with a specific Date object.
        // THEN: The createdAt attribute is set to the provided Date object.
        Date expectedDate = new Date(1678886400000L); // Example date
        sharedData.setCreatedAt(expectedDate);
        Date actualCreatedAt = sharedData.getCreatedAt();
        assertEquals(actualCreatedAt, expectedDate, "CreatedAt should be set correctly");
    }

    @Test
    void testOnPrePersist() {
        // GIVEN: A new SharedData instance is created.
        // WHEN: The onPrePersist() method is called.
        // THEN: The createdAt attribute is initialized with the current date and time.
        Date createdAt = sharedData.getCreatedAt();
        assertNotNull(createdAt, "CreatedAt should not be null");
    }
}