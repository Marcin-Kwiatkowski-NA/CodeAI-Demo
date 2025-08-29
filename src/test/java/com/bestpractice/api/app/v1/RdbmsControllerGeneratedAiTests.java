package com.bestpractice.api.app.v1;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.JustifiedMockitoExtension;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JustifiedMockitoExtension.class)
class RdbmsControllerGeneratedAiTests {

    private InfoServiceImpl infoService;
    private InfoPersistentRepository infoRepository;
    private RdbmsController rdbmsController;

    @BeforeEach
    void setUp() {
        InfoRepositoryMock infoRepositoryMock = Mockito.mock(InfoPersistentRepository.class);
        infoService = new InfoServiceImpl(infoRepositoryMock);
        rdbmsController = new RdbmsController(infoService);
    }

    @Test
    void getInfos_returnsAllInfos() {
        // GIVEN
        List<Info> infoEntities = Collections.singletonList(new Info().setId("1"));
        Mockito.when(infoRepositoryMock.findAll()).thenReturn(infoEntities);

        // WHEN
        List<InfoResponse> result = rdbmsController.getInfos();

        // THEN
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("Title", result.get(0).getTitle());
        assertEquals("Description", result.get(0).getDescription());
    }

    @Test
    void getInfo_returnsSingleInfo() {
        // GIVEN
        Info info = new Info().setId("1");
        Mockito.when(infoRepositoryMock.findById("1")).thenReturn(info);

        // WHEN
        InfoResponse result = rdbmsController.getInfo("1");

        // THEN
        assertEquals("1", result.getId());
        assertEquals("Title", result.getTitle());
        assertEquals("Description", result.getDescription());
    }

    @Test
    void postInfo_returnsCreatedInfoResponse() {
        // GIVEN
        InfoRequest req = new InfoRequest().setTitle("Title").setDescription("Description");
        Info info = req.convert("1");
        Mockito.when(infoRepositoryMock.insert(info)).thenReturn(info);
        Mockito.when(infoRepositoryMock.newId()).thenReturn("2");

        // WHEN
        ResponseEntity<InfoResponse> response = rdbmsController.postInfo(req);

        // THEN
        assertEquals(302, response.getStatusCode());
        String location = response.getLocation().toString();
        assertEquals("/api/v1/infos/2", location);
        assertEquals("2", response.getBody().getId());
        assertEquals("Title", response.getBody().getTitle());
        assertEquals("Description", response.getBody().getDescription());
    }

    @Test
    void putInfo_updatesInfoResponse() {
        // GIVEN
        Info info = new Info().setId("1");
        InfoRequest req = new InfoRequest().setTitle("New Title").setDescription("New Description");
        Mockito.when(infoRepositoryMock.findById("1")).thenReturn(info);
        Mockito.when(infoRepositoryMock.insert(info)).thenReturn(info);

        // WHEN
        InfoResponse result = rdbmsController.putInfo("1", req);

        // THEN
        assertEquals("1", result.getId());
        assertEquals("New Title", result.getTitle());
        assertEquals("New Description", result.getDescription());
    }

    @Test
    void deleteInfo_returnsOkMessage() {
        // GIVEN
        Mockito.when(infoRepositoryMock.removeById("1")).thenReturn();

        // WHEN
        Map<String, String> result = rdbmsController.deletejava
        assertEquals("ok", result.get("message"));
    }
}