package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

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
    // GIVEN: An InfoResponse object is created with a description of "Example Description".
    // WHEN: The getDescription() method is called.
    // THEN: The description ("Example Description") is returned.
    assertEquals("Example Description", infoResponse.getDescription());
  }
}
