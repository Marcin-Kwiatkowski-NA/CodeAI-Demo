package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SharedDataGeneratedAiTests {

    SharedData sharedData = new SharedData();

    @BeforeEach
    void setUp() {
        sharedData = new SharedData();
    }

    @Test
    void onPrePersist_setsCreatedAtToCurrentTimestamp() {
        // GIVEN: A new instance of SharedData is created.
        // WHEN: The onPrePersist method is called.
        // THEN: The createdAt field is set to the current date and time.
        sharedData.onPrePersist();
        LocalDateTime now = LocalDateTime.now();
        assertEquals(now, sharedData.getCreatedAt());
    }
}
