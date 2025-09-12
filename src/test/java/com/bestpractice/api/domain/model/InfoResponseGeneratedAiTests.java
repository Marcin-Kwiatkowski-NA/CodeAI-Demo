package com.bestpractice.api.domain.model;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @BeforeEach
  void setUp() {
    // Set up the InfoResponse object with sample values before each test
    this.infoResponse = new InfoResponse("123", "Example Title", "This is a description.");
  }

  @Test
  void getId_returnsId() {
    // GIVEN: An InfoResponse object is created with an ID of "123".
    // WHEN: The getId() method is called.
    // THEN: The ID ("123") is returned.
    assertEquals("123", infoResponse.getId());
  }

  @Test
  void getTitle_returnsTitle() {
    // GIVEN: An InfoResponse object is created with a title of "Example Title".
    // WHEN: The getTitle() method is called.
    // THEN: The title ("Example Title") is returned.
    assertEquals("Example Title", infoResponse.getTitle());
  }

  @Test
  void getDescription_returnsDescription() {
    // GIVEN: An InfoResponse object is created with a description of "This is a description.".
    // WHEN: The getDescription() method is called.
    // THEN: The description ("This is a description.") is returned.
    assertEquals("This is a description.", infoResponse.getDescription());
  }
}