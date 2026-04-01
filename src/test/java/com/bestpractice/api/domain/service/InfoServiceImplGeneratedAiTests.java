package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.mock;

public class InfoServiceImplGeneratedAiTests {

    private InfoPersistentRepository infoRepository;
    private InfoServiceImpl infoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        infoRepository = mock(InfoPersistentRepository.class);
        infoService = new InfoServiceImpl(infoRepository);
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
    void getInfo_shouldReturnInfoResponse_whenRepositoryReturnsInfo() {
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
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("Error"));

        assertThatThrownBy(() -> infoService.getInfo("123"))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findById("123");
    }

    @Test
    void updateInfo_shouldUpdateAndReturnInfoResponse_whenValidRequest() {
        Info existingInfo = new Info();
        existingInfo.setId("id1");
        existingInfo.setTitle("Old Title");
        existingInfo.setDescription("Old Desc");
        InfoRequest request = new InfoRequest();
        request.setTitle("New Title");
        request.setDescription("New Desc");
        Info updatedInfo = request.convert("id1");
        when(infoRepository.findById("id1")).thenReturn(existingInfo);
        when(infoRepository.insert(any(Info.class))).thenReturn(updatedInfo);

        InfoResponse result = infoService.updateInfo("id1", request);

        assertThat(result.getTitle()).isEqualTo("New Title");
        verify(infoRepository, times(1)).findById("id1");
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void updateInfo_shouldThrowBadRequest_whenFindByIdThrowsException() {
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");
        when(infoRepository.findById("id1")).thenThrow(new RuntimeException("Not found"));

        assertThatThrownBy(() -> infoService.updateInfo("id1", request))
                .isInstanceOf(BadRequest.class);
        verify(infoRepository, times(1)).findById("id1");
    }

    @Test
    void updateInfo_shouldThrowInternalServerError_whenInsertThrowsException() {
        Info existingInfo = new Info();
        existingInfo.setId("id1");
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");
        when(infoRepository.findById("id1")).thenReturn(existingInfo);
        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert error"));

        assertThatThrownBy(() -> infoService.updateInfo("id1", request))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findById("id1");
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void generateInfo_shouldReturnInfoResponse_whenInsertSucceeds() {
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");
        Info info = request.convert("newId");
        when(infoRepository.newId()).thenReturn("newId");
        when(infoRepository.insert(any(Info.class))).thenReturn(info);

        InfoResponse result = infoService.generateInfo(request);

        assertThat(result.getId()).isEqualTo("newId");
        assertThat(result.getTitle()).isEqualTo("Title");
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void generateInfo_shouldThrowConflict_whenRepositoryThrowsConflict() {
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");
        when(infoRepository.newId()).thenReturn("newId");
        when(infoRepository.insert(any(Info.class))).thenThrow(new Conflict("Conflict"));

        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(Conflict.class);
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void generateInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");
        when(infoRepository.newId()).thenReturn("newId");
        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Error"));

        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void deleteInfo_shouldCallRemoveById_whenValidId() {
        when(infoRepository.removeById("id1")).thenReturn(true);

        infoService.deleteInfo("id1");

        verify(infoRepository, times(1)).removeById("id1");
    }

    @Test
    void deleteInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        when(infoRepository.removeById("id1")).thenThrow(new RuntimeException("Error"));

        assertThatThrownBy(() -> infoService.deleteInfo("id1"))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).removeById("id1");
    }
}
