package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class Info extends SharedData {

    @Column(name = "id")
    private String id;

    @NotNull
    @Column(nullable = false, name = "title")
    private String title;

    @NotNull
    @Column(nullable = false, name = "description")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class InfoGeneratedAiTests {

    @Test
    public void testCreateInfo() {
        // GIVEN a new Info object
        Info info = new Info();

        // WHEN the title and description are set
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        // THEN the title and description are correctly set
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }

    @Test
    public void testSetId() {
        // GIVEN a new Info object
        Info info = new Info();

        // WHEN the id is set
        info.setId("TestId");

        // THEN the id is correctly set
        assertEquals("TestId", info.getId());
    }

    @Test
    public void testCreatedAt() {
        // GIVEN a new Info object
        Info info = new Info();

        // WHEN the onPrePersist method is called
        info.onPrePersist();

        // THEN the createdAt field is set to the current date and time
        assertEquals(new Date(), info.getCreatedAt());
    }
}
