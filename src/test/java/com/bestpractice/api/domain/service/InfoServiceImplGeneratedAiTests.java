package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InfoServiceImplGeneratedAiTests {

    @Mock
    private InfoPersistentRepository infoRepository;

    @InjectMocks
    private InfoServiceImpl infoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reset(infoRepository);
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
        assertThat(result.get(0).getId()).isEqualTo("1");
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfos_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        assertThatThrownBy(() -> infoService.getInfos())
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void getInfo_shouldReturnInfoResponse_whenRepositoryReturnsInfo() {
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Desc");
        when(infoRepository.findById("123")).thenReturn(info);

        InfoResponse response = infoService.getInfo("123");

        assertThat(response.getId()).isEqualTo("123");
        assertThat(response.getTitle()).isEqualTo("Title");
        verify(infoRepository, times(1)).findById("123");
    }

    @Test
    void getInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("Error"));

        assertThatThrownBy(() -> infoService.getInfo("123"))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void updateInfo_shouldUpdateAndReturnInfoResponse_whenValidRequest() {
        Info existing = new Info();
        existing.setId("1");
        existing.setTitle("Old");
        existing.setDescription("OldDesc");
        InfoRequest req = new InfoRequest();
        req.setTitle("New");
        req.setDescription("NewDesc");
        Info updated = req.convert("1");

        when(infoRepository.findById("1")).thenReturn(existing);
        when(infoRepository.insert(any(Info.class))).thenReturn(updated);

        InfoResponse response = infoService.updateInfo("1", req);

        assertThat(response.getTitle()).isEqualTo("New");
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void updateInfo_shouldThrowBadRequest_whenFindByIdThrowsException() {
        InfoRequest req = new InfoRequest();
        req.setTitle("T");
        req.setDescription("D");
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Error"));

        assertThatThrownBy(() -> infoService.updateInfo("1", req))
                .isInstanceOf(BadRequest.class);
    }

    @Test
    void updateInfo_shouldThrowInternalServerError_whenInsertThrowsException() {
        Info existing = new Info();
        existing.setId("1");
        InfoRequest req = new InfoRequest();
        req.setTitle("T");
        req.setDescription("D");
        when(infoRepository.findById("1")).thenReturn(existing);
        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert error"));

        assertThatThrownBy(() -> infoService.updateInfo("1", req))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void generateInfo_shouldReturnInfoResponse_whenInsertSucceeds() {
        InfoRequest req = new InfoRequest();
        req.setTitle("T");
        req.setDescription("D");
        Info info = req.convert("newId");
        when(infoRepository.newId()).thenReturn("newId");
        when(infoRepository.insert(any(Info.class))).thenReturn(info);

        InfoResponse response = infoService.generateInfo(req);

        assertThat(response.getId()).isEqualTo("newId");
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void generateInfo_shouldThrowConflict_whenRepositoryThrowsConflict() {
        InfoRequest req = new InfoRequest();
        req.setTitle("T");
        req.setDescription("D");
        when(infoRepository.newId()).thenReturn("id");
        when(infoRepository.insert(any(Info.class))).thenThrow(new Conflict("conflict"));

        assertThatThrownBy(() -> infoService.generateInfo(req))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void generateInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        InfoRequest req = new InfoRequest();
        req.setTitle("T");
        req.setDescription("D");
        when(infoRepository.newId()).thenReturn("id");
        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("error"));

        assertThatThrownBy(() -> infoService.generateInfo(req))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void deleteInfo_shouldCallRepositoryRemoveById_whenValidId() {
        when(infoRepository.removeById("1")).thenReturn(true);

        infoService.deleteInfo("1");

        verify(infoRepository, times(1)).removeById("1");
    }

    @Test
    void deleteInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        doThrow(new RuntimeException("error")).when(infoRepository).removeById("1");

        assertThatThrownBy(() -> infoService.deleteInfo("1"))
                .isInstanceOf(InternalServerError.class);
    }
}
