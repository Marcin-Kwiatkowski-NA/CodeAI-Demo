package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

import static org.assertj.core.api.Assertions.assertThat;

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
    void givenValidConstructorArguments_whenGetId_thenReturnsCorrectId() {
        // GIVEN: An InfoResponse object initialized with valid arguments

        // WHEN: The getId method is called
        String id = infoResponse.getId();

        // THEN: The returned id should match the expected value
        assertThat(id).isEqualTo(TEST_ID);
    }

    @Test
    void givenValidConstructorArguments_whenGetTitle_thenReturnsCorrectTitle() {
        // GIVEN: An InfoResponse object initialized with valid arguments

        // WHEN: The getTitle method is called
        String title = infoResponse.getTitle();

        // THEN: The returned title should match the expected value
        assertThat(title).isEqualTo(TEST_TITLE);
    }

    @Test
    void givenValidConstructorArguments_whenGetDescription_thenReturnsCorrectDescription() {
        // GIVEN: An InfoResponse object initialized with valid arguments

        // WHEN: The getDescription method is called
        String description = infoResponse.getDescription();

        // THEN: The returned description should match the expected value
        assertThat(description).isEqualTo(TEST_DESCRIPTION);
    }
}
