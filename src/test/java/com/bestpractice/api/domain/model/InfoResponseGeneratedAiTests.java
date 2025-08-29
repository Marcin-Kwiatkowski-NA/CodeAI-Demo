package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoResponse {

  @JsonProperty("id")
  private final String id;

  @JsonProperty("title")
  private final String title;

  @JsonProperty("description")
  private final String description;

  public InfoResponse(String id, String title, String description) {
    this.id = id;
    this.title = title;
    this.description = description;
  }

  public String  getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }
}


package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @BeforeEach
  void setUp() {
    // GIVEN: Initialize a new InfoResponse object before each test.
    infoResponse = new InfoResponse("123", "Test Title", "Test Description");
  }

  @Test
  void getId() {
    // GIVEN: An InfoResponse object is created with an ID.
    // WHEN: The getId() method is called.
    // THEN: The ID is returned.
    assertEquals("123", infoResponse.getId());
  }

  @Test
  void getTitle() {
    // GIVEN: An InfoResponse object is created with a title.
    // WHEN: The getTitle() method is called.
    // THEN: The title is returned.
    assertEquals("Test Title", infoResponse.getTitle());
  }

  @Test
  void getDescription() {
    // GIVEN: An InfoResponse object is created with a description.
    // WHEN: The getDescription() method is called.
    // THEN: The description is returned.
    assertEquals("Test Description", infoResponse.getDescription());
  }
}
