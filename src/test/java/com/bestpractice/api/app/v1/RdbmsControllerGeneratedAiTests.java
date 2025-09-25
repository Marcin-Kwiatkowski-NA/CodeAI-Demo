package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RdbmsControllerGeneratedAiTests {

    private InfoServiceImpl infoService;
    private RdbmsController controller;

    @BeforeEach
    public void setUp() {
        infoService = Mockito.mock(InfoServiceImpl.class);
        controller = new RdbmsController(infoService);
    }

    @Test
    public void testGetInfos() {
        // GIVEN
        List<InfoResponse> expectedList = Arrays.asList(
                new InfoResponse("1", "title1", "desc1"),
                new InfoResponse("2", "title2", "desc2")
        );
        when(infoService.getInfos()).thenReturn(expectedList);

        // WHEN
        List<InfoResponse> result = controller.getInfos();

        // THEN
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getId());
        verify(infoService, times(1)).getInfos();
    }

    @Test
    public void testGetInfo() {
        // GIVEN
        InfoResponse expected = new InfoResponse("1", "title", "desc");
        when(infoService.getInfo("1")).thenReturn(expected);

        // WHEN
        InfoResponse result = controller.getInfo("1");

        // THEN
        assertNotNull(result);
        assertEquals("1", result.getId());
        verify(infoService, times(1)).getInfo("1");
    }

    @Test
    public void testPostInfo() throws URISyntaxException {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("title");
        request.setDescription("desc");
        InfoResponse response = new InfoResponse("123", "title", "desc");
        when(infoService.generateInfo(request)).thenReturn(response);

        // WHEN
        ResponseEntity<InfoResponse> entity = controller.postInfo(request);

        // THEN
        assertEquals(201, entity.getStatusCodeValue());
        assertEquals("123", entity.getBody().getId());
        verify(infoService, times(1)).generateInfo(request);
    }

    @Test
    public void testPutInfo() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("title");
        request.setDescription("desc");
        InfoResponse updated = new InfoResponse("1", "title", "desc");
        when(infoService.updateInfo("1", request)).thenReturn(updated);

        // WHEN
        InfoResponse result = controller.putInfo("1", request);

        // THEN
        assertNotNull(result);
        assertEquals("1", result.getId());
        verify(infoService, times(1)).updateInfo("1", request);
    }

    @Test
    public void testDeleteInfo() {
        // GIVEN
        doNothing().when(infoService).deleteInfo("1");

        // WHEN
        Map<String, String> result = controller.deleteInfo("1");

        // THEN
        assertEquals(Collections.singletonMap("message", "ok"), result);
        verify(infoService, times(1)).deleteInfo("1");
    }
}
