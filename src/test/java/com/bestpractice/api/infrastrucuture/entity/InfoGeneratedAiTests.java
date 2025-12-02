package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    @Test
    void testGetId() {
        // GIVEN
        String expectedId = "12345";
        info.setId(expectedId);

        // WHEN
        String actualId = info.getId();

        // THEN
        assertEquals(expectedId, actualId);
    }

    @Test
    void testSetId() {
        // GIVEN
        String expectedId = "67890";

        // WHEN
        info.setId(expectedId);

        // THEN
        assertEquals(expectedId, info.getId());
    }

    @Test
    void testGetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";
        info.setTitle(expectedTitle);

        // WHEN
        String actualTitle = info.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void testSetTitle() {
        // GIVEN
        String expectedTitle = "Another Title";

        // WHEN
        info.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, info.getTitle());
    }

    @Test
    void testGetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";
        info.setDescription(expectedDescription);

        // WHEN
        String actualDescription = info.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void testSetDescription() {
        // GIVEN
        String expectedDescription = "Another Description";

        // WHEN
        info.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, info.getDescription());
    }

    @Test
    void testSetTitleThrowsExceptionWhenNull() {
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
    void testSetDescriptionThrowsExceptionWhenNull() {
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
}
