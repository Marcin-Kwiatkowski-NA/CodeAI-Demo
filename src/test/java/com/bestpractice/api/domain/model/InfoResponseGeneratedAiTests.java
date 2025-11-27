package com.bestpractice.api.domain.model;

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
    void givenValidId_whenGetId_thenReturnsCorrectId() {
        // GIVEN: An InfoResponse object initialized with a specific ID
        // WHEN: Calling getId()
        String id = infoResponse.getId();

        // THEN: The returned ID matches the initialized value
        assertEquals(TEST_ID, id);
    }

    @Test
    void givenValidTitle_whenGetTitle_thenReturnsCorrectTitle() {
        // GIVEN: An InfoResponse object initialized with a specific title
        // WHEN: Calling getTitle()
        String title = infoResponse.getTitle();

        // THEN: The returned title matches the initialized value
        assertEquals(TEST_TITLE, title);
    }

    @Test
    void givenValidDescription_whenGetDescription_thenReturnsCorrectDescription() {
        // GIVEN: An InfoResponse object initialized with a specific description
        // WHEN: Calling getDescription()
        String description = infoResponse.getDescription();

        // THEN: The returned description matches the initialized value
        assertEquals(TEST_DESCRIPTION, description);
    }
}
