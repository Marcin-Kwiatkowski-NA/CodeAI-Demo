package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
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
        infoRequest.setTitle("Updated Title");
        infoRequest.setDescription("Updated Description");
    }

    @Test
    void getInfos_shouldReturnListOfInfoResponses() {
        // GIVEN
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info));

        // WHEN
        List<InfoResponse> result = infoService.getInfos();

        // THEN
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getTitle()).isEqualTo("Title");
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfos_shouldThrowInternalServerError_whenRepositoryFails() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.getInfos())
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfo_shouldReturnInfoResponse() {
        // GIVEN
        when(infoRepository.findById("1")).thenReturn(info);

        // WHEN
        InfoResponse result = infoService.getInfo("1");

        // THEN
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo("Title");
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void getInfo_shouldThrowInternalServerError_whenRepositoryFails() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.getInfo("1"))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void updateInfo_shouldUpdateAndReturnInfoResponse() {
        // GIVEN
        when(infoRepository.findById("1")).thenReturn(info);
        Info updatedInfo = infoRequest.convert("1");
        when(infoRepository.insert(any(Info.class))).thenReturn(updatedInfo);

        // WHEN
        InfoResponse result = infoService.updateInfo("1", infoRequest);

        // THEN
        assertThat(result.getTitle()).isEqualTo("Updated Title");
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
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void updateInfo_shouldThrowInternalServerError_whenInsertFails() {
        // GIVEN
        when(infoRepository.findById("1")).thenReturn(info);
        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert failed"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", infoRequest))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void generateInfo_shouldReturnInfoResponse() {
        // GIVEN
        when(infoRepository.newId()).thenReturn("2");
        Info newInfo = infoRequest.convert("2");
        when(infoRepository.insert(any(Info.class))).thenReturn(newInfo);

        // WHEN
        InfoResponse result = infoService.generateInfo(infoRequest);

        // THEN
        assertThat(result.getId()).isEqualTo("2");
        assertThat(result.getTitle()).isEqualTo("Updated Title");
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void generateInfo_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        when(infoRepository.newId()).thenReturn("2");
        when(infoRepository.insert(any(Info.class))).thenThrow(new Conflict("Conflict"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(infoRequest))
                .isInstanceOf(Conflict.class);
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void generateInfo_shouldThrowInternalServerError_whenRepositoryFails() {
        // GIVEN
        when(infoRepository.newId()).thenReturn("2");
        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> infoService.generateInfo(infoRequest))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void deleteInfo_shouldRemoveInfoSuccessfully() {
        // GIVEN
        doNothing().when(infoRepository).removeById("1");

        // WHEN
        infoService.deleteInfo("1");

        // THEN
        verify(infoRepository, times(1)).removeById("1");
    }

    @Test
    void deleteInfo_shouldThrowInternalServerError_whenRepositoryFails() {
        // GIVEN
        doThrow(new RuntimeException("Delete failed")).when(infoRepository).removeById("1");

        // WHEN THEN
        assertThatThrownBy(() -> infoService.deleteInfo("1"))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).removeById("1");
    }
}
