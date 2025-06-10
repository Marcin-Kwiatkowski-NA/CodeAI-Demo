package com.bestpractice.api.app.v1;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class RdbmsControllerGeneratedAiTests {

    @InjectMocks
    private RdbmsController rdbmsController;

    @Mock
    private InfoServiceImpl infoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetInfos() {
        // GIVEN
        List<InfoResponse> mockInfos = Collections.singletonList(new InfoResponse("1", "Title", "Description"));
        when(infoService.getInfos()).thenReturn(mockInfos);

        // WHEN
        List<InfoResponse> infos = rdbmsController.getInfos();

        // THEN
        assertEquals(1, infos.size());
        assertEquals("Title", infos.get(0).getTitle());
    }

    @Test
    public void testGetInfo() {
        // GIVEN
        InfoResponse mockInfo = new InfoResponse("1", "Title", "Description");
        when(infoService.getInfo("1")).thenReturn(mockInfo);

        // WHEN
        InfoResponse info = rdbmsController.getInfo("1");

        // THEN
        assertEquals("Title", info.getTitle());
    }

    @Test
    public void testPostInfo() throws Exception {
        // GIVEN
        InfoRequest req = new InfoRequest();
        req.setTitle("New Title");
        req.setDescription("New Description");
        InfoResponse mockRes = new InfoResponse("1", "New Title", "New Description");
        when(infoService.generateInfo(req)).thenReturn(mockRes);

        // WHEN
        ResponseEntity<InfoResponse> response = rdbmsController.postInfo(req);

        // THEN
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("/api/v1/infos/1", response.getHeaders().getLocation().toString());
    }

    @Test
    public void testPutInfo() {
        // GIVEN
        InfoRequest req = new InfoRequest();
        req.setTitle("Updated Title");
        req.setDescription("Updated Description");
        InfoResponse mockRes = new InfoResponse("1", "Updated Title", "Updated Description");
        when(infoService.updateInfo("1", req)).thenReturn(mockRes);

        // WHEN
        InfoResponse info = rdbmsController.putInfo("1", req);

        // THEN
        assertEquals("Updated Title", info.getTitle());
    }

    @Test
    public void testDeleteInfo() {
        // GIVEN
        doNothing().when(infoService).deleteInfo("1");

        // WHEN
        Map<String, String> response = rdbmsController.deleteInfo("1");

        // THEN
        assertEquals("ok", response.get("message"));
    }
}
