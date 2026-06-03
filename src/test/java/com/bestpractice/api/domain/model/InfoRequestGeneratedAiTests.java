package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String expectedTitle = "Sample Title";
        String actualTitle = infoRequest.getTitle();
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void testSetTitle() {
        String newTitle = "Updated Title";
        infoRequest.setTitle(newTitle);
        assertEquals(newTitle, infoRequest.getTitle());
    }

    @Test
    void testGetDescription() {
        String expectedDescription = "Sample Description";
        String actualDescription = infoRequest.getDescription();
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void testSetDescription() {
        String newDescription = "Updated Description";
        infoRequest.setDescription(newDescription);
        assertEquals(newDescription, infoRequest.getDescription());
    }

    @Test
    void testConvertCreatesInfoCorrectly() {
        String id = "12345";
        Info info = infoRequest.convert(id);
        assertEquals(id, info.getId());
        assertEquals(infoRequest.getTitle(), info.getTitle());
        assertEquals(infoRequest.getDescription(), info.getDescription());
    }

    @Test
    void testConvertHandlesNullValuesGracefully() {
        InfoRequest emptyRequest = new InfoRequest();
        Info info = emptyRequest.convert("id-null");
        assertEquals("id-null", info.getId());
        assertEquals(null, info.getTitle());
        assertEquals(null, info.getDescription());
    }

    @Test
    void testConvertWithEmptyStringId() {
        String id = "";
        infoRequest.setTitle("Edge Title");
        infoRequest.setDescription("Edge Description");
        Info info = infoRequest.convert(id);
        assertEquals("", info.getId());
        assertEquals("Edge Title", info.getTitle());
        assertEquals("Edge Description", info.getDescription());
    }

    @Test
    void testConvertWithWhitespaceOnlyId() {
        String id = "   ";
        infoRequest.setTitle("Whitespace Title");
        infoRequest.setDescription("Whitespace Description");
        Info info = infoRequest.convert(id);
        assertEquals("   ", info.getId());
        assertEquals("Whitespace Title", info.getTitle());
        assertEquals("Whitespace Description", info.getDescription());
    }

    @Test
    void testSetTitleWithEmptyString() {
        String emptyTitle = "";
        infoRequest.setTitle(emptyTitle);
        assertEquals("", infoRequest.getTitle());
    }

    @Test
    void testSetTitleWithWhitespaceOnly() {
        String whitespaceTitle = "   ";
        infoRequest.setTitle(whitespaceTitle);
        assertEquals("   ", infoRequest.getTitle());
    }

    @Test
    void testSetDescriptionWithEmptyString() {
        String emptyDescription = "";
        infoRequest.setDescription(emptyDescription);
        assertEquals("", infoRequest.getDescription());
    }

    @Test
    void testSetDescriptionWithWhitespaceOnly() {
        String whitespaceDescription = "   ";
        infoRequest.setDescription(whitespaceDescription);
        assertEquals("   ", infoRequest.getDescription());
    }

    @Test
    void testConvertWithLongIdBoundary() {
        String id = String.valueOf(Long.MAX_VALUE);
        infoRequest.setTitle("Boundary Title");
        infoRequest.setDescription("Boundary Description");
        Info info = infoRequest.convert(id);
        assertEquals(String.valueOf(Long.MAX_VALUE), info.getId());
        assertEquals("Boundary Title", info.getTitle());
        assertEquals("Boundary Description", info.getDescription());
    }

    @Test
    void testConvertWithSingleCharacterId() {
        String id = "A";
        infoRequest.setTitle("Single Char Title");
        infoRequest.setDescription("Single Char Description");
        Info info = infoRequest.convert(id);
        assertEquals("A", info.getId());
        assertEquals("Single Char Title", info.getTitle());
        assertEquals("Single Char Description", info.getDescription());
    }

    @Test
    void testConvertWithVeryLongStringId() {
        String id = "X".repeat(10000);
        infoRequest.setTitle("Long Title");
        infoRequest.setDescription("Long Description");
        Info info = infoRequest.convert(id);
        assertEquals("X".repeat(10000), info.getId());
        assertEquals("Long Title", info.getTitle());
        assertEquals("Long Description", info.getDescription());
    }

    @Test
    void testConvertWithUnicodeCharacters() {
        infoRequest.setTitle("タイトル");
        infoRequest.setDescription("説明");
        String id = "ユニコード";
        Info info = infoRequest.convert(id);
        assertEquals("ユニコード", info.getId());
        assertEquals("タイトル", info.getTitle());
        assertEquals("説明", info.getDescription());
    }

    @Test
    void testConvertWithSpecialCharacters() {
        infoRequest.setTitle("!@#$%^&*()");
        infoRequest.setDescription("<>{}[]");
        String id = "!@#";
        Info info = infoRequest.convert(id);
        assertEquals("!@#", info.getId());
        assertEquals("!@#$%^&*()", info.getTitle());
        assertEquals("<>{}[]", info.getDescription());
    }

    @Test
    void testConvertWithEmptyTitleAndDescription() {
        infoRequest.setTitle("");
        infoRequest.setDescription("");
        String id = "edge-id";
        Info info = infoRequest.convert(id);
        assertEquals("edge-id", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    void testConvertWithWhitespaceTitleAndDescription() {
        infoRequest.setTitle("   ");
        infoRequest.setDescription("   ");
        String id = "whitespace-id";
        Info info = infoRequest.convert(id);
        assertEquals("whitespace-id", info.getId());
        assertEquals("   ", info.getTitle());
        assertEquals("   ", info.getDescription());
    }
}
