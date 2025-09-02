package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class InfoRequestObject {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

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

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}

package com.bestpractice.api.infrastrucuture.entity;

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

package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.infrastrucuture.entity.Info;

public class InfoRequestGeneratedAiTests {

    InfoRequestObject infoRequest;

    @BeforeEach
    void setUp() {
        infoRequest = new InfoRequestObject();
    }

    @Test
    void convert_validId_returnsInfoWithCorrectValues() {
        // GIVEN: A valid ID string.
        String id = "123";

        // WHEN: The InfoRequest object converts to an Info object.
        Info info = infoRequest.convert(id);

        // THEN: The resulting Info object should have the correct ID, title, and description.
        assertEquals("123", info.getId());
        assertEquals("testTitle", info.getTitle());
        assertEquals("testDescription", info.getDescription());
    }

    @Test
    void convert_emptyId_throwsException() {
        // GIVEN: An empty ID string.
        String id = "";

        // WHEN: The InfoRequest object attempts to convert to an Info object.
        assertThrows(NullPointerException.class, () -> infoRequest.convert(id));
    }

    @Test
    void setTitle_updatesTitleField() {
        // GIVEN: An InfoRequest object with an initial title.
        String title = "initialTitle";
        infoRequest.setTitle(title);

        // THEN: The Info object's title should be updated to the new value.
        assertEquals(title, infoRequest.getDescription());
    }

    @Test
    void setDescription_updatesDescriptionField() {
        // GIVEN: An InfoRequest object with an initial description.
        String description = "initialDescription";
        infoRequest.setDescription(description);

        // THEN: The Info object's description should be updated to the new value.
        assertEquals(description, infoRequest.getTitle());
    }
}
