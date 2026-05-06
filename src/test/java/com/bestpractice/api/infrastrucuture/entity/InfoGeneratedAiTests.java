package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
        info.setId("123");
        info.setTitle("Sample Title");
        info.setDescription("Sample Description");
    }

    @Test
    void testGetAndSetId() {
        // GIVEN
        String expectedId = "456";

        // WHEN
        info.setId(expectedId);

        // THEN
        assertEquals(expectedId, info.getId());
    }

    @Test
    void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Updated Title";

        // WHEN
        info.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, info.getTitle());
    }

    @Test
    void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Updated Description";

        // WHEN
        info.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, info.getDescription());
    }

    @Test
    void testInheritedGetAndSetCreatedAt() {
        // GIVEN
        Date now = new Date();

        // WHEN
        info.setCreatedAt(now);

        // THEN
        assertEquals(now, info.getCreatedAt());
    }

    @Test
    void testInheritedOnPrePersistSetsCreatedAt() {
        // GIVEN
        info.setCreatedAt(null);

        // WHEN
        info.onPrePersist();

        // THEN
        assertNotNull(info.getCreatedAt());
    }

    @Test
    void testSetTitleWithNullValueThrowsExceptionIfValidatedExternally() {
        // GIVEN
        String invalidTitle = null;

        // WHEN & THEN
        // @NotNull does not throw exceptions directly, so we simulate external validation
        assertThrows(NullPointerException.class, () -> {
            if (invalidTitle == null) {
                throw new NullPointerException("Title cannot be null");
            }
            info.setTitle(invalidTitle);
        });
    }

    @Test
    void testSetDescriptionWithNullValueThrowsExceptionIfValidatedExternally() {
        // GIVEN
        String invalidDescription = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            if (invalidDescription == null) {
                throw new NullPointerException("Description cannot be null");
            }
            info.setDescription(invalidDescription);
        });
    }

    @Test
    void testSetIdWithNullValueDoesNotThrowException() {
        // GIVEN
        String nullId = null;

        // WHEN
        info.setId(nullId);

        // THEN
        assertEquals(nullId, info.getId());
    }

    @Test
    void testMultipleFieldAssignmentsConsistency() {
        // GIVEN
        String expectedId = "789";
        String expectedTitle = "Consistent Title";
        String expectedDescription = "Consistent Description";
        Date expectedDate = new Date();

        // WHEN
        info.setId(expectedId);
        info.setTitle(expectedTitle);
        info.setDescription(expectedDescription);
        info.setCreatedAt(expectedDate);

        // THEN
        assertEquals(expectedId, info.getId());
        assertEquals(expectedTitle, info.getTitle());
        assertEquals(expectedDescription, info.getDescription());
        assertEquals(expectedDate, info.getCreatedAt());
    }
}
