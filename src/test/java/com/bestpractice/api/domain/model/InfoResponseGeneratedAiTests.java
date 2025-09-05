package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @Test
  void getId_returnsId() {
    // GIVEN: We have an InfoResponse object with an ID.
    // WHEN: We call the getId() method.
    // THEN: The getId() method should return the ID.
    assertEquals("123", infoResponse.getId());
  }

  @Test
  void getTitle_returnsTitle() {
    // GIVEN: We have an InfoResponse object with a title.
    // WHEN: We call the getTitle() method.
    // THEN: The getTitle() method should return the title.
    assertEquals("Example Title", infoResponse.getTitle());
  }

  @Test
  void getDescription_returnsDescription() {
    // GIVEN: We have an InfoResponse object with a description.
    // WHEN: We call the getDescription() method.
    // THEN: The getDescription() method should return the description.
    assertEquals("This is a description.", infoResponse.getDescription());
  }
}
