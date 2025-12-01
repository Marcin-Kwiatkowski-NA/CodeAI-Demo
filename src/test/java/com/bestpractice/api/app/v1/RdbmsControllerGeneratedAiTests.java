package com.bestpractice.api.app.v1;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;

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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RdbmsControllerGeneratedAiTests {

    @Mock
    private InfoServiceImpl infoService;

    @InjectMocks
    private RdbmsController rdbmsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenInfosExist_whenGetInfos_thenReturnListOfInfoResponses() {
        // GIVEN
        List<InfoResponse> mockResponses = List.of(
                new InfoResponse("1", "Title1", "Description1"),
                new InfoResponse("2", "Title2", "Description2")
        );
        when(infoService.getInfos()).thenReturn(mockResponses);

        // WHEN
        List<InfoResponse> result = rdbmsController.getInfos();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo("1");
        verify(infoService, times(1)).getInfos();
    }

    @Test
    void givenValidId_whenGetInfo_thenReturnInfoResponse() {
        // GIVEN
        String id = "1";
        InfoResponse mockResponse = new InfoResponse(id, "Title1", "Description1");
        when(infoService.getInfo(id)).thenReturn(mockResponse);

        // WHEN
        InfoResponse result = rdbmsController.getInfo(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        verify(infoService, times(1)).getInfo(id);
    }

    @Test
    void givenValidRequest_whenPostInfo_thenReturnCreatedResponseEntity() throws URISyntaxException {
        // GIVEN
        InfoRequest mockRequest = new InfoRequest();
        mockRequest.setTitle("Title1");
        mockRequest.setDescription("Description1");
        InfoResponse mockResponse = new InfoResponse("1", "Title1", "Description1");
        when(infoService.generateInfo(mockRequest)).thenReturn(mockResponse);

        // WHEN
        ResponseEntity<InfoResponse> result = rdbmsController.postInfo(mockRequest);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getStatusCodeValue()).isEqualTo(201);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getId()).isEqualTo("1");
        verify(infoService, times(1)).generateInfo(mockRequest);
    }

    @Test
    void givenValidIdAndRequest_whenPutInfo_thenReturnUpdatedInfoResponse() {
        // GIVEN
        String id = "1";
        InfoRequest mockRequest = new InfoRequest();
        mockRequest.setTitle("UpdatedTitle");
        mockRequest.setDescription("UpdatedDescription");
        InfoResponse mockResponse = new InfoResponse(id, "UpdatedTitle", "UpdatedDescription");
        when(infoService.updateInfo(id, mockRequest)).thenReturn(mockResponse);

        // WHEN
        InfoResponse result = rdbmsController.putInfo(id, mockRequest);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo("UpdatedTitle");
        verify(infoService, times(1)).updateInfo(id, mockRequest);
    }

    @Test
    void givenValidId_whenDeleteInfo_thenReturnConfirmationMessage() {
        // GIVEN
        String id = "1";
        doNothing().when(infoService).deleteInfo(id);

        // WHEN
        Map<String, String> result = rdbmsController.deleteInfo(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).containsEntry("message", "ok");
        verify(infoService, times(1)).deleteInfo(id);
    }
}