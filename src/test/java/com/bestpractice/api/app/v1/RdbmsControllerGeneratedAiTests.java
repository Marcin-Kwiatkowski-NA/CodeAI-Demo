package com.bestpractice.api.app.v1;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class RdbmsControllerGeneratedAiTests {

    @Mock
    private InfoServiceImpl infoService;

    @InjectMocks
    private RdbmsController controller;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getInfos_shouldReturnListOfInfoResponses() {
        // GIVEN
        InfoResponse response = new InfoResponse("1", "Title", "Description");
        Mockito.when(infoService.getInfos()).thenReturn(List.of(response));

        // WHEN
        List<InfoResponse> result = controller.getInfos();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("1");
        Mockito.verify(infoService).getInfos();
    }

    @Test
    void getInfo_shouldReturnSingleInfoResponse() {
        // GIVEN
        InfoResponse response = new InfoResponse("123", "Sample Title", "Sample Description");
        Mockito.when(infoService.getInfo("123")).thenReturn(response);

        // WHEN
        InfoResponse result = controller.getInfo("123");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("123");
        assertThat(result.getTitle()).isEqualTo("Sample Title");
        Mockito.verify(infoService).getInfo("123");
    }

    @Test
    void postInfo_shouldReturnCreatedResponseEntity() throws URISyntaxException {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("New Title");
        request.setDescription("New Description");
        InfoResponse response = new InfoResponse("999", "New Title", "New Description");
        Mockito.when(infoService.generateInfo(any(InfoRequest.class))).thenReturn(response);

        // WHEN
        ResponseEntity<InfoResponse> result = controller.postInfo(request);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getId()).isEqualTo("999");
        Mockito.verify(infoService).generateInfo(any(InfoRequest.class));
    }

    @Test
    void putInfo_shouldReturnUpdatedInfoResponse() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Updated Title");
        request.setDescription("Updated Description");
        InfoResponse response = new InfoResponse("456", "Updated Title", "Updated Description");
        Mockito.when(infoService.updateInfo(eq("456"), any(InfoRequest.class))).thenReturn(response);

        // WHEN
        InfoResponse result = controller.putInfo("456", request);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("456");
        assertThat(result.getTitle()).isEqualTo("Updated Title");
        Mockito.verify(infoService).updateInfo(eq("456"), any(InfoRequest.class));
    }

    @Test
    void deleteInfo_shouldReturnOkMessage() {
        // GIVEN
        Mockito.doNothing().when(infoService).deleteInfo("789");

        // WHEN
        Map<String, String> result = controller.deleteInfo("789");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).containsEntry("message", "ok");
        Mockito.verify(infoService).deleteInfo("789");
    }
}