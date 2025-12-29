package com.bestpractice.api.domain.service;

import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

public class InfoServiceTest {

    @Mock
    private InfoRepository infoRepository;

    private InfoService infoService;

    @BeforeAll
    static void setUpBeforeClass() {
        // Setup code if needed
    }

    @BeforeEach
    void setUp() {
        infoService = new InfoService(infoRepository);
    }

    @Test
    void generateInfo_Success_Response_HasCorrectValues() {
        // GIVEN
        InfoRequest request = new InfoRequest("Title 1", "Description 1");
        Info response = new Info("id1", "Title 1", "Description 1");
        when(infoRepository.newId()).thenReturn("id1");
        when(infoRepository.insert(any(Info.class))).thenReturn(response);

        // WHEN
        Info result = infoService.generateInfo(request);

        // THEN
        assertThat(result.getTitle()).isEqualTo("Title 1");
        assertThat(result.getDescription()).isEqualTo("Description 1");
    }

    @Test
    void generateInfo_Conflict_Exception_ThrowsConflict() {
        // GIVEN
        InfoRequest request = new InfoRequest("Title 1", "Description 1");
        when(infoRepository.newId()).thenReturn("id1");
        when(infoRepository.insert(any(Info.class))).thenThrow(new Conflict("Duplicate ID"));

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(Conflict.class)
                .hasMessageContaining("Duplicate ID");
    }

    @Test
    void generateInfo_InternalError_Exception_ThrowsInternalServerError() {
        // GIVEN
        InfoRequest request = new InfoRequest("Title 1", "Description 1");
        when(infoRepository.newId()).thenReturn("id1");
        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Database error"));

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void deleteInfo_Success_ThrowsNoException() {
        // GIVEN
        String id = "id1";
        when(infoRepository.removeById(id)).thenReturn(true);

        // WHEN
        infoService.deleteInfo(id);

        // THEN
        Mockito.verify(infoRepository, Mockito.times(1)).removeById(id);
    }

    @Test
    void deleteInfo_Exception_ThrowsInternalServerError() {
        // GIVEN
        String id = "id1";
        when(infoRepository.removeById(id)).thenThrow(new RuntimeException("Delete failed"));

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.deleteInfo(id))
                .isInstanceOf(InternalServerError.class);
    }
}
