package com.bestpractice.api.domain.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

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
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class InfoServiceImplGeneratedAiTests {

    @Mock
    private InfoPersistentRepository infoRepository;

    @Mock
    private InfoRequest infoRequest;

    private InfoServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new InfoServiceImpl(infoRepository);
    }

    @Test
    void getInfos_success() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title 1");
        info1.setDescription("Description 1");

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title 2");
        info2.setDescription("Description 2");

        List<Info> infoList = Arrays.asList(info1, info2);
        when(infoRepository.findAll()).thenReturn(infoList);

        // WHEN
        List<InfoResponse> responses = service.getInfos();

        // THEN
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getId()).isEqualTo("1");
        assertThat(responses.get(0).getTitle()).isEqualTo("Title 1");
        assertThat(responses.get(0).getDescription()).isEqualTo("Description 1");
        assertThat(responses.get(1).getId()).isEqualTo("2");
        assertThat(responses.get(1).getTitle()).isEqualTo("Title 2");
        assertThat(responses.get(1).getDescription()).isEqualTo("Description 2");
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfos_repositoryThrowsException() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> service.getInfos());

        // THEN
        assertThat(thrown).hasMessageContaining("DB error");
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfo_success() {
        // GIVEN
        Info info = new Info();
        info.setId("42");
        info.setTitle("The Answer");
        info.setDescription("Answer to everything");
        when(infoRepository.findById("42")).thenReturn(info);

        // WHEN
        InfoResponse response = service.getInfo("42");

        // THEN
        assertThat(response.getId()).isEqualTo("42");
        assertThat(response.getTitle()).isEqualTo("The Answer");
        assertThat(response.getDescription()).isEqualTo("Answer to everything");
        verify(infoRepository, times(1)).findById("42");
    }

    @Test
    void getInfo_repositoryThrowsException() {
        // GIVEN
        when(infoRepository.findById("99")).thenThrow(new RuntimeException("Not found"));

        // WHEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> service.getInfo("99"));

        // THEN
        assertThat(thrown).hasMessageContaining("Not found");
        verify(infoRepository, times(1)).findById("99");
    }

    @Test
    void updateInfo_success() {
        // GIVEN
        Info existing = new Info();
        existing.setId("10");
        existing.setTitle("Old Title");
        existing.setDescription("Old Description");
        when(infoRepository.findById("10")).thenReturn(existing);

        Info updated = new Info();
        updated.setId("10");
        updated.setTitle("New Title");
        updated.setDescription("New Description");
        when(infoRequest.convert("10")).thenReturn(updated);

        when(infoRepository.insert(updated)).thenReturn(updated);

        // WHEN
        InfoResponse response = service.updateInfo("10", infoRequest);

        // THEN
        assertThat(response.getId()).isEqualTo("10");
        assertThat(response.getTitle()).isEqualTo("New Title");
        assertThat(response.getDescription()).isEqualTo("New Description");
        verify(infoRepository, times(1)).findById("10");
        verify(infoRepository, times(1)).insert(updated);
    }

    @Test
    void updateInfo_findByIdThrows() {
        // GIVEN
        when(infoRepository.findById("5")).thenThrow(new RuntimeException("DB error"));

        // WHEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> service.updateInfo("5", infoRequest));

        // THEN
        assertThat(thrown).hasMessageContaining("DB error");
        verify(infoRepository, times(1)).findById("5");
        verifyNoMoreInteractions(infoRepository);
    }

    @Test
    void generateInfo_success() {
        // GIVEN
        String newId = "NEW_ID";
        when(infoRepository.newId()).thenReturn(newId);

        Info created = new Info();
        created.setId(newId);
        created.setTitle("Generated Title");
        created.setDescription("Generated Description");
        when(infoRequest.convert(newId)).thenReturn(created);

        when(infoRepository.insert(created)).thenReturn(created);

        // WHEN
        InfoResponse response = service.generateInfo(infoRequest);

        // THEN
        assertThat(response.getId()).isEqualTo(newId);
        assertThat(response.getTitle()).isEqualTo("Generated Title");
        assertThat(response.getDescription()).isEqualTo("Generated Description");
        verify(infoRepository, times(1)).newId();
        verify(infoRequest, times(1)).convert(newId);
        verify(infoRepository, times(1)).insert(created);
    }

    @Test
    void generateInfo_conflict() {
        // GIVEN
        String newId = "CONFLICT_ID";
        when(infoRepository.newId()).thenReturn(newId);
        when(infoRequest.convert(newId)).thenThrow(new Conflict("Duplicate ID"));

        // WHEN
        Conflict thrown = assertThrows(Conflict.class, () -> service.generateInfo(infoRequest));

        // THEN
        assertThat(thrown).hasMessageContaining("Duplicate ID");
        verify(infoRepository, times(1)).newId();
        verify(infoRequest, times(1)).convert(newId);
        verify(infoRepository, never()).insert(any());
    }

    @Test
    void generateInfo_genericException() {
        // GIVEN
        String newId = "GENERIC_ID";
        when(infoRepository.newId()).thenReturn(newId);
        when(infoRequest.convert(newId)).thenThrow(new RuntimeException("Unexpected"));

        // WHEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> service.generateInfo(infoRequest));

        // THEN
        assertThat(thrown).hasMessageContaining("Unexpected");
        verify(infoRepository, times(1)).newId();
        verify(infoRequest, times(1)).convert(newId);
        verify(infoRepository, never()).insert(any());
    }

    @Test
    void deleteInfo_success() {
        // GIVEN
        doNothing().when(infoRepository).removeById("5");

        // WHEN
        service.deleteInfo("5");

        // THEN
        verify(infoRepository, times(1)).removeById("5");
    }

    @Test
    void deleteInfo_repositoryThrowsException() {
        // GIVEN
        doThrow(new RuntimeException("Delete error")).when(infoRepository).removeById("7");

        // WHEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> service.deleteInfo("7"));

        // THEN
        assertThat(thrown).hasMessageContaining("Delete error");
        verify(infoRepository, times(1)).removeById("7");
    }
}
