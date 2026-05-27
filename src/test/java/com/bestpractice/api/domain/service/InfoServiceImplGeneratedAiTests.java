package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InfoServiceImplGeneratedAiTests {

    @Mock
    private InfoPersistentRepository infoRepository;

    @Mock
    private InfoRequest infoRequest;

    @InjectMocks
    private InfoServiceImpl infoService;

    @BeforeEach
    void setUp() {
        reset(infoRepository, infoRequest);
    }

    @Test
    void getInfos_shouldReturnListOfInfoResponses_whenRepositoryReturnsData() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");

        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> result = infoService.getInfos();

        // THEN
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(1).getTitle()).isEqualTo("Title2");
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
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertThat(response.getId()).isEqualTo("123");
        assertThat(response.getTitle()).isEqualTo("TestTitle");
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
    void updateInfo_shouldReturnUpdatedInfoResponse_whenSuccessful() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");

        Info updatedInfo = new Info();
        updatedInfo.setId("1");
        updatedInfo.setTitle("NewTitle");
        updatedInfo.setDescription("NewDesc");

        when(infoRepository.findById("1")).thenReturn(existingInfo);
        when(infoRequest.convert("1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("1", infoRequest);

        // THEN
        assertThat(response.getTitle()).isEqualTo("NewTitle");
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).insert(updatedInfo);
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
        Info existingInfo = new Info();
        existingInfo.setId("1");
        when(infoRepository.findById("1")).thenReturn(existingInfo);
        Info convertedInfo = new Info();
        convertedInfo.setId("1");
        when(infoRequest.convert("1")).thenReturn(convertedInfo);
        when(infoRepository.insert(convertedInfo)).thenThrow(new RuntimeException("Insert error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", infoRequest))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void generateInfo_shouldReturnInfoResponse_whenSuccessful() {
        // GIVEN
        Info newInfo = new Info();
        newInfo.setId("newId");
        newInfo.setTitle("Title");
        newInfo.setDescription("Desc");

        when(infoRepository.newId()).thenReturn("newId");
        when(infoRequest.convert("newId")).thenReturn(newInfo);
        when(infoRepository.insert(newInfo)).thenReturn(newInfo);

        // WHEN
        InfoResponse response = infoService.generateInfo(infoRequest);

        // THEN
        assertThat(response.getId()).isEqualTo("newId");
        assertThat(response.getTitle()).isEqualTo("Title");
        verify(infoRepository, times(1)).insert(newInfo);
    }

    @Test
    void generateInfo_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        Info newInfo = new Info();
        newInfo.setId("id");
        when(infoRepository.newId()).thenReturn("id");
        when(infoRequest.convert("id")).thenReturn(newInfo);
        when(infoRepository.insert(newInfo)).thenThrow(new Conflict("conflict"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(infoRequest))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void generateInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        Info newInfo = new Info();
        newInfo.setId("id");
        when(infoRepository.newId()).thenReturn("id");
        when(infoRequest.convert("id")).thenReturn(newInfo);
        when(infoRepository.insert(any())).thenThrow(new RuntimeException("Error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(infoRequest))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void deleteInfo_shouldCallRepositoryRemoveById_whenSuccessful() {
        // GIVEN
        doNothing().when(infoRepository).removeById("1");

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
