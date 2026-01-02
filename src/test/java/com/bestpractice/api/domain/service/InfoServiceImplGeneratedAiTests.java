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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class InfoServiceImplGeneratedAiTests {

    @Mock
    private InfoPersistentRepository repository;

    @InjectMocks
    private InfoServiceImpl service;

    @BeforeEach
    void setUp() {
        reset(repository);
    }

    @Test
    void testGetInfosReturnsMappedResponses() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");

        when(repository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = service.getInfos();

        // THEN
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getId()).isEqualTo("1");
        assertThat(responses.get(0).getTitle()).isEqualTo("Title1");
        assertThat(responses.get(0).getDescription()).isEqualTo("Desc1");
        assertThat(responses.get(1).getId()).isEqualTo("2");
        assertThat(responses.get(1).getTitle()).isEqualTo("Title2");
        assertThat(responses.get(1).getDescription()).isEqualTo("Desc2");
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetInfosThrowsInternalServerErrorWhenRepositoryFails() {
        // GIVEN
        when(repository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> service.getInfos());

        // THEN
        assertThat(thrown.getCause()).hasMessage("DB error");
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetInfoReturnsResponse() {
        // GIVEN
        Info info = new Info();
        info.setId("42");
        info.setTitle("Sample");
        info.setDescription("Sample description");
        when(repository.findById("42")).thenReturn(info);

        // WHEN
        InfoResponse response = service.getInfo("42");

        // THEN
        assertThat(response.getId()).isEqualTo("42");
        assertThat(response.getTitle()).isEqualTo("Sample");
        assertThat(response.getDescription()).isEqualTo("Sample description");
        verify(repository, times(1)).findById("42");
    }

    @Test
    void testGetInfoThrowsInternalServerErrorWhenRepositoryFails() {
        // GIVEN
        when(repository.findById("99")).thenThrow(new RuntimeException("Not found"));

        // WHEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> service.getInfo("99"));

        // THEN
        assertThat(thrown.getCause()).hasMessage("Not found");
        verify(repository, times(1)).findById("99");
    }

    @Test
    void testUpdateInfoReturnsUpdatedResponse() {
        // GIVEN
        Info existing = new Info();
        existing.setId("10");
        existing.setTitle("Old");
        existing.setDescription("Old desc");

        InfoRequest request = new InfoRequest();
        request.setTitle("New");
        request.setDescription("New desc");

        Info updated = new Info();
        updated.setId("10");
        updated.setTitle("New");
        updated.setDescription("New desc");

        when(repository.findById("10")).thenReturn(existing);
        when(repository.insert(any(Info.class))).thenReturn(updated);

        // WHEN
        InfoResponse response = service.updateInfo("10", request);

        // THEN
        assertThat(response.getId()).isEqualTo("10");
        assertThat(response.getTitle()).isEqualTo("New");
        assertThat(response.getDescription()).isEqualTo("New desc");
        verify(repository).findById("10");
        verify(repository).insert(argThat(info -> "10".equals(info.getId())
                && "New".equals(info.getTitle())
                && "New desc".equals(info.getDescription())));
    }

    @Test
    void testUpdateInfoThrowsBadRequestWhenFindByIdFails() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Any");
        request.setDescription("Any");
        when(repository.findById("invalid")).thenThrow(new RuntimeException("Not found"));

        // WHEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> service.updateInfo("invalid", request));

        // THEN
        assertThat(thrown).isNotNull();
        verify(repository).findById("invalid");
    }

    @Test
    void testUpdateInfoThrowsInternalServerErrorWhenInsertFails() {
        // GIVEN
        Info existing = new Info();
        existing.setId("5");
        existing.setTitle("Old");
        existing.setDescription("Old");

        InfoRequest request = new InfoRequest();
        request.setTitle("New");
        request.setDescription("New");

        when(repository.findById("5")).thenReturn(existing);
        when(repository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert error"));

        // WHEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> service.updateInfo("5", request));

        // THEN
        assertThat(thrown.getCause()).hasMessage("Insert error");
        verify(repository).findById("5");
        verify(repository).insert(any(Info.class));
    }

    @Test
    void testGenerateInfoReturnsResponse() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Gen");
        request.setDescription("Gen desc");

        String newId = "gen123";
        Info created = new Info();
        created.setId(newId);
        created.setTitle("Gen");
        created.setDescription("Gen desc");

        when(repository.newId()).thenReturn(newId);
        when(repository.insert(any(Info.class))).thenReturn(created);

        // WHEN
        InfoResponse response = service.generateInfo(request);

        // THEN
        assertThat(response.getId()).isEqualTo(newId);
        assertThat(response.getTitle()).isEqualTo("Gen");
        assertThat(response.getDescription()).isEqualTo("Gen desc");
        verify(repository).newId();
        verify(repository).insert(argThat(info -> newId.equals(info.getId())
                && "Gen".equals(info.getTitle())
                && "Gen desc".equals(info.getDescription())));
    }

    @Test
    void testGenerateInfoThrowsConflictWhenRepositoryThrowsConflict() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Conflict");
        request.setDescription("Conflict desc");

        when(repository.newId()).thenReturn("conflictId");
        when(repository.insert(any(Info.class))).thenThrow(new Conflict("Duplicate"));

        // WHEN
        Conflict thrown = assertThrows(Conflict.class, () -> service.generateInfo(request));

        // THEN
        assertThat(thrown).hasMessage("Duplicate");
        verify(repository).newId();
        verify(repository).insert(any(Info.class));
    }

    @Test
    void testGenerateInfoThrowsInternalServerErrorWhenOtherException() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Error");
        request.setDescription("Error desc");

        when(repository.newId()).thenReturn("errorId");
        when(repository.insert(any(Info.class))).thenThrow(new RuntimeException("DB fail"));

        // WHEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> service.generateInfo(request));

        // THEN
        assertThat(thrown.getCause()).hasMessage("DB fail");
        verify(repository).newId();
        verify(repository).insert(any(Info.class));
    }

    @Test
    void testDeleteInfoCallsRepository() {
        // GIVEN
        String id = "del123";

        // WHEN
        service.deleteInfo(id);

        // THEN
        verify(repository).removeById(id);
    }
}
