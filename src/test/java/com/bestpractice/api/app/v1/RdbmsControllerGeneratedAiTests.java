package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
                new InfoResponse("1", "Title1", "Desc1"),
                new InfoResponse("2", "Title2", "Desc2")
        );
        when(infoService.getInfos()).thenReturn(expectedList);

        // WHEN
        List<InfoResponse> result = controller.getInfos();

        // THEN
        assertEquals(expectedList.size(), result.size());
        assertEquals(expectedList.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void testGetInfo() {
        // GIVEN
        InfoResponse expected = new InfoResponse("1", "Title1", "Desc1");
        when(infoService.getInfo("1")).thenReturn(expected);

        // WHEN
        InfoResponse result = controller.getInfo("1");

        // THEN
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getTitle(), result.getTitle());
    }

    @Test
    public void testPostInfo() throws URISyntaxException {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title1");
        request.setDescription("Desc1");
        InfoResponse expected = new InfoResponse("1", "Title1", "Desc1");
        when(infoService.generateInfo(request)).thenReturn(expected);

        // WHEN
        ResponseEntity<InfoResponse> responseEntity = controller.postInfo(request);

        // THEN
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(expected.getId(), responseEntity.getBody().getId());
    }

    @Test
    public void testPutInfo() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("UpdatedTitle");
        request.setDescription("UpdatedDesc");
        InfoResponse expected = new InfoResponse("1", "UpdatedTitle", "UpdatedDesc");
        when(infoService.updateInfo("1", request)).thenReturn(expected);

        // WHEN
        InfoResponse result = controller.putInfo("1", request);

        // THEN
        assertEquals(expected.getTitle(), result.getTitle());
        assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void testDeleteInfo() {
        // GIVEN
        doNothing().when(infoService).deleteInfo("1");

        // WHEN
        Map<String, String> result = controller.deleteInfo("1");

        // THEN
        assertEquals(Collections.singletonMap("message", "ok"), result);
    }
}
