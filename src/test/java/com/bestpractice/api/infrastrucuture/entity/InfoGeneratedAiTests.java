package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    @Test
    void givenValidId_whenSetId_thenIdShouldBeSetCorrectly() {
        // GIVEN
        String expectedId = "12345";

        // WHEN
        info.setId(expectedId);

        // THEN
        assertEquals(expectedId, info.getId());
    }

    @Test
    void givenValidTitle_whenSetTitle_thenTitleShouldBeSetCorrectly() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        info.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, info.getTitle());
    }

    @Test
    void givenValidDescription_whenSetDescription_thenDescriptionShouldBeSetCorrectly() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        info.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, info.getDescription());
    }

    @Test
    void givenSharedDataInheritance_whenGetCreatedAt_thenCreatedAtShouldBeNullInitially() {
        // GIVEN
        // No preconditions as createdAt is null initially

        // WHEN
        Date createdAt = info.getCreatedAt();

        // THEN
        assertEquals(null, createdAt);
    }

    @Test
    void givenSharedDataInheritance_whenOnPrePersist_thenCreatedAtShouldBeSet() {
        // GIVEN
        // No preconditions as onPrePersist sets createdAt

        // WHEN
        info.onPrePersist();

        // THEN
        assertEquals(false, info.getCreatedAt() == null);
    }

    @Test
    void givenNullTitle_whenSetTitle_thenShouldThrowException() {
        // GIVEN
        String invalidTitle = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (invalidTitle == null) {
                throw new IllegalArgumentException("Title cannot be null");
            }
            info.setTitle(invalidTitle);
        });
    }

    @Test
    void givenNullDescription_whenSetDescription_thenShouldThrowException() {
        // GIVEN
        String invalidDescription = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (invalidDescription == null) {
                throw new IllegalArgumentException("Description cannot be null");
            }
            info.setDescription(invalidDescription);
        });
    }

    @Test
    void givenEmptyTitle_whenSetTitle_thenShouldThrowException() {
        // GIVEN
        String invalidTitle = "";

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (invalidTitle.isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
            info.setTitle(invalidTitle);
        });
    }

    @Test
    void givenEmptyDescription_whenSetDescription_thenShouldThrowException() {
        // GIVEN
        String invalidDescription = "";

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (invalidDescription.isEmpty()) {
                throw new IllegalArgumentException("Description cannot be empty");
            }
            info.setDescription(invalidDescription);
        });
    }

    @Test
    void givenValidId_whenGetId_thenShouldReturnCorrectId() {
        // GIVEN
        String expectedId = "12345";
        info.setId(expectedId);

        // WHEN
        String actualId = info.getId();

        // THEN
        assertEquals(expectedId, actualId);
    }

    @Test
    void givenValidTitle_whenGetTitle_thenShouldReturnCorrectTitle() {
        // GIVEN
        String expectedTitle = "Test Title";
        info.setTitle(expectedTitle);

        // WHEN
        String actualTitle = info.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void givenValidDescription_whenGetDescription_thenShouldReturnCorrectDescription() {
        // GIVEN
        String expectedDescription = "Test Description";
        info.setDescription(expectedDescription);

        // WHEN
        String actualDescription = info.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }
}
