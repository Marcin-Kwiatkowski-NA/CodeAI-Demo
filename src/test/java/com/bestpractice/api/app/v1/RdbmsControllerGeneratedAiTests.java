package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import java.util.List;

import com.bestpractice.api.domain.InfoRequest;
import com.bestpractice.api.domain.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;

@ExtendWith(MockitoExtension.class)
class RdbmsControllerTest {

    private RdbmsController controller;
    private InfoServiceImpl infoService;
    private InfoRequest infoRequest;
    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        infoService = Mockito.mock(InfoServiceImpl.class);
        infoRequest = new InfoRequest();
        infoResponse = new InfoResponse("", "", "");
        controller = new RdbmsController(infoService);
    }

    @Test
    void getInfo_whenInfoServiceReturnsInfo() {
        String id = "123";
        Mockito.when(infoService.getInfo(id)).thenReturn(infoResponse);
        InfoResponse actual = controller.getInfo(id);
        assertEquals(infoResponse, actual);
    }

    @Test
    void postInfo_whenInfoServiceCreatesInfo() {
        String id = "456";
        Mockito.when(infoService.createInfo(infoRequest)).thenReturn(infoResponse);
        InfoResponse actual = controller.postInfo(infoRequest);
        assertEquals(infoResponse, actual);
    }

    @Test
    void putInfo_whenInfoServiceUpdatesInfo() {
        String id = "789";
        Mockito.when(infoService.updateInfo(id, infoRequest)).thenReturn(infoResponse);
        InfoResponse actual = controller.putInfo(id, infoRequest);
        assertEquals(infoResponse, actual);
    }
}
