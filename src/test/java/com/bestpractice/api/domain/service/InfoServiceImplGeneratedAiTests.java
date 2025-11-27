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

import java.util.Arrays;
import java.util.Collections;
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
    void givenInfosExist_whenGetInfos_thenReturnInfoResponses() {
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
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getId()).isEqualTo("1");
        assertThat(responses.get(0).getTitle()).isEqualTo("Title1");
        assertThat(responses.get(0).getDescription()).isEqualTo("Description1");
        assertThat(responses.get(1).getId()).isEqualTo("2");
        assertThat(responses.get(1).getTitle()).isEqualTo("Title2");
        assertThat(responses.get(1).getDescription()).isEqualTo("Description2");
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void givenNoInfosExist_whenGetInfos_thenReturnEmptyList() {
        // GIVEN
        when(infoRepository.findAll()).thenReturn(Collections.emptyList());

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertThat(responses).isEmpty();
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void givenValidId_whenGetInfo_thenReturnInfoResponse() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title1");
        info.setDescription("Description1");

        when(infoRepository.findById("1")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("1");

        // THEN
        assertThat(response.getId()).isEqualTo("1");
        assertThat(response.getTitle()).isEqualTo("Title1");
        assertThat(response.getDescription()).isEqualTo("Description1");
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void givenInvalidId_whenGetInfo_thenThrowInternalServerError() {
        // GIVEN
        when(infoRepository.findById("invalid")).thenThrow(new RuntimeException());

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.getInfo("invalid"))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).findById("invalid");
    }

    @Test
    void givenValidIdAndRequest_whenUpdateInfo_thenReturnUpdatedInfoResponse() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDescription");

        InfoRequest request = new InfoRequest();
        request.setTitle("NewTitle");
        request.setDescription("NewDescription");

        Info updatedInfo = request.convert("1");

        when(infoRepository.findById("1")).thenReturn(existingInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("1", request);

        // THEN
        assertThat(response.getId()).isEqualTo("1");
        assertThat(response.getTitle()).isEqualTo("NewTitle");
        assertThat(response.getDescription()).isEqualTo("NewDescription");
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).insert(updatedInfo);
    }

    @Test
    void givenInvalidId_whenUpdateInfo_thenThrowBadRequest() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("NewTitle");
        request.setDescription("NewDescription");

        when(infoRepository.findById("invalid")).thenThrow(new RuntimeException());

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.updateInfo("invalid", request))
                .isInstanceOf(BadRequest.class);
        verify(infoRepository, times(1)).findById("invalid");
    }

    @Test
    void givenValidRequest_whenGenerateInfo_thenReturnGeneratedInfoResponse() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Description");

        Info generatedInfo = request.convert("newId");

        when(infoRepository.newId()).thenReturn("newId");
        when(infoRepository.insert(generatedInfo)).thenReturn(generatedInfo);

        // WHEN
        InfoResponse response = infoService.generateInfo(request);

        // THEN
        assertThat(response.getId()).isEqualTo("newId");
        assertThat(response.getTitle()).isEqualTo("Title");
        assertThat(response.getDescription()).isEqualTo("Description");
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(generatedInfo);
    }

    @Test
    void givenConflictOccurs_whenGenerateInfo_thenThrowConflict() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Description");

        Info generatedInfo = request.convert("newId");

        when(infoRepository.newId()).thenReturn("newId");
        when(infoRepository.insert(generatedInfo)).thenThrow(new Conflict());

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(Conflict.class);
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(generatedInfo);
    }

    @Test
    void givenValidId_whenDeleteInfo_thenVerifyDeletion() {
        // GIVEN
        String id = "1";

        doNothing().when(infoRepository).removeById(id);

        // WHEN
        infoService.deleteInfo(id);

        // THEN
        verify(infoRepository, times(1)).removeById(id);
    }

    @Test
    void givenInvalidId_whenDeleteInfo_thenThrowInternalServerError() {
        // GIVEN
        String id = "invalid";

        doThrow(new RuntimeException()).when(infoRepository).removeById(id);

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.deleteInfo(id))
                .isInstanceOf(InternalServerError.class);
        verify(infoRepository, times(1)).removeById(id);
    }
}
