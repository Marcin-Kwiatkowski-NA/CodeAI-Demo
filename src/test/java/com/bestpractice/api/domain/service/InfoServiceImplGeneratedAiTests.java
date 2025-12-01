package com.bestpractice.api.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastructure.entity.Info;
import com.bestpractice.api.infrastructure.persistent.InfoPersistentRepository;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InfoServiceImplGeneratedAiTests {

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
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getTitle()).isEqualTo("Title1");
        assertThat(result.get(0).getDescription()).isEqualTo("Description1");
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException());

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

        when(infoRepository.findById("1")).thenReturn(mockInfo);

        // WHEN
        InfoResponse result = infoService.getInfo("1");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("Title1");
        assertThat(result.getDescription()).isEqualTo("Description1");
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException());

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
        mockInfo.setTitle("Updated Title");
        mockInfo.setDescription("Updated Description");

        InfoRequest mockRequest = mock(InfoRequest.class);
        when(infoRepository.findById("1")).thenReturn(mockInfo);
        when(mockRequest.convert("1")).thenReturn(mockInfo);
        when(infoRepository.insert(mockInfo)).thenReturn(mockInfo);

        // WHEN
        InfoResponse result = infoService.updateInfo("1", mockRequest);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("Updated Title");
        assertThat(result.getDescription()).isEqualTo("Updated Description");
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).insert(mockInfo);
    }

    @Test
    void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException());

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
        mockInfo.setTitle("Title");
        mockInfo.setDescription("Description");

        InfoRequest mockRequest = mock(InfoRequest.class);
        when(infoRepository.findById("1")).thenReturn(mockInfo);
        when(mockRequest.convert("1")).thenReturn(mockInfo);
        when(infoRepository.insert(mockInfo)).thenThrow(new RuntimeException());

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", mockRequest))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).insert(mockInfo);
    }

    @Test
    void testGenerateInfo() {
        // GIVEN
        Info mockInfo = new Info();
        mockInfo.setId("1");
        mockInfo.setTitle("Generated Title");
        mockInfo.setDescription("Generated Description");

        InfoRequest mockRequest = mock(InfoRequest.class);
        when(infoRepository.newId()).thenReturn("1");
        when(mockRequest.convert("1")).thenReturn(mockInfo);
        when(infoRepository.insert(mockInfo)).thenReturn(mockInfo);

        // WHEN
        InfoResponse result = infoService.generateInfo(mockRequest);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("Generated Title");
        assertThat(result.getDescription()).isEqualTo("Generated Description");
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(mockInfo);
    }

    @Test
    void testGenerateInfoThrowsConflict() {
        // GIVEN
        InfoRequest mockRequest = mock(InfoRequest.class);
        when(infoRepository.newId()).thenReturn("1");
        when(mockRequest.convert("1")).thenThrow(new Conflict());

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(mockRequest))
                .isInstanceOf(Conflict.class);
        verify(infoRepository, times(1)).newId();
        verify(mockRequest, times(1)).convert("1");
    }

    @Test
    void testGenerateInfoThrowsInternalServerError() {
        // GIVEN
        Info mockInfo = new Info();
        mockInfo.setId("1");
        mockInfo.setTitle("Generated Title");
        mockInfo.setDescription("Generated Description");

        InfoRequest mockRequest = mock(InfoRequest.class);
        when(infoRepository.newId()).thenReturn("1");
        when(mockRequest.convert("1")).thenReturn(mockInfo);
        when(infoRepository.insert(mockInfo)).thenThrow(new RuntimeException());

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(mockRequest))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(mockInfo);
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
        when(infoRepository.removeById("1")).thenThrow(new RuntimeException());

        // WHEN THEN
        assertThatThrownBy(() -> infoService.deleteInfo("1"))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).removeById("1");
    }
}
