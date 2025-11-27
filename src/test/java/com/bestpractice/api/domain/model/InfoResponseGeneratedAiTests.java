package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InfoResponseGeneratedAiTests {

    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        infoResponse = new InfoResponse("123", "Test Title", "Test Description");
    }

    @Test
    void givenValidId_whenGetId_thenReturnsCorrectId() {
        // GIVEN: An InfoResponse object with a specific id
        String expectedId = "123";

        // WHEN: The getId method is called
        String actualId = infoResponse.getId();

        // THEN: The returned id matches the expected id
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void givenValidTitle_whenGetTitle_thenReturnsCorrectTitle() {
        // GIVEN: An InfoResponse object with a specific title
        String expectedTitle = "Test Title";

        // WHEN: The getTitle method is called
        String actualTitle = infoResponse.getTitle();

        // THEN: The returned title matches the expected title
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    void givenValidDescription_whenGetDescription_thenReturnsCorrectDescription() {
        // GIVEN: An InfoResponse object with a specific description
        String expectedDescription = "Test Description";

        // WHEN: The getDescription method is called
        String actualDescription = infoResponse.getDescription();

        // THEN: The returned description matches the expected description
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }
}
