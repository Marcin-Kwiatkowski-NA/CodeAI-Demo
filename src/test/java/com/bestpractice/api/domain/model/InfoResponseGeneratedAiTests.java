package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @BeforeEach
  void setUp() {
    // GIVEN: A new InfoResponse object is created with an ID of "123", a title of "Example Title", and a description of "Example Description".
    this.infoResponse = new InfoResponse("123", "Example Title", "Example Description");
  }

  @Test
  void getId_returnsId() {
    // WHEN: The getId() method is called.
    // THEN: The ID ("123") is returned.
    assertEquals("123", infoResponse.getId());
  }

  @Test
  void getTitle_returnsTitle() {
    // GIVEN: A new InfoResponse object is created with a title of "Example Title".
    // WHEN: The getTitle() method is called.
    // THEN: The title ("Example Title") is returned.
    assertEquals("Example Title", infoResponse.getTitle());
  }

  @Test
  void getDescription_returnsDescription() {
    // GIVEN: A new InfoResponse object is created with a description of "Example Description".
    // WHEN: The getDescription() method is called.
    // THEN: The description ("Example Description") is returned.
    assertEquals("Example Description", infoResponse.getDescription());
  }

  @AfterEach
  void tearDown() {
    // Reset the state of the InfoResponse object before each test.
    infoResponse = null;
  }
}
