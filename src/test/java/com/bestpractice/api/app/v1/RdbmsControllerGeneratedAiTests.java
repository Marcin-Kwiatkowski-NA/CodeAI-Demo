package com.bestpractice.api.app.v1;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class RdbmsControllerGeneratedAiTests {

    @Mock
    private InfoPersistentRepository infoRepository;

    @InjectMocks
    private RdbmsController controller;

    @Captor
    private ArgumentCaptor<Info> infoCaptor;

    @Captor
    private ArgumentCaptor<String> idCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new RdbmsController(new InfoServiceImpl(infoRepository));
    }

    @Test
    void getInfos_shouldReturnAllInfos() {
        // GIVEN
        List<Info> mockInfos = new ArrayList<>();
        mockInfos.add(new Info("id1", "title1", "desc1"));
        mockInfos.add(new Info("id2", "title2", "desc2"));

        when(infoRepository.findAll()).thenReturn(mockInfos);

        // WHEN
        List<InfoResponse> responses = controller.getInfos();

        // THEN
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getTitle()).isEqualTo("title1");
        assertThat(responses.get(1).getTitle()).isEqualTo("title2");
    }

    @Test
    void getInfo_shouldReturnInfoById() {
        // GIVEN
        String id = "id1";
        Info info = new Info(id, "title1", "desc1");
        when(infoRepository.findById(id)).thenReturn(info);

        // WHEN
        InfoResponse response = controller.getInfo(id);

        // THEN
        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getTitle()).isEqualTo("title1");
        assertThat(response.getDescription()).isEqualTo("desc1");
    }

    @Test
    void postInfo_shouldCreateNewInfoAndReturnCreatedResponse() throws URISyntaxException {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("new title");
        request.setDescription("new description");

        String expectedId = "new-id";
        when(infoRepository.newId()).thenReturn(expectedId);
        when(infoRepository.insert(any(Info.class))).thenReturn(new Info(expectedId, "new title", "new description"));

        // WHEN
        ResponseEntity<InfoResponse> response = controller.postInfo(request);

        // THEN
        assertThat(response.getStatusCode().value()).isEqualTo(201);
        assertThat(response.getBody().getTitle()).isEqualTo("new title");
        assertThat(response.getBody().getDescription()).isEqualTo("new description");
        assertThat(response.getHeaders().getLocation().toString()).contains("/api/v1/infos/" + expectedId);
    }

    @Test
    void putInfo_shouldUpdateExistingInfo() {
        // GIVEN
        String id = "id1";
        InfoRequest request = new InfoRequest();
        request.setTitle("updated title");
        request.setDescription("updated description");

        Info existingInfo = new Info(id, "old title", "old description");
        when(infoRepository.findById(id)).thenReturn(existingInfo);
        when(infoRepository.insert(any(Info.class))).thenReturn(new Info(id, "updated title", "updated description"));

        // WHEN
        InfoResponse response = controller.putInfo(id, request);

        // THEN
        assertThat(response.getTitle()).isEqualTo("updated title");
        assertThat(response.getDescription()).isEqualTo("updated description");
    }

    @Test
    void putInfo_shouldThrowBadRequestWhenInfoNotFound() {
        // GIVEN
        String id = "id1";
        InfoRequest request = new InfoRequest();
        request.setTitle("updated title");
        request.setDescription("updated description");

        when(infoRepository.findById(id)).thenThrow(new RuntimeException("Not found"));

        // WHEN & THEN
        assertThatThrownBy(() -> controller.putInfo(id, request))
                .isInstanceOf(BadRequest.class);
    }

    @Test
    void delete    void deleteInfo_shouldRemoveInfoById() {
        // GIVEN
        String id = "id1";

        when(infoRepository.removeById(id)).thenReturn(true);

        // WHEN
        Map<String, String> result = controller.deleteInfo(id);

        // THEN
        assertThat(result).containsKey("message");
        assertThat(result.get("message")).isEqualTo("ok");
    }

    @Test
    void deleteInfo_shouldThrowInternalServerErrorWhenDeletionFails() {
        // GIVEN
        String id = "id1";

        when(infoRepository.removeById(id)).thenThrow(new RuntimeException("Database error"));

        // WHEN & THEN
        assertThatThrownBy(() -> controller.deleteInfo(id))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void postInfo_shouldThrowConflictWhenIdAlreadyExists() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("title");
        request.setDescription("description");

        when(infoRepository.insert(any(Info.class))).thenThrow(new Conflict("Duplicate ID"));

        // WHEN & THEN
        assertThatThrownBy(() -> controller.postInfo(request))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void postInfo_shouldThrowInternalServerErrorWhenInsertFails() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("title");
        request.setDescription("description");

        when(infoRepository.insert(any(You are a helpful assistant.)).thenThrow(new RuntimeException("Unexpected error"));

        // WHEN & THEN
        assertThatThrownBy(() -> controller.postInfo(request))
                .isInstanceOf(InternalServerError.class);
    }
}