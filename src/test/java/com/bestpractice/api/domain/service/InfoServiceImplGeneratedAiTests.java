package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class InfoServiceImplGeneratedAiTests {

    @Mock
    private InfoPersistentRepository infoRepository;

    @InjectMocks
    private InfoServiceImpl infoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetInfos() {
        // GIVEN
        List<Info> mockInfoList = new ArrayList<>();
        Info mockInfo = new Info();
        mockInfo.setId("1");
        mockInfo.setTitle("Title1");
        mockInfo.setDescription("Description1");
        mockInfoList.add(mockInfo);
        when(infoRepository.findAll()).thenReturn(mockInfoList);

        // WHEN
        List<InfoResponse> result = infoService.getInfos();

        // THEN
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getTitle()).isEqualTo("Title1");
        assertThat(result.get(0).getDescription()).isEqualTo("Description1");
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.getInfos())
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void testGetInfo() {
        // GIVEN
        Info mockInfo = new Info();
        mockInfo.setId("1");
        mockInfo.setTitle("Title1");
        mockInfo.setDescription("Description1");
        when(infoRepository.findById("1")).thenReturn(java.util.Optional.of(mockInfo));

        // WHEN
        InfoResponse result = infoService.getInfo("1");

        // THEN
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("Title1");
        assertThat(result.getDescription()).isEqualTo("Description1");
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.getInfo("1"))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void testUpdateInfo() {
        // GIVEN
        Info mockInfo = new Info();
        mockInfo.setId("1");
        mockInfo.setTitle("Old Title");
        mockInfo.setDescription("Old Description");
        InfoRequest mockRequest = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("1");
        updatedInfo.setTitle("New Title");
        updatedInfo.setDescription("New Description");

        when(infoRepository.findById("1")).thenReturn(java.util.Optional.of(mockInfo));
        when(mockRequest.convert("1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse result = infoService.updateInfo("1", mockRequest);

        // THEN
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("New Title");
        assertThat(result.getDescription()).isEqualTo("New Description");
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).insert(updatedInfo);
    }

    @Test
    void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Invalid ID"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", mock(InfoRequest.class)))
                .isInstanceOf(BadRequest.class);
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void testUpdateInfoThrowsInternalServerError() {
        // GIVEN
        Info mockInfo = new Info();
        mockInfo.setId("1");
        mockInfo.setTitle("Old Title");
        mockInfo.setDescription("Old Description");
        InfoRequest mockRequest = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("1");
        updatedInfo.setTitle("New Title");
        updatedInfo.setDescription("New Description");

        when(infoRepository.findById("1")).thenReturn(java.util.Optional.of(mockInfo));
        when(mockRequest.convert("1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", mockRequest))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).insert(updatedInfo);
    }

    @Test
    void testGenerateInfo() {
        // GIVEN
        InfoRequest mockRequest = mock(InfoRequest.class);
        Info newInfo = new Info();
        newInfo.setId("1");
        newInfo.setTitle("Generated Title");
        newInfo.setDescription("Generated Description");

        when(infoRepository.newId()).thenReturn("1");
        when(mockRequest.convert("1")).thenReturn(newInfo);
        when(infoRepository.insert(newInfo)).thenReturn(newInfo);

        // WHEN
        InfoResponse result = infoService.generateInfo(mockRequest);

        // THEN
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("Generated Title");
        assertThat(result.getDescription()).isEqualTo("Generated Description");
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(newInfo);
    }

    @Test
    void testGenerateInfoThrowsConflict() {
        // GIVEN
        InfoRequest mockRequest = mock(InfoRequest.class);
        Info newInfo = new Info();
        newInfo.setId("1");
        newInfo.setTitle("Generated Title");
        newInfo.setDescription("Generated Description");

        when(infoRepository.newId()).thenReturn("1");
        when(mockRequest.convert("1")).thenReturn(newInfo);
        when(infoRepository.insert(newInfo)).thenThrow(new Conflict("Conflict occurred"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(mockRequest))
                .isInstanceOf(Conflict.class);
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(newInfo);
    }

    @Test
    void testGenerateInfoThrowsInternalServerError() {
        // GIVEN
        InfoRequest mockRequest = mock(InfoRequest.class);
        Info newInfo = new Info();
        newInfo.setId("1");
        newInfo.setTitle("Generated Title");
        newInfo.setDescription("Generated Description");

        when(infoRepository.newId()).thenReturn("1");
        when(mockRequest.convert("1")).thenReturn(newInfo);
        when(infoRepository.insert(newInfo)).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(mockRequest))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(newInfo);
    }

    @Test
    void testDeleteInfo() {
        // GIVEN
        when(infoRepository.removeById("1")).thenReturn(true);

        // WHEN
        infoService.deleteInfo("1");

        // THEN
        verify(infoRepository, times(1)).removeById("1");
    }

    @Test
    void testDeleteInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.removeById("1")).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.deleteInfo("1"))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).removeById("1");
    }
}
