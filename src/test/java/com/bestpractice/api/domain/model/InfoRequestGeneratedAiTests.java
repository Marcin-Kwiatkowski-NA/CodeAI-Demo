package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    void setUp() {
        infoRequest = new InfoRequest();
    }

    // Test for getTitle method
    @Test
    void givenTitleIsSet_whenGetTitle_thenReturnsCorrectTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";
        infoRequest.setTitle(expectedTitle);

        // WHEN
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    // Test for setTitle method
    @Test
    void givenTitle_whenSetTitle_thenTitleIsUpdated() {
        // GIVEN
        String newTitle = "Updated Title";

        // WHEN
        infoRequest.setTitle(newTitle);

        // THEN
        assertThat(infoRequest.getTitle()).isEqualTo(newTitle);
    }

    // Test for getDescription method
    @Test
    void givenDescriptionIsSet_whenGetDescription_thenReturnsCorrectDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";
        infoRequest.setDescription(expectedDescription);

        // WHEN
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    // Test for setDescription method
    @Test
    void givenDescription_whenSetDescription_thenDescriptionIsUpdated() {
        // GIVEN
        String newDescription = "Updated Description";

        // WHEN
        infoRequest.setDescription(newDescription);

        // THEN
        assertThat(infoRequest.getDescription()).isEqualTo(newDescription);
    }

    // Test for convert method
    @Test
    void givenValidInfoRequest_whenConvert_thenReturnsCorrectInfoObject() {
        // GIVEN
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
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
}
