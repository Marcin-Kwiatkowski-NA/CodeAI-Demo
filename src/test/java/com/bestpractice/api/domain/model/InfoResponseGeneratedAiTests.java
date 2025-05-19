package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Objects;

class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @BeforeEach
  void setUp() {
    // Initialize the InfoResponse object with sample data.
    this.infoResponse = new InfoResponse("123", "Example Title", "This is a sample description.");
  }

  @Test
  void getId_returnsId() {
    // GIVEN: We have an InfoResponse object with an ID of "123".
    // WHEN: We call the getId() method.
    // THEN: The getId() method should return the ID "123".
    String id = infoResponse.getId();
    Objects.requireNonNull(id, "ID should not be null");
    assertEquals("123", id);
  }

  @Test
  void getTitle_returnsTitle() {
    // GIVEN: We have an InfoResponse object with a title of "Example Title".
    // WHEN: We call the getTitle() method.
    // THEN: The getTitle() method should return the title "Example Title".
    String title = infoResponse.getTitle();
    Objects.requireNonNull(title, "Title should not be null");
    assertEquals("Example Title", title);
  }

  @Test
  void getDescription_returnsDescription() {
    // GIVEN: We have an InfoResponse object with a description of "This is a sample description".
    // WHEN: We call the getDescription() method.
    // THEN: The getDescription() method should return the description "This is a sample description".
    String description = infoResponse.getDescription();
    Objects.requireNonNull(description, "Description should not be null");
    assertEquals("This is a sample description", description);
  }
}
