package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @Test
    void testConvert_validInput() {
        String id = "123";
        infoRequest.setTitle("Test Title");
        infoRequest.setDescription("Test Description");
        Info info = infoRequest.convert(id);
        assertEquals("123", info.getId());
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }

    @Test
    void testConvert_emptyTitle() {
        String id = "456";
        infoRequest.setTitle("");
        infoRequest.setDescription("Empty Description");
        Info info = infoRequest.convert(id);
        assertEquals("456", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("Empty Description", info.getDescription());
    }

    @Test
    void testConvert_emptyDescription() {
        String id = "789";
        infoRequest.setTitle("Empty Title");
        infoRequest.setDescription("");
        Info info = infoRequest.convert(id);
        assertEquals("789", info.getId());
        assertEquals("Empty Title", info.getTitle());
        assertEquals("", info.getDescription());
    }

    @Test
    void testConvert_bothEmpty() {
        String id = "111";
        infoRequest.setTitle("");
        infoRequest.setDescription("");
        Info info = infoRequest.convert(id);
        assertEquals("111", info.getId());
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
    }
}
