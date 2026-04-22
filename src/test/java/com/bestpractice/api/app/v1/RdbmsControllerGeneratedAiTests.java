package com.bestpractice.api.app.v1;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.doAnswer;
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
    private RdbmsController rdbmsController;

    @BeforeEach
    void setUp() {
        Mockito.reset(infoService);
    }

    @Test
    void getInfos_shouldReturnListOfInfoResponses() {
        // GIVEN
        InfoResponse response = new InfoResponse("1", "Title", "Description");
        when(infoService.getInfos()).thenReturn(Collections.singletonList(response));

        // WHEN
        List<InfoResponse> result = rdbmsController.getInfos();

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("1");
        verify(infoService, times(1)).getInfos();
    }

    @Test
    void getInfo_shouldReturnSingleInfoResponse() {
        // GIVEN
        String id = "123";
        InfoResponse response = new InfoResponse(id, "Title", "Description");
        when(infoService.getInfo(id)).thenReturn(response);

        // WHEN
        InfoResponse result = rdbmsController.getInfo(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        verify(infoService, times(1)).getInfo(id);
    }

    @Test
    void postInfo_shouldReturnCreatedResponseEntity() throws URISyntaxException {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Description");
        InfoResponse response = new InfoResponse("1", "Title", "Description");
        when(infoService.generateInfo(any(InfoRequest.class))).thenReturn(response);

        // WHEN
        ResponseEntity<InfoResponse> result = rdbmsController.postInfo(request);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getStatusCodeValue()).isEqualTo(201);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getId()).isEqualTo("1");
        verify(infoService, times(1)).generateInfo(any(InfoRequest.class));
    }

    @Test
    void putInfo_shouldReturnUpdatedInfoResponse() {
        // GIVEN
        String id = "1";
        InfoRequest request = new InfoRequest();
        request.setTitle("Updated Title");
        request.setDescription("Updated Description");
        InfoResponse response = new InfoResponse(id, "Updated Title", "Updated Description");
        when(infoService.updateInfo(eq(id), any(InfoRequest.class))).thenReturn(response);

        // WHEN
        InfoResponse result = rdbmsController.putInfo(id, request);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Updated Title");
        verify(infoService, times(1)).updateInfo(eq(id), any(InfoRequest.class));
    }

    @Test
    void deleteInfo_shouldReturnOkMessage() {
        // GIVEN
        String id = "1";
        doAnswer(invocation -> null).when(infoService).deleteInfo(id);

        // WHEN
        Map<String, String> result = rdbmsController.deleteInfo(id);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.get("message")).isEqualTo("ok");
        verify(infoService, times(1)).deleteInfo(id);
    }
}