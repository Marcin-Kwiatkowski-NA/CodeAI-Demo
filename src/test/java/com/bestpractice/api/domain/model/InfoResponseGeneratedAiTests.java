package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @Test
  void getId_returnsId() {
    // GIVEN: We have an InfoResponse object with an ID of "123".
    // WHEN: We call the getId() method.
    // THEN: The getId() method returns the ID "123".
    assertEquals("123", infoResponse.getId());
  }

  @Test
  void getTitle_returnsTitle() {
    // GIVEN: We have an InfoResponse object with a title of "Example Title".
    // WHEN: We call the getTitle() method.
    // THEN: The getTitle() method returns the title "Example Title".
    assertEquals("Example Title", infoResponse.getTitle());
  }

  @Test
  void getDescription_returnsDescription() {
    // GIVEN: We have an InfoResponse object with a description of "Example Description".
    // WHEN: We call the getDescription() method.
    // THEN: The getDescription() method returns the description "Example Description".
    assertEquals("Example Description", infoResponse.getDescription());
  }
}
