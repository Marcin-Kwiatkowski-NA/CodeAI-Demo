package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;

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
        Mockito.reset(infoRepository);
    }

    @Test
    void getInfos_shouldReturnListOfInfoResponses_whenRepositoryReturnsEntities() {
        // GIVEN
        List<Info> infoEntities = new ArrayList<>();
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title1");
        info.setDescription("Description1");
        infoEntities.add(info);

        when(infoRepository.findAll()).thenReturn(infoEntities);

        // WHEN
        List<InfoResponse> result = infoService.getInfos();

        // THEN
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getTitle()).isEqualTo("Title1");
        assertThat(result.get(0).getDescription()).isEqualTo("Description1");
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfos_shouldThrowInternalServerError_whenRepositoryThrowsException() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.getInfos())
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class);
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

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.getInfo("1"))
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class);
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void updateInfo_shouldThrowBadRequest_whenRepositoryFindByIdThrowsException() {
        // GIVEN
        InfoRequest request = mock(InfoRequest.class);
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Entity not found"));

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", request))
                .isInstanceOf(BadRequest.class);
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, never()).insert(any(Info.class));
    }

    @Test
    void deleteInfo_shouldThrowInternalServerError_whenRepositoryRemoveByIdThrowsException() {
        // GIVEN
        String id = "1";
        doThrow(new RuntimeException("Database error")).when(infoRepository).removeById(id);

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.deleteInfo(id))
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class);
        verify(infoRepository, times(1)).removeById(id);
    }
}
