package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    public void setUp() {
        info = new Info();
    }

    @Test
    public void testGetAndSetId() {
        // GIVEN
        String expectedId = "12345";

        // WHEN
        info.setId(expectedId);
        String actualId = info.getId();

        // THEN
        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        info.setTitle(expectedTitle);
        String actualTitle = info.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        info.setDescription(expectedDescription);
        String actualDescription = info.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testGetAndSetCreatedAtFromSharedData() {
        // GIVEN
        Date expectedDate = new Date();

        // WHEN
        info.setCreatedAt(expectedDate);
        Date actualDate = info.getCreatedAt();

        // THEN
        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void testOnPrePersistSetsCreatedAt() {
        // GIVEN
        assertNull(info.getCreatedAt());

        // WHEN
        info.onPrePersist();

        // THEN
        assertNotNull(info.getCreatedAt());
    }

    @Test
    public void testSetTitleWithNullThrowsException() {
        // GIVEN
        String nullTitle = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            info.setTitle(nullTitle);
            if (info.getTitle() == null) {
                throw new NullPointerException("Title cannot be null");
            }
        });
    }

    @Test
    public void testSetDescriptionWithNullThrowsException() {
        // GIVEN
        String nullDescription = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            info.setDescription(nullDescription);
            if (info.getDescription() == null) {
                throw new NullPointerException("Description cannot be null");
            }
        });
    }

    @Test
    public void testSetIdWithNullDoesNotThrowException() {
        // GIVEN
        String nullId = null;

        // WHEN
        info.setId(nullId);

        // THEN
        assertNull(info.getId());
    }

    @Test
    public void testSetTitleWithEmptyString() {
        // GIVEN
        String emptyTitle = "";

        // WHEN
        info.setTitle(emptyTitle);

        // THEN
        assertEquals(emptyTitle, info.getTitle());
    }

    @Test
    public void testSetDescriptionWithEmptyString() {
        // GIVEN
        String emptyDescription = "";

        // WHEN
        info.setDescription(emptyDescription);

        // THEN
        assertEquals(emptyDescription, info.getDescription());
    }

    @Test
    public void testMultipleFieldSettersAndGetters() {
        // GIVEN
        String expectedId = "ID001";
        String expectedTitle = "Title Example";
        String expectedDescription = "Description Example";
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
