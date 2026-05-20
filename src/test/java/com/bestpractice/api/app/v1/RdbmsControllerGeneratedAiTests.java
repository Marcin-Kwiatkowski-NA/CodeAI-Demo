package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

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
class RdbmsControllerGeneratedAiTests {

    @Mock
    private InfoServiceImpl infoService;

    @InjectMocks
    private RdbmsController controller = new RdbmsController(null);

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
        String id = "1";
        when(infoService.getInfo(id)).thenReturn(infoResponse);

        // WHEN
        InfoResponse result = controller.getInfo(id);

        // THEN
        assertThat(result.getTitle()).isEqualTo("Test Title");
        verify(infoService, times(1)).getInfo(id);
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
        String id = "1";
        when(infoService.updateInfo(id, infoRequest)).thenReturn(infoResponse);

        // WHEN
        InfoResponse result = controller.putInfo(id, infoRequest);

        // THEN
        assertThat(result.getDescription()).isEqualTo("Test Description");
        verify(infoService, times(1)).updateInfo(id, infoRequest);
    }

    @Test
    void deleteInfo_shouldReturnOkMessage() {
        // GIVEN
        String id = "1";
        doNothing().when(infoService).deleteInfo(id);

        // WHEN
        Map<String, String> result = controller.deleteInfo(id);

        // THEN
        assertThat(result).containsEntry("message", "ok");
        verify(infoService, times(1)).deleteInfo(id);
    }
}
