package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doThrow;
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

import java.util.Arrays;
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
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Description1");

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Description2");

        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> result = infoService.getInfos();

        // THEN
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getTitle()).isEqualTo("Title1");
        assertThat(result.get(0).getDescription()).isEqualTo("Description1");
        assertThat(result.get(1).getId()).isEqualTo("2");
        assertThat(result.get(1).getTitle()).isEqualTo("Title2");
        assertThat(result.get(1).getDescription()).isEqualTo("Description2");
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfos_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.getInfos())
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfo_shouldReturnInfoResponse_whenRepositoryReturnsEntity() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title1");
        info.setDescription("Description1");

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
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void updateInfo_shouldReturnUpdatedInfoResponse_whenRepositoryUpdatesEntity() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        existingInfo.setTitle("Old Title");
        existingInfo.setDescription("Old Description");

        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("1");
        updatedInfo.setTitle("New Title");
        updatedInfo.setDescription("New Description");

        when(infoRepository.findById("1")).thenReturn(existingInfo);
        when(request.convert("1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
InfoResponse result = infoService.updateInfo("1", request);

        // THEN
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("New Title");
        assertThat(result.getDescription()).isEqualTo("New Description");
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).insert(updatedInfo);
    }

    @Test
    void updateInfo_shouldThrowBadRequest_whenRepositoryThrowsExceptionOnFind() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Database error"));

        InfoRequest request = mock(InfoRequest.class);

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", request))
                .isInstanceOf(BadRequest.class);
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void updateInfo_shouldThrowInternalServerError_whenRepositoryThrowsExceptionOnInsert() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        existingInfo.setTitle("Old Title");
        existingInfo.setDescription("Old Description");

        InfoRequest request = mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("1");
        updatedInfo.setTitle("New Title");
        updatedInfo.setDescription("New Description");

        when(infoRepository.findById("1")).thenReturn(existingInfo);
        when(request.convert("1")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", request))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).insert(updatedInfo);
    }

    @Test
    void generateInfo_shouldReturnInfoResponse_whenRepositoryInsertsEntity() {
        // GIVEN
        InfoRequest request = mock(InfoRequest.class);
        Info newInfo = new Info();
        newInfo.setId("1");
        newInfo.setTitle("Title");
        newInfo.setDescription("Description");

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
    void generateInfo_shouldThrowConflict_whenRepositoryThrowsConflictException() {
        // GIVEN
        InfoRequest request = mock(InfoRequest.class);
        Info newInfo = new Info();
        newInfo.setId("1");
        newInfo.setTitle("Title");
        newInfo.setDescription("Description");

        when(infoRepository.newId()).thenReturn("1");
        when(request.convert("1")).thenReturn(newInfo);
        when(infoRepository.insert(newInfo)).thenThrow(new Conflict("Conflict error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(Conflict.class);
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(newInfo);
    }

    @Test
    void generateInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        InfoRequest request = mock(InfoRequest.class);
        Info newInfo = new Info();
        newInfo.setId("1");
        newInfo.setTitle("Title");
        newInfo.setDescription("Description");

        when(infoRepository.newId()).thenReturn("1");
        when(request.convert("1")).thenReturn(newInfo);
        when(infoRepository.insert(newInfo)).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(newInfo);
    }

    @Test
    void deleteInfo_shouldCallRepositoryRemoveById_whenRepositoryExecutesSuccessfully() {
        // GIVEN
        String id = "1";

        // WHEN
        infoService.deleteInfo(id);

        // THEN
        verify(infoRepository, times(1)).removeById(id);
    }

    @Test
    void deleteInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        String id = "1";
        doThrow(new RuntimeException("Database error")).when(infoRepository).removeById(id);

        //WHEN THEN
        assertThatThrownBy(() -> infoService.deleteInfo(id))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).removeById(id);
    }
}