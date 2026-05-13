package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InfoServiceImplGeneratedAiTests {

    @Mock
    private InfoPersistentRepository infoRepository;

    @InjectMocks
    private InfoServiceImpl infoService;

    @BeforeAll
    static void initMocks() {
        MockitoAnnotations.openMocks(InfoServiceImplGeneratedAiTests.class);
    }

    @BeforeEach
    void setUp() {
        infoService = new InfoServiceImpl(infoRepository);
    }

    @Test
    void getInfos_shouldReturnListOfInfoResponses_whenRepositoryReturnsData() {
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        List<InfoResponse> result = infoService.getInfos();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getTitle()).isEqualTo("Title1");
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfos_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        assertThatThrownBy(() -> infoService.getInfos())
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfo_shouldReturnInfoResponse_whenRepositoryReturnsData() {
        Info info = new Info();
        info.setId("123");
        info.setTitle("Sample Title");
        info.setDescription("Sample Description");
        when(infoRepository.findById("123")).thenReturn(info);

        InfoResponse result = infoService.getInfo("123");

        assertThat(result.getId()).isEqualTo("123");
        assertThat(result.getTitle()).isEqualTo("Sample Title");
        verify(infoRepository, times(1)).findById("123");
    }

    @Test
    void getInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        assertThatThrownBy(() -> infoService.getInfo("123"))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findById("123");
    }

    @Test
    void updateInfo_shouldUpdateAndReturnInfoResponse_whenValidRequest() {
        Info existingInfo = new Info();
        existingInfo.setId("1");
        existingInfo.setTitle("Old Title");
        existingInfo.setDescription("Old Desc");

        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("1");
        updatedInfo.setTitle("New Title");
        updatedInfo.setDescription("New Desc");

        when(infoRepository.findById("1")).thenReturn(existingInfo);
        when(request.convert("1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        InfoResponse result = infoService.updateInfo("1", request);

        assertThat(result.getTitle()).isEqualTo("New Title");
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).insert(updatedInfo);
    }

    @Test
    void updateInfo_shouldThrowBadRequest_whenFindByIdThrowsException() {
        InfoRequest request = mock(InfoRequest.class);
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Not found"));

        assertThatThrownBy(() -> infoService.updateInfo("1", request))
                .isInstanceOf(BadRequest.class);
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void updateInfo_shouldThrowInternalServerError_whenInsertThrowsException() {
        Info existingInfo = new Info();
        existingInfo.setId("1");
        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("1");
        when(infoRepository.findById("1")).thenReturn(existingInfo);
        when(request.convert("1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenThrow(new RuntimeException("Insert failed"));

        assertThatThrownBy(() -> infoService.updateInfo("1", request))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).insert(updatedInfo);
    }

    @Test
    void generateInfo_shouldReturnInfoResponse_whenInsertSucceeds() {
        InfoRequest request = mock(InfoRequest.class);
        Info info = new Info();
        info.setId("newId");
        info.setTitle("Generated Title");
        info.setDescription("Generated Desc");
        when(infoRepository.newId()).thenReturn("newId");
        when(request.convert("newId")).thenReturn(info);
        when(infoRepository.insert(info)).thenReturn(info);

        InfoResponse result = infoService.generateInfo(request);

        assertThat(result.getId()).isEqualTo("newId");
        assertThat(result.getTitle()).isEqualTo("Generated Title");
        verify(infoRepository, times(1)).insert(info);
    }

    @Test
    void generateInfo_shouldThrowConflict_whenRepositoryThrowsConflict() {
        InfoRequest request = mock(InfoRequest.class);
        Info info = new Info();
        info.setId("newId");
        when(infoRepository.newId()).thenReturn("newId");
        when(request.convert("newId")).thenReturn(info);
        when(infoRepository.insert(info)).thenThrow(new Conflict("Conflict error"));

        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(Conflict.class);
        verify(infoRepository, times(1)).insert(info);
    }

    @Test
    void generateInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        InfoRequest request = mock(InfoRequest.class);
        Info info = new Info();
        info.setId("newId");
        when(infoRepository.newId()).thenReturn("newId");
        when(request.convert("newId")).thenReturn(info);
        when(infoRepository.insert(info)).thenThrow(new RuntimeException("DB error"));

        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).insert(info);
    }

    @Test
    void deleteInfo_shouldInvokeRepositoryRemoveById_whenValidId() {
        doNothing().when(infoRepository).removeById("1");

        infoService.deleteInfo("1");

        verify(infoRepository, times(1)).removeById("1");
    }

    @Test
    void deleteInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        doThrow(new RuntimeException("Delete failed")).when(infoRepository).removeById("1");

        assertThatThrownBy(() -> infoService.deleteInfo("1"))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).removeById("1");
    }
}
