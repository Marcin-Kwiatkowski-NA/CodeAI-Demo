package com.bestpractice.api.domain.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    private InfoPersistentRepository infoRepository;

    @Mock
    private InfoRequest infoRequest;

    @InjectMocks
    private InfoServiceImpl infoService;

    @BeforeEach
    void setUp() {
        reset(infoRepository, infoRequest);
    }

    @Test
    void getInfos_success() {
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
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getId()).isEqualTo("1");
        assertThat(responses.get(0).getTitle()).isEqualTo("Title1");
        assertThat(responses.get(0).getDescription()).isEqualTo("Desc1");
        assertThat(responses.get(1).getId()).isEqualTo("2");
        assertThat(responses.get(1).getTitle()).isEqualTo("Title2");
        assertThat(responses.get(1).getDescription()).isEqualTo("Desc2");
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfos_internalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN / THEN
        assertThatThrownBy(() -> infoService.getInfos())
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("DB error");
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfo_success() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("Sample");
        info.setDescription("Sample description");

        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertThat(response.getId()).isEqualTo("123");
        assertThat(response.getTitle()).isEqualTo("Sample");
        assertThat(response.getDescription()).isEqualTo("Sample description");
        verify(infoRepository, times(1)).findById("123");
    }

    @Test
    void getInfo_internalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN / THEN
        assertThatThrownBy(() -> infoService.getInfo("123"))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("DB error");
        verify(infoRepository, times(1)).findById("123");
    }

    @Test
    void updateInfo_success() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("456");
        existingInfo.setTitle("Old");
        existingInfo.setDescription("Old desc");

        Info updatedInfo = new Info();
        updatedInfo.setId("456");
        updatedInfo.setTitle("New");
        updatedInfo.setDescription("New desc");

        when(infoRepository.findById("456")).thenReturn(existingInfo);
        when(infoRequest.convert("456")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("456", infoRequest);

        // THEN
        assertThat(response.getId()).isEqualTo("456");
        assertThat(response.getTitle()).isEqualTo("New");
        assertThat(response.getDescription()).isEqualTo("New desc");
        verify(infoRepository, times(1)).findById("456");
        verify(infoRequest, times(1)).convert("456");
        verify(infoRepository, times(1)).insert(updatedInfo);
    }

    @Test
    void updateInfo_badRequest() {
        // GIVEN
        when(infoRepository.findById("456")).thenThrow(new RuntimeException("Not found"));

        // WHEN / THEN
        assertThatThrownBy(() -> infoService.updateInfo("456", infoRequest))
                .isInstanceOf(BadRequest.class);
        verify(infoRepository, times(1)).findById("456");
        verify(infoRequest, never()).convert(anyString());
    }

    @Test
    void updateInfo_internalServerError() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("456");
        when(infoRepository.findById("456")).thenReturn(existingInfo);
        when(infoRequest.convert("456")).thenReturn(existingInfo);
        when(infoRepository.insert(existingInfo)).thenThrow(new RuntimeException("Insert error"));

        // WHEN / THEN
        assertThatThrownBy(() -> infoService.updateInfo("456", infoRequest))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Insert error");
        verify(infoRepository, times(1)).findById("456");
        verify(infoRequest, times(1)).convert("456");
        verify(infoRepository, times(1)).insert(existingInfo);
    }

    @Test
    void generateInfo_success() {
        // GIVEN
        Info newInfo = new Info();
        newInfo.setId("newId");
        newInfo.setTitle("Generated");
        newInfo.setDescription("Generated desc");

        when(infoRepository.newId()).thenReturn("newId");
        when(infoRequest.convert("newId")).thenReturn(newInfo);
        when(infoRepository.insert(newInfo)).thenReturn(newInfo);

        // WHEN
        InfoResponse response = infoService.generateInfo(infoRequest);

        // THEN
        assertThat(response.getId()).isEqualTo("newId");
        assertThat(response.getTitle()).isEqualTo("Generated");
        assertThat(response.getDescription()).isEqualTo("Generated desc");
        verify(infoRepository, times(1)).newId();
        verify(infoRequest, times(1)).convert("newId");
        verify(infoRepository, times(1)).insert(newInfo);
    }

    @Test
    void generateInfo_conflict() {
        // GIVEN
        Info newInfo = new Info();
        newInfo.setId("newId");
        when(infoRepository.newId()).thenReturn("newId");
        when(infoRequest.convert("newId")).thenReturn(newInfo);
        when(infoRepository.insert(newInfo)).thenThrow(new Conflict("Duplicate ID"));

        // WHEN / THEN
        assertThatThrownBy(() -> infoService.generateInfo(infoRequest))
                .isInstanceOf(Conflict.class)
                .hasMessageContaining("Duplicate ID");
        verify(infoRepository, times(1)).newId();
        verify(infoRequest, times(1)).convert("newId");
        verify(infoRepository, times(1)).insert(newInfo);
    }

    @Test
    void generateInfo_internalServerError() {
        // GIVEN
        Info newInfo = new Info();
        newInfo.setId("newId");
        when(infoRepository.newId()).thenReturn("newId");
        when(infoRequest.convert("newId")).thenReturn(newInfo);
        when(infoRepository.insert(newInfo)).thenThrow(new RuntimeException("DB error"));

        // WHEN / THEN
        assertThatThrownBy(() -> infoService.generateInfo(infoRequest))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("DB error");
        verify(infoRepository, times(1)).newId();
        verify(infoRequest, times(1)).convert("newId");
        verify(infoRepository, times(1)).insert(newInfo);
    }

    @Test
    void deleteInfo_success() {
        // GIVEN
        when(infoRepository.removeById("789")).thenReturn(true);

        // WHEN
        infoService.deleteInfo("789");

        // THEN
        verify(infoRepository, times(1)).removeById("789");
    }

    @Test
    void deleteInfo_internalServerError() {
        // GIVEN
        when(infoRepository.removeById("789")).thenThrow(new RuntimeException("Delete error"));

        // WHEN / THEN
        assertThatThrownBy(() -> infoService.deleteInfo("789"))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Delete error");
        verify(infoRepository, times(1)).removeById("789");
    }
}
