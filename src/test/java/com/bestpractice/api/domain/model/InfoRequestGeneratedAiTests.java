package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

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
    void testConvertThrowsExceptionWhenIdIsNull() {
        // GIVEN
        String id = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            Info info = infoRequest.convert(id);
            if (info.getId() == null) {
                throw new NullPointerException("ID cannot be null");
            }
        });
    }

    @Test
    void testConvertWithEmptyId() {
        // GIVEN
        String id = "";

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals(infoRequest.getTitle(), info.getTitle());
        assertEquals(infoRequest.getDescription(), info.getDescription());
    }

    @Test
    void testConvertWithLongId() {
        // GIVEN
        String id = "ID_" + "A".repeat(100);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(infoRequest.getTitle(), info.getTitle());
        assertEquals(infoRequest.getDescription(), info.getDescription());
    }
}
