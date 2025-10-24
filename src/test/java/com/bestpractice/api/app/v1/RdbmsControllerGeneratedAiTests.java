package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        List<InfoResponse> mockList = Arrays.asList(
                new InfoResponse("1", "Title1", "Desc1"),
                new InfoResponse("2", "Title2", "Desc2")
        );
        Mockito.when(infoService.getInfos()).thenReturn(mockList);

        // WHEN
        List<InfoResponse> result = controller.getInfos();

        // THEN
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Title1", result.get(0).getTitle());
    }

    @Test
    public void testGetInfosThrowsException() {
        // GIVEN
        Mockito.when(infoService.getInfos()).thenThrow(new RuntimeException("Test Exception"));

        // WHEN & THEN
        assertThrows(RuntimeException.class, () -> controller.getInfos());
    }

    @Test
    public void testGetInfo() {
        // GIVEN
        InfoResponse mockResponse = new InfoResponse("1", "Title1", "Desc1");
        Mockito.when(infoService.getInfo("1")).thenReturn(mockResponse);

        // WHEN
        InfoResponse result = controller.getInfo("1");

        // THEN
        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("Title1", result.getTitle());
    }

    @Test
    public void testGetInfoThrowsException() {
        // GIVEN
        Mockito.when(infoService.getInfo("1")).thenThrow(new RuntimeException("Test Exception"));

        // WHEN & THEN
        assertThrows(RuntimeException.class, () -> controller.getInfo("1"));
    }

    @Test
    public void testPostInfo() throws URISyntaxException {
        // GIVEN
        InfoRequest req = new InfoRequest();
        req.setTitle("Title1");
        req.setDescription("Desc1");
        InfoResponse mockResponse = new InfoResponse("1", "Title1", "Desc1");
        Mockito.when(infoService.generateInfo(req)).thenReturn(mockResponse);

        // WHEN
        ResponseEntity<InfoResponse> result = controller.postInfo(req);

        // THEN
        assertNotNull(result);
        assertEquals(201, result.getStatusCodeValue());
        assertEquals("1", result.getBody().getId());
    }

    @Test
    public void testPostInfoThrowsRuntimeException() {
        // GIVEN
        InfoRequest req = new InfoRequest();
        req.setTitle("Title1");
        req.setDescription("Desc1");
        Mockito.when(infoService.generateInfo(req)).thenThrow(new RuntimeException("Test Exception"));

        // WHEN & THEN
        assertThrows(RuntimeException.class, () -> controller.postInfo(req));
    }

    @Test
    public void testPutInfo() {
        // GIVEN
        InfoRequest req = new InfoRequest();
        req.setTitle("UpdatedTitle");
        req.setDescription("UpdatedDesc");
        InfoResponse mockResponse = new InfoResponse("1", "UpdatedTitle", "UpdatedDesc");
        Mockito.when(infoService.updateInfo("1", req)).thenReturn(mockResponse);

        // WHEN
        InfoResponse result = controller.putInfo("1", req);

        // THEN
        assertNotNull(result);
        assertEquals("UpdatedTitle", result.getTitle());
        assertEquals("UpdatedDesc", result.getDescription());
    }

    @Test
    public void testPutInfoThrowsException() {
        // GIVEN
        InfoRequest req = new InfoRequest();
        req.setTitle("UpdatedTitle");
        req.setDescription("UpdatedDesc");
        Mockito.when(infoService.updateInfo("1", req)).thenThrow(new RuntimeException("Test Exception"));

        // WHEN & THEN
        assertThrows(RuntimeException.class, () -> controller.putInfo("1", req));
    }

    @Test
    public void testDeleteInfo() {
        // GIVEN
        Mockito.doNothing().when(infoService).deleteInfo("1");

        // WHEN
        Map<String, String> result = controller.deleteInfo("1");

        // THEN
        assertNotNull(result);
       assertEquals(Collections.singletonMap("message", "ok"), result);
    }

    @Test
    public void testDeleteInfoThrowsException() {
        // GIVEN
        Mockito.doThrow(new RuntimeException("Test Exception")).when(infoService).deleteInfo("1");

        // WHEN & THEN
        assertThrows(RuntimeException.class, () -> controller.deleteInfo("1"));
    }
}
