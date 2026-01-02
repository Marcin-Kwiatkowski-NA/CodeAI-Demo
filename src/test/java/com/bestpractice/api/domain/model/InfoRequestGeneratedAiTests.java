package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    void setUp() {
        // GIVEN a fresh InfoRequest instance
        infoRequest = new InfoRequest();
    }

    @Test
    void testSetAndGetTitle() {
        // GIVEN a title value
        String title = "Sample Title";

        // WHEN setting the title
        infoRequest.setTitle(title);

        // THEN the getter should return the same title
        assertThat(infoRequest.getTitle()).isEqualTo(title);
    }

    @Test
    void testSetAndGetDescription() {
        // GIVEN a description value
        String description = "Sample Description";

        // WHEN setting the description
        infoRequest.setDescription(description);

        // THEN the getter should return the same description
        assertThat(infoRequest.getDescription()).isEqualTo(description);
    }

    @Test
    void testGettersReturnNullWhenNotSet() {
        // GIVEN a new InfoRequest with no values set
        InfoRequest newRequest = new InfoRequest();

        // THEN getters should return null
        assertThat(newRequest.getTitle()).isNull();
        assertThat(newRequest.getDescription()).isNull();
    }

    @Test
    void testConvertCreatesInfoWithCorrectFields() {
        // GIVEN an InfoRequest with title and description
        String title = "Title";
        String description = "Description";
        String id = "12345";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN converting to Info
        Info info = infoRequest.convert(id);

        // THEN the resulting Info should have the same id, title, and description
        assertThat(info).isNotNull();
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    void testConvertWithNullId() {
        // GIVEN an InfoRequest with title and description
        String title = "Title";
        String description = "Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN converting with a null id
        Info info = infoRequest.convert(null);

        // THEN the resulting Info should have a null id but correct title and description
        assertThat(info).isNotNull();
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    void testConvertDoesNotModifyOriginalRequest() {
        // GIVEN an InfoRequest with title and description
        String title = "Original Title";
        String description = "Original Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN converting to Info
        Info info = infoRequest.convert("id");

        // THEN the original InfoRequest should remain unchanged
        assertThat(infoRequest.getTitle()).isEqualTo(title);
        assertThat(infoRequest.getDescription()).isEqualTo(description);
    }

    @Test
    void testConvertCreatesNewInfoInstance() {
        // GIVEN an InfoRequest
        infoRequest.setTitle("Title");
        infoRequest.setDescription("Description");

        // WHEN converting to Info twice
        Info info1 = infoRequest.convert("id1");
        Info info2 = infoRequest.convert("id2");

        // THEN each conversion should produce a distinct Info instance
        assertThat(info1).isNotSameAs(info2);
    }

    @Test
    void testConvertWithNullTitleAndDescription() {
        // GIVEN an InfoRequest with null title and description
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN converting to Info
        Info info = infoRequest.convert("id");

        // THEN the resulting Info should have null title and description
        assertThat(info).isNotNull();
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    void testConvertWithEmptyStrings() {
        // GIVEN an InfoRequest with empty title and description
        infoRequest.setTitle("");
        infoRequest.setDescription("");

        // WHEN converting to Info
        Info info = infoRequest.convert("empty-id");

        // THEN the resulting Info should have empty title and description
        assertThat(info).isNotNull();
        assertThat(info.getTitle()).isEqualTo("");
        assertThat(info.getDescription()).isEqualTo("");
    }
}
