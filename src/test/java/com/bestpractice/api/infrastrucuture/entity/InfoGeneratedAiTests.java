package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    public void setUp() {
        info = new Info();
        info.setId(null);
        info.setTitle(null);
        info.setDescription(null);
        info.setCreatedAt(null);
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        String expectedId = "12345";

        // WHEN
        info.setId(expectedId);

        // THEN
        assertEquals(expectedId, info.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        info.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, info.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        info.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, info.getDescription());
    }

    @Test
    public void testSetAndGetCreatedAtFromSharedData() {
        // GIVEN
        Date expectedDate = new Date();

        // WHEN
        info.setCreatedAt(expectedDate);

        // THEN
        assertEquals(expectedDate, info.getCreatedAt());
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
}
