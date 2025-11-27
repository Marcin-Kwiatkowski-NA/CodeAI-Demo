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
    void givenTitle_whenSetTitle_thenTitleIsSetCorrectly() {
        // GIVEN
        String title = "Sample Title";

        // WHEN
        infoRequest.setTitle(title);

        // THEN
        assertThat(infoRequest.getTitle()).isEqualTo(title);
    }

    @Test
    void givenDescription_whenSetDescription_thenDescriptionIsSetCorrectly() {
        // GIVEN
        String description = "Sample Description";

        // WHEN
        infoRequest.setDescription(description);

        // THEN
        assertThat(infoRequest.getDescription()).isEqualTo(description);
    }

    @Test
    void givenValidInfoRequest_whenConvert_thenInfoObjectIsCreatedCorrectly() {
        // GIVEN
        String id = "123";
        String title = "Sample Title";
        String description = "Sample Description";
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
