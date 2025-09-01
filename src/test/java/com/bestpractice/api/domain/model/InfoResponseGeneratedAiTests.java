package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfoResponseGeneratedAiTests {

  private InfoResponse infoResponse;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    // Reset the state before each test
    infoResponse = null;
  }

  @Test
  void getId() {
    // GIVEN a valid InfoResponse object
    String id = "123";
    String title = "Test Title";
    String description = "Test Description";
    infoResponse = new InfoResponse(id, title, description);

    // WHEN we call getID()
    String retrievedId = infoResponse.getId();

    // THEN the retrieved ID should match the original ID
    assertEquals(id, retrievedId);
  }

  @Test
  void getTitle() {
    // GIVEN a valid InfoResponse object
    String id = "456";
    String title = "Another Title";
    String description = "Another Description";
    infoResponse = new InfoResponse(id, title, description);

    // WHEN we call getTitle()
    String retrievedTitle = infoResponse.getTitle();

    // THEN the retrieved title should match the original title
    assertEquals(title, retrievedTitle);
  }

  @Test
  void getDescription() {
    // GIVEN a valid InfoResponse object
    String id = "789";
    String title = "Third Title";
    String description = "Third Description";
    infoResponse = new InfoResponse(id, title, description);

    // WHEN we call getDescription()
    String retrievedDescription = infoResponse.getDescription();

    // THEN the retrieved description should match the original description
    assertEquals(description, retrievedDescription);
  }
}
