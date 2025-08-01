package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @Test
  void getId_returnsId() {
    // GIVEN: A new InfoResponse object is created with id "123".
    // WHEN: The getId() method is called.
    // THEN: The id ("123") is returned.
    this.infoResponse = new InfoResponse("123", "Example Title", "Example Description");
    assertEquals("123", infoResponse.getId());
  }

  @Test
  void getTitle_returnsTitle() {
    // GIVEN: A new InfoResponse object is created with title "Example Title".
    // WHEN: The getTitle() method is called.
    // THEN: The title ("Example Title") is returned.
    this.infoResponse = new InfoResponse("123", "Example Title", "Example Description");
    assertEquals("Example Title", infoResponse.getTitle());
  }

  @Test
  void getDescription_returnsDescription() {
    // GIVEN: A new InfoResponse object is created with description "Example Description".
    // WHEN: The getDescription() method is called.
    // THEN: The description ("Example Description") is returned.
    this.infoResponse = new InfoResponse("123", "Example Title", "Example Description");
    assertEquals("Example Description", infoResponse.getDescription());
  }
}
