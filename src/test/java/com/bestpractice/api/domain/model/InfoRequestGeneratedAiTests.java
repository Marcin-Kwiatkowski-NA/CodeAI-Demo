package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    void gettersAndSetters_ShouldReturnCorrectValues() {
        // GIVEN
        String title = "Sample Title";
        String description = "Sample Description";

        // WHEN
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);
        String retrievedTitle = infoRequest.getTitle();
        String retrievedDescription = infoRequest.getDescription();

        // THEN
        assertThat(retrievedTitle).isEqualTo(title);
        assertThat(retrievedDescription).isEqualTo(description);
    }

    @Test
    void convert_ShouldCreateInfoWithMatchingFields() {
        // GIVEN
        String title = "Test Title";
        String description = "Test Description";
        String id = "info-123";

        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertThat(info).isNotNull();
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
        assertThat(info).isNotSameAs(infoRequest);
    }

    @Test
    void convert_WithNullId_ShouldSetIdAsNull() {
        // GIVEN
        String title = "Null ID Title";
        String description = "Null ID Description";

        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(null);

        // THEN
        assertThat(info).isNotNull();
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    void convert_WithEmptyStrings_ShouldPreserveEmptyValues() {
        // GIVEN
        String title = "";
        String description = "";
        String id = "empty-strings";

        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertThat(info).isNotNull();
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    void convert_WithNullTitleAndDescription_ShouldSetNullInInfo() {
        // GIVEN
        String id = "id-null";

        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertThat(info).isNotNull();
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    void convert_WithNullTitle_ShouldSetNullInInfo() {
        // GIVEN
        String id = "id-null-title";
        String description = "Some description";

        infoRequest.setTitle(null);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertThat(info).isNotNull();
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    void convert_WithNullDescription_ShouldSetNullInInfo() {
        // GIVEN
        String id = "id-null-description";
        String title = "Some title";

        infoRequest.setTitle(title);
        infoRequest.setDescription(null);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertThat(info).isNotNull();
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isNull();
    }

    @Test
    void convert_ShouldReturnNewInstanceEachCall() {
        // GIVEN
        String id1 = "id1";
        String id2 = "id2";
        infoRequest.setTitle("Title");
        infoRequest.setDescription("Desc");

        // WHEN
        Info info1 = infoRequest.convert(id1);
        Info info2 = infoRequest.convert(id2);

        // THEN
        assertThat(info1).isNotSameAs(info2);
        assertThat(info1.getId()).isEqualTo(id1);
        assertThat(info2.getId()).isEqualTo(id2);
    }
}
