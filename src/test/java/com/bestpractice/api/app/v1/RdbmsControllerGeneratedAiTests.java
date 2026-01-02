package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class RdbmsControllerGeneratedAiTests {

    @Mock
    private InfoServiceImpl infoService;

    @InjectMocks
    private RdbmsController controller;

    @BeforeEach
    void setUp() {
        // Reset mocks before each test to ensure isolation
        reset(infoService);
    }

    @Test
    void testGetInfosReturnsList() {
        // GIVEN
        List<InfoResponse> mockList = Arrays.asList(
                new InfoResponse("1", "Title1", "Desc1"),
                new InfoResponse("2", "Title2", "Desc2")
        );
        when(infoService.getInfos()).thenReturn(mockList);

        // WHEN
        List<InfoResponse> result = controller.getInfos();

        // THEN
        assertThat(result).isSameAs(mockList);
        verify(infoService, times(1)).getInfos();
    }

    @Test
    void testGetInfoReturnsInfoResponse() {
        // GIVEN
        String id = "123";
        InfoResponse mockResponse = new InfoResponse(id, "Sample Title", "Sample Description");
        when(infoService.getInfo(id)).thenReturn(mockResponse);

        // WHEN
        InfoResponse result = controller.getInfo(id);

        // THEN
        assertThat(result).isSameAs(mockResponse);
        verify(infoService, times(1)).getInfo(id);
    }

    @Test
    void testPostInfoCreatesResourceAndReturnsLocation() throws URISyntaxException {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("New Title");
        request.setDescription("New Description");
        InfoResponse mockResponse = new InfoResponse("42", "New Title", "New Description");
        when(infoService.generateInfo(request)).thenReturn(mockResponse);

        // WHEN
        ResponseEntity<InfoResponse> responseEntity = controller.postInfo(request);

        // THEN
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody()).isSameAs(mockResponse);
        assertThat(responseEntity.getHeaders().getLocation()).isEqualTo(new URI("/api/v1/infos/42"));
        verify(infoService, times(1)).generateInfo(request);
    }

    @Test
    void testPostInfoThrowsUriSyntaxExceptionWhenInvalidId() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");
        InfoResponse mockResponse = new InfoResponse("invalid id", "Title", "Desc");
        when(infoService.generateInfo(request)).thenReturn(mockResponse);

        // WHEN & THEN
        assertThatThrownBy(() -> controller.postInfo(request))
                .isInstanceOf(URISyntaxException.class);
        verify(infoService, times(1)).generateInfo(request);
    }

    @Test
    void testPutInfoUpdatesInfo() {
        // GIVEN
        String id = "77";
        InfoRequest request = new InfoRequest();
        request.setTitle("Updated Title");
        request.setDescription("Updated Desc");
        InfoResponse mockResponse = new InfoResponse(id, "Updated Title", "Updated Desc");
        when(infoService.updateInfo(id, request)).thenReturn(mockResponse);

        // WHEN
        InfoResponse result = controller.putInfo(id, request);

        // THEN
        assertThat(result).isSameAs(mockResponse);
        verify(infoService, times(1)).updateInfo(id, request);
    }

    @Test
    void testDeleteInfoReturnsOkMessage() {
        // GIVEN
        String id = "99";
        doNothing().when(infoService).deleteInfo(id);

        // WHEN
        Map<String, String> result = controller.deleteInfo(id);

        // THEN
        assertThat(result).containsEntry("message", "ok");
        verify(infoService, times(1)).deleteInfo(id);
    }
}
