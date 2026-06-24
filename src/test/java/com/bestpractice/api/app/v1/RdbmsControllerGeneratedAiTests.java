package com.bestpractice.api.app.v1;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RdbmsControllerGeneratedAiTests {

    @Mock
    private InfoServiceImpl infoService;

    @InjectMocks
    private RdbmsController controller;

    @BeforeEach
    void setUp() {
        Mockito.clearInvocations(infoService);
    }

    @Test
    void getInfos_shouldReturnListOfInfoResponses() {
        // GIVEN
        InfoResponse response = new InfoResponse("1", "title", "desc");
        when(infoService.getInfos()).thenReturn(List.of(response));

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
        InfoResponse response = new InfoResponse("1", "title", "desc");
        when(infoService.getInfo("1")).thenReturn(response);

        // WHEN
        InfoResponse result = controller.getInfo("1");

        // THEN
        assertThat(result.getTitle()).isEqualTo("title");
        verify(infoService, times(1)).getInfo("1");
    }

    @Test
    void postInfo_shouldReturnCreatedResponseEntity() throws URISyntaxException {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("title");
        request.setDescription("desc");
        InfoResponse response = new InfoResponse("1", "title", "desc");
        when(infoService.generateInfo(request)).thenReturn(response);

        // WHEN
        ResponseEntity<InfoResponse> result = controller.postInfo(request);

        // THEN
        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getId()).isEqualTo("1");
        verify(infoService, times(1)).generateInfo(request);
    }

    @Test
    void putInfo_shouldReturnUpdatedInfoResponse() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("updated");
        request.setDescription("desc");
        InfoResponse response = new InfoResponse("1", "updated", "desc");
        when(infoService.updateInfo("1", request)).thenReturn(response);

        // WHEN
        InfoResponse result = controller.putInfo("1", request);

        // THEN
        assertThat(result.getTitle()).isEqualTo("updated");
        verify(infoService, times(1)).updateInfo("1", request);
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