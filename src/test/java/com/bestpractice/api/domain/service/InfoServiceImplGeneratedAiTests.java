package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void getInfos_shouldReturnListOfInfoResponses_whenRepositoryReturnsData() {
        // GIVEN
        List<Info> mockInfoList = new ArrayList<>();
        mockInfoList.add(new Info() {{
            setId("1");
            setTitle("Title1");
            setDescription("Description1");
        }});
        mockInfoList.add(new Info() {{
            setId("2");
            setTitle("Title2");
            setDescription("Description2");
        }});
        when(infoRepository.findAll()).thenReturn(mockInfoList);

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
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class);
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfo_shouldReturnInfoResponse_whenRepositoryReturnsData() {
        // GIVEN
        Info mockInfo = new Info();
        mockInfo.setId("1");
        mockInfo.setTitle("Title1");
        mockInfo.setDescription("Description1");
        when(infoRepository.findById("1")).thenReturn(mockInfo);

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
    void updateInfo_shouldReturnUpdatedInfoResponse_whenRepositoryUpdatesData() {
        // GIVEN
        Info mockInfo = new Info();
        mockInfo.setId("1");
        mockInfo.setTitle("Old Title");
        mockInfo.setDescription("Old Description");
        InfoRequest mockRequest = new InfoRequest();
        mockRequest.setTitle("New Title");
        mockRequest.setDescription("New Description");
        Info updatedInfo = mockRequest.convert("1");
        when(infoRepository.findById("1")).thenReturn(mockInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse result = infoService.updateInfo("1", mockRequest);

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
        InfoRequest mockRequest = new InfoRequest();
        mockRequest.setTitle("New Title");
        mockRequest.setDescription("New Description");
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", mockRequest))
                .isInstanceOf(BadRequest.class);
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, never()).insert(any());
    }

    @Test
    void updateInfo_shouldThrowInternalServerError_whenRepositoryThrowsExceptionOnInsert() {
        // GIVEN
        Info mockInfo = new Info();
        mockInfo.setId("1");
        mockInfo.setTitle("Old Title");
        mockInfo.setDescription("Old Description");
        InfoRequest mockRequest = new InfoRequest();
        mockRequest.setTitle("New Title");
        mockRequest.setDescription("New Description");
        Info updatedInfo = mockRequest.convert("1");
        when(infoRepository.findById("1")).thenReturn(mockInfo);
        when(infoRepository.insert(updatedInfo)).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", mockRequest))
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class);
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).insert(updatedInfo);
    }

    @Test
    void generateInfo_shouldReturnGeneratedInfoResponse_whenRepositoryInsertsData() {
        // GIVEN
        InfoRequest mockRequest = new InfoRequest();
        mockRequest.setTitle("Title");
        mockRequest.setDescription("Description");
        Info mockInfo = mockRequest.convert("1");
        when(infoRepository.newId()).thenReturn("1");
        when(infoRepository.insert(mockInfo)).thenReturn(mockInfo);

        // WHEN
        InfoResponse result = infoService.generateInfo(mockRequest);

        // THEN
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("Title");
        assertThat(result.getDescription()).isEqualTo("Description");
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(mockInfo);
    }

    @Test
    void generateInfo_shouldThrowConflict_whenRepositoryThrowsConflictException() {
        // GIVEN
        InfoRequest mockRequest = new InfoRequest();
        mockRequest.setTitle("Title");
        mockRequest.setDescription("Description");
        Info mockInfo = mockRequest.convert("1");
        when(infoRepository.newId()).thenReturn("1");
        when(infoRepository.insert(mockInfo)).thenThrow(new Conflict("Conflict error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(mockRequest))
                .isInstanceOf(Conflict.class)
                .hasMessage("Conflict error");
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(mockInfo);
    }

    @Test
    void generateInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        InfoRequest mockRequest = new InfoRequest();
        mockRequest.setTitle("Title");
        mockRequest.setDescription("Description");
        Info mockInfo = mockRequest.convert("1");
        when(infoRepository.newId()).thenReturn("1");
        when(infoRepository.insert(mockInfo)).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(mockRequest))
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class);
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(mockInfo);
    }

    @Test
    void deleteInfo_shouldCallRepositoryRemoveById_whenRepositorySuccessfullyDeletes() {
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
        when(infoRepository.removeById("1")).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.deleteInfo("1"))
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class);
        verify(infoRepository, times(1)).removeById("1");
    }
}