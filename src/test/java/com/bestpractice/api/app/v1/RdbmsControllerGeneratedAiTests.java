package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import java.util.List;

@ExtendWith(MyAnnotations.class)
class RdbmsControllerGeneratedAiTests {

    @BeforeEach
    void setUp() {
        InfoServiceImpl infoService = new InfoServiceImpl(new InfoPersistentRepository() {
            @Override
            public List<Info> findAll() {
                List<Info> infoEntities = Collections.singletonList(new Info().setId("1"));
                return infoEntities;
            }

            @Override
            public Info findById(String id) {
                return null;
            }

            @Override
            public Info insert(Info info) {
                return info;
            }

            @Override
            public Info removeById(String id) {
                return null;
            }

            @Override
            public Info newId() {
                return new Info().setId("2");
            }
        });
        RdbmsController controller = new RdbmsController(infoService);
    }

    @Test
    void getInfos() {
        // GIVEN
        List<InfoResponse> result = controller.getInfos();

        // THEN
        assertEquals(1, result.size());
        InfoResponse response = result.get(0);
        assertEquals("1", response.getId());
        assertEquals("Title", response.getTitle());
        assertEquals("Description", response.getDescription());
    }

    @Test
    void getInfo() {
        // GIVEN
        InfoResponse result = controller.getInfo("1");

        // THEN
        assertEquals("1", result.getId());
        assertEquals("Title", result.getTitle());
        assertEquals("Description", result.getDescription());
    }

    @Test
    void postInfo() {
        // GIVEN
        InfoRequest req = new InfoRequest().setTitle("New Title").setDescription("New Description");

        // WHEN
        ResponseEntity<InfoResponse> response = controller.postInfo(req);

        // THEN
        assertEquals(201, response.getStatusCode());
        InfoResponse result = response.getBody();
        assertEquals("2", result.getId());
        assertEquals("New Title", result.getTitle());
        assertEquals("New Description", result.getDescription());
    }

    @Test
    void putInfo() {
        // GIVEN
        InfoRequest req = new InfoRequest().setTitle("Updated Title").setDescription("Updated Description");

        // WHEN
        InfoResponse result = controller.putInfo("1", req);

        // THEN
        assertEquals("1", result.getId());
        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Description", result.getDescription());
    }

    @Test
    void deleteInfo() {
        // GIVEN
        // WHEN
        Map<String, String> result = controller.deleteInfo("1");

        // THEN
        assertEquals("ok", result.get("message"));
    }
}
