package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfoResponseGeneratedAiTests {

    @Test
    void getId_returnsCorrectId() {
        // GIVEN: We have an InfoResponse object with an ID of "123".
        // WHEN: We call the getId() method.
        // THEN: The getId() method returns the correct ID, which is "123".
        InfoResponse infoResponse = new InfoResponse("123", "Example Title", "This is a sample description.");
        assertEquals("123", infoResponse.getId());
    }

    @Test
    void getTitle_returnsCorrectTitle() {
        // GIVEN: We have an InfoResponse object with a title of "Example Title".
        // WHEN: We call the getTitle() method.
        // THEN: The getTitle() method returns the correct title, which is "Example Title".
        InfoResponse infoResponse = new InfoResponse("123", "Example Title", "This is a sample description.");
        assertEquals("Example Title", infoResponse.getTitle());
    }

    @Test
    void getDescription_returnsCorrectDescription() {
        // GIVEN: We have an InfoResponse object with a description of "This is a sample description.".
        // WHEN: We call the getDescription() method.
        // THEN: The getDescription() method returns the correct description, which is "This is a sample description.".
        InfoResponse infoResponse = new InfoResponse("123", "Example Title", "This is a sample description.");
        assertEquals("This is a sample description.", infoResponse.getDescription());
    }
}
