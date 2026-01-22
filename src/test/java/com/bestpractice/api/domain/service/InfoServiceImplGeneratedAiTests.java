package com.bestpractice.api.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class InfoServiceImplGeneratedAiTests {

    @Mock
    private InfoPersistentRepository infoRepository;

    @InjectMocks
    private InfoServiceImpl infoService;

    @BeforeEach
    void setUp() {
        // Reset mocks before each test to ensure isolation
        Mockito.reset(infoRepository);
    }

    @Test
    void getInfosReturnsMappedResponses() {
        // GIVEN
        List<Info> infoEntities = new ArrayList<>();
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        infoEntities.add(info1);
        infoEntities.add(info2);
        when(infoRepository.findAll()).thenReturn(infoEntities);

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getId()).isEqualTo("1");
        assertThat(responses.get(0).getTitle()).isEqualTo("Title1");
        assertThat(responses.get(0).getDescription()).isEqualTo("Desc1");
        assertThat(responses.get(1).getId()).isEqualTo("2");
        assertThat(responses.get(1).getTitle()).isEqualTo("Title2");
        assertThat(responses.get(1).getDescription()).isEqualTo("Desc2");
    }

    @Test
    void getInfosThrowsInternalServerErrorOnException() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.getInfos())
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class);
    }

    @Test
    void getInfosReturnsEmptyListWhenNoEntities() {
        // GIVEN
        when(infoRepository.findAll()).thenReturn(new ArrayList<>());

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertThat(responses).isEmpty();
    }

    @Test
    void getInfoReturnsResponse() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Sample Title");
        info.setDescription("Sample Description");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo("123");
        assertThat(response.getTitle()).isEqualTo("Sample Title");
        assertThat(response.getDescription()).isEqualTo("Sample Description");
    }

    @Test
    void getInfoThrowsInternalServerErrorOnException() {
        // GIVEN
        when(infoRepository.findById("any")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.getInfo("any"))
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class);
    }

    @Test
    void updateInfoSuccessful() {
        // GIVEN
        Info existing = new Info();
        existing.setId("456");
        existing.setTitle("Old Title");
        existing.setDescription("Old Desc");
        when(infoRepository.findById("456")).thenReturn(existing);

        InfoRequest request = mock(InfoRequest.class);
        Info updated = new Info();
        updated.setId("456");
        updated.setTitle("New Title");
        updated.setDescription("New Desc");
        when(request.convert("456")).thenReturn(updated);

        when(infoRepository.insert(updated)).thenReturn(updated);

        // WHEN
        InfoResponse response = infoService.updateInfo("456", request);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo("456");
        assertThat(response.getTitle()).isEqualTo("New Title");
        assertThat(response.getDescription()).isEqualTo("New Desc");
        verify(infoRepository).findById("456");
        verify(request).convert("456");
        verify(infoRepository).insert(updated);
    }

    @Test
    void updateInfoThrowsBadRequestWhenFindByIdFails() {
        // GIVEN
        when(infoRepository.findById("missing")).thenThrow(new RuntimeException("Not found"));
        InfoRequest request = mock(InfoRequest.class);

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.updateInfo("missing", request))
                .isInstanceOf(BadRequest.class);
    }

    @Test
    void generateInfoSuccessful() {
        // GIVEN
        when(infoRepository.newId()).thenReturn("789");
        InfoRequest request = mock(InfoRequest.class);
        Info generated = new Info();
        generated.setId("789");
        generated.setTitle("Generated Title");
        generated.setDescription("Generated Desc");
        when(infoRepository.insert(generated)).thenReturn(generated);

        // WHEN
        InfoResponse response = infoService.generateInfo(request);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo("789");
        assertThat(response.getTitle()).isEqualTo("Generated Title");
        assertThat(response.getDescription()).isEqualTo("Generated Desc");
        verify(infoRepository).newId();
        verify(infoRepository).insert(generated);
    }

    @Test
    void generateInfoThrowsConflictWhenInsertFails() {
        // GIVEN
        when(infoRepository.newId()).thenReturn("conflictId");
        InfoRequest request = mock(InfoRequest.class);
        when(infoRepository.insert(any(Info.class))).thenThrow(new Conflict("Duplicate key"));

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(Conflict.class)
                .hasMessageContaining("Duplicate key");
    }

    @Test
    void generateInfoThrowsInternalServerErrorWhenInsertFails() {
        // GIVEN
        when(infoRepository.newId()).thenReturn("errorId");
        InfoRequest request = mock(InfoRequest.class);
        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class);
    }

    @Test
    void deleteInfoSuccessful() {
        // GIVEN
        doNothing().when(infoRepository).removeById("toDelete");

        // WHEN
        infoService.deleteInfo("toDelete");

        // THEN
        verify(infoRepository).removeById("toDelete");
    }

    @Test
    void deleteInfoThrowsInternalServerErrorWhenException() {
        // GIVEN
        doThrow(new RuntimeException("Delete error")).when(infoRepository).removeById("badId");

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.deleteInfo("badId"))
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class);
    }
}
