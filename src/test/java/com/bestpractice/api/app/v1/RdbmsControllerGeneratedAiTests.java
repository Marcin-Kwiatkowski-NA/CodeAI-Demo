package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RdbmsControllerGeneratedAiTests {

    @Mock
    private InfoServiceImpl infoService;

    private RdbmsController controller;

    @BeforeEach
    void setUp() {
        reset(infoService);
        controller = new RdbmsController(infoService);
    }

    @Test
    void testGetInfosReturnsList() {
        // GIVEN
        InfoResponse response = new InfoResponse("1", "Title", "Description");
        when(infoService.getInfos()).thenReturn(List.of(response));

        // WHEN
        List<InfoResponse> result = controller.getInfos();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("1");
    }

    @Test
    void testGetInfoReturnsSingleInfo() {
        // GIVEN
        InfoResponse response = new InfoResponse("123", "Sample Title", "Sample Description");
        when(infoService.getInfo("123")).thenReturn(response);

        // WHEN
        InfoResponse result = controller.getInfo("123");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("123");
        assertThat(result.getTitle()).isEqualTo("Sample Title");
    }

    @Test
    void testPostInfoCreatesNewInfo() throws URISyntaxException {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("New Title");
        request.setDescription("New Description");
        InfoResponse response = new InfoResponse("999", "New Title", "New Description");
        when(infoService.generateInfo(any(InfoRequest.class))).thenReturn(response);

        // WHEN
        ResponseEntity<InfoResponse> result = controller.postInfo(request);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getId()).isEqualTo("999");
        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(result.getHeaders().getLocation().toString()).contains("/api/v1/infos/999");
    }

    @Test
    void testPutInfoUpdatesExistingInfo() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Updated Title");
        request.setDescription("Updated Description");
        InfoResponse response = new InfoResponse("321", "Updated Title", "Updated Description");
        when(infoService.updateInfo(eq("321"), any(InfoRequest.class))).thenReturn(response);

        // WHEN
        InfoResponse result = controller.putInfo("321", request);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("321");
        assertThat(result.getTitle()).isEqualTo("Updated Title");
    }

    @Test
    void testDeleteInfoReturnsOkMessage() {
        // GIVEN
        doNothing().when(infoService).deleteInfo("555");

        // WHEN
        Map<String, String> result = controller.deleteInfo("555");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).containsKey("message");
        assertThat(result.get("message")).isEqualTo("ok");
    }
}
