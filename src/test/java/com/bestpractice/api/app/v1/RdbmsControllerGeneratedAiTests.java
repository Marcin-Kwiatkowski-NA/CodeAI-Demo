package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

public class RdbmsControllerGeneratedAiTests {

    @Mock
    private InfoServiceImpl infoService;

    @InjectMocks
    private RdbmsController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInfos_shouldReturnListOfInfoResponses() {
        // GIVEN
        InfoResponse response1 = new InfoResponse("1", "Title1", "Desc1");
        InfoResponse response2 = new InfoResponse("2", "Title2", "Desc2");
        when(infoService.getInfos()).thenReturn(List.of(response1, response2));

        // WHEN
        List<InfoResponse> result = controller.getInfos();

        // THEN
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getTitle()).isEqualTo("Title1");
        verify(infoService).getInfos();
    }

    @Test
    void getInfo_shouldReturnSingleInfoResponse() {
        // GIVEN
        String id = "123";
        InfoResponse expectedResponse = new InfoResponse(id, "Title", "Description");
        when(infoService.getInfo(id)).thenReturn(expectedResponse);

        // WHEN
        InfoResponse result = controller.getInfo(id);

        // THEN
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo("Title");
        verify(infoService).getInfo(id);
    }

    @Test
    void postInfo_shouldReturnCreatedResponseEntity() throws URISyntaxException {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("New Title");
        request.setDescription("New Description");
        InfoResponse response = new InfoResponse("999", "New Title", "New Description");
        when(infoService.generateInfo(request)).thenReturn(response);

        // WHEN
        ResponseEntity<InfoResponse> result = controller.postInfo(request);

        // THEN
        assertThat(result.getStatusCodeValue()).isEqualTo(201);
        assertThat(result.getBody().getId()).isEqualTo("999");
        assertThat(result.getHeaders().getLocation()).isEqualTo(new URI("/api/v1/infos/999"));
        verify(infoService).generateInfo(request);
    }

    @Test
    void putInfo_shouldReturnUpdatedInfoResponse() {
        // GIVEN
        String id = "abc";
        InfoRequest request = new InfoRequest();
        request.setTitle("Updated Title");
        request.setDescription("Updated Description");
        InfoResponse updatedResponse = new InfoResponse(id, "Updated Title", "Updated Description");
        when(infoService.updateInfo(id, request)).thenReturn(updatedResponse);

        // WHEN
        InfoResponse result = controller.putInfo(id, request);

        // THEN
        assertThat(result.getTitle()).isEqualTo("Updated Title");
        assertThat(result.getDescription()).isEqualTo("Updated Description");
        verify(infoService).updateInfo(id, request);
    }

    @Test
    void deleteInfo_shouldReturnOkMessage() {
        // GIVEN
        String id = "del123";
        Mockito.doNothing().when(infoService).deleteInfo(id);

        // WHEN
        Map<String, String> result = controller.deleteInfo(id);

        // THEN
        assertThat(result).isEqualTo(Collections.singletonMap("message", "ok"));
        verify(infoService).deleteInfo(id);
    }
}
