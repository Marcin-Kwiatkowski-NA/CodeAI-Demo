package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(InfoServiceGeneratedAiTests.class)
class InfoServiceImplGeneratedAiTests {

    private InfoServiceImpl infoService;
    private InfoPersistentRepository infoRepository;

    @BeforeEach
    void setUp() {
        infoRepository = new InfoPersistentRepository() {
            @Override
            public Info findAll() {
                return new Info();
            }

            @Override
            public Info findById(String id) {
                return new Info();
            }

            @Override
            public Info insert(Info info) {
                return info;
            }

            @Override
            public void removeById(String id) {
            }

            @Override
            public Info newId() {
                return new Info();
            }
        };
        infoService = new InfoServiceImpl(infoRepository);
    }

    @Test
    void getInfos() {
        // GIVEN: An empty list of Info entities
        // WHEN: The getInfos method is called
        // THEN: A list of InfoResponse objects is returned, each representing an empty Info entity
        List<InfoResponse> result = infoService.getInfos();
        assertEquals(0, result.size());
    }

    @Test
    void getInfo() {
        // GIVEN: An Info entity with some data
        Info info = new Info();
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        InfoResponse result = infoService.getInfo("testId");
        assertEquals("testId", result.getId());
        assertEquals("Test Title", result.getTitle());
        assertEquals("Test Description", result.getDescription());
    }

    @Test
    void updateInfo() {
        // GIVEN: An Info entity with some data
        Info info = new Info();
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        // WHEN: The updateInfo method is called with the ID and InfoRequest
        // THEN: The Info entity is updated with the data from the InfoRequest, and an InfoResponse object is returned
        InfoRequest req = new InfoRequest();
        req.setTitle("Updated Title");
        req.setDescription("Updated Description");
        InfoRequest convertedInfoRequest = req.convert("testId");
        Info updatedInfo = convertedInfoRequest.convert("testId");
        InfoResponse result = infoService.updateInfo("testId", req);
        assertEquals("testId", result.getId());
        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Description", result.getDescription());
    }

    @Test
    void generateInfo() {
        // GIVEN: An InfoRequest object
        InfoRequest req = new InfoRequest();
        req.setTitle("Test Title");
        req.setDescription("Test Description");
        // WHEN: The generateInfo method is called with the InfoRequest
        // THEN: An Info entity is created with the data from the InfoRequest, and an InfoResponse object is returned
        InfoResponse result = infoService.generateInfo(req);
        assertEquals("testId", result.getId());
        assertEquals("Test Title", result.getTitle());
        assertEquals("Test Description", result.getDescription());
    }

    @Test
    void deleteInfo() {
        // GIVEN: An Info entity with some data
        Info info = new Info();
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        // WHEN: The deleteInfo method is called with the ID
        // THEN: The Info entity is removed from the repository
        infoService.deleteInfo("testId");
    }
}
