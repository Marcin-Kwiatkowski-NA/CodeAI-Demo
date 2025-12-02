package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;

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
    void getInfos_shouldReturnListOfInfoResponses_whenRepositoryReturnsData() {
        // GIVEN
        List<Info> mockInfoList = new ArrayList<>();
        mockInfoList.add(createMockInfo("1", "Title1", "Description1"));
        mockInfoList.add(createMockInfo("2", "Title2", "Description2"));
        when(infoRepository.findAll()).thenReturn(mockInfoList);

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

        // WHEN / THEN
        assertThatThrownBy(() -> infoService.getInfos())
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfo_shouldReturnInfoResponse_whenRepositoryReturnsData() {
        // GIVEN
        Info mockInfo = createMockInfo("1", "Title1", "Description1");
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

        // WHEN / THEN
        assertThatThrownBy(() -> infoService.getInfo("1"))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void updateInfo_shouldReturnUpdatedInfoResponse_whenRepositoryUpdatesData() {
        // GIVEN
        Info mockInfo = createMockInfo("1", "OldTitle", "OldDescription");
        InfoRequest mockRequest = createMockInfoRequest("NewTitle", "NewDescription");
        Info updatedInfo = createMockInfo("1", "NewTitle", "NewDescription");

        when(infoRepository.findById("1")).thenReturn(mockInfo);
        when(infoRepository.save(any(Info.class))).thenReturn(updatedInfo);

        // WHEN
        InfoResponse result = infoService.updateInfo("1", mockRequest);

        // THEN
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("NewTitle");
        assertThat(result.getDescription()).isEqualTo("NewDescription");
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).save(any(Info.class));
    }

    @Test
    void updateInfo_shouldThrowBadRequest_whenRepositoryFindByIdThrowsException() {
        // GIVEN
        InfoRequest mockRequest = createMockInfoRequest("NewTitle", "NewDescription");
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Not found"));

        // WHEN / THEN
       void updateInfo_shouldThrowBadRequest_whenRepositoryFindByIdThrowsException() {
        // GIVEN
        InfoRequest mockRequest = createMockInfoRequest("NewTitle", "NewDescription");
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Not found"));

        // WHEN / THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", mockRequest))
                .isInstanceOf(BadRequest.class);
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void updateInfo_shouldThrowInternalServerError_whenRepositorySaveThrowsException() {
        // GIVEN
        Info mockInfo = createMockInfo("1", "OldTitle", "OldDescription");
        InfoRequest mockRequest = createMockInfoRequest("NewTitle", "NewDescription");

        when(infoRepository.findById("1")).thenReturn(mockInfo);
        when(infoRepository.save(any(Info.class))).thenThrow(new RuntimeException("Database error"));

        // WHEN / THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", mockRequest))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).save(any(Info.class));
    }

    @Test
    void generateInfo_shouldReturnInfoResponse_whenRepositorySavesData() {
        // GIVEN
        InfoRequest mockRequest = createMockInfoRequest("Title1", "Description1");
        Info mockInfo = createMockInfo("1", "Title1", "Description1");

        when(infoRepository.newId()).thenReturn("1");
        when(infoRepository.save(any(Info.class))).thenReturn(mockInfo);

        // WHEN
        InfoResponse result = infoService.generateInfo(mockRequest);

        // THEN
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("Title1");
        assertThat(result.getDescription()).isEqualTo("Description1");
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).save(any(Info.class));
    }

    @Test
    void generateInfo_shouldThrowConflict_whenRepositoryThrowsConflictException() {
        // GIVEN
        InfoRequest mockRequest = createMockInfoRequest("Title1", "Description1");

        when(infoRepository.newId()).thenReturn("1");
        when(infoRepository.save(any(Info.class))).thenThrow(new Conflict("Conflict error"));

        // WHEN / THEN
        assertThatThrownBy(() -> infoService.generateInfo(mockRequest))
                .isInstanceOf(Conflict.class);
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).save(any(Info.class));
    }

    @Test
    void generateInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        InfoRequest mockRequest = createMockInfoRequest("Title1", "Description1");

        when(infoRepository.newId()).thenReturn("1");
        when(infoRepository.save(any(Info.class))).thenThrow(new RuntimeException("Database error"));

        // WHEN / THEN
        assertThatThrownBy(() -> infoService.generateInfo(mockRequest))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).save(any(Info.class));
    }

    @Test
    void deleteInfo_shouldCallRepositoryRemoveById_whenIdIsValid() {
        // GIVEN
        String id = "1";
        when(infoRepository.removeById(id)).thenReturn(true);

        // WHEN
        infoService.deleteInfo(id);

        // THEN
        verify(infoRepository, times(1)).removeById(id);
    }

    @Test
    void deleteInfo_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        String id = "1";
        when(infoRepository.removeById(id)).thenThrow(new RuntimeException("Database error"));

        // WHEN / THEN
        assertThatThrownBy(() -> infoService.deleteInfo(id))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).removeById(id);
    }

    private Info createMockInfo(String id, String title, String description) {
        Info info = new Info();
        info.setId(id);
        info.setTitle(title);
        info.setDescription(description);
        return info;
    }

    private InfoRequest createMockInfoRequest(String title, String description) {
        InfoRequest request = new InfoRequest();
        request.setTitle(title);
        request.setDescription(description);
        return request;
    }
}
