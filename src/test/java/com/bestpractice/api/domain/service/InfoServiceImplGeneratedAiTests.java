package com.bestpractice.api.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

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
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenInfoEntities_whenGetInfos_thenReturnInfoResponses() {
        // GIVEN
        List<Info> infoEntities = new ArrayList<>();
        infoEntities.add(new Info());
        infoEntities.add(new Info());
        Mockito.when(infoRepository.findAll()).thenReturn(infoEntities);

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertThat(responses).hasSize(2);
        Mockito.verify(infoRepository, times(1)).findAll();
    }

    @Test
    void givenRepositoryThrowsException_whenGetInfos_thenThrowInternalServerError() {
        // GIVEN
        Mockito.when(infoRepository.findAll()).thenThrow(new RuntimeException());

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.getInfos())
                .isInstanceOf(InternalServerError.class);
        Mockito.verify(infoRepository, times(1)).findAll();
    }

    @Test
    void givenValidId_whenGetInfo_thenReturnInfoResponse() {
        // GIVEN
        Info info = new Info();
        Mockito.when(infoRepository.findById("1")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("1");

        // THEN
        assertThat(response).isNotNull();
        Mockito.verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void givenRepositoryThrowsException_whenGetInfo_thenThrowInternalServerError() {
        // GIVEN
        Mockito.when(infoRepository.findById("1")).thenThrow(new RuntimeException());

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.getInfo("1"))
                .isInstanceOf(InternalServerError.class);
        Mockito.verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void givenValidIdAndRequest_whenUpdateInfo_thenReturnUpdatedInfoResponse() {
        // GIVEN
        Info existingInfo = new Info();
        Info updatedInfo = new Info();
        InfoRequest request = mock(InfoRequest.class);
        Mockito.when(infoRepository.findById("1")).thenReturn(existingInfo);
        Mockito.when(request.convert()).thenReturn(updatedInfo);
        Mockito.when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("1", request);

        // THEN
        assertThat(response).isNotNull();
        Mockito.verify(infoRepository, times(1)).findById("1");
        Mockito.verify(infoRepository, times(1)).insert(updatedInfo);
    }

    @Test
    void givenInvalidId_whenUpdateInfo_thenThrowBadRequest() {
        // GIVEN
        Mockito.when(infoRepository.findById("1")).thenThrow(new RuntimeException());

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.updateInfo("1", mock(InfoRequest.class)))
                .isInstanceOf(BadRequest.class);
        Mockito.verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void givenRequest_whenGenerateInfo_thenReturnGeneratedInfoResponse() {
        // GIVEN
        Info generatedInfo = new Info();
        InfoRequest request = mock(InfoRequest.class);
        Mockito.when(request.convert()).thenReturn(generatedInfo);
        Mockito.when(infoRepository.insert(generatedInfo)).thenReturn(generatedInfo);

        // WHEN
        InfoResponse response = infoService.generateInfo(request);

        // THEN
        assertThat(response).isNotNull();
        Mockito.verify(infoRepository, times(1)).insert(generatedInfo);
    }

    @Test
    void givenConflictOccurs_whenGenerateInfo_thenThrowConflict() {
        // GIVEN
        InfoRequest request = mock(InfoRequest.class);
        Mockito.when(request.convert()).thenThrow(new Conflict());

        // WHEN & THEN
       assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(Conflict.class);
        Mockito.verify(request, times(1)).convert();
    }

    @Test
    void givenRepositoryThrowsException_whenGenerateInfo_thenThrowInternalServerError() {
        // GIVEN
        InfoRequest request = mock(InfoRequest.class);
        Mockito.when(request.convert()).thenReturn(new Info());
        Mockito.when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException());

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.generateInfo(request))
                .isInstanceOf(InternalServerError.class);
        Mockito.verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void givenValidId_whenDeleteInfo_thenRemoveInfo() {
        // GIVEN
        Mockito.doNothing().when(infoRepository).removeById("1");

        // WHEN
        infoService.deleteInfo("1");

        // THEN
        Mockito.verify(infoRepository, times(1)).removeById("1");
    }

    @Test
    void givenRepositoryThrowsException_whenDeleteInfo_thenThrowInternalServerError() {
        // GIVEN
        Mockito.doThrow(new RuntimeException()).when(infoRepository).removeById("1");

        // WHEN & THEN
        assertThatThrownBy(() -> infoService.deleteInfo("1"))
                .isInstanceOf(InternalServerError.class);
        Mockito.verify(infoRepository, times(1)).removeById("1");
    }
}
