package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RdbmsControllerGeneratedAiTests {

    @Mock
    private InfoServiceImpl infoService;

    @InjectMocks
    private RdbmsController controller;

    private InfoRequest infoRequest;
    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        infoRequest = new InfoRequest();
        infoRequest.setTitle("Test Title");
        infoRequest.setDescription("Test Description");
        infoResponse = new InfoResponse("1", "Test Title", "Test Description");
    }

    @Test
    void getInfos_shouldReturnListOfInfoResponses() {
        // GIVEN
        when(infoService.getInfos()).thenReturn(List.of(infoResponse));

        // WHEN
        List<InfoResponse> result = controller.getInfos();

        // THEN
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getId()).isEqualTo("1");
        verify(infoService, times(1)).getInfos();
    }

    @Test
    void getInfo_shouldReturnSingleInfoResponse() {
        // GIVEN
        when(infoService.getInfo("1")).thenReturn(infoResponse);

        // WHEN
        InfoResponse result = controller.getInfo("1");

        // THEN
        assertThat(result.getTitle()).isEqualTo("Test Title");
        verify(infoService, times(1)).getInfo("1");
    }

    @Test
    void postInfo_shouldReturnCreatedResponseEntity() throws URISyntaxException {
        // GIVEN
        when(infoService.generateInfo(infoRequest)).thenReturn(infoResponse);

        // WHEN
        ResponseEntity<InfoResponse> response = controller.postInfo(infoRequest);

        // THEN
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo("1");
        verify(infoService, times(1)).generateInfo(infoRequest);
    }

    @Test
    void putInfo_shouldReturnUpdatedInfoResponse() {
        // GIVEN
        when(infoService.updateInfo("1", infoRequest)).thenReturn(infoResponse);

        // WHEN
        InfoResponse result = controller.putInfo("1", infoRequest);

        // THEN
        assertThat(result.getDescription()).isEqualTo("Test Description");
        verify(infoService, times(1)).updateInfo("1", infoRequest);
    }

    @Test
    void deleteInfo_shouldReturnOkMessage() {
        // GIVEN
        doNothing().when(infoService).deleteInfo("1");

        // WHEN
        Map<String, String> result = controller.deleteInfo("1");

        // THEN
        assertThat(result).containsEntry("message", "ok");
        verify(infoService, times(1)).deleteInfo("1");
    }
}
