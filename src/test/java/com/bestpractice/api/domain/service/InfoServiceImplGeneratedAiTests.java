package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        assertThat(result.get(0).getTitle()).isEqualTo("Title1");
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfos_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.getInfos())
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfo_shouldReturnInfoResponse_whenRepositoryReturnsInfo() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Sample Title");
        info.setDescription("Sample Description");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertThat(response.getId()).isEqualTo("123");
        assertThat(response.getTitle()).isEqualTo("Sample Title");
        verify(infoRepository, times(1)).findById("123");
    }

    @Test
    void getInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("Error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.getInfo("123"))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findById("123");
    }

    @Test
    void updateInfo_shouldUpdateAndReturnInfoResponse_whenValidRequest() {
        // GIVEN
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

        // WHEN
        InfoResponse response = infoService.updateInfo("id1", request);

        // THEN
        assertThat(response.getTitle()).isEqualTo("New Title");
        verify(infoRepository, times(1)).findById("id1");
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void updateInfo_shouldThrowBadRequest_whenFindByIdThrowsException() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");
        when(infoRepository.findById("id1")).thenThrow(new RuntimeException("Not found"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("id1", request))
                .isInstanceOf(BadRequest.class);
        verify(infoRepository, times(1)).findById("id1");
    }

    @Test
    void updateInfo_shouldThrowInternalServerError_whenInsertThrowsException() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("id1");
        existingInfo.setTitle("Old Title");
        existingInfo.setDescription("Old Desc");
        InfoRequest request = new InfoRequest();
        request.setTitle("New Title");
        request.setDescription("New Desc");
        when(infoRepository.findById("id1")).thenReturn(existingInfo);
        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert failed"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("id1", request))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findById("id1");
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void generateInfo_shouldReturnInfoResponse_whenInsertSucceeds() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");
        Info info = request.convert("newId");
        when(infoRepository.newId()).thenReturn("newId");
        when(infoRepository.insert(any(Info.class))).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.generateInfo(request);

        // THEN
        assertThat(response.getId()).isEqualTo("newId");
        assertThat(response.getTitle()).isEqualTo("Title");
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void generateInfo_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");
        when(infoRepository.newId()).thenReturn("newId");
        when(infoRepository.insert(any(Info.class))).thenThrow(new Conflict());

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(Conflict.class);
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void generateInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");
        when(infoRepository.newId()).thenReturn("newId");
        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void deleteInfo_shouldCallRemoveById_whenValidId() {
        // GIVEN
        when(infoRepository.removeById("id1")).thenReturn(true);

        // WHEN
        infoService.deleteInfo("id1");

        // THEN
        verify(infoRepository, times(1)).removeById("id1");
    }

    @Test
    void deleteInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        doThrow(new RuntimeException("Delete failed")).when(infoRepository).removeById("id1");

        // WHEN THEN
        assertThatThrownBy(() -> infoService.deleteInfo("id1"))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).removeById("id1");
    }
}
