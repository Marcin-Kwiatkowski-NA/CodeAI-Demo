package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import java.net.URI;
import java.util.Arrays;
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
        reset(infoService);
    }

    @Test
    void getInfos_returnsAllInfos() {
        // GIVEN
        List<InfoResponse> expected = Arrays.asList(
                new InfoResponse("1", "Title1", "Desc1"),
                new InfoResponse("2", "Title2", "Desc2")
        );
        when(infoService.getInfos()).thenReturn(expected);

        // WHEN
        List<InfoResponse> actual = controller.getInfos();

        // THEN
        assertThat(actual).isEqualTo(expected);
        verify(infoService, times(1)).getInfos();
    }

    @Test
    void getInfo_returnsSpecificInfo() {
        // GIVEN
        String id = "123";
        InfoResponse expected = new InfoResponse(id, "Sample Title", "Sample Description");
        when(infoService.getInfo(id)).thenReturn(expected);

        // WHEN
        InfoResponse actual = controller.getInfo(id);

        // THEN
        assertThat(actual).isEqualTo(expected);
        verify(infoService, times(1)).getInfo(id);
    }

    @Test
    void postInfo_returnsCreatedResponseWithLocationHeader() throws Exception {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("New Title");
        request.setDescription("New Description");
        InfoResponse created = new InfoResponse("456", "New Title", "New Description");
        when(infoService.generateInfo(request)).thenReturn(created);

        // WHEN
        var response = controller.postInfo(request);

        // THEN
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isEqualTo(created);
        assertThat(response.getHeaders().getLocation()).isNotNull();
        assertThat(response.getHeaders().getLocation()).isEqualTo(URI.create("/api/v1/infos/" + created.getId()));
        verify(infoService, times(1)).generateInfo(request);
    }

    @Test
    void putInfo_updatesExistingInfo() {
        // GIVEN
        String id = "789";
        InfoRequest request = new InfoRequest();
        request.setTitle("Updated Title");
        request.setDescription("Updated Description");
        InfoResponse updated = new InfoResponse(id, "Updated Title", "Updated Description");
        when(infoService.updateInfo(id, request)).thenReturn(updated);

        // WHEN
        InfoResponse actual = controller.putInfo(id, request);

        // THEN
        assertThat(actual).isEqualTo(updated);
        verify(infoService, times(1)).updateInfo(id, request);
    }

    @Test
    void deleteInfo_returnsOkMessage() {
        // GIVEN
        String id = "321";
        doNothing().when(infoService).deleteInfo(id);

        // WHEN
        Map<String, String> result = controller.deleteInfo(id);

        // THEN
        assertThat(result).containsExactly(entry("message", "ok"));
        verify(infoService, times(1)).deleteInfo(id);
    }
}
