package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
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