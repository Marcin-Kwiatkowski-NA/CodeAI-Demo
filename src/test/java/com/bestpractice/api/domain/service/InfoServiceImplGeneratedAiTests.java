package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
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

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InfoServiceImplGeneratedAiTests {

    @Mock
    private InfoPersistentRepository infoRepository;

    @InjectMocks
    private InfoServiceImpl infoService;

    private Info info;
    private InfoRequest infoRequest;

    @BeforeEach
    void setUp() {
        info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");

        infoRequest = new InfoRequest();
        infoRequest.setTitle("New Title");
        infoRequest.setDescription("New Description");
    }

    @Test
    void getInfos_shouldReturnListOfInfoResponses_whenRepositoryReturnsData() {
        // GIVEN
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertThat(responses).isNotEmpty();
        assertThat(responses.get(0).getId()).isEqualTo("1");
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
    void getInfo_shouldReturnInfoResponse_whenRepositoryReturnsData() {
        // GIVEN
        when(infoRepository.findById("1")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("1");

        // THEN
        assertThat(response.getId()).isEqualTo("1");
        assertThat(response.getTitle()).isEqualTo("Title");
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void getInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.getInfo("1"))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void updateInfo_shouldReturnUpdatedInfoResponse_whenRepositorySucceeds() {
        // GIVEN
        when(infoRepository.findById("1")).thenReturn(info);
        when(infoRepository.insert(any(Info.class))).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.updateInfo("1", infoRequest);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo("1");
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void updateInfo_shouldThrowBadRequest_whenFindByIdFails() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Not found"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", infoRequest))
                .isInstanceOf(BadRequest.class);
    }

    @Test
    void updateInfo_shouldThrowInternalServerError_whenInsertFails() {
        // GIVEN
        when(infoRepository.findById("1")).thenReturn(info);
        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert failed"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", infoRequest))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void generateInfo_shouldReturnInfoResponse_whenInsertSucceeds() {
        // GIVEN
        when(infoRepository.newId()).thenReturn("1");
        when(infoRepository.insert(any(Info.class))).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.generateInfo(infoRequest);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo("1");
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void generateInfo_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        when(infoRepository.newId()).thenReturn("1");
        when(infoRepository.insert(any(Info.class))).thenThrow(new Conflict());

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(infoRequest))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void generateInfo_shouldThrowInternalServerError_whenRepositoryThrowsOtherException() {
        // GIVEN
        when(infoRepository.newId()).thenReturn("1");
        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(infoRequest))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void deleteInfo_shouldCallRepositoryRemoveById_whenSuccessful() {
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
        doThrow(new RuntimeException("DB error")).when(infoRepository).removeById("1");

        // WHEN THEN
        assertThatThrownBy(() -> infoService.deleteInfo("1"))
                .isInstanceOf(InternalServerError.class);
    }
}
