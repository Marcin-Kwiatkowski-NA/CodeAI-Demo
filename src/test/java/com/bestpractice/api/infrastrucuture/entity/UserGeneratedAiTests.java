package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserGeneratedAiTests extends AbstractTestWithMockito {

    private SharedData sharedData;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData("Initial Data");
    }

    @Test
    void testGetData() {
        assertEquals("Initial Data", sharedData.getData());
    }
}
