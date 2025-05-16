package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @BeforeEach
  void setUp() {
    // Set up the InfoResponse object before each test
    this.infoResponse = new InfoResponse("123", "Example Title", "Example Description");
  }

  @Test
  void getId_returnsId() {
    // GIVEN: An InfoResponse object is created with an ID of "123".
    // WHEN: The getId() method is called.
    // THEN: The method returns the ID "123".
    assertEquals("123", infoResponse.getId());
  }

  @Test
  void getTitle_returnsTitle() {
    // GIVEN: An InfoResponse object is created with a title of "Example Title".
    // WHEN: The getTitle() method is called.
    // THEN: The method returns the title "Example Title".
    assertEquals("Example Title", infoResponse.getTitle());
  }

  @Test
  void getDescription_returnsDescription() {
    // GIVEN: An InfoResponse object is created with a description of "Example Description".
    // WHEN: The getDescription() method is called.
    // THEN: The method returns the description "Example Description".
    assertEquals("Example Description", infoResponse.getDescription());
  }
}
