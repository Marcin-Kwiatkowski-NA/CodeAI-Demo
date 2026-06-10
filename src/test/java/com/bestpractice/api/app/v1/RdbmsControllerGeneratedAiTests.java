package com.bestpractice.api.app.v1;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

@ExtendWith(MockitoExtension.class)
public class RdbmsControllerGeneratedAiTests {

    @Mock
    private InfoServiceImpl infoService;

    @InjectMocks
    private RdbmsController controller;

    @BeforeEach
    void setUp() {
        Mockito.reset(infoService);
    }

    @Test
    void testGetInfos() {
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
    void testGetInfo() {
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
    void testPostInfo() throws URISyntaxException {
        // GIVEN
        InfoRequest req = new InfoRequest();
        req.setTitle("title");
        req.setDescription("desc");
        InfoResponse res = new InfoResponse("1", "title", "desc");
        when(infoService.generateInfo(any(InfoRequest.class))).thenReturn(res);

        // WHEN
        ResponseEntity<InfoResponse> responseEntity = controller.postInfo(req);

        // THEN
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody().getId()).isEqualTo("1");
        verify(infoService, times(1)).generateInfo(any(InfoRequest.class));
    }

    @Test
    void testPutInfo() {
        // GIVEN
        InfoRequest req = new InfoRequest();
        req.setTitle("updated");
        req.setDescription("updated desc");
        InfoResponse res = new InfoResponse("1", "updated", "updated desc");
        when(infoService.updateInfo(eq("1"), any(InfoRequest.class))).thenReturn(res);

        // WHEN
        InfoResponse result = controller.putInfo("1", req);

        // THEN
        assertThat(result.getTitle()).isEqualTo("updated");
        verify(infoService, times(1)).updateInfo(eq("1"), any(InfoRequest.class));
    }

    @Test
    void testDeleteInfo() {
        // GIVEN
        doNothing().when(infoService).deleteInfo("1");

        // WHEN
        Map<String, String> result = controller.deleteInfo("1");

        // THEN
        assertThat(result).containsEntry("message", "ok");
        verify(infoService, times(1)).deleteInfo("1");
    }
}