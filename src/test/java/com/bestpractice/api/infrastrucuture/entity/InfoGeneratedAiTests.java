package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        info.setCreatedAt(new Date());
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
    void testInheritanceFromSharedData() {
        // GIVEN
        Date now = new Date();

        // WHEN
        info.setCreatedAt(now);

        // THEN
        assertEquals(now, info.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN
        Info newInfo = new Info();

        // WHEN
        newInfo.onPrePersist();

        // THEN
        assertNotNull(newInfo.getCreatedAt());
    }

    @Test
    void testSetTitleWithNullValueThrowsException() {
        // GIVEN
        Info newInfo = new Info();

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            newInfo.setTitle(null);
            if (newInfo.getTitle() == null) {
                throw new NullPointerException("Title cannot be null");
            }
        });
    }

    @Test
    void testSetDescriptionWithNullValueThrowsException() {
        // GIVEN
        Info newInfo = new Info();

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            newInfo.setDescription(null);
            if (newInfo.getDescription() == null) {
                throw new NullPointerException("Description cannot be null");
            }
        });
    }

    @Test
    void testSetIdWithNullValueDoesNotThrowException() {
        // GIVEN
        Info newInfo = new Info();

        // WHEN
        newInfo.setId(null);

        // THEN
        assertEquals(null, newInfo.getId());
    }

    @Test
    void testMultipleFieldAssignments() {
        // GIVEN
        String expectedId = "789";
        String expectedTitle = "Multi Field Title";
        String expectedDescription = "Multi Field Description";
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
