package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class RdbmsControllerGeneratedAiTests {

    @Mock
    private InfoServiceImpl infoService;

    @InjectMocks
    private RdbmsController controller;

    @BeforeEach
    void setUp() {
        Mockito.reset(infoService);
    }

    @Test
    void getInfos_shouldReturnListFromService() {
        // GIVEN
        InfoResponse expectedResponse = new InfoResponse("1", "Title", "Description");
        List<InfoResponse> serviceResult = List.of(expectedResponse);
        when(infoService.getInfos()).thenReturn(serviceResult);

        // WHEN
        List<InfoResponse> result = controller.getInfos();

        // THEN
        assertThat(result).isEqualTo(serviceResult);
        verify(infoService, times(1)).getInfos();
    }

    @Test
    void getInfo_shouldReturnInfoFromService() {
        // GIVEN
        String id = "42";
        InfoResponse expectedResponse = new InfoResponse(id, "Title", "Description");
        when(infoService.getInfo(id)).thenReturn(expectedResponse);

        // WHEN
        InfoResponse result = controller.getInfo(id);

        // THEN
        assertThat(result).isEqualTo(expectedResponse);
        verify(infoService, times(1)).getInfo(id);
    }

    @Test
    void postInfo_shouldReturnCreatedResponseWithLocationHeader() throws URISyntaxException {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("New Title");
        request.setDescription("New Description");
        InfoResponse serviceResponse = new InfoResponse("99", "New Title", "New Description");
        when(infoService.generateInfo(request)).thenReturn(serviceResponse);

        // WHEN
        ResponseEntity<InfoResponse> responseEntity = controller.postInfo(request);

        // THEN
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody()).isEqualTo(serviceResponse);
        assertThat(responseEntity.getHeaders().getLocation()).isEqualTo(new URI("/" + serviceResponse.getId()));
        verify(infoService, times(1)).generateInfo(request);
    }

    @Test
    void putInfo_shouldReturnUpdatedInfoFromService() {
        // GIVEN
        String id = "77";
        InfoRequest request = new InfoRequest();
        request.setTitle("Updated Title");
        request.setDescription("Updated Description");
        InfoResponse serviceResponse = new InfoResponse(id, "Updated Title", "Updated Description");
        when(infoService.putInfo(id, request)).thenReturn(serviceResponse);

        // WHEN
        InfoResponse result = controller.putInfo(id, request);

        // THEN
        assertThat(result).isEqualTo(serviceResponse);
        verify(infoService, times(1)).putInfo(id, request);
    }

    @Test
    void deleteInfo_shouldReturnOkMessageAndInvokeService() {
        // GIVEN
        String id = "77";
        doNothing().when(infoService).deleteInfo(id);

        // WHEN
        Map<String, String> result = controller.deleteInfo(id);

        // THEN
        assertThat(result).containsEntry("message", "ok");
        verify(infoService, times(1)).deleteInfo(id);
    }
}
