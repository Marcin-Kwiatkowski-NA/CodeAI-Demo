package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    public void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, infoRequest.getTitle());
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, infoRequest.getDescription());
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN
        String title = "Title";
        String description = "Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(null);

        // THEN
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithEmptyId() {
        // GIVEN
        String title = "Title";
        String description = "Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert("");

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testAccessTitleThrowsExceptionWhenTitleIsNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription("Some Description");

        // WHEN
        Info info = infoRequest.convert("789");

        // THEN
        assertThrows(NullPointerException.class, () -> {
            info.getTitle().length();
        });
    }

    @Test
    public void testAccessDescriptionThrowsExceptionWhenDescriptionIsNull() {
        // GIVEN
        infoRequest.setTitle("Some Title");
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert("101");

        // THEN
        assertThrows(NullPointerException.class, () -> {
            info.getDescription().length();
        });
    }

    @Test
    public void testAccessTitleAndDescriptionThrowsExceptionWhenBothAreNull() {
        // GIVEN
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert("202");

        // THEN
        assertThrows(NullPointerException.class, () -> {
            info.getTitle().length();
        });
        assertThrows(NullPointerException.class, () -> {
            info.getDescription().length();
        });
    }

    @Test
    public void testConvertWithMockedInfoObject() {
        // GIVEN
        Info mockedInfo = mock(Info.class);
        String id = "mockId";
        String title = "mockTitle";
        String description = "mockDescription";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }
}
