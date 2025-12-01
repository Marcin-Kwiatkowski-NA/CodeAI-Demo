package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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

    @Test
    void testGetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";
        infoRequest.setTitle(expectedTitle);

        // WHEN
        String actualTitle = infoRequest.getTitle();

        // THEN
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    void testSetTitle() {
        // GIVEN
        String expectedTitle = "New Title";

        // WHEN
        infoRequest.setTitle(expectedTitle);

        // THEN
        assertThat(infoRequest.getTitle()).isEqualTo(expectedTitle);
    }

    @Test
    void testGetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";
        infoRequest.setDescription(expectedDescription);

        // WHEN
        String actualDescription = infoRequest.getDescription();

        // THEN
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    void testSetDescription() {
        // GIVEN
        String expectedDescription = "New Description";

        // WHEN
        infoRequest.setDescription(expectedDescription);

        // THEN
        assertThat(infoRequest.getDescription()).isEqualTo(expectedDescription);
    }

    @Test
    void testConvert() {
        // GIVEN
        String id = "12345";
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
