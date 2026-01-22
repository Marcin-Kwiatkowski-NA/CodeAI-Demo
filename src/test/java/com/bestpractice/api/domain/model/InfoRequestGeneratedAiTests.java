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
    void testGettersAndSetters() {
        // GIVEN
        String title = "Sample Title";
        String description = "Sample Description";

        // WHEN
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // THEN
        assertThat(infoRequest.getTitle()).isEqualTo(title);
        assertThat(infoRequest.getDescription()).isEqualTo(description);
    }

    @Test
    void testConvertMethod() {
        // GIVEN
        String title = "Convert Title";
        String description = "Convert Description";
        String id = "12345";

        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN
        Info info = infoRequest.convert(id);

        // THEN
        assertThat(info).isNotNull();
        assertThat(info.getId()).isEqualTo(id);
        assertThat(info.getTitle()).isEqualTo(title);
        assertThat(info.getDescription()).isEqualTo(description);
        // createdAt is set by JPA lifecycle, should be null before persistence
        assertThat(info.getCreatedAt()).isNull();
    }
}
