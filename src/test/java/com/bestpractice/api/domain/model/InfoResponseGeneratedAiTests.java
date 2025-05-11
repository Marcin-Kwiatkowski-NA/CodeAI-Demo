package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @BeforeEach
  void setUp() {
    // Set up the InfoResponse object with sample data for each test.
    infoResponse = new InfoResponse("123", "Example Title", "This is a sample description.");
  }

  @Test
  void getId_returnsId() {
    // GIVEN: An InfoResponse object has been created with an ID of "123".
    // WHEN: The getId() method is called.
    // THEN: The method returns the ID "123".
    assertEquals("123", infoResponse.getId());
  }

  @Test
  void getTitle_returnsTitle() {
    // GIVEN: An InfoResponse object has been created with a title of "Example Title".
    // WHEN: The getTitle() method is called.
    // THEN: The method returns the title "Example Title".
    assertEquals("Example Title", infoResponse.getTitle());
  }

  @Test
  void getDescription_returnsDescription() {
    // GIVEN: An InfoResponse object has been created with a description of "This is a sample description.".
    // WHEN: The getDescription() method is called.
    // THEN: The method returns the description "This is a sample description.".
    assertEquals("This is a sample description.", infoResponse.getDescription());
  }
}
