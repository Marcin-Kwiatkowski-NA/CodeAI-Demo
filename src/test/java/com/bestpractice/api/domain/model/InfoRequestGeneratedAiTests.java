package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Info Title";
        String description = "Info Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info result = infoRequest.convert(id);

        // THEN
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(title, result.getTitle());
        assertEquals(description, result.getDescription());
    }

    @Test
    void testConvertHandlesNullValuesGracefully() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info result = infoRequest.convert(id);

        // THEN
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertNull(result.getTitle());
        assertNull(result.getDescription());
    }

    @Test
    void testConvertWithNullIdDoesNotThrowException() {
        // GIVEN
        infoRequest.setTitle("Title");
        infoRequest.setDescription("Description");

        // WHEN
        Info result = infoRequest.convert(null);

        // THEN
        assertNotNull(result);
        assertNull(result.getId());
        assertEquals("Title", result.getTitle());
        assertEquals("Description", result.getDescription());
    }

    @Test
    void testConvertWithEmptyStrings() {
        // GIVEN
        String id = "";
        infoRequest.setTitle("");
        infoRequest.setDescription("");

        // WHEN
        Info result = infoRequest.convert(id);

        // THEN
        assertNotNull(result);
        assertEquals("", result.getId());
        assertEquals("", result.getTitle());
        assertEquals("", result.getDescription());
    }

    @Test
    void testConvertWithLongStrings() {
        // GIVEN
        String id = "999";
        String longTitle = "A".repeat(1000);
        String longDescription = "B".repeat(2000);
        infoRequest.setTitle(longTitle);
        infoRequest.setDescription(longDescription);

        // WHEN
        Info result = infoRequest.convert(id);

        // THEN
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(longTitle, result.getTitle());
        assertEquals(longDescription, result.getDescription());
    }
}
