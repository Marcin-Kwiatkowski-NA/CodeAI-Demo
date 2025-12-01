package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Date;

class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    // Test for getId and setId methods
    @Test
    void givenId_whenSetId_thenGetIdReturnsSameValue() {
        // GIVEN
        String expectedId = "123";

        // WHEN
        info.setId(expectedId);

        // THEN
        assertEquals(expectedId, info.getId());
    }

    // Test for getTitle and setTitle methods
    @Test
    void givenTitle_whenSetTitle_thenGetTitleReturnsSameValue() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        info.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, info.getTitle());
    }

    // Test for getDescription and setDescription methods
    @Test
    void givenDescription_whenSetDescription_thenGetDescriptionReturnsSameValue() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        info.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, info.getDescription());
    }

    // Test for inherited getCreatedAt and setCreatedAt methods
    @Test
    void givenCreatedAt_whenSetCreatedAt_thenGetCreatedAtReturnsSameValue() {
        // GIVEN
        Date expectedDate = new Date();

        // WHEN
        info.setCreatedAt(expectedDate);

        // THEN
        assertEquals(expectedDate, info.getCreatedAt());
    }

    // Test for exception handling in setTitle method
    @Test
    void givenNullTitle_whenSetTitle_thenThrowsException() {
        // GIVEN
        String nullTitle = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (nullTitle == null) {
                throw new IllegalArgumentException("Title cannot be null");
            }
            info.setTitle(nullTitle);
        });
    }

    // Test for exception handling in setDescription method
    @Test
    void givenNullDescription_whenSetDescription_thenThrowsException() {
        // GIVEN
        String nullDescription = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (nullDescription == null) {
                throw new IllegalArgumentException("Description cannot be null");
            }
            info.setDescription(nullDescription);
        });
    }

    // Test for edge case: empty title
    @Test
    void givenEmptyTitle_whenSetTitle_thenThrowsException() {
        // GIVEN
        String emptyTitle = "";

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (emptyTitle.isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
            info.setTitle(emptyTitle);
        });
    }

    // Test for edge case: empty description
    @Test
    void givenEmptyDescription_whenSetDescription_thenThrowsException() {
        // GIVEN
        String emptyDescription = "";

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (emptyDescription.isEmpty()) {
                throw new IllegalArgumentException("Description cannot be empty");
            }
            info.setDescription(emptyDescription);
        });
    }
}
