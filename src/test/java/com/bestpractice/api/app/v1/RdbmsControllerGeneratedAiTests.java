package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
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
import static org.junit.jupiter.api.Assertions.*;

@MyAnnotations
class RdbmsControllerGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN
        InfoServiceImpl infoService = new InfoServiceImpl(new InfoPersistentRepository() {
            @Override
            public String newId() {
                return "testId";
            }

            @Override
            public List<Info> findAll() {
                return Collections.emptyList();
            }

            @Override
            public Info findById(String id) {
                Info info = new Info();
                info.setId("testId");
                info.setTitle("Test Title");
                info.setDescription("Test Description");
                return info;
            }

            @Override
            public Info insert(Info info) {
                return info;
            }

            @Override
            public Info replace(String id, Info info) {
                return info;
            }

            @Override
            public boolean removeById(String id) {
                return true;
            }
        });
        RdbmsController controller = new RdbmsController(infoService);
    }

    @Test
    void testGetInfos() {
        // GIVEN
        // WHEN
        List<InfoResponse> responses = controller.getInfos();

        // THEN
        assertEquals(1, responses.size());
        InfoResponse response = responses.get(0);
        assertEquals("Test Title", response.getTitle());
        assertEquals("Test Description", response.getDescription());
    }

    @Test
    void testGetInfo() {
        // GIVEN
        // WHEN
        InfoResponse response = controller.getInfo("testId");

        // THEN
        assertEquals("Test Title", response.getTitle());
        assertEquals("Test Description", response.getDescription());
    }

    @Test
    void testPostInfo() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("New Title");
        request.setDescription("New Description");

        // WHEN
        InfoResponse response = controller.postInfo(request);

        // THEN
        assertEquals("New Title", response.getTitle());
        assertEquals("New Description", response.getDescription());
    }

    @Test
    void testPutInfo() {
        // GIVEN
        String id = "testId";
        InfoRequest request = new InfoRequest();
        request.setTitle("Updated Title");
        request.setDescription("Updated Description");

        // WHEN
        InfoResponse response = controller.putInfo(id, request);

        // THEN
        assertEquals("Updated Title", response.getTitle());
        assertEquals("Updated Description", response.getDescription());
    }

    @Test
    void testDeleteInfo() {
        // GIVEN
        // WHEN
        Map<String, String> responses = controller.deleteInfo(id);

        // THEN
        assertEquals("ok", responses.get("message"));
    }
}
