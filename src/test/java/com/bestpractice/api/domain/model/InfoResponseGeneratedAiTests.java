package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
    // Setup the InfoResponse object before each test
    infoResponse = new InfoResponse("123", "Test Title", "Test Description");
  }

  @Test
  void getId() {
    // GIVEN a valid InfoResponse object
    // WHEN the getId() method is called
    // THEN the id should return "123"
    assertEquals("123", infoResponse.getId());
  }

  @Test
  void getTitle() {
    // GIVEN a valid InfoResponse object
    // WHEN the getTitle() method is called
    // THEN the title should return "Test Title"
    assertEquals("Test Title", infoResponse.getTitle());
  }

  @Test
  void getDescription() {
    // GIVEN a valid InfoResponse object
    // WHEN the getDescription() method is called
    // THEN the description should return "Test Description"
    assertEquals("Test Description", infoResponse.getDescription());
  }
}
