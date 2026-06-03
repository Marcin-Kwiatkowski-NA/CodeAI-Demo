package com.bestpractice.api.domain.service;

import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class InfoServiceImplGeneratedAiTests {

    @Mock
    private InfoPersistentRepository infoRepository;

    @InjectMocks
    private InfoServiceImpl infoService;

    @BeforeEach
    void setUp() {
        reset(infoRepository);
    }

    @Test
    void getInfos_shouldReturnListOfInfoResponses_whenRepositoryReturnsData() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        when(infoRepository.findAll()).thenReturn(Collections.singletonList(info));

        // WHEN
        List<InfoResponse> result = infoService.getInfos();

        // THEN
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("1");
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfos_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.getInfos())
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void getInfo_shouldReturnInfoResponse_whenRepositoryReturnsInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Sample");
        info.setDescription("Desc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertThat(response.getId()).isEqualTo("123");
        assertThat(response.getTitle()).isEqualTo("Sample");
        verify(infoRepository, times(1)).findById("123");
    }

    @Test
    void getInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("Error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.getInfo("123"))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void updateInfo_shouldUpdateAndReturnInfoResponse_whenValidRequest() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        existingInfo.setTitle("Old");
        existingInfo.setDescription("Old Desc");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        Info updatedInfo = new Info();
        updatedInfo.setId("1");
        updatedInfo.setTitle("New");
        updatedInfo.setDescription("New Desc");
        when(infoRepository.insert(any(Info.class))).thenReturn(updatedInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("New");
        request.setDescription("New Desc");

        // WHEN
        InfoResponse response = infoService.updateInfo("1", request);

        // THEN
        assertThat(response.getTitle()).isEqualTo("New");
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void updateInfo_shouldThrowBadRequest_whenFindByIdFails() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Not found"));
        InfoRequest request = new InfoRequest();
        request.setTitle("T");
        request.setDescription("D");

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", request))
                .isInstanceOf(BadRequest.class);
    }

    @Test
    void updateInfo_shouldThrowInternalServerError_whenInsertFails() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        when(infoRepository.findById("1")).thenReturn(info);
        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert fail"));
        InfoRequest request = new InfoRequest();
        request.setTitle("T");
        request.setDescription("D");

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", request))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void generateInfo_shouldReturnInfoResponse_whenInsertSucceeds() {
        // GIVEN
        Info info = new Info();
        info.setId("newId");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(infoRepository.newId()).thenReturn("newId");
        when(infoRepository.insert(any(Info.class))).thenReturn(info);

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        // WHEN
        InfoResponse response = infoService.generateInfo(request);

        // THEN
        assertThat(response.getId()).isEqualTo("newId");
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void generateInfo_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("T");
        request.setDescription("D");
        when(infoRepository.newId()).thenReturn("id");
        when(infoRepository.insert(any(Info.class))).thenThrow(new Conflict());

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void generateInfo_shouldThrowInternalServerError_whenRepositoryThrowsOtherException() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("T");
        request.setDescription("D");
        when(infoRepository.newId()).thenReturn("id");
        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void deleteInfo_shouldCallRepositoryRemoveById() {
        // GIVEN
        when(infoRepository.removeById("1")).thenReturn(true);

        // WHEN
        infoService.deleteInfo("1");

        // THEN
        verify(infoRepository, times(1)).removeById("1");
    }

    @Test
    void deleteInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        doThrow(new RuntimeException("Error")).when(infoRepository).removeById("1");

        // WHEN THEN
        assertThatThrownBy(() -> infoService.deleteInfo("1"))
                .isInstanceOf(InternalServerError.class);
    }
}
