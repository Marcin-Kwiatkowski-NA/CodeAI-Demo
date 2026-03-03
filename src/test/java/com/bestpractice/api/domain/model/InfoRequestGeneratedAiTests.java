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

public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    void setUp() {
        infoRequest = new InfoRequest();
    }

    @Test
    void testGettersAndSetters() {
        // GIVEN
        String title = "Sample Title";
        String description = "Sample Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        String retrievedTitle = infoRequest.getTitle();
        String retrievedDescription = infoRequest.getDescription();

        // THEN
        assertThat(retrievedTitle).isEqualTo(title);
        assertThat(retrievedDescription).isEqualTo(description);
    }

    @Test
    void testConvertSetsFieldsCorrectly() {
        // GIVEN
        String title = "Convert Title";
        String description = "Convert Description";
        String id = "info-001";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    void testConvertWithNullFields() {
        // GIVEN
        String id = null;
        // title and description remain null

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    @Test
    void testConvertAfterChangingFields() {
        // GIVEN
        String firstTitle = "First Title";
        String firstDescription = "First Description";
        String secondTitle = "Second Title";
        String secondDescription = "Second Description";
        String id = "id-123";

        infoRequest.setTitle(firstTitle);
        infoRequest.setDescription(firstDescription);

        // WHEN
        Info firstInfo = infoRequest.convert(id);

        // THEN
        assertThat(firstInfo.getTitle()).isEqualTo(firstTitle);
        assertThat(firstInfo.getDescription()).isEqualTo(firstDescription);

        // GIVEN
        infoRequest.setTitle(secondTitle);
        infoRequest.setDescription(secondDescription);

        // WHEN
        Info secondInfo = infoRequest.convert(id);

        // THEN
        assertThat(secondInfo.getTitle()).isEqualTo(secondTitle);
        assertThat(secondInfo.getDescription()).isEqualTo(secondDescription);
        assertThat(firstInfo.getTitle()).isEqualTo(firstTitle);
        assertThat(firstInfo.getDescription()).isEqualTo(firstDescription);
    }

    @Test
    void testSettersOverwritePreviousValues() {
        // GIVEN
        String initialTitle = "Initial";
        String newTitle = "Updated";
        String initialDescription = "Initial Desc";
        String newDescription = "Updated Desc";

        infoRequest.setTitle(initialTitle);
        infoRequest.setDescription(initialDescription);

        // WHEN
        infoRequest.setTitle(newTitle);
        infoRequest.setDescription(newDescription);

        // THEN
        assertThat(infoRequest.getTitle()).isEqualTo(newTitle);
        assertThat(infoRequest.getDescription()).isEqualTo(newDescription);
    }

    @Test
    void testConvertReturnsNewInstanceEachTime() {
        // GIVEN
        String title = "Title";
        String description = "Description";
        String id = "unique-id";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info firstInfo = infoRequest.convert(id);
        Info secondInfo = infoRequest.convert(id);

        // THEN
        assertThat(firstInfo).isNotSameAs(secondInfo);
        assertThat(firstInfo.getId()).isEqualTo(id);
        assertThat(secondInfo.getId()).isEqualTo(id);
    }
}
