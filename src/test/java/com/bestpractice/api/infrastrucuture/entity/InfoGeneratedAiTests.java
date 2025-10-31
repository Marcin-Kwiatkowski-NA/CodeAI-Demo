package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
        info.setId(null);
        info.setTitle(null);
        info.setDescription(null);
        info.setCreatedAt(null);
    }

    @Test
    void testSetAndGetId() {
        // GIVEN
        String expectedId = "12345";

        // WHEN
        info.setId(expectedId);

        // THEN
        assertEquals(expectedId, info.getId());
    }

    @Test
    void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        info.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, info.getTitle());
    }

    @Test
    void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        info.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, info.getDescription());
    }

    @Test
    void testSetAndGetCreatedAt() {
        // GIVEN
        Date expectedDate = new Date();

        // WHEN
        info.setCreatedAt(expectedDate);

        // THEN
        assertEquals(expectedDate, info.getCreatedAt());
    }

    @Test
    void testOnPrePersistSetsCreatedAt() {
        // GIVEN
        assertNull(info.getCreatedAt());

        // WHEN
        info.onPrePersist();

        // THEN
        assertNotNull(info.getCreatedAt());
    }

    @Test
    void testSetIdWithNullValue() {
        // GIVEN
        String nullId = null;

        // WHEN
        info.setId(nullId);

        // THEN
        assertNull(info.getId());
    }

    @Test
    void testSetTitleWithNullValue() {
        // GIVEN
        String nullTitle = null;

        // WHEN
        info.setTitle(nullTitle);

        // THEN
        assertNull(info.getTitle());
    }

    @Test
    void testSetDescriptionWithNullValue() {
        // GIVEN
        String nullDescription = null;

        // WHEN
        info.setDescription(nullDescription);

        // THEN
        assertNull(info.getDescription());
    }

    @Test
    void testSetTitleDoesNotThrowExceptionForNull() {
        // GIVEN
        String invalidTitle = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> info.setTitle(invalidTitle));
        assertNull(info.getTitle());
    }

    @Test
    void testSetDescriptionDoesNotThrowExceptionForNull() {
        // GIVEN
        String invalidDescription = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> info.setDescription(invalidDescription));
        assertNull(info.getDescription());
    }

    @Test
    void testSetIdDoesNotThrowExceptionForNull() {
        // GIVEN
        String invalidId = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> info.setId(invalidId));
        assertNull(info.getId());
    }

    @Test
    void testMultipleFieldAssignments() {
        // GIVEN
        String expectedId = "id-001";
        String expectedTitle = "Title";
        String expectedDescription = "Description";
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
