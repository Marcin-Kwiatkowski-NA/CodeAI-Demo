package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RdbmsControllerGeneratedAiTests {

    @Mock
    private InfoServiceImpl infoService;

    @InjectMocks
    private RdbmsController controller;

    private InfoResponse sampleResponse;
    private InfoRequest sampleRequest;

    @BeforeEach
    void setUp() {
        controller = new RdbmsController(infoService);
        sampleResponse = new InfoResponse("1", "Sample Title", "Sample Description");
        sampleRequest = new InfoRequest();
        sampleRequest.setTitle("Sample Title");
        sampleRequest.setDescription("Sample Description");
    }

    @Test
    void testGetInfos_ReturnsListOfInfoResponses() {
        // GIVEN
        when(infoService.getInfos()).thenReturn(List.of(sampleResponse));

        // WHEN
        List<InfoResponse> result = controller.getInfos();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("1");
        verify(infoService, times(1)).getInfos();
    }

    @Test
    void testGetInfo_ReturnsSingleInfoResponse() {
        // GIVEN
        when(infoService.getInfo("1")).thenReturn(sampleResponse);

        // WHEN
        InfoResponse result = controller.getInfo("1");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Sample Title");
        verify(infoService, times(1)).getInfo("1");
    }

    @Test
    void testPostInfo_CreatesNewInfo() throws URISyntaxException {
        // GIVEN
        when(infoService.generateInfo(any(InfoRequest.class))).thenReturn(sampleResponse);

        // WHEN
        ResponseEntity<InfoResponse> response = controller.postInfo(sampleRequest);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo("1");
        verify(infoService, times(1)).generateInfo(any(InfoRequest.class));
    }

    @Test
    void testPutInfo_UpdatesExistingInfo() {
        // GIVEN
        when(infoService.updateInfo("1", sampleRequest)).thenReturn(sampleResponse);

        // WHEN
        InfoResponse result = controller.putInfo("1", sampleRequest);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getDescription()).isEqualTo("Sample Description");
        verify(infoService, times(1)).updateInfo("1", sampleRequest);
    }

    @Test
    void testDeleteInfo_RemovesInfoSuccessfully() {
        // GIVEN
        doNothing().when(infoService).deleteInfo("1");

        // WHEN
        Map<String, String> result = controller.deleteInfo("1");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.get("message")).isEqualTo("ok");
        verify(infoService, times(1)).deleteInfo("1");
    }
}
