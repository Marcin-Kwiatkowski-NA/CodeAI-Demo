package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.extension.ExtendWith;

class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @BeforeEach
  void setUp() {
    // Initialize the InfoResponse object with sample values before each test
    this.infoResponse = new InfoResponse("123", "Example Title", "This is a description.");
  }

  @Test
  void getId_returnsCorrectId() {
    // GIVEN: An InfoResponse object is created with an ID of "123".
    // WHEN: The getId() method is called.
    // THEN: The method returns the correct ID, which is "123".
    assertEquals("123", infoResponse.getId());
  }

  @Test
  void getTitle_returnsCorrectTitle() {
    // GIVEN: An InfoResponse object is created with a title of "Example Title".
    // WHEN: The getTitle() method is called.
    // THEN: The method returns the correct title, which is "Example Title".
    assertEquals("Example Title", infoResponse.getTitle());
  }

  @Test
  void getDescription_returnsCorrectDescription() {
    // GIVEN: An InfoResponse object is created with a description of "This is a description.".
    // WHEN: The getDescription() method is called.
    // THEN: The method returns the correct description, which is "This is a description.".
    assertEquals("This is a description.", infoResponse.getDescription());
  }
}
