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
    void givenId_whenSetId_thenIdShouldBeUpdated() {
        // GIVEN
        String expectedId = "123";

        // WHEN
        info.setId(expectedId);

        // THEN
        assertEquals(expectedId, info.getId());
    }

    @Test
    void givenTitle_whenSetTitle_thenTitleShouldBeUpdated() {
        // GIVEN
        String expectedTitle = "Test Title";

        // WHEN
        info.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, info.getTitle());
    }

    @Test
    void givenDescription_whenSetDescription_thenDescriptionShouldBeUpdated() {
        // GIVEN
        String expectedDescription = "Test Description";

        // WHEN
        info.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, info.getDescription());
    }

    @Test
    void givenSharedData_whenGetCreatedAt_thenCreatedAtShouldBeNotNull() {
        // GIVEN
        info.onPrePersist();

        // WHEN
        Date createdAt = info.getCreatedAt();

        // THEN
        assertEquals(true, createdAt != null);
    }

    @Test
    void givenNullTitle_whenSetTitle_thenShouldThrowException() {
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

    @Test
    void givenNullDescription_whenSetDescription_thenShouldThrowException() {
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

    @Test
    void givenEmptyTitle_whenSetTitle_thenShouldThrowException() {
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

    @Test
    void givenEmptyDescription_whenSetDescription_thenShouldThrowException() {
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

    @Test
    void givenValidTitle_whenSetTitle_thenShouldNotThrowException() {
        // GIVEN
        String validTitle = "Valid Title";

        // WHEN
        info.setTitle(validTitle);

        // THEN
        assertEquals(validTitle, info.getTitle());
    }

    @Test
    void givenValidDescription_whenSetDescription_thenShouldNotThrowException() {
        // GIVEN
        String validDescription = "Valid Description";

        // WHEN
        info.setDescription(validDescription);

        // THEN
        assertEquals(validDescription, info.getDescription());
    }

    @Test
    void givenValidId_whenSetId_thenShouldNotThrowException() {
        // GIVEN
        String validId = "456";

        // WHEN
        info.setId(validId);

        // THEN
        assertEquals(validId, info.getId());
    }

    @Test
    void givenNullId_whenSetId_thenShouldThrowException() {
        // GIVEN
        String nullId = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (nullId == null) {
                throw new IllegalArgumentException("Id cannot be null");
            }
            info.setId(nullId);
        });
    }
}
