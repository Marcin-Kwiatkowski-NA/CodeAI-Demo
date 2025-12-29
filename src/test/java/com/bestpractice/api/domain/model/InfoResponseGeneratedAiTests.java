package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfoResponseGeneratedAiTests {

    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        infoResponse = new InfoResponse("1", "Test Title", "Test Description");
    }

    @Test
    void testInfoResponseGetters() {
        // GIVEN: A valid InfoResponse object
        // WHEN: Accessing the getters
        // THEN: The returned values should match the constructor arguments
        assertEquals("1", infoResponse.getId());
        assertEquals("Test Title", infoResponse.getTitle());
        assertEquals("Test Description", infoResponse.getDescription());
    }

    @Test
    void testInfoResponseConstructor() {
        // GIVEN: Valid parameters for InfoResponse constructor
        // WHEN: Creating a new InfoResponse object
        // THEN: The object should be initialized with the provided values
        InfoResponse response = new InfoResponse("2", "Another Title", "Another Description");
        assertEquals("2", response.getId());
        assertEquals("Another Title", response.getTitle());
        assertEquals("Another Description", response.getDescription());
    }

    @Test
    void testInfoResponseNullValues() {
        // GIVEN: Null values for InfoResponse constructor
        // WHEN: Creating a new InfoResponse object with null values
        // THEN: The object should be initialized with null values
        InfoResponse response = new InfoResponse(null, null, null);
        assertEquals(null, response.getId());
        assertEquals(null, response.getTitle());
        assertEquals(null, response.getDescription());
    }
}
