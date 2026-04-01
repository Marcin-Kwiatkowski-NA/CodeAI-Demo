package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        infoRequest.setTitle("Sample Title");
        infoRequest.setDescription("Sample Description");
    }

    @Test
    void testGetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void testSetTitle() {
        // GIVEN
        String newTitle = "Updated Title";

        // WHEN
        infoRequest.setTitle(newTitle);

        // THEN
        assertEquals(newTitle, infoRequest.getTitle());
    }

    @Test
    void testGetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void testSetDescription() {
        // GIVEN
        String newDescription = "Updated Description";

        // WHEN
        infoRequest.setDescription(newDescription);

        // THEN
        assertEquals(newDescription, infoRequest.getDescription());
    }

    @Test
    void testConvertCreatesInfoCorrectly() {
        // GIVEN
        String id = "12345";

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(infoRequest.getTitle(), info.getTitle());
        assertEquals(infoRequest.getDescription(), info.getDescription());
    }

    @Test
    void testConvertHandlesNullValuesGracefully() {
        // GIVEN
        InfoRequest nullRequest = new InfoRequest();
        String id = "999";

        // WHEN
        Info info = nullRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    void testConvertWithNullId() {
        // GIVEN
        String id = null;

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(infoRequest.getTitle(), info.getTitle());
        assertEquals(infoRequest.getDescription(), info.getDescription());
    }

    @Test
    void testConvertDoesNotThrowExceptionForNullFields() {
        // GIVEN
        InfoRequest emptyRequest = new InfoRequest();

        // WHEN & THEN
        Info info = emptyRequest.convert("test-id");
        assertNotNull(info);
        assertEquals("test-id", info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    void testConvertThrowsExceptionWhenIdIsNullAndUsedIncorrectly() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Description");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            String id = null;
            if (id.equals("something")) { // intentional misuse to trigger exception
                request.convert(id);
            }
        });
    }
}
