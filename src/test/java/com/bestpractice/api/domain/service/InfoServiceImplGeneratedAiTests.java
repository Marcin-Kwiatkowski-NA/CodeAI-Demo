package com.bestpractice.api.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

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
    void getInfos_shouldReturnListOfInfoResponses_whenRepositoryReturnsEntities() {
        // GIVEN
        List<Info> infoEntities = new ArrayList<>();
        infoEntities.add(createInfo("1", "Title1", "Description1"));
        infoEntities.add(createInfo("2", "Title2", "Description2"));
        when(infoRepository.findAll()).thenReturn(infoEntities);

        // WHEN
        List<InfoResponse> result = infoService.getInfos();

        // THEN
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getTitle()).isEqualTo("Title1");
        assertThat(result.get(0).getDescription()).isEqualTo("Description1");
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfos_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.getInfos())
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class);
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfo_shouldReturnInfoResponse_whenRepositoryReturnsEntity() {
        // GIVEN
        Info info = createInfo("1", "Title1", "Description1");
        when(infoRepository.findById("1")).thenReturn(info);

        // WHEN
        InfoResponse result = infoService.getInfo("1");

        // THEN
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("Title1");
        assertThat(result.getDescription()).isEqualTo("Description1");
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void getInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.getInfo("1"))
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class);
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void updateInfo_shouldReturnUpdatedInfoResponse_whenRepositoryUpdatesEntity() {
        // GIVEN
        Info existingInfo = createInfo("1", "OldTitle", "OldDescription");
        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = createInfo("1", "NewTitle", "NewDescription");

        when(infoRepository.findById("1")).thenReturn(existingInfo);
        when(request.convert("1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse result = infoService.updateInfo("1", request);

        // THEN
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("NewTitle");
        assertThat(result.getDescription()).isEqualTo("NewDescription");
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).insert(updatedInfo);
    }

    @Test
    void updateInfo_shouldThrowBadRequest_whenRepositoryFindByIdThrowsException() {
        // GIVEN
        InfoRequest request = mock(InfoRequest.class);
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Entity not found"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", request))
                .isInstanceOf(BadRequest.class);
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void updateInfo_shouldThrowInternalServerError_whenRepositoryInsertThrowsException() {
        // GIVEN
        Info existingInfo = createInfo("1", "OldTitle", "OldDescription");
        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = createInfo("1", "NewTitle", "NewDescription");

        when(infoRepository.findById("1")).thenReturn(existingInfo);
        when(request.convert("1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", request))
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class);
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).insert(updatedInfo);
    }

    @Test
    void generateInfo_shouldReturnInfoResponse_whenRepositoryInsertsEntity() {
        // GIVEN
        InfoRequest request = mock(InfoRequest.class);
        Info newInfo = createInfo("1", "Title", "Description");

        when(infoRepository.newId()).thenReturn("1");
        when(request.convert("1")).thenReturn(newInfo);
        when(infoRepository.insert(newInfo)).thenReturn(newInfo);

        // WHEN
        InfoResponse result = infoService.generateInfo(request);

        // THEN
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("Title");
        assertThat(result.getDescription()).isEqualTo("Description");
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(newInfo);
    }

    @Test
    void generateInfo_shouldThrowConflict_whenRepositoryInsertThrowsConflictException() {
        // GIVEN
        InfoRequest request = mock(InfoRequest.class);
        Info newInfo = createInfo("1", "Title", "Description");

        when(infoRepository.newId()).thenReturn("1");
        when(request.convert("1")).thenReturn(newInfo);
        when(infoRepository.insert(newInfo)).thenThrow(new Conflict("Conflict error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(Conflict.class)
                .hasMessage("Conflict error");
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(newInfo);
    }

    @Test
    void generateInfo_shouldThrowInternalServerError_whenRepositoryInsertThrowsException() {
        // GIVEN
        InfoRequest request = mock(InfoRequest.class);
        Info newInfo = createInfo("1", "Title", "Description");

        when(infoRepository.newId()).thenReturn("1");
        when(request.convert("1")).thenReturn(newInfo);
        when(infoRepository.insert(newInfo)).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class);
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(newInfo);
    }

    @Test
    void deleteInfo_shouldCallRepositoryRemoveById_whenRepositoryRemovesEntity() {
        // GIVEN
        when(infoRepository.removeById("1")).thenReturn(true);

        // WHEN
        infoService.deleteInfo("1");

        // THEN
        verify(infoRepository, times(1)).removeById("1");
    }

    @Test
    void deleteInfo_shouldThrowInternalServerError_whenRepositoryRemoveByIdThrowsException() {
        // GIVEN
        when(infoRepository.removeById("1")).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.deleteInfo("1"))
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class);
        verify(infoRepository, times(1)).removeById("1");
    }

    private Info createInfo(String id, String title, String description) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(title);
        info.setDescription(description);
        return info;
    }
}
