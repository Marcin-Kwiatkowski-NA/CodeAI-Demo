package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    @Test
    void testSetAndGetId() {
        // GIVEN
        String expectedId = "12345";

        // WHEN
        info.setId(expectedId);
        String actualId = info.getId();

        // THEN
        assertEquals(expectedId, actualId);
    }

    @Test
    void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        info.setTitle(expectedTitle);
        String actualTitle = info.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "This is a description.";

        // WHEN
        info.setDescription(expectedDescription);
        String actualDescription = info.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void testInheritedSetAndGetCreatedAt() {
        // GIVEN
        Date expectedDate = new Date();

        // WHEN
        info.setCreatedAt(expectedDate);
        Date actualDate = info.getCreatedAt();

        // THEN
        assertEquals(expectedDate, actualDate);
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
    void testSetTitleWithNullDoesNotThrowException() {
        // GIVEN
        String invalidTitle = null;

        // WHEN
        info.setTitle(invalidTitle);

        // THEN
        assertEquals(null, info.getTitle());
    }

    @Test
    void testSetDescriptionWithNullDoesNotThrowException() {
        // GIVEN
        String invalidDescription = null;

        // WHEN
        info.setDescription(invalidDescription);

        // THEN
        assertEquals(null, info.getDescription());
    }

    @Test
    void testSetIdWithNullDoesNotThrowException() {
        // GIVEN
        String invalidId = null;

        // WHEN
        info.setId(invalidId);

        // THEN
        assertEquals(null, info.getId());
    }

    @Test
    void testSetCreatedAtWithNullDoesNotThrowException() {
        // GIVEN
        Date invalidDate = null;

        // WHEN
        info.setCreatedAt(invalidDate);

        // THEN
        assertEquals(null, info.getCreatedAt());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenAlreadySet() {
        // GIVEN
        Date existingDate = new Date();
        info.setCreatedAt(existingDate);

        // WHEN
        info.onPrePersist();

        // THEN
        assertNotNull(info.getCreatedAt());
    }
}
