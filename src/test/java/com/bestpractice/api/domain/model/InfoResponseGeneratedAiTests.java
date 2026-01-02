package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class InfoResponseGeneratedAiTests {

    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        // WHEN
        infoResponse = new InfoResponse(id, title, description);
        // THEN
        // no assertions needed for setup
    }

    @Test
    void getIdReturnsExpectedValue() {
        // GIVEN
        String expectedId = "123";
        // WHEN
        String actualId = infoResponse.getId();
        // THEN
        assertEquals(expectedId, actualId);
    }

    @Test
    void getTitleReturnsExpectedValue() {
        // GIVEN
        String expectedTitle = "Test Title";
        // WHEN
        String actualTitle = infoResponse.getTitle();
        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void getDescriptionReturnsExpectedValue() {
        // GIVEN
        String expectedDescription = "Test Description";
        // WHEN
        String actualDescription = infoResponse.getDescription();
        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void constructorAcceptsNullValues() {
        // GIVEN
        // WHEN
        InfoResponse nullInfo = new InfoResponse(null, null, null);
        // THEN
        assertNull(nullInfo.getId());
        assertNull(nullInfo.getTitle());
        assertNull(nullInfo.getDescription());
    }

    @Test
    void gettersReturnSameInstanceAsPassed() {
        // GIVEN
        String id = new String("unique-id");
        String title = new String("unique-title");
        String description = new String("unique-description");
        InfoResponse uniqueInfo = new InfoResponse(id, title, description);
        // WHEN
        String actualId = uniqueInfo.getId();
        String actualTitle = uniqueInfo.getTitle();
        String actualDescription = uniqueInfo.getDescription();
        // THEN
        assertSame(id, actualId);
        assertSame(title, actualTitle);
        assertSame(description, actualDescription);
    }
}
