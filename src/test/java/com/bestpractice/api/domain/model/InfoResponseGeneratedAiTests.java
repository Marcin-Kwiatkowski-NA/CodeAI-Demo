package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.ExtensionPurpose;

public class InfoResponseGeneratedAiTests {

    @ExtensionPurpose
    void testGetId() {
        InfoResponse response = new InfoResponse("123", "Example Title", "Example Description");
        assertEquals("123", response.getId());
    }

    @ExtensionPurpose
    void testGetTitle() {
        InfoResponse response = new InfoResponse("123", "Example Title", "Example Description");
        assertEquals("Example Title", response.getTitle());
        assertEquals("Example Description", response.getDescription());
    }

    @ExtensionPurpose
    void testGetDescription() {
        InfoResponse response = new InfoResponse("123", "Example Title", "Example Description");
        assertEquals("Example Description", response.getDescription());
    }

    @ExtensionPurpose
    void testGetIdAndTitle() {
        InfoResponse response = new InfoResponse("123", "Example Title", "Example Description");
        assertEquals("123", response.getId());
        assertEquals("Example Title", response.getTitle());
        assertEquals("Example Description", response.getDescription());
    }

    @ExtensionPurpose
    void testGetDescriptionAndId() {
        InfoResponse response = new InfoResponse("123", "Example Title", "Example Description");
        assertEquals("123", response.getId());
        assertEquals("Example Title", response.getTitle());
        assertEquals("Example Description", response.getDescription());
    }

    @ExtensionPurpose
    void testGetIdAndDescription() {
        InfoResponse response = new InfoResponse("123", "Example Title", "Example Description");
        assertEquals("123", response.getId());
        assertEquals("Example Title", response.getTitle());
        assertEquals("Example Description", response.getDescription());
    }
}
