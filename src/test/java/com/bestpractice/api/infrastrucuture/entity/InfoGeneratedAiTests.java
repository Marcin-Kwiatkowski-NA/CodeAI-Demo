package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    @Test
    void givenValidId_whenSetId_thenIdIsSetCorrectly() {
        // GIVEN
        String id = "12345";

        // WHEN
        info.setId(id);

        // THEN
        assertThat(info.getId()).isEqualTo(id);
    }

    @Test
    void givenValidTitle_whenSetTitle_thenTitleIsSetCorrectly() {
        // GIVEN
        String title = "Test Title";

        // WHEN
        info.setTitle(title);

        // THEN
        assertThat(info.getTitle()).isEqualTo(title);
    }

    @Test
    void givenValidDescription_whenSetDescription_thenDescriptionIsSetCorrectly() {
        // GIVEN
        String description = "Test Description";

        // WHEN
        info.setDescription(description);

        // THEN
        assertThat(info.getDescription()).isEqualTo(description);
    }

    @Test
    void givenSharedData_whenOnPrePersist_thenCreatedAtIsSet() {
        // GIVEN
        Date beforePersist = new Date();

        // WHEN
        info.onPrePersist();

        // THEN
        assertThat(info.getCreatedAt()).isNotNull();
        assertThat(info.getCreatedAt()).isAfterOrEqualsTo(beforePersist);
    }
}
