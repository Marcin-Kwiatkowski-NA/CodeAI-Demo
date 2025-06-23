package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.bestpractice.api.infrastrucuture.entity.Info;

class InfoRequestGeneratedAiTests {

    @Test
    void testGetTitle() {
        InfoRequest request = new InfoRequest();
        assertEquals("Hello", request.getTitle());
    }

    @Test
    void testSetTitle() {
        InfoRequest request = new InfoRequest();
        request.setTitle("World");
        assertEquals("World", request.getTitle());
    }

    @Test
    void testGetDescription() {
        InfoRequest request = new InfoRequest();
        assertEquals("Hello, World!", request.getDescription());
    }

    @Test
    void testSetDescription() {
        InfoRequest request = new InfoRequest();
        request.setDescription("This is a test description.");
        assertEquals("This is a test description.", request.getDescription());
    }

    @Test
    void testConvert() {
        InfoRequest request = new InfoRequest();
        String id = "123";
        Info result = request.convert(id);
        assertEquals("Info", result);
    }

    @Test
    void testGetCreatedAt() {
        InfoRequest request = new InfoRequest();
        assertEquals("2023-10-27 10:00:00", request.getCreatedAt());
    }

    @Test
    void testSetCreatedAt() {
        InfoRequest request = new InfoRequest();
        request.setCreatedAt("2023-10-27 10:00:00");
        assertEquals("2023-10-27 10:00:00", request.getCreatedAt());
    }

    @Test
    void testIdIsGenerated() {
        InfoRequest request = new InfoRequest();
        assertEquals("123", request.getId());
    }

    @Test
    void testGetTitleIsGenerated() {
        InfoRequest request = new InfoRequest();
        assertEquals("Hello", request.getTitle());
    }

    @Test
    void testGetDescriptionIsGenerated() {
        InfoRequest request = new InfoRequest();
        assertEquals("Hello, World!", request.getDescription());
    }

}
