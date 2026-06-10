package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.Mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void testConvertPopulatesInfoCorrectly() {
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
    void testConvertWithNullFields() {
        // GIVEN
        String id = "456";
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(null, info.getTitle());
        assertEquals(null, info.getDescription());
    }

    @Test
    void testConvertWithEmptyStrings() {
        // GIVEN
        String id = "789";
        infoRequest.setTitle("");
        infoRequest.setDescription("");

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    void testConvertWithWhitespaceStrings() {
        // GIVEN
        String id = "101";
        infoRequest.setTitle("   ");
        infoRequest.setDescription("   ");

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals("   ", info.getTitle());
        assertEquals("   ", info.getDescription());
    }

    @Test
    void testConvertWithSingleCharacterStrings() {
        // GIVEN
        String id = "202";
        infoRequest.setTitle("A");
        infoRequest.setDescription("B");

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals("A", info.getTitle());
        assertEquals("B", info.getDescription());
    }

    @Test
    void testConvertWithLongStrings() {
        // GIVEN
        String id = "303";
        String longTitle = "T".repeat(1000);
        String longDescription = "D".repeat(2000);
        infoRequest.setTitle(longTitle);
        infoRequest.setDescription(longDescription);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(longTitle, info.getTitle());
        assertEquals(longDescription, info.getDescription());
    }

    @Test
    void testConvertWithSpecialCharacters() {
        // GIVEN
        String id = "404";
        String title = "!@#$%^&*()_+";
        String description = "<xml>description</xml>";
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
    void testConvertWithUnicodeCharacters() {
        // GIVEN
        String id = "505";
        String title = "タイトル";
        String description = "描述";
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
    void testConvertWithNullId() {
        // GIVEN
        infoRequest.setTitle("Title");
        infoRequest.setDescription("Description");

        // WHEN
        Info info = infoRequest.convert(null);

        // THEN
        assertNotNull(info);
        assertEquals(null, info.getId());
        assertEquals("Title", info.getTitle());
        assertEquals("Description", info.getDescription());
    }

    @Test
    void testConvertWithEmptyId() {
        // GIVEN
        infoRequest.setTitle("Title");
        infoRequest.setDescription("Description");

        // WHEN
        Info info = infoRequest.convert("");

        // THEN
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("Title", info.getTitle());
        assertEquals("Description", info.getDescription());
    }

    @Test
    void testConvertWithWhitespaceId() {
        // GIVEN
        infoRequest.setTitle("Title");
        infoRequest.setDescription("Description");

        // WHEN
        Info info = infoRequest.convert("   ");

        // THEN
        assertNotNull(info);
        assertEquals("   ", info.getId());
        assertEquals("Title", info.getTitle());
        assertEquals("Description", info.getDescription());
    }

    @Test
    void testConvertCreatesNewInfoInstanceEachTime() {
        // GIVEN
        infoRequest.setTitle("Title");
        infoRequest.setDescription("Description");

        // WHEN
        Info info1 = infoRequest.convert("1");
        Info info2 = infoRequest.convert("2");

        // THEN
        assertNotNull(info1);
        assertNotNull(info2);
        assertEquals("1", info1.getId());
        assertEquals("2", info2.getId());
        assertEquals(info1.getTitle(), info2.getTitle());
        assertEquals(info1.getDescription(), info2.getDescription());
    }

    @Test
    void testConvertDoesNotModifyOriginalInfoRequestState() {
        // GIVEN
        infoRequest.setTitle("Original Title");
        infoRequest.setDescription("Original Description");

        // WHEN
        Info info = infoRequest.convert("999");

        // THEN
        assertNotNull(info);
        assertEquals("999", info.getId());
        assertEquals("Original Title", info.getTitle());
        assertEquals("Original Description", info.getDescription());
        assertEquals("Original Title", infoRequest.getTitle());
        assertEquals("Original Description", infoRequest.getDescription());
    }
}
