package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
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
    void testSetNullValuesDoesNotThrowException() {
        // GIVEN
        String nullValue = null;

        // WHEN & THEN
        info.setId(nullValue);
        info.setTitle(nullValue);
        info.setDescription(nullValue);

        assertEquals(nullValue, info.getId());
        assertEquals(nullValue, info.getTitle());
        assertEquals(nullValue, info.getDescription());
    }

    @Test
    void testOnPrePersistDoesNotThrowExceptionWhenCalledMultipleTimes() {
        // GIVEN
        info.setCreatedAt(null);

        // WHEN
        info.onPrePersist();
        Date firstDate = info.getCreatedAt();

        info.onPrePersist();
        Date secondDate = info.getCreatedAt();

        // THEN
        assertNotNull(firstDate);
        assertNotNull(secondDate);
    }

    @Test
    void testSetIdWithNullDoesNotThrowException() {
        // GIVEN
        String nullId = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            String value = nullId.toString();
        });
    }
}
