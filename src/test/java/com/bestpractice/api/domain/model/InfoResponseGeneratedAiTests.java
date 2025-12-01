package com.bestpractice.api.domain.model;

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
import static org.junit.jupiter.api.Assertions.assertEquals;

class InfoResponseGeneratedAiTests {

    private static final String TEST_ID = "123";
    private static final String TEST_TITLE = "Test Title";
    private static final String TEST_DESCRIPTION = "Test Description";

    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        infoResponse = new InfoResponse(TEST_ID, TEST_TITLE, TEST_DESCRIPTION);
    }

    @Test
    void testGetId() {
        // GIVEN: An InfoResponse object initialized with test data
        // WHEN: The getId method is called
        String id = infoResponse.getId();

        // THEN: The returned id should match the expected test id
        assertEquals(TEST_ID, id);
    }

    @Test
    void testGetTitle() {
        // GIVEN: An InfoResponse object initialized with test data
        // WHEN: The getTitle method is called
        String title = infoResponse.getTitle();

        // THEN: The returned title should match the expected test title
        assertEquals(TEST_TITLE, title);
    }

    @Test
    void testGetDescription() {
        // GIVEN: An InfoResponse object initialized with test data
        // WHEN: The getDescription method is called
        String description = infoResponse.getDescription();

        // THEN: The returned description should match the expected test description
        assertEquals(TEST_DESCRIPTION, description);
    }
}
