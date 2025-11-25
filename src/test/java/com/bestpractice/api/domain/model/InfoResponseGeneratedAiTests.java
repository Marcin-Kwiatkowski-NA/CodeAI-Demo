package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfoResponseGeneratedAiTests {

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
        // GIVEN: An InfoResponse object with a predefined ID

        // WHEN: The getId method is called
        String id = infoResponse.getId();

        // THEN: The returned ID matches the predefined ID
        assertEquals(TEST_ID, id);
    }

    @Test
    void givenValidTitle_whenGetTitle_thenReturnsCorrectTitle() {
        // GIVEN: An InfoResponse object with a predefined title

        // WHEN: The getTitle method is called
        String title = infoResponse.getTitle();

        // THEN: The returned title matches the predefined title
        assertEquals(TEST_TITLE, title);
    }

    @Test
    void givenValidDescription_whenGetDescription_thenReturnsCorrectDescription() {
        // GIVEN: An InfoResponse object with a predefined description

        // WHEN: The getDescription method is called
        String description = infoResponse.getDescription();

        // THEN: The returned description matches the predefined description
        assertEquals(TEST_DESCRIPTION, description);
    }
}
