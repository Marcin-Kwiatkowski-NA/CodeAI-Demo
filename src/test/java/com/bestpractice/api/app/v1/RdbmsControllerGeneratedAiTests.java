package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

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
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RdbmsControllerGeneratedAiTests {

    @Mock
    private InfoServiceImpl infoService;

    @InjectMocks
    private RdbmsController controller;

    @BeforeEach
    void setUp() {
        Mockito.reset(infoService);
    }

    @Test
    void getInfos_returnsListFromService() {
        // GIVEN
        InfoResponse resp1 = new InfoResponse("1", "Title1", "Desc1");
        InfoResponse resp2 = new InfoResponse("2", "Title2", "Desc2");
        List<InfoResponse> expected = Arrays.asList(resp1, resp2);
        Mockito.when(infoService.getInfos()).thenReturn(expected);

        // WHEN
        List<InfoResponse> actual = controller.getInfos();

        // THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getInfo_returnsInfoFromService() {
        // GIVEN
        String id = "123";
        InfoResponse expected = new InfoResponse(id, "Sample Title", "Sample Description");
        Mockito.when(infoService.getInfo(id)).thenReturn(expected);

        // WHEN
        InfoResponse actual = controller.getInfo(id);

        // THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void postInfo_returnsCreatedResponseWithLocationHeader() throws Exception {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("New Title");
        request.setDescription("New Description");
        InfoResponse created = new InfoResponse("42", "New Title", "New Description");
        Mockito.when(infoService.generateInfo(request)).thenReturn(created);

        // WHEN
        ResponseEntity<InfoResponse> response = controller.postInfo(request);

        // THEN
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isEqualTo(created);
        assertThat(response.getHeaders().getLocation()).isEqualTo(new URI("/api/v1/infos/42"));
    }

    @Test
    void putInfo_returnsUpdatedInfo() {
        // GIVEN
        String id = "99";
        InfoRequest request = new InfoRequest();
        request.setTitle("Updated Title");
        request.setDescription("Updated Description");
        InfoResponse updated = new InfoResponse(id, "Updated Title", "Updated Description");
        Mockito.when(infoService.updateInfo(id, request)).thenReturn(updated);

        // WHEN
        InfoResponse actual = controller.putInfo(id, request);

        // THEN
        assertThat(actual).isEqualTo(updated);
    }

    @Test
    void deleteInfo_returnsOkMessage() {
        // GIVEN
        String id = "77";
        Mockito.doNothing().when(infoService).deleteInfo(id);

        // WHEN
        Map<String, String> result = controller.deleteInfo(id);

        // THEN
        assertThat(result).containsExactlyEntriesOf(Collections.singletonMap("message", "ok"));
    }
}
