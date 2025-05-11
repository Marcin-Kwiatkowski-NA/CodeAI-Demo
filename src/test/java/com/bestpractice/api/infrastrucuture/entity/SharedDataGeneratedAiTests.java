package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Date;

class SharedDataGeneratedAiTests {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void onPrePersist_setsCreatedAtToCurrentDate() {
        // GIVEN: A new instance of SharedData is created.
        // WHEN: The onPrePersist method is called.
        sharedData.onPrePersist();

        // THEN: The createdAt field is set to the current date and time.
        Date createdAt = sharedData.getCreatedAt();
        Assertions.assertEquals(new Date(), createdAt);
    }

    @Test
    void getCreatedAt_returnsCreatedAtDate() {
        // GIVEN: A SharedData object with a createdAt date.
        Date createdAt = new Date(1678886400000L);
        sharedData.setCreatedAt(createdAt);

        // WHEN: The getCreatedAt method is called.
        Date retrievedCreatedAt = sharedData.getCreatedAt();

        // THEN: The retrieved createdAt date matches the original date.
        Assertions.assertEquals(createdAt, retrievedCreatedAt);
    }

    @Test
    void setCreatedAt_updatesCreatedAtDate() {
        // GIVEN: A SharedData object with an initial createdAt date.
        Date initialCreatedAt = new Date(1678886400000L);
        sharedData.setCreatedAt(initialCreatedAt);

        // WHEN: The setCreatedAt method is called with a new date.
        Date newCreatedAt = new Date();
        sharedData.setCreatedAt(newCreatedAt);

        // THEN: The createdAt field is updated to the new date.
        Assertions.assertEquals(newCreatedAt, sharedData.getCreatedAt());
    }

    @Test
    void setCreatedAt_null_doesNotThrowException() {
        // GIVEN: A SharedData object.

        // WHEN: The setCreatedAt method is called with null.

        // THEN: The createdAt field is set to null.
        sharedData.setCreatedAt(null);
        Assertions.assertNull(sharedData.getCreatedAt());
    }
}
