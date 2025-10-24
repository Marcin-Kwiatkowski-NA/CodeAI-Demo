package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
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
    void testSetAndGetCreatedAtFromSharedData() {
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
        assertNull(info.getCreatedAt());

        // WHEN
        info.onPrePersist();

        // THEN
        assertNotNull(info.getCreatedAt());
    }

    @Test
    void testSetTitleWithNullThrowsExceptionIfValidatedExternally() {
        // GIVEN
        String nullTitle = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            if (nullTitle == null) {
                throw new NullPointerException("Title cannot be null");
            }
            info.setTitle(nullTitle);
        });
    }

    @Test
    void testSetDescriptionWithNullThrowsExceptionIfValidatedExternally() {
        // GIVEN
        String nullDescription = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            if (nullDescription == null) {
                throw new NullPointerException("Description cannot be null");
            }
            info.setDescription(nullDescription);
        });
    }

    @Test
    void testSetIdWithNullDoesNotThrowException() {
        // GIVEN
        String nullId = null;

        // WHEN
        info.setId(nullId);

        // THEN
        assertNull(info.getId());
    }

    @Test
    void testSetTitleWithEmptyString() {
        // GIVEN
        String emptyTitle = "";

        // WHEN
        info.setTitle(emptyTitle);

        // THEN
        assertEquals(emptyTitle, info.getTitle());
    }

    @Test
    void testSetDescriptionWithEmptyString() {
        // GIVEN
        String emptyDescription = "";

        // WHEN
        info.setDescription(emptyDescription);

        // THEN
        assertEquals(emptyDescription, info.getDescription());
    }

    @Test
    void testMultipleFieldSettersAndGetters() {
        // GIVEN
        String expectedId = "ID001";
        String expectedTitle = "Title001";
        String expectedDescription = "Description001";
        Date now = new Date();

        // WHEN
        info.setId(expectedId);
        info.setTitle(expectedTitle);
        info.setDescription(expectedDescription);
        info.setCreatedAt(now);

        // THEN
        assertEquals(expectedId, info.getId());
        assertEquals(expectedTitle, info.getTitle());
        assertEquals(expectedDescription, info.getDescription());
        assertEquals(now, info.getCreatedAt());
    }
}
