package com.bestpractice.api.app.v1;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;

import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class RdbmsControllerGeneratedAiTests {

    @Mock
    private InfoServiceImpl infoService;

    @InjectMocks
    private RdbmsController rdbmsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetInfos() {
        // GIVEN
        List<InfoResponse> mockResponses = List.of(new InfoResponse("1", "Title1", "Description1"));
        when(infoService.getInfos()).thenReturn(mockResponses);

        // WHEN
        List<InfoResponse> result = rdbmsController.getInfos();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getTitle()).isEqualTo("Title1");
        assertThat(result.get(0).getDescription()).isEqualTo("Description1");
        verify(infoService, times(1)).getInfos();
    }

    @Test
    void testGetInfo() {
        // GIVEN
        String id = "1";
        InfoResponse mockResponse = new InfoResponse(id, "Title1", "Description1");
        when(infoService.getInfo(id)).thenReturn(mockResponse);

        // WHEN
        InfoResponse result = rdbmsController.getInfo(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo("Title1");
        assertThat(result.getDescription()).isEqualTo("Description1");
        verify(infoService, times(1)).getInfo(id);
    }

    @Test
    void testPostInfo() throws URISyntaxException {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title1");
        request.setDescription("Description1");
        InfoResponse mockResponse = new InfoResponse("1", "Title1", "Description1");
        when(infoService.generateInfo(request)).thenReturn(mockResponse);

        // WHEN
        ResponseEntity<InfoResponse> result = rdbmsController.postInfo(request);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getStatusCodeValue()).isEqualTo(201);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getId()).isEqualTo("1");
        assertThat(result.getBody().getTitle()).isEqualTo("Title1");
        assertThat(result.getBody().getDescription()).isEqualTo("Description1");
        verify(infoService, times(1)).generateInfo(request);
    }

    @Test
    void testPutInfo() {
        // GIVEN
        String id = "1";
        InfoRequest request = new InfoRequest();
        request.setTitle("Updated Title");
        request.setDescription("Updated Description");
        InfoResponse mockResponse = new InfoResponse(id, "Updated Title", "Updated Description");
        when(infoService.updateInfo(id, request)).thenReturn(mockResponse);

        // WHEN
        InfoResponse result = rdbmsController.putInfo(id, request);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo("Updated Title");
        assertThat(result.getDescription()).isEqualTo("Updated Description");
        verify(infoService, times(1)).updateInfo(id, request);
    }

    @Test
    void testDeleteInfo() {
        // GIVEN
        String id = "1";
        doNothing().when(infoService).deleteInfo(id);

        // WHEN
        Map<String, String> result = rdbmsController.deleteInfo(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result        assertThat(result).containsEntry("message", "ok");
        verify(infoService, times(1)).deleteInfo(id);
    }
}